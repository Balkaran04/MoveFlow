package exception;

import it.polimi.moveflow.expection.MaterialeNonTrovatoException;
import it.polimi.moveflow.expection.OperazioneMagNonTrovata;
import it.polimi.moveflow.expection.UbicazioneNonTrovataException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionTest {

    @Test
    void materialeNonTrovato() {
        MaterialeNonTrovatoException e =
                new MaterialeNonTrovatoException("Materiale non trovato");

        assertEquals("Materiale non trovato", e.getMessage());
    }

    @Test
    void ubicazioneNonTrovata() {
        UbicazioneNonTrovataException e =
                new UbicazioneNonTrovataException("Ubicazione non trovata");

        assertEquals("Ubicazione non trovata", e.getMessage());
    }

    @Test
    void operazioneMagazzino() {
        OperazioneMagNonTrovata e =
                new OperazioneMagNonTrovata("Operazione non consentita");

        assertEquals("Operazione non consentita", e.getMessage());
    }
}
