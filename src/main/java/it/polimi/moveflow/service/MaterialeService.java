package it.polimi.moveflow.service;

import it.polimi.moveflow.model.Materiale;
import it.polimi.moveflow.repository.MaterialeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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
           salvaMateriale(m2);
       }

    }
}
