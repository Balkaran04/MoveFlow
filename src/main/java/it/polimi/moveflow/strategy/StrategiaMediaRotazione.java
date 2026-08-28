package it.polimi.moveflow.strategy;

import it.polimi.moveflow.model.Ubicazione;


public class StrategiaMediaRotazione implements StrategiaRotazione {

    @Override
    public boolean preferisci(Ubicazione candidata, Ubicazione attuale){

        return false;

    };
}
