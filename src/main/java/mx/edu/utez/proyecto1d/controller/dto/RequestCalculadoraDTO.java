package mx.edu.utez.proyecto1d.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RequestCalculadoraDTO {
    private int num1;
    private int num2;

    @NotBlank(message = "La operacion es obligatoria")
    private String operacion;


}
