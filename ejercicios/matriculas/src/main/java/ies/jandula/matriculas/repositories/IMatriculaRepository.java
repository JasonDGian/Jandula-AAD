package ies.jandula.matriculas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ies.jandula.matriculas.entities.MatriculaEntity;
import ies.jandula.matriculas.entities.MatriculaId;

public interface IMatriculaRepository extends JpaRepository<MatriculaEntity, MatriculaId>
{

}
