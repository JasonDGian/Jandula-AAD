package es.iesjandula.aadrepaso.entities;

import java.util.Date;

import es.iesjandula.aadrepaso.entities.ids.HistorialPuestosId;
import jakarta.persistence.Column;
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
@IdClass( HistorialPuestosId.class)
public class HistorialPuestos
{

	@Id
	@JoinColumn( name = "id_empleado")
	@ManyToOne
	private Empleados idEmpleado;
	
	@Id
	@Column( name = "fecha_inicio" )
	private Date fechaInicio;
	
	@Column( name = "fecha_fin", nullable = false)
	private Date fechaFin;
	
	@JoinColumn ( name = "id_puesto")
	@ManyToOne
	private Puestos idPuesto;
	
	@JoinColumn( name = "id_departamento")
	@ManyToOne
	private Departamentos idDepartamento;
	
}
