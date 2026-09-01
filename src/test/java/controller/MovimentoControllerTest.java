package controller;

import it.polimi.moveflow.controller.MovimentoController;
import it.polimi.moveflow.model.Movimento;
import it.polimi.moveflow.service.MovimentoService;
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
public class MovimentoControllerTest {

    @Mock
    private MovimentoService movimentoService;

    @Mock
    private Model model;

    @InjectMocks
    private MovimentoController movimentoController;

    @Test
    void listaMovimenti() {

        List<Movimento> movimenti =
                List.of(new Movimento(), new Movimento());

        when(movimentoService.trovaTutti())
                .thenReturn(movimenti);

        String pagina =
                movimentoController.listaMovimenti(model);

        assertEquals("movimenti", pagina);

        verify(model)
                .addAttribute("movimenti", movimenti);
    }
}
