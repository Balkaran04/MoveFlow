package it.polimi.moveflow.service;

import it.polimi.moveflow.expection.MaterialeNonTrovatoException;
import it.polimi.moveflow.model.Materiale;
import it.polimi.moveflow.repository.MaterialeRepository;
import org.springframework.stereotype.Service;

import java.util.IllformedLocaleException;
import java.util.List;
import java.util.Optional;

/*
* Funzioni Service per la gestione del materiale
* semplici funzioni per trovare,modificare, eliminare e salvare*/
@Service
public class MaterialeService {

    private final MaterialeRepository materialeRepository;
    public MaterialeService(MaterialeRepository materialeRepository) {
        this.materialeRepository = materialeRepository;
        
    }

    public List<Materiale> trovaTutti(){
        return materialeRepository.findAll();
    }

    public Materiale salvaMateriale(Materiale m)
    {
        return materialeRepository.save(m);
    }

    public Optional<Materiale> trovaPerId(Long id){
        return materialeRepository.findById(id);
    }

    public void eliminaPerId(Long id){
        Optional<Materiale> m1 = materialeRepository.findById(id);

        if(m1.isEmpty()){
            throw new MaterialeNonTrovatoException("Materiale non esite");
        }
        Materiale m = m1.get();
        if(m.getUbicazione() != null){
            throw new IllegalArgumentException("Materiale ubicato! Liberare prima!");
        }
        materialeRepository.deleteById(id);

    }

    public void modificaMateriale(Long id, Materiale m ){
       Optional<Materiale> m1 =  materialeRepository.findById(id);
       if(m1.isPresent())
       {
           Materiale m2 = m1.get();
           m2.setAltezza(m.getAltezza());
           m2.setClasseRotazione(m.getClasseRotazione());
           m2.setCodice(m.getCodice());
           m2.setDescrizione(m.getDescrizione());
           m2.setLarghezza(m.getLarghezza());
           m2.setPeso(m.getPeso());
           m2.setProfondita(m.getProfondita());
           m2.setQuantita(m.getQuantita());
           salvaMateriale(m2);
       }

    }
}
