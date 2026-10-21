package mx.edu.utez.proyecto1d.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestBodyDto {
    private String nombre;
    private int edad;
    private String correo;
    private String curp;
}
