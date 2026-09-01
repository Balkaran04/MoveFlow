package strategy;

import it.polimi.moveflow.model.Ubicazione;
import it.polimi.moveflow.strategy.StrategiaBassaRotazione;
import it.polimi.moveflow.strategy.StrategiaMediaRotazione;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class StrategiaMediaRotazioneTest {

    @Test
    void preferisci(){
        StrategiaMediaRotazione strategia = new StrategiaMediaRotazione();

        Ubicazione attuale = new Ubicazione();
        Ubicazione candidata = new Ubicazione();



        assertFalse(strategia.preferisci(candidata, attuale));


    }
}


