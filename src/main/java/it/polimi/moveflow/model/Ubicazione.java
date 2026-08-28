package it.polimi.moveflow.model;

import jakarta.persistence.*;

@Entity
public class Ubicazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codice;
    private int campata;
    private int livello;
    private int posizione;

    private double pesoMassimo;
    private double altezzaMassima;
    private double larghezzaMassima;
    private double profonditaMassima;
    @Enumerated(EnumType.STRING)
    private StatoUbicazione stato;

    public Ubicazione(){

    }
    public Ubicazione(Long id, String codice, int campata, int livello, int posizione, double pesoMassimo, double altezzaMassima, double larghezzaMassima, double profonditaMassima, StatoUbicazione stato) {
        this.id = id;
        this.codice = codice;
        this.campata = campata;
        this.livello = livello;
        this.posizione = posizione;
        this.pesoMassimo = pesoMassimo;
        this.altezzaMassima = altezzaMassima;
        this.larghezzaMassima = larghezzaMassima;
        this.profonditaMassima = profonditaMassima;
        this.stato = stato;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public void setCampata(int campata) {
        this.campata = campata;
    }

    public void setLivello(int livello) {
        this.livello = livello;
    }

    public void setPosizione(int posizione) {
        this.posizione = posizione;
    }

    public void setPesoMassimo(double pesoMassimo) {
        this.pesoMassimo = pesoMassimo;
    }

    public void setAltezzaMassima(double altezzaMassima) {
        this.altezzaMassima = altezzaMassima;
    }

    public void setLarghezzaMassima(double larghezzaMassima) {
        this.larghezzaMassima = larghezzaMassima;
    }

    public void setProfonditaMassima(double profonditaMassima) {
        this.profonditaMassima = profonditaMassima;
    }

    public void setStato(StatoUbicazione stato) {
        this.stato = stato;
    }

    public Long getId() {
        return id;
    }

    public String getCodice() {
        return codice;
    }

    public int getCampata() {
        return campata;
    }

    public int getLivello() {
        return livello;
    }

    public int getPosizione() {
        return posizione;
    }

    public double getPesoMassimo() {
        return pesoMassimo;
    }

    public double getAltezzaMassima() {
        return altezzaMassima;
    }

    public double getLarghezzaMassima() {
        return larghezzaMassima;
    }

    public double getProfonditaMassima() {
        return profonditaMassima;
    }

    public StatoUbicazione getStato() {
        return stato;
    }


}
