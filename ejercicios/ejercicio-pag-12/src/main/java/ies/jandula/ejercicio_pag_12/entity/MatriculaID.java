package ies.jandula.ejercicio_pag_12.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaID
{
	
	private Alumno alumno;

	private Asignatura asignatura;

	private Curso curso;

}
