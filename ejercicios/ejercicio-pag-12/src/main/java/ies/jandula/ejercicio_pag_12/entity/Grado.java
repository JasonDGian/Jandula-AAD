package ies.jandula.ejercicio_pag_12.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Grado
{

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;
	
	private String nombre;
	
	@OneToMany( mappedBy = "grado" )
	private List<Asignatura> asignaturas;
	
}
