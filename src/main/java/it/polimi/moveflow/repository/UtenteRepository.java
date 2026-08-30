package it.polimi.moveflow.repository;

import it.polimi.moveflow.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente,Long> {
    Optional<Utente> findByUsername(String username);


}
