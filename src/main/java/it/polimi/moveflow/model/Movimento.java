package it.polimi.moveflow.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Movimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "materiale_id")
    private Materiale materiale;
    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;
    @Enumerated
    private TipoMovimento tipoMovimento;
    private String ubicazioneOrigine;
    private String ubicazioneDestinazione;
    private LocalDateTime dataOra;



    public Movimento(){

    };

    public Movimento(Materiale materiale, TipoMovimento tipoMovimento, String ubicazioneOrigine, String ubicazioneDestinazione, LocalDateTime dataOra, Utente utente) {
        this.materiale = materiale;
        this.tipoMovimento = tipoMovimento;
        this.ubicazioneOrigine = ubicazioneOrigine;
        this.ubicazioneDestinazione = ubicazioneDestinazione;
        this.dataOra = dataOra;
        this.utente = utente;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipoMovimento(TipoMovimento tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public void setUbicazioneOrigine(String ubicazioneOrigine) {
        this.ubicazioneOrigine = ubicazioneOrigine;
    }

    public void setUbicazioneDestinazione(String ubicazioneDestinazione) {
        this.ubicazioneDestinazione = ubicazioneDestinazione;
    }

    public void setDataOra(LocalDateTime dataOra) {
        this.dataOra = dataOra;
    }

    public Long getId() {
        return id;
    }

    public TipoMovimento getTipoMovimento() {
        return tipoMovimento;
    }

    public String getUbicazioneOrigine() {
        return ubicazioneOrigine;
    }

    public String getUbicazioneDestinazione() {
        return ubicazioneDestinazione;
    }

    public LocalDateTime getDataOra() {
        return dataOra;
    }


    public Materiale getMateriale() {
        return materiale;
    }

    public void setMateriale(Materiale materiale) {
        this.materiale = materiale;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
