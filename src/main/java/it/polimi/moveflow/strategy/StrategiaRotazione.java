package it.polimi.moveflow.strategy;

import it.polimi.moveflow.model.Ubicazione;

public interface StrategiaRotazione {

    boolean preferisci(Ubicazione candidata, Ubicazione attuale);
}
