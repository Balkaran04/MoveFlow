package it.polimi.moveflow.controller;

import it.polimi.moveflow.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService){
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard")
    public String getDashboard(Model model){
        model.addAttribute("totali",dashboardService.getUbicazioniTotali());
        model.addAttribute("libere",dashboardService.getUbicazioniLibere());
        model.addAttribute("occupate",dashboardService.getUbicazioniOccupate());
        model.addAttribute("bloccate",dashboardService.getUbicazioniBloccate());
        model.addAttribute("percentuale",dashboardService.getPercentualeOccupato());

        return "dashboard";
    }
}
