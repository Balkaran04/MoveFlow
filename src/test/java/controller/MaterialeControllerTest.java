package controller;

import it.polimi.moveflow.controller.MaterialeController;
import it.polimi.moveflow.model.Materiale;
import it.polimi.moveflow.service.MaterialeService;
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
public class MaterialeControllerTest {

    @Mock
    private MaterialeService materialeService;
    @Mock
    private Model model;

    @InjectMocks
    private MaterialeController materialeController;

    @Test
    void stampaMateriali() {
        Materiale m1 = new Materiale();
        Materiale m2 = new Materiale();

        List<Materiale> lista = List.of(m1, m2);

        when(materialeService.trovaTutti())
                .thenReturn(lista);

        String pagina = materialeController.stampaMateriali(model);

        assertEquals("materiali", pagina);
        verify(model).addAttribute("materiali", lista);
    }


    @Test
    void inserimentoMateriale() {

        String pagina = materialeController.inserimentoMateriale(model);

        assertEquals("materiale-form-ins", pagina);

        verify(model).addAttribute(
                eq("materiale"),
                any(Materiale.class)
        );
    }


    @Test
    void inserisciMateriale() {
        Materiale materiale = new Materiale();

        String pagina =
                materialeController.inserisciMateriale(materiale);

        assertEquals("redirect:/materiali", pagina);

        verify(materialeService).salvaMateriale(materiale);
    }


    @Test
    void eliminaMateriale() {

        String pagina =
                materialeController.eliminaMateriale(1L);

        assertEquals("redirect:/materiali", pagina);

        verify(materialeService).eliminaPerId(1L);
    }


    @Test
    void modificaMaterialeTrovato() {
        Materiale materiale = new Materiale();

        when(materialeService.trovaPerId(1L))
                .thenReturn(Optional.of(materiale));

        String pagina =
                materialeController.modificaMateriale(model, 1L);

        assertEquals("modifica-materiale", pagina);

        verify(model).addAttribute("materiale", materiale);
    }


    @Test
    void modificaMaterialeNonTrovato() {

        when(materialeService.trovaPerId(1L))
                .thenReturn(Optional.empty());

        String pagina =
                materialeController.modificaMateriale(model, 1L);

        assertEquals("redirect:/materiali", pagina);
    }


    @Test
    void salvaModificaMateriale() {
        Materiale materiale = new Materiale();

        String pagina =
                materialeController.modificaMateriale(materiale, 1L);

        assertEquals("redirect:/materiali", pagina);

        verify(materialeService)
                .modificaMateriale(1L, materiale);
    }

}
