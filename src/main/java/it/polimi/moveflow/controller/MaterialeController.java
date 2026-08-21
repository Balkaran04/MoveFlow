package it.polimi.moveflow.controller;

import it.polimi.moveflow.model.Materiale;
import it.polimi.moveflow.repository.MaterialeRepository;
import it.polimi.moveflow.service.MaterialeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
