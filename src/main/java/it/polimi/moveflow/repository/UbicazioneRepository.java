package it.polimi.moveflow.repository;

import it.polimi.moveflow.model.Materiale;
import it.polimi.moveflow.model.StatoUbicazione;
import it.polimi.moveflow.model.Ubicazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UbicazioneRepository extends JpaRepository<Ubicazione,Long> {

    List<Ubicazione> findByStato(StatoUbicazione stato);
}
 