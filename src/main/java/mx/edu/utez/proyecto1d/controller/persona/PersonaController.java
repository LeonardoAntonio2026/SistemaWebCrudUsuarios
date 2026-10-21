package mx.edu.utez.proyecto1d.controller.persona;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto1d.controller.persona.dto.PersonaDatosDTO;
import mx.edu.utez.proyecto1d.controller.persona.dto.PersonaRespuestaDTO;
import mx.edu.utez.proyecto1d.service.persona.PersonaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/persona")
public class PersonaController {

    private final PersonaService servicio;

    public PersonaController(PersonaService servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaRespuestaDTO> consultarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(servicio.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaRespuestaDTO> actualizarDatos(
            @PathVariable Long id,
            @RequestBody @Valid PersonaDatosDTO cuerpo) {
        return ResponseEntity.ok(servicio.editarPersona(id, cuerpo));
    }
}
