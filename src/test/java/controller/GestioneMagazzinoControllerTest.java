package controller;

import it.polimi.moveflow.controller.GestioneMagazzinoController;
import it.polimi.moveflow.model.Materiale;
import it.polimi.moveflow.model.Ubicazione;
import it.polimi.moveflow.service.GestioneMagazzinoService;
import it.polimi.moveflow.service.MaterialeService;
import it.polimi.moveflow.service.UbicazioneService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GestioneMagazzinoControllerTest {

    @Mock
    private GestioneMagazzinoService gestioneMagazzinoService;
    @Mock
    private MaterialeService materialeService;
    @Mock
    private UbicazioneService ubicazioneService;
    @Mock
    private Model model;

    @InjectMocks
    private GestioneMagazzinoController controller;


    @Test
    void paginaAssegnazioneAutomatica() {
        Materiale m = new Materiale();
        List<Materiale> lista = List.of(m);

        when(materialeService.trovaTutti())
                .thenReturn(lista);

        String pagina = controller.assegnaAutoMateriale(model);

        assertEquals("magazzino-assegnazione", pagina);
        verify(model).addAttribute("materiali", lista);
    }


    @Test
    void assegnaAutomaticamente() {

        String pagina = controller.assegnaAutoMateriale(1L);

        assertEquals("redirect:/materiali", pagina);

        verify(gestioneMagazzinoService)
                .assegnaAutomaticamente(1L);
    }


    @Test
    void paginaAssegnazioneManuale() {
        List<Materiale> materiali = List.of(new Materiale());
        List<Ubicazione> ubicazioni = List.of(new Ubicazione());

        when(materialeService.trovaTutti())
                .thenReturn(materiali);

        when(ubicazioneService.trovaTutte())
                .thenReturn(ubicazioni);

        String pagina = controller.assegnaMateriale(model);

        assertEquals("magazzino-assegnazione", pagina);

        verify(model).addAttribute("materiali", materiali);
        verify(model).addAttribute("ubicazioni", ubicazioni);
    }


    @Test
    void assegnaMateriale() {

        String pagina = controller.assegnaMateriale(1L, 2L);

        assertEquals("redirect:/materiali", pagina);

        verify(gestioneMagazzinoService)
                .assegnaMateriale(1L, 2L);
    }


    @Test
    void paginaSpostamento() {
        List<Materiale> materiali = List.of(new Materiale());
        List<Ubicazione> ubicazioni = List.of(new Ubicazione());

        when(materialeService.trovaTutti())
                .thenReturn(materiali);

        when(ubicazioneService.trovaTutte())
                .thenReturn(ubicazioni);

        String pagina = controller.spostaMateriale(model);

        assertEquals("magazzino-spostamento", pagina);

        verify(model).addAttribute("materiali", materiali);
        verify(model).addAttribute("ubicazioni", ubicazioni);
    }


    @Test
    void spostaMateriale() {

        String pagina = controller.spostaMateriale(1L, 2L);

        assertEquals("redirect:/materiali", pagina);

        verify(gestioneMagazzinoService)
                .spostaMateriale(1L, 2L);
    }


    @Test
    void liberaMateriale() {

        String pagina = controller.liberaMateriale(1L);

        assertEquals("redirect:/materiali", pagina);

        verify(gestioneMagazzinoService)
                .liberaMateriale(1L);
    }
}
