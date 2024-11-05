package ies.jandula.matriculas.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartamentoEntity {
	
	@Id
	@Column(length = 100)
	private String nombre;
	
	@OneToMany(mappedBy = "departamentoProprietario")
	private List<AsignaturaEntity> asignaturaPrestada;
	
	@OneToMany(mappedBy = "departamentoReceptor")
	private List<AsignaturaEntity> asignaturaRecibida;

}
