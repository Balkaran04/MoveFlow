package service;

import it.polimi.moveflow.model.ClasseRotazione;
import it.polimi.moveflow.model.Materiale;
import it.polimi.moveflow.repository.MaterialeRepository;
import it.polimi.moveflow.repository.MovimentoRepository;
import it.polimi.moveflow.repository.UbicazioneRepository;
import it.polimi.moveflow.service.GestioneMagazzinoService;
import it.polimi.moveflow.service.MaterialeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MaterialeServiceTest {
    @Mock
    private MaterialeRepository materialeRepository;

    @Mock
    private UbicazioneRepository ubicazioneRepository;

    @Mock
    private MovimentoRepository movimentoRepository;

    @InjectMocks
    private MaterialeService materialeService;

    @Test
    void trovaTutti(){
        Materiale m1 = new Materiale();
        Materiale m2 = new Materiale();

        when(materialeRepository.findAll())
                .thenReturn(List.of(m1,m2));

        List<Materiale> ris= materialeService.trovaTutti();

        assertEquals(2,ris.size());

        verify(materialeRepository).findAll();
    }

    @Test
    void modificaMateriale(){

        Materiale vecchio = new Materiale();
        vecchio.setCodice("OLD");
        vecchio.setDescrizione("Vecchio");
        vecchio.setQuantita(5);
        vecchio.setPeso(10);

        Materiale nuovo = new Materiale();
        nuovo.setCodice("NEW");
        nuovo.setDescrizione("Nuovo");
        nuovo.setQuantita(20);
        nuovo.setPeso(50);
        nuovo.setAltezza(30);
        nuovo.setLarghezza(40);
        nuovo.setProfondita(60);
        nuovo.setClasseRotazione(ClasseRotazione.ALTA);

        when(materialeRepository.findById(1L))
                .thenReturn(Optional.of(vecchio));

        materialeService.modificaMateriale(1L, nuovo);

        assertEquals("NEW", vecchio.getCodice());
        assertEquals("Nuovo", vecchio.getDescrizione());
        assertEquals(20, vecchio.getQuantita());
        assertEquals(50, vecchio.getPeso());
        assertEquals(30, vecchio.getAltezza());
        assertEquals(40, vecchio.getLarghezza());
        assertEquals(60, vecchio.getProfondita());
        assertEquals(ClasseRotazione.ALTA, vecchio.getClasseRotazione());

        verify(materialeRepository).save(vecchio);
    }

    @Test
    void salvaMateriale() {
        Materiale materiale = new Materiale();

        when(materialeRepository.save(materiale))
                .thenReturn(materiale);

        Materiale risultato = materialeService.salvaMateriale(materiale);

        assertEquals(materiale, risultato);

        verify(materialeRepository).save(materiale);
    }

    @Test
    void trovaPerId() {
        Materiale materiale = new Materiale();

        when(materialeRepository.findById(1L))
                .thenReturn(Optional.of(materiale));

        Optional<Materiale> risultato = materialeService.trovaPerId(1L);

        assertEquals(materiale, risultato.get());

        verify(materialeRepository).findById(1L);
    }

    @Test
    void eliminaPerId() {
        Materiale m = new Materiale();

        when(materialeRepository.findById(1L))
                .thenReturn(Optional.of(m));
        materialeService.eliminaPerId(1L);

        verify(materialeRepository).deleteById(1L);
    }

}
