package it.polimi.moveflow.controller;

import it.polimi.moveflow.model.Materiale;
import it.polimi.moveflow.repository.MaterialeRepository;
import it.polimi.moveflow.service.MaterialeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MaterialeController {

    private final MaterialeService materialeService;
    public MaterialeController(MaterialeService materialeService){
        this.materialeService = materialeService;
    }

    @GetMapping("/materiali")
    public String stampaMateriali(Model model){
       List<Materiale> m1 =  materialeService.trovaTutti();
       model.addAttribute("materiali",m1);

        return "materiali";
    }
    @GetMapping("/materiali/inserimento")
    public String inserimentoMateriale(Model model){
        Materiale m = new Materiale();
        model.addAttribute("materiale",m);
        return "materiale-form-ins";

    }

    @PostMapping("/materiali/inserimento")
    public String inserisciMateriale(@ModelAttribute Materiale m){
        materialeService.salvaMateriale(m);

        return "redirect:/materiali";
    }


}
