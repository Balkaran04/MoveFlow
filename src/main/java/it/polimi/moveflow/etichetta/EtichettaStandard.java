package it.polimi.moveflow.etichetta;

import it.polimi.moveflow.model.Materiale;

public class EtichettaStandard implements Etichetta{
    private final Materiale materiale;

    public EtichettaStandard(Materiale materiale){
        this.materiale  = materiale;
    }
    @Override
    public String generaTesto() {
        String ubicazione = "Non assegnata";

        if(materiale.getUbicazione() != null){
            ubicazione= materiale.getUbicazione().getCodice();
        }

        return"Etichetta Materiale\n" +
               "Codice: "+materiale.getCodice()+ "\n" +
               "Descrizione: "+materiale.getDescrizione()+"\n" +
               "Quantità: "+ materiale.getQuantita()+"\n" +
               "Ubicazione: "+ ubicazione +"\n" +
               "Classe Rotazione: "+materiale.getClasseRotazione().toString();
    }
}
