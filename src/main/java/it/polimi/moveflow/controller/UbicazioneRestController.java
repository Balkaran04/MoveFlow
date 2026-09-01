package it.polimi.moveflow.controller;

import it.polimi.moveflow.model.Ubicazione;
import it.polimi.moveflow.service.UbicazioneService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller per API REST delle Ubicazioni,
 * se si fa richiesta API torna JSON di lista dati
 */
@RestController
@RequestMapping("/api/ubicazioni")
public class UbicazioneRestController {
    private final UbicazioneService ubicazioneService;

    public UbicazioneRestController(UbicazioneService ubicazioneService){
        this.ubicazioneService = ubicazioneService;
    }

    @GetMapping
    public List<Ubicazione> listaUbicazioni(){
        return ubicazioneService.trovaTutte();
    }
}
