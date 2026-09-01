package it.polimi.moveflow.strategy;

import it.polimi.moveflow.model.Ubicazione;

public class StrategiaAltaRotazione implements StrategiaRotazione{
    // Per alta rotazione cerco se trovo ubicazione piu vicina
    @Override
    public boolean preferisci(Ubicazione candidata, Ubicazione attuale){
        if(candidata.getCampata() < attuale.getCampata()){
            return true;
        } else if (candidata.getCampata() == attuale.getCampata() &&
                   candidata.getLivello() < attuale.getLivello() ){
            return true;
        } else if (candidata.getCampata() == attuale.getCampata() &&
                   candidata.getLivello() == attuale.getLivello() &&
                   candidata.getPosizione() < attuale.getPosizione()) {
            return true;
        }
        return false;

    };

}
