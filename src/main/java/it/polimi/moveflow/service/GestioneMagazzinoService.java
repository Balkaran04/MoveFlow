package it.polimi.moveflow.service;

import it.polimi.moveflow.model.*;
import it.polimi.moveflow.repository.MaterialeRepository;
import it.polimi.moveflow.repository.MovimentoRepository;
import it.polimi.moveflow.repository.UbicazioneRepository;
import it.polimi.moveflow.strategy.StrategiaAltaRotazione;
import it.polimi.moveflow.strategy.StrategiaBassaRotazione;
import it.polimi.moveflow.strategy.StrategiaMediaRotazione;
import it.polimi.moveflow.strategy.StrategiaRotazione;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class GestioneMagazzinoService {

    private final UbicazioneRepository ubicazioneRepository;
    private final MaterialeRepository materialeRepository;
    private final MovimentoRepository movimentoRepository;

    public GestioneMagazzinoService(MaterialeRepository materialeRepository, UbicazioneRepository ubicazioneRepository, MovimentoRepository movimentoRepository) {
        this.materialeRepository = materialeRepository;
        this.ubicazioneRepository = ubicazioneRepository;
        this.movimentoRepository = movimentoRepository;
    }
    @Transactional
    public void assegnaMateriale(Long materialeId, Long ubicazioneId)
    {
        Optional<Materiale> m ;
        m =materialeRepository.findById(materialeId);
        
        Optional<Ubicazione> u;
        u = ubicazioneRepository.findById(ubicazioneId);
        
        if(m.isEmpty()){
          throw new IllegalArgumentException("Materiale non trovato!");
        }
        if(u.isEmpty()){
            throw new IllegalArgumentException("Ubicazione non trovata!");
        }

        Materiale m1 = m.get();
        Ubicazione u1 = u.get();

        if(u1.getStato() != StatoUbicazione.LIBERA){
            throw new IllegalArgumentException("Ubicazione non libera!");
        }

        if(m1.getUbicazione() != null){
            throw new IllegalArgumentException("Materiale già ubicato in campata " + m1.getUbicazione().getCampata());
        }

        if(m1.getPeso() > u1.getPesoMassimo()){
            throw new IllegalArgumentException("Materiale supera il peso massimo per Ubicazione");
        }
        if(m1.getAltezza() > u1.getAltezzaMassima()){
            throw new IllegalArgumentException("Materiale supera l'altezza massima per Ubicazione");
        }
        if(m1.getLarghezza() > u1.getLarghezzaMassima()){
            throw new IllegalArgumentException("Materiale supera la larghezza massima per Ubicazione");
        }
        if(m1.getProfondita() > u1.getProfonditaMassima()){
            throw new IllegalArgumentException("Materiale supera la profondita massima per Ubicazione");
        }

        // se supero tutti i controlli assegno il materiale effettivamente
        m1.setUbicazione(u1);
        u1.setStato(StatoUbicazione.OCCUPATA);
        registraMovimento(m1,TipoMovimento.CARICO,null, u1.getCodice(),null);
        materialeRepository.save(m1);
        ubicazioneRepository.save(u1);

    }
    @Transactional
    public void assegnaAutomaticamente(Long materialeiD){
        double volumeUbicazione;
        double volumeMateriale;
        double sprecoMinimo;
        double spreco;
        Ubicazione ubicazioneMigliore = null;

        Optional<Materiale> m = materialeRepository.findById(materialeiD);

        if(m.isEmpty()){
            throw new IllegalArgumentException("Non esiste il materiale");
        }
        Materiale m1 = m.get();

        if(m1.getUbicazione() != null){
            throw new IllegalArgumentException("Il materiale è gia ubicato!");
        }

        List<Ubicazione> listaUbicazione ;
        //mi carico la lista con ubicazioni libere
        listaUbicazione = ubicazioneRepository.findByStato(StatoUbicazione.LIBERA);

        Iterator<Ubicazione> iterator;
        iterator = listaUbicazione.iterator();
        while(iterator.hasNext()){
            Ubicazione ubicazione = iterator.next();
            if(m1.getPeso() > ubicazione.getPesoMassimo() ||
               m1.getProfondita() > ubicazione.getProfonditaMassima() ||
               m1.getAltezza() > ubicazione.getAltezzaMassima() ||
               m1.getLarghezza() > ubicazione.getLarghezzaMassima())
            {
                iterator.remove();
            }


        }
        if(listaUbicazione.isEmpty()){
            throw new IllegalArgumentException("Nessuna ubicazione compatibile con il materiale");
        }
        sprecoMinimo = Double.MAX_VALUE;
        volumeMateriale = m1.getAltezza() * m1.getLarghezza() * m1.getProfondita();
        // ragionamento per scelta migliore del ubicazione
        Iterator<Ubicazione> iterator1;
        iterator1 = listaUbicazione.iterator();
        StrategiaRotazione strategia = null;

        ClasseRotazione cl;
        cl = m1.getClasseRotazione();
        switch (cl){
            case ALTA:
                strategia = new StrategiaAltaRotazione();
                break;
            case MEDIA:
                strategia = new StrategiaMediaRotazione();
                break;
            case BASSA:
                strategia = new StrategiaBassaRotazione();
                break;
        }

        while(iterator1.hasNext()){
            Ubicazione ubicazione1 = iterator1.next();
            volumeUbicazione = ubicazione1.getAltezzaMassima() * ubicazione1.getLarghezzaMassima() * ubicazione1.getProfonditaMassima();

            spreco = volumeUbicazione - volumeMateriale;

            if(spreco < sprecoMinimo)
            {
                sprecoMinimo = spreco;
                ubicazioneMigliore = ubicazione1;
                
            } else if (Double.compare(spreco,sprecoMinimo) == 0 ){

                if(strategia.preferisci(ubicazione1,ubicazioneMigliore) == true){
                    ubicazioneMigliore = ubicazione1;
                }
            }


        }

        m1.setUbicazione(ubicazioneMigliore);
        ubicazioneMigliore.setStato(StatoUbicazione.OCCUPATA);
        registraMovimento(m1,TipoMovimento.CARICO,null, ubicazioneMigliore.getCodice(),null);

        materialeRepository.save(m1);
        ubicazioneRepository.save(ubicazioneMigliore);


    }

    @Transactional
    public void spostaMateriale (Long materialeId, Long nuovaUbic){

        Optional<Materiale> m = materialeRepository.findById(materialeId);

        if(m.isEmpty()){
            throw new IllegalArgumentException("Non esiste il materiale");
        }
        Materiale m1 = m.get();

        if(m1.getUbicazione() == null){
            throw new IllegalArgumentException("Il materiale non è ubicato! Non posso spostarlo");
        }

        Optional<Ubicazione> u = ubicazioneRepository.findById(nuovaUbic);

        if(u.isEmpty()){
            throw new IllegalArgumentException("Non esiste Ubicazione!");
        }

        Ubicazione u1 = u.get();

        if(u1.getStato() != StatoUbicazione.LIBERA){
            throw new IllegalArgumentException("Ubicazione di destinazione non è libera!");
        }

        if(m1.getPeso() > u1.getPesoMassimo() || m1.getLarghezza() > u1.getLarghezzaMassima() ||
           m1.getProfondita() > u1.getProfonditaMassima() || m1.getAltezza() > u1.getAltezzaMassima()
        )
        {
            throw new IllegalArgumentException("Ubicazione non compatibile per il materiale!");
        }

        Ubicazione vecchiaU = m1.getUbicazione();

        vecchiaU.setStato(StatoUbicazione.LIBERA);
        m1.setUbicazione(u1);
        u1.setStato(StatoUbicazione.OCCUPATA);
        registraMovimento(m1,TipoMovimento.SPOSTAMENTO,vecchiaU.getCodice(), u1.getCodice(),null);

        materialeRepository.save(m1);
        ubicazioneRepository.save(vecchiaU);
        ubicazioneRepository.save(u1);
    }

    @Transactional
    public void liberaMateriale(Long materialeId){
        Optional<Materiale> m = materialeRepository.findById(materialeId);

        if(m.isEmpty()){
            throw new IllegalArgumentException("Il materiale non esiste");

        }
        Materiale m1 = m.get();

        Ubicazione u = m1.getUbicazione();
        if(u == null){
            throw new IllegalArgumentException("Il materiale non è ubicato!");

        }

        m1.setUbicazione(null);
        u.setStato(StatoUbicazione.LIBERA);
        registraMovimento(m1,TipoMovimento.SCARICO,u.getCodice(), null,null);

        materialeRepository.save(m1);
        ubicazioneRepository.save(u);
    }

    public void registraMovimento(Materiale materiale,TipoMovimento tipoMovimento, String ubicazioneOrig, String ubicazioneDest,Utente utente){
        Movimento m  = new Movimento(materiale,tipoMovimento,ubicazioneOrig,ubicazioneDest, LocalDateTime.now(), utente);
        movimentoRepository.save(m);
    }
}
