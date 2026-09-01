package it.polimi.moveflow.controller;

import it.polimi.moveflow.model.Materiale;
import it.polimi.moveflow.model.Ubicazione;
import it.polimi.moveflow.service.UbicazioneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

/***
 * Controller per la gestione Ubicazione
 * gestisce il insert, update e delete e blocca ubicazione
 */
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

    @PostMapping("/ubicazioni/elimina/{id}")
    public String eliminaUbicazione(@PathVariable Long id){
        ubicazioneService.eliminaPerId(id);
        return "redirect:/ubicazioni";
    }

    @GetMapping("/ubicazioni/modifica/{id}")
    public String modificaUbicazione(Model model, @PathVariable Long id){
        Optional<Ubicazione> m1;
        m1 = ubicazioneService.trovaPerId(id);
        if(m1.isPresent()){
            model.addAttribute("ubicazione",m1.get());
            return "modifica-ubicazione";
        }
        else
        {
            return "redirect:/ubicazioni";
        }

    }

    @PostMapping("/ubicazioni/modifica/{id}")
    public String  modificaUbicazione(@ModelAttribute Ubicazione ubicazione, @PathVariable Long id){
        ubicazioneService.modificaUbicazione(id, ubicazione);

        return "redirect:/ubicazioni";
    }

    @PostMapping("/ubicazioni/blocca/{id}")
    public String bloccaUbicazione(@PathVariable Long id){
        ubicazioneService.bloccaUbicazione(id);
        return "redirect:/ubicazioni";
    }

    @PostMapping("/ubicazioni/sblocca/{id}")
    public String sbloccaUbicazione(@PathVariable Long id){
        ubicazioneService.sbloccaUbicazione(id);
        return "redirect:/ubicazioni";
    }

}
