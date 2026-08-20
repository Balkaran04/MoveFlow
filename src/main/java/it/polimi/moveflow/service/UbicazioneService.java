package it.polimi.moveflow.service;

import it.polimi.moveflow.model.StatoUbicazione;
import it.polimi.moveflow.model.Ubicazione;
import it.polimi.moveflow.repository.UbicazioneRepository;
import org.springframework.stereotype.Service;

import javax.swing.plaf.metal.MetalBorders;
import java.util.List;
import java.util.Optional;


@Service
public class UbicazioneService {

    private final UbicazioneRepository ubicazioneRepository;
    public UbicazioneService(UbicazioneRepository ubicazioneRepository) {
        this.ubicazioneRepository = ubicazioneRepository;

    }

    public List<Ubicazione> trovaTutte(){
        return ubicazioneRepository.findAll();
    }

    public Optional<Ubicazione> trovaPerId(Long id){
        return ubicazioneRepository.findById(id);
    }

    public Ubicazione salvaUbicazione(Ubicazione u){
        return ubicazioneRepository.save(u);
    }

    public void modificaUbicazione(Long id, Ubicazione u) {
        Optional<Ubicazione> u1 = ubicazioneRepository.findById(id);
        if (u1.isPresent()) {
            Ubicazione u2 = u1.get();
            u2.setAltezzaMassima(u.getAltezzaMassima());
            u2.setCampata(u.getCampata());
            u2.setCodice(u.getCodice());
            u2.setLarghezzaMassima(u.getLarghezzaMassima());
            u2.setLivello(u.getLivello());
            u2.setPesoMassimo(u.getPesoMassimo());
            u2.setProfonditaMassima(u.getProfonditaMassima());
            u2.setStato(u.getStato());
            u2.setPosizione(u.getPosizione());
            ubicazioneRepository.save(u2);

        }

    }

    public void eliminaPerId(Long id){
        ubicazioneRepository.deleteById(id);
    }

    public void bloccaUbicazione (Long id)
    {
        Optional<Ubicazione> u1 = ubicazioneRepository.findById(id);
        if(u1.isPresent()) {
            Ubicazione u2 = u1.get();
            u2.setStato(StatoUbicazione.BLOCCATA);
            ubicazioneRepository.save(u2);
        }

    }




}
