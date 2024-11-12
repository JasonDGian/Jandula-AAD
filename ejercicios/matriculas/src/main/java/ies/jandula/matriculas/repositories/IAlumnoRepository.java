package ies.jandula.matriculas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ies.jandula.matriculas.entities.AlumnoEntity;

public interface IAlumnoRepository extends JpaRepository<AlumnoEntity, Integer> {

}
