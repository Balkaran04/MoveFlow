package it.polimi.moveflow.controller;

import it.polimi.moveflow.model.Materiale;
import it.polimi.moveflow.model.Ubicazione;
import it.polimi.moveflow.service.GestioneMagazzinoService;
import it.polimi.moveflow.service.MaterialeService;
import it.polimi.moveflow.service.UbicazioneService;
import org.hibernate.type.format.jaxb.JaxbXmlFormatMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class GestioneMagazzinoController{
    private final GestioneMagazzinoService gestioneMagazzinoService;
    private final MaterialeService materialeService;
    private final UbicazioneService ubicazioneService;
    public GestioneMagazzinoController(GestioneMagazzinoService gestioneMagazzinoService,MaterialeService materialeService, UbicazioneService ubicazioneService){
        this.materialeService = materialeService;
        this.ubicazioneService = ubicazioneService;
        this.gestioneMagazzinoService = gestioneMagazzinoService;
    }
    @GetMapping("/magazzino/assegnazioneauto")
    public String assegnaAutoMateriale(Model model){
        List<Materiale> m1 = materialeService.trovaTutti();

        model.addAttribute("materiali",m1);

        return "magazzino-assegnazione";
    }

    @PostMapping("/magazzino/assegnazioneauto")
    public String assegnaAutoMateriale(@RequestParam Long idMateriale){
        gestioneMagazzinoService.assegnaAutomaticamente(idMateriale);

        return "redirect:/materiali";
    }


    @GetMapping("/magazzino/assegnazione")
    public String assegnaMateriale(Model model){
        List<Materiale> m1 =  materialeService.trovaTutti();
        List<Ubicazione> u1 = ubicazioneService.trovaTutte();

        model.addAttribute("materiali",m1);
        model.addAttribute("ubicazioni",u1);

        return "magazzino-assegnazione";
    }


    @PostMapping("/magazzino/assegnazione")
    public String assegnaMateriale(@RequestParam Long idMateriale, @RequestParam Long idUbicazione){
        gestioneMagazzinoService.assegnaMateriale(idMateriale,idUbicazione);

        return "redirect:/materiali";
    }

    @GetMapping("/magazzino/spostamento")
    public String spostaMateriale(Model model )
    {
        List<Materiale> m1 =  materialeService.trovaTutti();
        List<Ubicazione> u1 = ubicazioneService.trovaTutte();

        model.addAttribute("materiali",m1);
        model.addAttribute("ubicazioni",u1);

        return "magazzino-spostamento";
    }

    @PostMapping("/magazzino/spostamento")
    public String spostaMateriale(@RequestParam Long idMateriale, @RequestParam Long idUbicazione){
        gestioneMagazzinoService.spostaMateriale(idMateriale,idUbicazione);

        return "redirect:/materiali";
    }

    @PostMapping("/magazzino/libera/{id}")
    public String liberaMateriale(@PathVariable Long id){
        gestioneMagazzinoService.liberaMateriale(id);

        return "redirect:/materiali";
    }
}


