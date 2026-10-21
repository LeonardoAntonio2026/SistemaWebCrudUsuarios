package mx.edu.utez.proyecto1d.service.usuario;

import mx.edu.utez.proyecto1d.controller.persona.dto.PersonaDatosDTO;
import mx.edu.utez.proyecto1d.controller.usuario.dto.UsuarioEdicionDTO;
import mx.edu.utez.proyecto1d.controller.usuario.dto.UsuarioRegistroDTO;
import mx.edu.utez.proyecto1d.controller.usuario.dto.UsuarioRespuestaDTO;
import mx.edu.utez.proyecto1d.exeption.customExeptions.SolicitudInvalidaException;
import mx.edu.utez.proyecto1d.model.persona.Persona;
import mx.edu.utez.proyecto1d.model.usuario.Roles;
import mx.edu.utez.proyecto1d.model.usuario.Usuario;
import mx.edu.utez.proyecto1d.repository.persona.PersonaRepository;
import mx.edu.utez.proyecto1d.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repositorio;
    private final PersonaRepository repositorioPersonas;

    public UsuarioService(UsuarioRepository repositorio, PersonaRepository repositorioPersonas) {
        this.repositorio = repositorio;
        this.repositorioPersonas = repositorioPersonas;
    }

    @Transactional
    public UsuarioRespuestaDTO registrarUsuario(UsuarioRegistroDTO datos) {
        if (repositorio.findByUsername(datos.getUsername()).isPresent()) {
            throw new SolicitudInvalidaException("El usuario ya esta en uso");
        }
        PersonaDatosDTO datosPersona = datos.getPersona();
        if (repositorioPersonas.existsByCurp(datosPersona.getCurp())) {
            throw new SolicitudInvalidaException("Ya existe una persona registrada con esa CURP");
        }

        Persona persona = new Persona();
        persona.setNombres(datosPersona.getNombres());
        persona.setPrimerApellido(datosPersona.getPrimerApellido());
        persona.setSegundoApellido(datosPersona.getSegundoApellido());
        persona.setFechaNacimiento(datosPersona.getFechaNacimiento());
        persona.setCurp(datosPersona.getCurp());
        persona.setCorreo(datosPersona.getCorreo());

        Usuario creado = new Usuario();
        creado.setPersona(repositorioPersonas.save(persona));
        creado.setUsername(datos.getUsername());
        creado.setPassword(datos.getPassword());
        creado.setDescripcion(datos.getDescripcion());
        creado.setRol(Roles.valueOf(datos.getRol()));
        creado.setEnable(true);
        return aRespuesta(repositorio.save(creado));
    }

    @Transactional(readOnly = true)
    public UsuarioRespuestaDTO obtenerPorId(Long idUsuario) {
        return aRespuesta(localizar(idUsuario));
    }

    @Transactional(readOnly = true)
    public List<UsuarioRespuestaDTO> obtenerUsuarios() {
        return repositorio.findAll().stream().map(this::aRespuesta).toList();
    }

    @Transactional
    public UsuarioRespuestaDTO modificarEstado(Long idUsuario, boolean activo) {
        Usuario existente = localizar(idUsuario);
        existente.setEnable(activo);
        return aRespuesta(repositorio.save(existente));
    }

    @Transactional
    public UsuarioRespuestaDTO editarDatosBasicos(Long idUsuario, UsuarioEdicionDTO cambios) {
        Usuario existente = localizar(idUsuario);

        existente.setDescripcion(cambios.getDescripcion());
        existente.setUsername(cambios.getUsername());

        return aRespuesta(repositorio.save(existente));
    }

    private Usuario localizar(Long idUsuario) {
        return repositorio.findById(idUsuario)
                .orElseThrow(() -> new SolicitudInvalidaException("El usuario con ID " + idUsuario + " no existe."));
    }

    private UsuarioRespuestaDTO aRespuesta(Usuario usuario) {
        Long idPersona = usuario.getPersona() != null ? usuario.getPersona().getId() : null;
        return new UsuarioRespuestaDTO(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getDescripcion(),
                usuario.isEnable(),
                usuario.getRol() != null ? usuario.getRol().name() : null,
                idPersona
        );
    }
}
