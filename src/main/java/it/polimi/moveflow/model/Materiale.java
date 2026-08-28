package it.polimi.moveflow.model;

import jakarta.persistence.*;
@Entity
public class Materiale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codice;
    private String descrizione;
    private double peso;
    private double altezza;
    private double larghezza;
    private double profondita;
    @Enumerated(EnumType.STRING)
    private ClasseRotazione classeRotazione;

    @OneToOne
    @JoinColumn(name = "ubicazione_id")
    private Ubicazione ubicazione;

    public Materiale(){
    }
    public Materiale(Long id, String codice, String descrizione, double peso, double altezza, double larghezza, double profondita, ClasseRotazione classeRotazione) {
        this.id = id;
        this.codice = codice;
        this.descrizione = descrizione;
        this.peso = peso;
        this.altezza = altezza;
        this.larghezza = larghezza;
        this.profondita = profondita;
        this.classeRotazione = classeRotazione;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setAltezza(double altezza) {
        this.altezza = altezza;
    }

    public void setLarghezza(double larghezza) {
        this.larghezza = larghezza;
    }

    public void setProfondita(double profondita) {
        this.profondita = profondita;
    }

    public void setClasseRotazione(ClasseRotazione classeRotazione) {
        this.classeRotazione = classeRotazione;
    }

    public void setUbicazione(Ubicazione ubicazione){
        this.ubicazione = ubicazione;
    }
    public Long getId() {
        return id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public double getPeso() {
        return peso;
    }

    public double getAltezza() {
        return altezza;
    }

    public double getLarghezza() {
        return larghezza;
    }

    public double getProfondita() {
        return profondita;
    }

    public ClasseRotazione getClasseRotazione() {
        return classeRotazione;
    }

    public String getCodice() {
        return codice;
    }
    public Ubicazione getUbicazione() { return  ubicazione;}

}
