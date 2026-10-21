package mx.edu.utez.proyecto1d.model.persona_curso;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import mx.edu.utez.proyecto1d.model.cursos.Curso;
import mx.edu.utez.proyecto1d.model.persona.Persona;

import java.time.LocalDate;

@Entity
@Table(name = "personas_cursos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaIngreso;
    private double calificacion;

    @ManyToOne
    @JoinColumn(name="persona_id")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name="curso_id")
    private Curso curso;
}
