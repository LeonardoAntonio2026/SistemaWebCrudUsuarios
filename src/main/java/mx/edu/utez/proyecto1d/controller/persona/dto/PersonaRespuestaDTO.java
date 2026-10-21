package mx.edu.utez.proyecto1d.controller.persona.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class PersonaRespuestaDTO {
    private Long id;
    private String nombres;
    private String primerApellido;
    private String segundoApellido;
    private LocalDate fechaNacimiento;
    private String curp;
    private String correo;
}
