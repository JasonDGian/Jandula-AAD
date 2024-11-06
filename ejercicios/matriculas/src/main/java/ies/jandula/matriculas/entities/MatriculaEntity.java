package ies.jandula.matriculas.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(MatriculaId.class)
public class MatriculaEntity
{

	/*
	 * Este trozo de código, apunta a la entidad entera, porque el manytoone, solo
	 * se puede usar par apuntar a entidades copletas, no a campos singulares.
	 */
	@Id  // Establece este campo, como clave de la entidad Matricula.
	@ManyToOne // Relaciona este campo, con la entidad asignatura.
	// "Se trae" los campos especificados para montar el ID de asignatura (id compuesto)
	@JoinColumns(
	{ @JoinColumn(name = "asignatura_curso", referencedColumnName = "curso"),
			@JoinColumn(name = "asignatura_etapa", referencedColumnName = "etapa"),
			@JoinColumn(name = "asignatura_grupo", referencedColumnName = "grupo"),
			@JoinColumn(name = "asignatura_nombre", referencedColumnName = "nombre") })
	private AsignaturaEntity asignaturaId;

	// referencia simple a entidad alumno.
	@Id
	@ManyToOne
	@JoinColumn(name = "alumno_id", referencedColumnName = "id")
	private AlumnoEntity alumnoId;

}

