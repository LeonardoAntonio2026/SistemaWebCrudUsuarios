package mx.edu.utez.proyecto1d.repository.persona;

import mx.edu.utez.proyecto1d.model.persona.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    boolean existsByCurp(String curp);

    boolean existsByCurpAndIdNot(String curp, Long id);
}
