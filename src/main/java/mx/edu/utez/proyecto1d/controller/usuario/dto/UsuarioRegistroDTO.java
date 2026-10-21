package mx.edu.utez.proyecto1d.controller.usuario.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.proyecto1d.controller.persona.dto.PersonaDatosDTO;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioRegistroDTO {

    @NotBlank(message = "El username es obligatorio")
    @Size(min = 6, max = 20, message = "El username debe contener entre 6 y 20 caracteres")
    private String username;

    @NotBlank(message = "El password es obligatorio")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "La contraseña debe tener al menos 8 caracteres, una mayúscula y un número"
    )
    private String password;

    @NotBlank(message = "El rol es obligatorio")
    @Pattern(
            regexp = "^(ADMIN|PROFESOR|ESTUDIANTE)$",
            message = "El rol debe ser ADMIN, PROFESOR o ESTUDIANTE"
    )
    private String rol;

    private String descripcion;

    @NotNull(message = "Los datos de la persona son obligatorios")
    @Valid
    private PersonaDatosDTO persona;
}
