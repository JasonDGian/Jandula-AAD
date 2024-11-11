package ies.jandula.ejercicio.entidades;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "empleados")
public class EmpleadoEntity
{
	@Id
	private String nif;
	
	@Column(nullable = false)
	private String nombre;
	
	@Column(nullable = false)
	private String apellido1;
	
	@Column(nullable = true)
	private String apellido2;
	
	private Date fechaNacimiento;
	
	private String telefono;
	
	private String titulacion;
	
	@ManyToOne
	private DepartamentoEntity departamento;
	
	@OneToMany(mappedBy = "empleado")
	private List<EmpleadoTrabajaProyecto> proyecto;
	
	
}
