package mx.edu.utez.proyecto1d.controller.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsuarioRespuestaDTO {
    private Long id;
    private String username;
    private String descripcion;
    private boolean activo;
    private String rol;
    private Long personaId;
}
