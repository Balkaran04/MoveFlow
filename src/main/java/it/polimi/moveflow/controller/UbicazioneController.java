package it.polimi.moveflow.controller;

import it.polimi.moveflow.model.Materiale;
import it.polimi.moveflow.model.Ubicazione;
import it.polimi.moveflow.service.UbicazioneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UbicazioneController {

    private final UbicazioneService ubicazioneService;
    public UbicazioneController(UbicazioneService ubicazioneService){
        this.ubicazioneService = ubicazioneService;
    }

    @GetMapping("/ubicazioni")
    public String stampaUbicazioni(Model model){
        List<Ubicazione> u1 =  ubicazioneService.trovaTutte();
        model.addAttribute("ubicazioni",u1);

        return "ubicazioni";
    }
    @GetMapping("/ubicazioni/inserimento")
    public String inserimentoUbicazione(Model model){
        Ubicazione m = new Ubicazione();
        model.addAttribute("ubicazioni",m);
        return "ubicazioni-form-ins";

    }

    @PostMapping("/ubicazioni/inserimento")
    public String inserisciUbicazione(@ModelAttribute Ubicazione m){
        ubicazioneService.salvaUbicazione(m);

        return "redirect:/ubicazioni";
    }

}
