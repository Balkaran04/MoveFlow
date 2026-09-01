package controller;

import it.polimi.moveflow.controller.DashboardController;
import it.polimi.moveflow.service.DashboardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.jdbc.autoconfigure.ApplicationDataSourceScriptDatabaseInitializer;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DashboardControllerTest {

    @Mock
    private DashboardService dashboardService;

    @Mock
    private ApplicationDataSourceScriptDatabaseInitializer dataSourceScriptDatabaseInitializer;

    @Mock
    private Model model;

    @InjectMocks
    private DashboardController dashboardController;

    @Test
    void getDashboard() {

        when(dashboardService.getUbicazioniTotali()).thenReturn(10L);
        when(dashboardService.getUbicazioniLibere()).thenReturn(5L);
        when(dashboardService.getUbicazioniOccupate()).thenReturn(4L);
        when(dashboardService.getUbicazioniBloccate()).thenReturn(1L);
        when(dashboardService.getPercentualeOccupato()).thenReturn(44.4);
        when(dashboardService.getQuantita()).thenReturn(100L);

        String pagina = dashboardController.getDashboard(model);

        assertEquals("dashboard", pagina);

        verify(model).addAttribute("totali", 10L);
        verify(model).addAttribute("libere", 5L);
        verify(model).addAttribute("occupate", 4L);
        verify(model).addAttribute("bloccate", 1L);
        verify(model).addAttribute("percentuale", 44.4);
        verify(model).addAttribute("quantita", 100L);

    }
}