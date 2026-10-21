package mx.edu.utez.proyecto1d.model.direccion;

import jakarta.persistence.*;
import mx.edu.utez.proyecto1d.model.persona.Persona;
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String calle;
    private int noExterior;
    private String colonia;
    private String municipio;
    private String estado;
    private String codigoPostal;

    @ManyToOne
    @JoinColumn(name="persona_id")
    private Persona persona;
}
