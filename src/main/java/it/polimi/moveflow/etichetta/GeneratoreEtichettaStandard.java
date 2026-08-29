package it.polimi.moveflow.etichetta;

import it.polimi.moveflow.model.Materiale;

public class GeneratoreEtichettaStandard  extends GeneratoreEtichetta{
    @Override
    public Etichetta creaEtichetta(Materiale materiale) {
        return new EtichettaStandard(materiale);
    }
}
