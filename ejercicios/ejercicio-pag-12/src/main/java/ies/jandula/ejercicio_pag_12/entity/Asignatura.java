package ies.jandula.ejercicio_pag_12.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Asignatura
{

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;
	
	private double creditos;
	
	private int cuatrimestre;
	
	private int curso;
	
	private String nombre;
	
	private String tipo;
	
	@ManyToOne
	@JoinColumn( name = "id_grado" ) // FK en la tabla asignatura.
	private Grado grado;
	
	@ManyToOne
	@JoinColumn( name = "id_profesor") // FK en la tabla asignatura.
	private Profesor profesor;

	@OneToMany(mappedBy = "asignatura")
	private List <Matricula> matricula;
	
}
