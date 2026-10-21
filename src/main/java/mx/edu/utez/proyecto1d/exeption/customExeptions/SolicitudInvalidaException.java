package mx.edu.utez.proyecto1d.exeption.customExeptions;

public class SolicitudInvalidaException extends RuntimeException{

    public SolicitudInvalidaException(String mensaje){
        super(mensaje);
    }

}
