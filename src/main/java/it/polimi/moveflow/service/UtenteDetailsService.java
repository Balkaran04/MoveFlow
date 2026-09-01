package it.polimi.moveflow.service;

import it.polimi.moveflow.model.Utente;
import it.polimi.moveflow.repository.UtenteRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
/*
* Service usato da Spring Security per i dati del utente loggato*/
@Service
public class UtenteDetailsService implements UserDetailsService {

    private final UtenteRepository utenteRepository;

    public UtenteDetailsService(UtenteRepository utenteRepository){
        this.utenteRepository = utenteRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {
        Optional<Utente> risultato = utenteRepository.findByUsername(username);

        if(risultato.isEmpty()){
            throw new UsernameNotFoundException("Utente non trovato");

        }
        Utente utente = risultato.get();
        UserDetails userDetails = User.builder()
                .username(utente.getUsername())
                .password(utente.getPassword())
                .roles(utente.getRuolo().name())
                .build();
        return userDetails;
    }
}
