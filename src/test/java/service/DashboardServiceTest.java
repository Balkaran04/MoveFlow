package service;

import it.polimi.moveflow.model.StatoUbicazione;
import it.polimi.moveflow.repository.MaterialeRepository;
import it.polimi.moveflow.repository.MovimentoRepository;
import it.polimi.moveflow.repository.UbicazioneRepository;
import it.polimi.moveflow.service.DashboardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DashboardServiceTest {

    @Mock
    private UbicazioneRepository ubicazioneRepository;

    @Mock
    private MaterialeRepository materialeRepository;

    @Mock
    private MovimentoRepository movimentoRepository;

    @InjectMocks
    private DashboardService dashboardService;
   @Test
    void dashboard()
   {
       when(ubicazioneRepository.countByStato(StatoUbicazione.LIBERA))
               .thenReturn(6L);

       when(ubicazioneRepository.countByStato(StatoUbicazione.OCCUPATA))
               .thenReturn(4L);

       when(ubicazioneRepository.countByStato(StatoUbicazione.BLOCCATA))
               .thenReturn(2L);

       when(ubicazioneRepository.count())
               .thenReturn(12L);

       when(materialeRepository.sommaQuantita())
               .thenReturn(100L);

       assertEquals(6, dashboardService.getUbicazioniLibere());
       assertEquals(4, dashboardService.getUbicazioniOccupate());
       assertEquals(2, dashboardService.getUbicazioniBloccate());
       assertEquals(12, dashboardService.getUbicazioniTotali());
       assertEquals(40.0, dashboardService.getPercentualeOccupato());
       assertEquals(100, dashboardService.getQuantita());
   }
}
