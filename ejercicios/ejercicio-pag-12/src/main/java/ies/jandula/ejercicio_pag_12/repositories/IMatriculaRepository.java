package ies.jandula.ejercicio_pag_12.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ies.jandula.ejercicio_pag_12.entity.Alumno;
import ies.jandula.ejercicio_pag_12.entity.Asignatura;
import ies.jandula.ejercicio_pag_12.entity.Curso;
import ies.jandula.ejercicio_pag_12.entity.Matricula;
import ies.jandula.ejercicio_pag_12.entity.MatriculaID;

public interface IMatriculaRepository extends JpaRepository<Matricula, MatriculaID>
{
	
	
	public default Optional<Matricula> findByCompositeId( Alumno alumno, Asignatura asignatura, Curso curso ) {
		return findById ( new MatriculaID( alumno, asignatura, curso ) ) ;
	}
	
	public default boolean existsByCompositeId( Alumno alumno, Asignatura asignatura, Curso curso ) {
		Optional<Matricula> matricula = findByCompositeId(alumno , asignatura, curso);
		return matricula.isPresent();
	}
}

