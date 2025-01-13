package es.iesjandula.aadrepaso.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Departamentos
{

	@Id
	@Column( name = "id_departamento", precision = 4, scale = 0)
	private Long idDepartamento;
	
	@Column( name = "nombre_departamento", length = 30, nullable = false)
	private String nombreDepartamento;
	
	// Este campo me chirria.
	@Column( name = "id_gerente" )
	private Long idGerente;
	
	@JoinColumn( name = "id_ubicacion")
	@ManyToOne
	private Ubicaciones idUbicacion;
	
	@OneToMany( mappedBy = "idDepartamento" )
	private List<HistorialPuestos> historialpuestos;
	
	@OneToMany( mappedBy = "idDepartamento" )
	private List<Empleados> empleados;
	
}
