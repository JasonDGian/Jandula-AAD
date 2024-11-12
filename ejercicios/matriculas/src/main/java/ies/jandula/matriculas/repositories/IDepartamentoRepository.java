package ies.jandula.matriculas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ies.jandula.matriculas.entities.DepartamentoEntity;

public interface IDepartamentoRepository extends JpaRepository<DepartamentoEntity, String>
{

}
