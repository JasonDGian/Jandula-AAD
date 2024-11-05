package ies.jandula.matriculas.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(CursoEtapaId.class)
public class CursoEtapaEntity
{
	@Id
	private long curso;
	@Id
	private String etapa;
}
