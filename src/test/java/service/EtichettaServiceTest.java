package service;

import it.polimi.moveflow.model.ClasseRotazione;
import it.polimi.moveflow.model.Materiale;
import it.polimi.moveflow.repository.MaterialeRepository;
import it.polimi.moveflow.service.EtichettaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EtichettaServiceTest {

    @Mock
    private MaterialeRepository materialeRepository;

    @InjectMocks
    private EtichettaService etichettaService;

    @Test
    void generaEtichetta() {

        Materiale materiale = new Materiale();
        materiale.setCodice("MAT01");
        materiale.setDescrizione("Scatola");
        materiale.setQuantita(10);
        materiale.setClasseRotazione(ClasseRotazione.ALTA);

        when(materialeRepository.findById(1L))
                .thenReturn(Optional.of(materiale));

        String risultato = etichettaService.generaEtichetta(1L);

        assertTrue(risultato.contains("MAT01"));
        assertTrue(risultato.contains("Scatola"));
        assertTrue(risultato.contains("10"));
    }


    @Test
    void generaPdfEtichetta() throws IOException {

        Materiale materiale = new Materiale();
        materiale.setId(1L);
        materiale.setCodice("MAT01");
        materiale.setDescrizione("Scatola");
        materiale.setQuantita(10);
        materiale.setClasseRotazione(ClasseRotazione.ALTA);

        when(materialeRepository.findById(1L))
                .thenReturn(Optional.of(materiale));

        byte[] pdf = etichettaService.generaPdfEtichetta(1L);

        assertTrue(pdf.length > 0);
    }

}
