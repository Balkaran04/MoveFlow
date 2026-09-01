package it.polimi.moveflow.expection;

public class MaterialeNonTrovatoException extends RuntimeException {
    public MaterialeNonTrovatoException(String errore){
        super(errore);
    }
}
