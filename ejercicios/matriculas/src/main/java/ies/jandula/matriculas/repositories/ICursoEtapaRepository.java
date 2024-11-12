package ies.jandula.matriculas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ies.jandula.matriculas.entities.CursoEtapaEntity;
import ies.jandula.matriculas.entities.CursoEtapaId;

public interface ICursoEtapaRepository extends JpaRepository< CursoEtapaEntity, CursoEtapaId >
{

}
