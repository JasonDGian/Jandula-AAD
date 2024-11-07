package ies.jandula.matriculas.entities;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaId implements Serializable {
	
    private static final long serialVersionUID = 1L;  
    
    private AsignaturaId asignaturaId;
    
    // Identificador de alumno.
    private int alumnoId; 
}
