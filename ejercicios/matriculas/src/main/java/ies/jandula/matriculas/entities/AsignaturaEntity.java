package ies.jandula.matriculas.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(AsignaturaId.class)
public class AsignaturaEntity {
	
	@Id
	private int id;
	
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
	
}
