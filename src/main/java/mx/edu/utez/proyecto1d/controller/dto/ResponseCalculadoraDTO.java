package mx.edu.utez.proyecto1d.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCalculadoraDTO {
    private double resultado;
    private String operacionRealizada;
}
