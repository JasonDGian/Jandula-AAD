package ies.jandula.ejercicio.entidades;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "proyecto")
public class ProyectoEntity
{
	@Id
	private String codigo;
	
	@Column( columnDefinition = "TEXT")
	private String descripcion;
	
	@OneToMany(mappedBy = "proyecto")
	private List<EmpleadoTrabajaProyecto> empleado;
		
}
