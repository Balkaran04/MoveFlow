package model;

import it.polimi.moveflow.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelTest {

    @Test
    void materiale() {

        Materiale m = new Materiale();

        Ubicazione ubicazione = new Ubicazione();

        m.setId(1L);
        m.setCodice("MAT01");
        m.setDescrizione("Scatola");
        m.setPeso(10);
        m.setAltezza(20);
        m.setLarghezza(30);
        m.setProfondita(40);
        m.setQuantita(5);
        m.setClasseRotazione(ClasseRotazione.ALTA);
        m.setUbicazione(ubicazione);

        assertEquals(1L, m.getId());
        assertEquals("MAT01", m.getCodice());
        assertEquals("Scatola", m.getDescrizione());
        assertEquals(10, m.getPeso());
        assertEquals(20, m.getAltezza());
        assertEquals(30, m.getLarghezza());
        assertEquals(40, m.getProfondita());
        assertEquals(5, m.getQuantita());
        assertEquals(ClasseRotazione.ALTA, m.getClasseRotazione());
        assertEquals(ubicazione, m.getUbicazione());
    }


    @Test
    void ubicazione() {

        Ubicazione u = new Ubicazione();

        u.setId(1L);
        u.setCodice("U01");
        u.setCampata(2);
        u.setLivello(3);
        u.setPosizione(4);
        u.setPesoMassimo(500);
        u.setAltezzaMassima(200);
        u.setLarghezzaMassima(150);
        u.setProfonditaMassima(180);
        u.setStato(StatoUbicazione.LIBERA);

        assertEquals(1L, u.getId());
        assertEquals("U01", u.getCodice());
        assertEquals(2, u.getCampata());
        assertEquals(3, u.getLivello());
        assertEquals(4, u.getPosizione());
        assertEquals(500, u.getPesoMassimo());
        assertEquals(200, u.getAltezzaMassima());
        assertEquals(150, u.getLarghezzaMassima());
        assertEquals(180, u.getProfonditaMassima());
        assertEquals(StatoUbicazione.LIBERA, u.getStato());
    }


    @Test
    void utente() {

        Utente u = new Utente();

        u.setId(1L);
        u.setUsername("admin");
        u.setPassword("password");
        u.setRuolo(Ruolo.ADMIN);

        assertEquals(1L, u.getId());
        assertEquals("admin", u.getUsername());
        assertEquals("password", u.getPassword());
        assertEquals(Ruolo.ADMIN, u.getRuolo());
    }


    @Test
    void movimento() {

        Materiale materiale = new Materiale();
        Utente utente = new Utente();

        LocalDateTime data =
                LocalDateTime.of(2026, 9, 1, 10, 0);

        Movimento movimento = new Movimento();

        movimento.setId(1L);
        movimento.setMateriale(materiale);
        movimento.setUtente(utente);
        movimento.setTipoMovimento(TipoMovimento.SPOSTAMENTO);
        movimento.setUbicazioneOrigine("U01");
        movimento.setUbicazioneDestinazione("U02");
        movimento.setDataOra(data);

        assertEquals(1L, movimento.getId());
        assertEquals(materiale, movimento.getMateriale());
        assertEquals(utente, movimento.getUtente());
        assertEquals(TipoMovimento.SPOSTAMENTO,
                movimento.getTipoMovimento());
        assertEquals("U01", movimento.getUbicazioneOrigine());
        assertEquals("U02", movimento.getUbicazioneDestinazione());
        assertEquals(data, movimento.getDataOra());
    }

    @Test
    void costruttibase(){
        Materiale materiale = new Materiale(1L, "MAT01", "Scatola", 10, 20, 30, 40, ClasseRotazione.ALTA
        );

        Ubicazione ubicazione = new Ubicazione(1L, "U01", 1, 2, 3, 500, 100, 100, 100, StatoUbicazione.LIBERA
        );

        Utente utente = new Utente("operatore", "password", Ruolo.OPERATORE
        );

        assertEquals("MAT01", materiale.getCodice());
        assertEquals("U01", ubicazione.getCodice());
        assertEquals("operatore", utente.getUsername());
    }
}
