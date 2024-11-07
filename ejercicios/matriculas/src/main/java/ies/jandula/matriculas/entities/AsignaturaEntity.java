package ies.jandula.matriculas.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(AsignaturaId.class)
@Table( name = "asignatura")
public class AsignaturaEntity {
	
	@Id
	private Integer curso;
	
	@Id
	@Column(length = 50)
	private String etapa;
	
	@Id
	@Column(length = 2)
	private String grupo;
	
	@Id
	@Column(length = 100)
	private String nombre;

	@ManyToOne
	@JoinColumn( name = "departamento_proprietario")
	private DepartamentoEntity departamentoProprietario;
	
	@ManyToOne
	@JoinColumn ( name = "departamento_receptor")
	private DepartamentoEntity departamentoReceptor;
	
	@ManyToOne
	@JoinColumn ( name = "bloque_id" )
	private BloqueEntity bloqueId;
		
	@OneToMany( mappedBy = "asignaturaId")
	private List<MatriculaEntity> matricula;
	
}
