package ies.jandula.ejercicio_pag_12.entity;

import java.util.Date;
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
public class Alumno
{
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	private String apellido1;
	private String apellido2;
	private String ciudad;
	private String direccion;
	private Date fechaNacimiento;
	private String nif;
	private String nombre;
	private String sexo;
	private String telefono;
	
	@OneToMany(mappedBy = "alumno")
	private List <Matricula> matricula;
	
}
