package ies.jandula.ejercicio.entidades;

import java.util.List;

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
@Table(name = "departamentos")
public class DepartamentoEntity
{

	@Id
	private String codigo;
	
	private String nombre;
	
	@OneToMany(mappedBy = "departamento")
	private List<EmpleadoEntity> empleados;
	
}
