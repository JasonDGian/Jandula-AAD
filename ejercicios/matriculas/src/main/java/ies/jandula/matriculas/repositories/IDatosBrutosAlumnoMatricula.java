package ies.jandula.matriculas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ies.jandula.matriculas.entities.DatosBrutosAlumnoMatriculaEntity;

public interface IDatosBrutosAlumnoMatricula extends JpaRepository<DatosBrutosAlumnoMatriculaEntity, Integer>
{

}
