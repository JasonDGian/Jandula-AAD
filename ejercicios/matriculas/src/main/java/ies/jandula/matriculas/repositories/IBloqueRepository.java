package ies.jandula.matriculas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ies.jandula.matriculas.entities.BloqueEntity;

public interface IBloqueRepository extends JpaRepository<BloqueEntity, String>
{

}
