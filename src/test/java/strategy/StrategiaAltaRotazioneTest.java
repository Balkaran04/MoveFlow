package strategy;

import it.polimi.moveflow.model.Ubicazione;
import it.polimi.moveflow.strategy.StrategiaAltaRotazione;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class StrategiaAltaRotazioneTest {

    @Test
    void preferisceUbicazioneConCampataPiuBassa(){
        Ubicazione candidata = new Ubicazione();
        candidata.setCampata(1);

        Ubicazione attuale = new Ubicazione();
        attuale.setCampata(5);
        StrategiaAltaRotazione strategia = new StrategiaAltaRotazione();

        boolean ris = strategia.preferisci(candidata,attuale);

        assertTrue(ris);
    }

    @Test
    void preferisci() {

        StrategiaAltaRotazione strategia =
                new StrategiaAltaRotazione();

        Ubicazione attuale = new Ubicazione();
        attuale.setCampata(2);
        attuale.setLivello(2);
        attuale.setPosizione(2);

        Ubicazione candidata = new Ubicazione();

        // campata più bassa
        candidata.setCampata(1);
        candidata.setLivello(2);
        candidata.setPosizione(2);

        assertTrue(strategia.preferisci(candidata, attuale));

        // stessa campata, livello più basso
        candidata.setCampata(2);
        candidata.setLivello(1);

        assertTrue(strategia.preferisci(candidata, attuale));

        // stessa campata e livello, posizione più bassa
        candidata.setLivello(2);
        candidata.setPosizione(1);

        assertTrue(strategia.preferisci(candidata, attuale));

        // nessuna posizione migliore
        candidata.setPosizione(5);

        assertFalse(strategia.preferisci(candidata, attuale));
    }
}
