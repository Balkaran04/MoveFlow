package it.polimi.moveflow.service;

import it.polimi.moveflow.model.StatoUbicazione;
import it.polimi.moveflow.repository.MaterialeRepository;
import it.polimi.moveflow.repository.UbicazioneRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final UbicazioneRepository ubicazioneRepository ;
    private final MaterialeRepository materialeRepository;

    public DashboardService(UbicazioneRepository ubicazioneRepository, MaterialeRepository materialeRepository){
        this.ubicazioneRepository = ubicazioneRepository;
        this.materialeRepository = materialeRepository;
    }

    public long getUbicazioniLibere(){
        return ubicazioneRepository.countByStato(StatoUbicazione.LIBERA);
    }
    public long getUbicazioniOccupate(){
        return ubicazioneRepository.countByStato(StatoUbicazione.OCCUPATA);
    }
    public long getUbicazioniBloccate(){
        return ubicazioneRepository.countByStato(StatoUbicazione.BLOCCATA);
    }

    public long getUbicazioniTotali(){
        return ubicazioneRepository.count();
    }

    public double getPercentualeOccupato(){
      long ubicOccupate = ubicazioneRepository.countByStato(StatoUbicazione.OCCUPATA);
      long ubicLibere   =ubicazioneRepository.countByStato(StatoUbicazione.LIBERA);

      long disponibili = ubicLibere + ubicOccupate;

      if(disponibili == 0 ){
          return 0;
      }
      return ((double) ubicOccupate / disponibili)*100;

    }
    
    public long getQuantita(){
        return materialeRepository.sommaQuantita();
    }

}
