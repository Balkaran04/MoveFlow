package it.polimi.moveflow.expection;

public class UbicazioneNonTrovataException extends RuntimeException {
    public UbicazioneNonTrovataException(String errore){
        super(errore);
    }
}
