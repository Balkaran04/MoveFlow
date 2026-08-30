package it.polimi.moveflow.repository;

import it.polimi.moveflow.model.Materiale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MaterialeRepository extends JpaRepository<Materiale,Long>  {

    @Query("""
        SELECT COALESCE(SUM(m.quantita),0)
         FROM Materiale m 
         WHERE m.ubicazione IS NOT NULL""")
    Long sommaQuantita();
}
