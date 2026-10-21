package mx.edu.utez.proyecto1d.service.persona;

import mx.edu.utez.proyecto1d.controller.persona.dto.PersonaDatosDTO;
import mx.edu.utez.proyecto1d.controller.persona.dto.PersonaRespuestaDTO;
import mx.edu.utez.proyecto1d.exeption.customExeptions.SolicitudInvalidaException;
import mx.edu.utez.proyecto1d.model.persona.Persona;
import mx.edu.utez.proyecto1d.repository.persona.PersonaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaService {

    private final PersonaRepository repositorio;

    public PersonaService(PersonaRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Transactional(readOnly = true)
    public PersonaRespuestaDTO obtenerPorId(Long idPersona) {
        return aRespuesta(localizar(idPersona));
    }

    @Transactional
    public PersonaRespuestaDTO editarPersona(Long idPersona, PersonaDatosDTO cambios) {
        Persona existente = localizar(idPersona);

        if (repositorio.existsByCurpAndIdNot(cambios.getCurp(), idPersona)) {
            throw new SolicitudInvalidaException("Ya existe otra persona registrada con esa CURP");
        }

        existente.setCorreo(cambios.getCorreo());
        existente.setCurp(cambios.getCurp());
        existente.setFechaNacimiento(cambios.getFechaNacimiento());
        existente.setNombres(cambios.getNombres());
        existente.setPrimerApellido(cambios.getPrimerApellido());
        existente.setSegundoApellido(cambios.getSegundoApellido());

        return aRespuesta(repositorio.save(existente));
    }

    private Persona localizar(Long idPersona) {
        return repositorio.findById(idPersona)
                .orElseThrow(() -> new SolicitudInvalidaException("La persona con ID " + idPersona + " no existe."));
    }

    private PersonaRespuestaDTO aRespuesta(Persona persona) {
        return new PersonaRespuestaDTO(
                persona.getId(),
                persona.getNombres(),
                persona.getPrimerApellido(),
                persona.getSegundoApellido(),
                persona.getFechaNacimiento(),
                persona.getCurp(),
                persona.getCorreo()
        );
    }
}
