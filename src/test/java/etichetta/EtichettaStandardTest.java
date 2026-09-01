package etichetta;

import it.polimi.moveflow.etichetta.EtichettaStandard;
import it.polimi.moveflow.model.ClasseRotazione;
import it.polimi.moveflow.model.Materiale;
import it.polimi.moveflow.model.StatoUbicazione;
import it.polimi.moveflow.model.Ubicazione;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EtichettaStandardTest {

    @Test
    void generaEtichettaConUbi(){

        Materiale m = new Materiale();
        m.setCodice("MAT02");
        m.setDescrizione("Pallet");
        m.setQuantita(10);
        m.setClasseRotazione(ClasseRotazione.ALTA);

        Ubicazione u = new Ubicazione();
        u.setStato(StatoUbicazione.OCCUPATA);

        m.setUbicazione(u);

        EtichettaStandard etichetta = new EtichettaStandard(m);

        String testo = etichetta.generaTesto();

        assertTrue(testo.contains(u.toString()));

    }

    @Test
    void generaEtichettaSensaUbi(){
        Materiale m = new Materiale();
        m.setCodice("MAT01");
        m.setDescrizione("Scatola");
        m.setQuantita(10);
        m.setClasseRotazione(ClasseRotazione.ALTA);

        EtichettaStandard etichetta = new EtichettaStandard(m);

        String testo = etichetta.generaTesto();

        assertTrue(testo.contains("MAT01"));
        assertTrue(testo.contains("Scatola"));
        assertTrue(testo.contains("Non assegnata"));
        assertTrue(testo.contains("ALTA"));
    }


}
