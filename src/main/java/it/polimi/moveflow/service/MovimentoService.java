package it.polimi.moveflow.service;

import it.polimi.moveflow.model.Movimento;
import it.polimi.moveflow.repository.MovimentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovimentoService {
    private final MovimentoRepository movimentoRepository;

    public MovimentoService(MovimentoRepository movimentoRepository){
        this.movimentoRepository = movimentoRepository;
    }

    public List<Movimento> trovaTutti(){
        return movimentoRepository.findAll();
    }
}
