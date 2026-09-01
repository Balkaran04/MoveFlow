package controller;

import it.polimi.moveflow.controller.EtichettaController;
import it.polimi.moveflow.service.EtichettaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EtichettaControllerTest {

    @Mock
    private EtichettaService etichettaService;

    @Mock
    private Model model;

    @InjectMocks
    private EtichettaController controller;

    @Test
    void generaEtichetta() {

        when(etichettaService.generaEtichetta(1L))
                .thenReturn("Etichetta prova");

        String pagina = controller.generaEtichetta(model, 1L);

        assertEquals("etichetta", pagina);
        verify(model).addAttribute("etichetta", "Etichetta prova");
    }

    @Test
    void generaEtichettaPDF() throws Exception {

        byte[] pdf = {1, 2, 3};

        when(etichettaService.generaPdfEtichetta(1L))
                .thenReturn(pdf);

        ResponseEntity<byte[]> risposta =
                controller.generaEtichettaPDF(1L);

        assertEquals(200, risposta.getStatusCode().value());
        assertEquals(MediaType.APPLICATION_PDF,
                risposta.getHeaders().getContentType());

        assertArrayEquals(pdf, risposta.getBody());
    }
}
