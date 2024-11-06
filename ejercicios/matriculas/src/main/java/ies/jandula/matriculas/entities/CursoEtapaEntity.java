package ies.jandula.matriculas.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(CursoEtapaId.class)
@Table(name = "curso_etapa")
public class CursoEtapaEntity
{
	@Id
	private int curso;
	@Id
	private String etapa;
}
