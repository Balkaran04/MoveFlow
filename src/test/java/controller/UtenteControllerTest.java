package controller;

import it.polimi.moveflow.controller.UtenteController;
import it.polimi.moveflow.model.Ruolo;
import it.polimi.moveflow.model.Utente;
import it.polimi.moveflow.service.UtenteService;
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
public class UtenteControllerTest {


    @Mock
    private UtenteService utenteService;

    @Mock
    private Model model;

    @InjectMocks
    private UtenteController utenteController;

    @Test
    void listaUtenti() {
        List<Utente> utenti = List.of(new Utente(), new Utente());

        when(utenteService.listaUtenti())
                .thenReturn(utenti);

        String pagina = utenteController.listaUtenti(model);

        assertEquals("utenti", pagina);
        verify(model).addAttribute("utenti", utenti);
    }

    @Test
    void paginaCreaUtente() {
        String pagina = utenteController.creaUtente(model);

        assertEquals("utenti-inserimento", pagina);
    }

    @Test
    void creaUtente() {
        String pagina = utenteController.creaUtente(
                "operatore",
                "password123",
                Ruolo.OPERATORE
        );

        assertEquals("redirect:/utenti", pagina);

        verify(utenteService)
                .creaUtente("password123", "operatore", Ruolo.OPERATORE);
    }


}
