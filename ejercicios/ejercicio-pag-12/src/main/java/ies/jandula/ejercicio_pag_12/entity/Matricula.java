package ies.jandula.ejercicio_pag_12.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(MatriculaID.class)
public class Matricula
{
	@Id
	@ManyToOne
	@JoinColumn( name = "id_alumno")
	private Alumno alumno;
	@Id
	@ManyToOne
	@JoinColumn( name = "id_asignatura")
	private Asignatura asignatura;
	@Id
	@ManyToOne
	@JoinColumn( name = "id_curso")
	private Curso curso;
	
	
}
