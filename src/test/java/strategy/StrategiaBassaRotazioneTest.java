package strategy;

import it.polimi.moveflow.model.Ubicazione;
import it.polimi.moveflow.strategy.StrategiaBassaRotazione;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
    public class StrategiaBassaRotazioneTest {

        @Test
        void preferisci(){
            StrategiaBassaRotazione strategia = new StrategiaBassaRotazione();

            Ubicazione attuale = new Ubicazione();
            attuale.setCampata(2);
            attuale.setLivello(2);
            attuale.setPosizione(2);

            Ubicazione candidata = new Ubicazione();

            candidata.setCampata(5);
            candidata.setLivello(2);
            candidata.setPosizione(2);

            assertTrue(strategia.preferisci(candidata, attuale));

            candidata.setCampata(2);
            candidata.setLivello(5);

            assertTrue(strategia.preferisci(candidata, attuale));

            candidata.setLivello(2);
            candidata.setPosizione(5);

            assertTrue(strategia.preferisci(candidata, attuale));

            candidata.setPosizione(1);

            assertFalse(strategia.preferisci(candidata, attuale));
        }
    }


