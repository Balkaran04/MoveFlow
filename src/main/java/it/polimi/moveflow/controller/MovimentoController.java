package it.polimi.moveflow.controller;

import it.polimi.moveflow.service.MovimentoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MovimentoController {

    private final MovimentoService movimentoService;
    public MovimentoController(MovimentoService movimentoService){

        this.movimentoService = movimentoService;
    }

  @GetMapping("/movimenti")
  public String listaMovimenti(Model model){
        model.addAttribute("movimenti",movimentoService.trovaTutti());
        return "movimenti";
  }

}
