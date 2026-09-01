package it.polimi.moveflow.controller;

import it.polimi.moveflow.service.EtichettaService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

/**
 * Controller legato alla generazione etichetta
 */
@Controller
public class EtichettaController {

    private final EtichettaService etichettaService;
    public EtichettaController(EtichettaService etichettaService){
        this.etichettaService = etichettaService;
    }


    @GetMapping("/etichette/{id}")
    public String generaEtichetta(Model model, @PathVariable Long id) {
        model.addAttribute("etichetta", etichettaService.generaEtichetta(id));
        return "etichetta";

    }

    @GetMapping("/etichette/{id}/pdf")
    public ResponseEntity<byte[]> generaEtichettaPDF(@PathVariable Long id) throws IOException {
       byte[] pdf = etichettaService.generaPdfEtichetta(id);

       return ResponseEntity.ok()
               .contentType(MediaType.APPLICATION_PDF)
               .header(HttpHeaders.CONTENT_DISPOSITION,"inline; filename=etichetta.pdf")
               .body(pdf);

    }
    }
