package mx.edu.utez.proyecto1d.model.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.proyecto1d.model.persona.Persona;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    private String password;

    private boolean isEnable;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Transient
    private String atributoNoColumna;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20)")
    private Roles rol;

    @OneToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;
}