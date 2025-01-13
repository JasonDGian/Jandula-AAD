package es.iesjandula.aadrepaso.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Puestos
{
	 
	@Id
	@Column( name = "id_puesto", length = 10)
	private String idPuesto;
	
	@Column( name = "titulo_puesto", length = 50, nullable = false)
	private String tituloPuesto;
	
	@Column( name = "salario_min", precision = 6, scale = 0)
	private Long salarioMin;
	
	@Column( name = "salario_max", precision = 6, scale = 0 )
	private Long salarioMax;
	
	// Reflejo relacion con Empleado.
	@OneToMany( mappedBy = "idPuesto" )
	private List<Empleados> empleados;
	
	// Reflejo relacion con Historial Puestos
	@OneToMany( mappedBy = "idPuesto")
	private List<HistorialPuestos> historialPuestos;

}
