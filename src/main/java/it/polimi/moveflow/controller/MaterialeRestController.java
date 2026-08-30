package it.polimi.moveflow.controller;

import it.polimi.moveflow.model.Materiale;
import it.polimi.moveflow.service.MaterialeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/materiali")
public class MaterialeRestController    {
    private MaterialeService materialeService;

    public MaterialeRestController(MaterialeService materialeService){
        this.materialeService = materialeService;
    }

    @GetMapping
    public List<Materiale> listaMateriali(){
        return materialeService.trovaTutti();
    }
}
