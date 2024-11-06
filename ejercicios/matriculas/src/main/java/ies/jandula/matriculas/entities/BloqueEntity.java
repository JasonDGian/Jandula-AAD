package ies.jandula.matriculas.entities;

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
@Table( name = "bloque")
public class BloqueEntity
{	
	@Id
	@Column( length = 100)
	private String id;
	
	@OneToMany( mappedBy = "bloqueId")
	private List<AsignaturaEntity> asignatura;
}
