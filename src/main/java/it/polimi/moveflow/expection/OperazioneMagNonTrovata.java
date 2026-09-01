package it.polimi.moveflow.expection;

public class OperazioneMagNonTrovata extends RuntimeException{
    public OperazioneMagNonTrovata(String errore){
        super(errore);
    }
}
