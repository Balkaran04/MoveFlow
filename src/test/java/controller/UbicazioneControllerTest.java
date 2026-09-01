package controller;

import it.polimi.moveflow.controller.MaterialeController;
import it.polimi.moveflow.controller.UbicazioneController;
import it.polimi.moveflow.model.Ubicazione;
import it.polimi.moveflow.service.MaterialeService;
import it.polimi.moveflow.service.UbicazioneService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UbicazioneControllerTest {

    @Mock
    private MaterialeService materialeService;

    @Mock
    private UbicazioneService ubicazioneService;
    @Mock
    private Model model;

    @InjectMocks
    private UbicazioneController ubicazioneController;

    @Test
    void stampaUbicazioni() {
        Ubicazione u1 = new Ubicazione();
        Ubicazione u2 = new Ubicazione();

        List<Ubicazione> lista = List.of(u1, u2);

        when(ubicazioneService.trovaTutte())
                .thenReturn(lista);

        String pagina = ubicazioneController.stampaUbicazioni(model);

        assertEquals("ubicazioni", pagina);
        verify(model).addAttribute("ubicazioni", lista);
    }


    @Test
    void inserimentoUbicazione() {

        String pagina =
                ubicazioneController.inserimentoUbicazione(model);

        assertEquals("ubicazioni-form-ins", pagina);

        verify(model).addAttribute(
                eq("ubicazioni"),
                any(Ubicazione.class)
        );
    }


    @Test
    void inserisciUbicazione() {
        Ubicazione ubicazione = new Ubicazione();

        String pagina =
                ubicazioneController.inserisciUbicazione(ubicazione);

        assertEquals("redirect:/ubicazioni", pagina);

        verify(ubicazioneService).salvaUbicazione(ubicazione);
    }


    @Test
    void eliminaUbicazione() {

        String pagina =
                ubicazioneController.eliminaUbicazione(1L);

        assertEquals("redirect:/ubicazioni", pagina);

        verify(ubicazioneService).eliminaPerId(1L);
    }


    @Test
    void modificaUbicazioneTrovata() {
        Ubicazione ubicazione = new Ubicazione();

        when(ubicazioneService.trovaPerId(1L))
                .thenReturn(Optional.of(ubicazione));

        String pagina =
                ubicazioneController.modificaUbicazione(model, 1L);

        assertEquals("modifica-ubicazione", pagina);
        verify(model).addAttribute("ubicazione", ubicazione);
    }


    @Test
    void modificaUbicazioneNonTrovata() {

        when(ubicazioneService.trovaPerId(1L))
                .thenReturn(Optional.empty());

        String pagina =
                ubicazioneController.modificaUbicazione(model, 1L);

        assertEquals("redirect:/ubicazioni", pagina);
    }


    @Test
    void salvaModificaUbicazione() {
        Ubicazione ubicazione = new Ubicazione();

        String pagina =
                ubicazioneController.modificaUbicazione(ubicazione, 1L);

        assertEquals("redirect:/ubicazioni", pagina);

        verify(ubicazioneService)
                .modificaUbicazione(1L, ubicazione);
    }


    @Test
    void bloccaUbicazione() {

        String pagina =
                ubicazioneController.bloccaUbicazione(1L);

        assertEquals("redirect:/ubicazioni", pagina);

        verify(ubicazioneService).bloccaUbicazione(1L);
    }
}
