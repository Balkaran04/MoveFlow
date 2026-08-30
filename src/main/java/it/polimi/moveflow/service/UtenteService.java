package it.polimi.moveflow.service;

import it.polimi.moveflow.model.Ruolo;
import it.polimi.moveflow.model.Utente;
import it.polimi.moveflow.repository.UtenteRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;

@Service
public class UtenteService {
    private final UtenteRepository utenteRepository;

    public UtenteService(UtenteRepository utenteRepository){
        this.utenteRepository = utenteRepository;
    }

    public List<Utente> listaUtenti(){
        return utenteRepository.findAll();

    };

    public void creaUtente(String password, String username, Ruolo ruolo) {
      Utente utente = new Utente(username, encriptaPassword(password),ruolo);
      utenteRepository.save(utente);
    }

    public String encriptaPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);

    }
}
