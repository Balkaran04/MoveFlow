package it.polimi.moveflow.repository;

import it.polimi.moveflow.model.Materiale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialeRepository extends JpaRepository<Materiale,Long>  {
    Long id(Long id);
}
