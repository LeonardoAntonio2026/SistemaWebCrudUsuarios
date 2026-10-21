package mx.edu.utez.proyecto1d.controller.usuario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioEdicionDTO {

    @NotBlank(message = "El username es obligatorio")
    @Size(min = 6, max = 20, message = "El username debe contener entre 6 y 20 caracteres")
    private String username;

    private String descripcion;
}
