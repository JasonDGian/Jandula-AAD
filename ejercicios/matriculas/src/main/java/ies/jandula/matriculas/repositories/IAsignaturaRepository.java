package ies.jandula.matriculas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ies.jandula.matriculas.entities.AsignaturaEntity;
import ies.jandula.matriculas.entities.AsignaturaId;

public interface IAsignaturaRepository extends JpaRepository<AsignaturaEntity,AsignaturaId>
{

}
