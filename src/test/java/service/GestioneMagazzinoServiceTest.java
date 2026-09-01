package service;

import it.polimi.moveflow.repository.MaterialeRepository;
import it.polimi.moveflow.repository.MovimentoRepository;
import it.polimi.moveflow.repository.UbicazioneRepository;
import it.polimi.moveflow.repository.UtenteRepository;
import it.polimi.moveflow.service.GestioneMagazzinoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import it.polimi.moveflow.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GestioneMagazzinoServiceTest {

    @Mock
    private MaterialeRepository materialeRepository;

    @Mock
    private UbicazioneRepository ubicazioneRepository;

    @Mock
    private MovimentoRepository movimentoRepository;

    @Mock
    private UtenteRepository utenteRepository;

    @InjectMocks
    private GestioneMagazzinoService gestioneMagazzinoService;
   @Test
    void assegnaMaterialeCompatibile(){

       Materiale materiale = new Materiale();
       materiale.setPeso(50);
       materiale.setAltezza(20);
       materiale.setLarghezza(20);
       materiale.setProfondita(20);

       Ubicazione ubicazione = new Ubicazione();
       ubicazione.setCodice("UB01");
       ubicazione.setStato(StatoUbicazione.LIBERA);
       ubicazione.setPesoMassimo(100);
       ubicazione.setAltezzaMassima(100);
       ubicazione.setLarghezzaMassima(100);
       ubicazione.setProfonditaMassima(100);

       when(materialeRepository.findById(1L)
               ).thenReturn(Optional.of(materiale));

       when(ubicazioneRepository.findById(2L))
               .thenReturn(Optional.of(ubicazione));

       Utente admin = new Utente();
       admin.setUsername("admin");
       admin.setRuolo(Ruolo.ADMIN);
       when(utenteRepository.findByUsername("admin")).thenReturn(Optional.of(admin));

       gestioneMagazzinoService.assegnaMateriale(1L,2L);
       // verifico che sono stati modificati dati
       assertEquals(ubicazione,materiale.getUbicazione());
       assertEquals(StatoUbicazione.OCCUPATA, ubicazione.getStato());

       verify(materialeRepository).save(materiale);
       verify(ubicazioneRepository).save(ubicazione);
       verify(movimentoRepository).save(any(Movimento.class));

   }

   @Test
   void assegnaAutomaticamenteAltaRotazione(){
       Materiale materiale = new Materiale();
       materiale.setPeso(10);
       materiale.setAltezza(10);
       materiale.setLarghezza(10);
       materiale.setProfondita(10);
       materiale.setClasseRotazione(ClasseRotazione.ALTA);

       Ubicazione noUbicazi = new Ubicazione();
       noUbicazi.setCodice("U0");
       noUbicazi.setStato(StatoUbicazione.LIBERA);
       noUbicazi.setPesoMassimo(5);
       noUbicazi.setAltezzaMassima(100);
       noUbicazi.setLarghezzaMassima(100);
       noUbicazi.setProfonditaMassima(100);

       Ubicazione u1 = new Ubicazione();
       u1.setCodice("U1");
       u1.setStato(StatoUbicazione.LIBERA);
       u1.setCampata(5);
       u1.setLivello(1);
       u1.setPosizione(1);
       u1.setPesoMassimo(100);
       u1.setAltezzaMassima(20);
       u1.setLarghezzaMassima(20);
       u1.setProfonditaMassima(20);

       Ubicazione u2 = new Ubicazione();
       u2.setCodice("U2");
       u2.setStato(StatoUbicazione.LIBERA);
       u2.setCampata(1);
       u2.setLivello(1);
       u2.setPosizione(1);
       u2.setPesoMassimo(100);
       u2.setAltezzaMassima(20);
       u2.setLarghezzaMassima(20);
       u2.setProfonditaMassima(20);

       List<Ubicazione> ub = new ArrayList<>(List.of(noUbicazi,u1,u2));

       when(materialeRepository.findById(1L)
               ).thenReturn(Optional.of(materiale));

       when(ubicazioneRepository.findByStato(StatoUbicazione.LIBERA)).thenReturn(ub);

       Utente admin = new Utente();
       admin.setUsername("admin");
       admin.setRuolo(Ruolo.ADMIN);
       when(utenteRepository.findByUsername("admin")).thenReturn(Optional.of(admin));

       gestioneMagazzinoService.assegnaAutomaticamente(1L);

       assertEquals(u2,materiale.getUbicazione());
       assertEquals(StatoUbicazione.OCCUPATA,u2.getStato());

       verify(materialeRepository).save(materiale);
       verify(ubicazioneRepository).save(u2);
       verify(movimentoRepository).save(any(Movimento.class));
   }

   @Test
   void SpostaMateriale(){
       Materiale materiale = new Materiale();
       materiale.setPeso(10);
       materiale.setAltezza(10);
       materiale.setLarghezza(10);
       materiale.setProfondita(10);

       Ubicazione vecchiaUb = new Ubicazione();
       vecchiaUb.setCodice("Vecchia");
       vecchiaUb.setStato(StatoUbicazione.OCCUPATA);

       Ubicazione nuovaUb = new Ubicazione();
       nuovaUb.setCodice("Nuovo");
       nuovaUb.setStato(StatoUbicazione.LIBERA);
       nuovaUb.setPesoMassimo(100);
       nuovaUb.setAltezzaMassima(100);
       nuovaUb.setLarghezzaMassima(100);
       nuovaUb.setProfonditaMassima(100);

       materiale.setUbicazione(vecchiaUb);

       when(materialeRepository.findById(1L)
       ).thenReturn(Optional.of(materiale));

       when(ubicazioneRepository.findById(2L)
       ).thenReturn(Optional.of(nuovaUb));


       Utente admin = new Utente();
       admin.setUsername("admin");
       admin.setRuolo(Ruolo.ADMIN);
       when(utenteRepository.findByUsername("admin")).thenReturn(Optional.of(admin));

      gestioneMagazzinoService.spostaMateriale(1L,2L);

       assertEquals(nuovaUb,materiale.getUbicazione());
       assertEquals(StatoUbicazione.LIBERA ,vecchiaUb.getStato());
       assertEquals(StatoUbicazione.OCCUPATA,nuovaUb.getStato());



       verify(materialeRepository).save(materiale);
       verify(ubicazioneRepository).save(vecchiaUb);
       verify(ubicazioneRepository).save(nuovaUb);
       verify(movimentoRepository).save(any(Movimento.class));
   }

   @Test
   void liberaMateriale(){
       Materiale materiale = new Materiale();
       materiale.setPeso(10);
       materiale.setAltezza(10);
       materiale.setLarghezza(10);
       materiale.setProfondita(10);

       Ubicazione ubicazione = new Ubicazione();
       ubicazione.setCodice("U1");
       ubicazione.setStato(StatoUbicazione.OCCUPATA);

       materiale.setUbicazione(ubicazione);

       materialeRepository.findById(1L);

       Utente admin = new Utente();
       admin.setUsername("admin");
       admin.setRuolo(Ruolo.ADMIN);
       when(utenteRepository.findByUsername("admin")).thenReturn(Optional.of(admin));

       when(materialeRepository.findById(1L)
       ).thenReturn(Optional.of(materiale));

       gestioneMagazzinoService.liberaMateriale(1L);


        assertNull(materiale.getUbicazione());
        assertEquals(StatoUbicazione.LIBERA,ubicazione.getStato());

       verify(materialeRepository).save(materiale);
       verify(ubicazioneRepository).save(ubicazione);
       verify(movimentoRepository).save(any(Movimento.class));


   }

   @BeforeEach
    void preparaUtenteLoggato(){

       UsernamePasswordAuthenticationToken auth=
               new UsernamePasswordAuthenticationToken(
                       "admin",
                       "password"
               );

       SecurityContextHolder.getContext().setAuthentication(auth);

   }


}
