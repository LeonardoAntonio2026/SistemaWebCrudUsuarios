package mx.edu.utez.proyecto1d.service;

import mx.edu.utez.proyecto1d.controller.dto.RequestCalculadoraDTO;
import mx.edu.utez.proyecto1d.controller.dto.ResponseCalculadoraDTO;
import mx.edu.utez.proyecto1d.exeption.customExeptions.SolicitudInvalidaException;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    public ResponseCalculadoraDTO calculadora(RequestCalculadoraDTO data) {
        ResponseCalculadoraDTO respuesta = new ResponseCalculadoraDTO();
        String operacion = data.getOperacion();

        if ("DIVISION".equals(operacion) && data.getNum2() == 0) {
            throw new SolicitudInvalidaException("No se puede dividir entre cero");
        }

        double resultado = switch (operacion) {
            case "SUMA" -> data.getNum1() + data.getNum2();
            case "RESTA" -> data.getNum1() - data.getNum2();
            case "MULTIPLICACION" -> data.getNum1() * data.getNum2();
            case "DIVISION" -> data.getNum1() / data.getNum2();
            default -> throw new SolicitudInvalidaException("La operacion solicitada no esta disponible");
        };

        respuesta.setResultado(resultado);
        respuesta.setOperacionRealizada(operacion);

        return respuesta;
    }
}
