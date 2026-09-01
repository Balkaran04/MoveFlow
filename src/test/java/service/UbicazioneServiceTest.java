package service;

import it.polimi.moveflow.model.StatoUbicazione;
import it.polimi.moveflow.model.Ubicazione;
import it.polimi.moveflow.repository.UbicazioneRepository;
import it.polimi.moveflow.service.UbicazioneService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UbicazioneServiceTest {

    @Mock
    private UbicazioneRepository ubicazioneRepository;

    @InjectMocks
    private UbicazioneService ubicazioneService;


    @Test
    void trovaTutte() {
        Ubicazione u1 = new Ubicazione();
        Ubicazione u2 = new Ubicazione();

        when(ubicazioneRepository.findAll())
                .thenReturn(List.of(u1, u2));

        List<Ubicazione> risultato = ubicazioneService.trovaTutte();

        assertEquals(2, risultato.size());
    }


    @Test
    void trovaPerId() {
        Ubicazione u = new Ubicazione();

        when(ubicazioneRepository.findById(1L))
                .thenReturn(Optional.of(u));

        Optional<Ubicazione> risultato = ubicazioneService.trovaPerId(1L);

        assertEquals(u, risultato.get());
    }


    @Test
    void salvaUbicazione() {
        Ubicazione u = new Ubicazione();

        when(ubicazioneRepository.save(u))
                .thenReturn(u);

        Ubicazione risultato = ubicazioneService.salvaUbicazione(u);

        assertEquals(u, risultato);
    }


    @Test
    void modificaUbicazione() {
        Ubicazione vecchia = new Ubicazione();

        Ubicazione nuova = new Ubicazione();
        nuova.setCodice("U10");
        nuova.setCampata(2);
        nuova.setLivello(3);
        nuova.setPosizione(4);
        nuova.setPesoMassimo(500);
        nuova.setAltezzaMassima(200);
        nuova.setLarghezzaMassima(150);
        nuova.setProfonditaMassima(180);
        nuova.setStato(StatoUbicazione.LIBERA);

        when(ubicazioneRepository.findById(1L))
                .thenReturn(Optional.of(vecchia));

        ubicazioneService.modificaUbicazione(1L, nuova);

        assertEquals("U10", vecchia.getCodice());
        assertEquals(2, vecchia.getCampata());
        assertEquals(StatoUbicazione.LIBERA, vecchia.getStato());

        verify(ubicazioneRepository).save(vecchia);
    }


    @Test
    void eliminaPerId() {
        ubicazioneService.eliminaPerId(1L);

        verify(ubicazioneRepository).deleteById(1L);
    }


    @Test
    void bloccaUbicazione() {
        Ubicazione u = new Ubicazione();
        u.setStato(StatoUbicazione.LIBERA);

        when(ubicazioneRepository.findById(1L))
                .thenReturn(Optional.of(u));

        ubicazioneService.bloccaUbicazione(1L);

        assertEquals(StatoUbicazione.BLOCCATA, u.getStato());
    }


    @Test
    void trovaPerStato() {
        Ubicazione u1 = new Ubicazione();
        Ubicazione u2 = new Ubicazione();

        when(ubicazioneRepository.findByStato(StatoUbicazione.LIBERA))
                .thenReturn(List.of(u1, u2));

        List<Ubicazione> risultato =
                ubicazioneService.trovaPerStato(StatoUbicazione.LIBERA);

        assertEquals(2, risultato.size());
    }
}