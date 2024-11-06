package ies.jandula.matriculas.entities;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaId implements Serializable {
// Identificador de matricula, compuesto por los campos que a la vez componen los
// los identificadores de asignatura (id compuesto) y alumno (id simple)
    private static final long serialVersionUID = 1L;

    // Identificador asignatura (compuesto)
//    private int asignaturaCurso;
//    private String asignaturaEtapa;
//    private String asignaturaGrupo;
//    private String asignaturaNombre; 
    
    
    private AsignaturaId asignaturaId;
    
    // Identificador de alumno.
    private int alumnoId; 
}
