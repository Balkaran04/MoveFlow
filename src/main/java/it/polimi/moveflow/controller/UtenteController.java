package it.polimi.moveflow.controller;

import it.polimi.moveflow.model.Ruolo;
import it.polimi.moveflow.service.UtenteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.processing.SupportedAnnotationTypes;
@Controller
public class UtenteController {

    private final UtenteService utenteService;

    public UtenteController(UtenteService utenteService){
        this.utenteService= utenteService;
    }

    @GetMapping("/utenti")
    public String listaUtenti(Model model){
        model.addAttribute("utenti",utenteService.listaUtenti());
                return "utenti";
    }
    @GetMapping("/utenti/crea")
    public String creaUtente(Model model){
        return "utenti-inserimento";
    }
    @PostMapping("/utenti/crea")
    public String creaUtente(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam Ruolo ruolo){

        utenteService.creaUtente(password,username,ruolo);

        return "redirect:/utenti";
    }
    
    @PostMapping("utenti/elimina/{id}")
    public String eliminaUtente(@PathVariable Long id){
        utenteService.eliminaUtente(id);
        return  "redirect:/utenti";
    }
}
