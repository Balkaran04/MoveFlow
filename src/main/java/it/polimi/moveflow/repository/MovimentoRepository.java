package it.polimi.moveflow.repository;

import it.polimi.moveflow.model.Movimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentoRepository extends JpaRepository<Movimento,Long> {
}
