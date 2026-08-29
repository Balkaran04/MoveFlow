package it.polimi.moveflow.model;

import jakarta.persistence.*;

@Entity
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @Enumerated
    TipoMovimento tipoMovimento;
    public Utente(){

    }

    public Utente(Long id, String username, String password, TipoMovimento tipoMovimento) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.tipoMovimento = tipoMovimento;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTipoMovimento(TipoMovimento tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public TipoMovimento getTipoMovimento() {
        return tipoMovimento;
    }
}
