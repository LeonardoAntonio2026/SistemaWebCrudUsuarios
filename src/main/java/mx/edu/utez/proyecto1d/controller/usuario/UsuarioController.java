package mx.edu.utez.proyecto1d.controller.usuario;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto1d.controller.usuario.dto.UsuarioEdicionDTO;
import mx.edu.utez.proyecto1d.controller.usuario.dto.UsuarioRegistroDTO;
import mx.edu.utez.proyecto1d.controller.usuario.dto.UsuarioRespuestaDTO;
import mx.edu.utez.proyecto1d.service.usuario.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService servicio;

    public UsuarioController(UsuarioService servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<UsuarioRespuestaDTO> registrar(@Valid @RequestBody UsuarioRegistroDTO cuerpo) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(servicio.registrarUsuario(cuerpo));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioRespuestaDTO>> consultarTodos() {
        return ResponseEntity.ok(servicio.obtenerUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioRespuestaDTO> consultarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(servicio.obtenerPorId(id));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<UsuarioRespuestaDTO> actualizarEstado(@PathVariable Long id, @RequestParam boolean estatus) {
        return ResponseEntity.ok(servicio.modificarEstado(id, estatus));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioRespuestaDTO> actualizarDatos(
            @PathVariable Long id,
            @RequestBody @Valid UsuarioEdicionDTO cuerpo) {
        return ResponseEntity.ok(servicio.editarDatosBasicos(id, cuerpo));
    }
}
