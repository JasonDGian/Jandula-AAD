package ies.jandula.ejercicio_pag_12.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ies.jandula.ejercicio_pag_12.entity.Curso;

public interface ICursoRepository extends JpaRepository<Curso, Long>
{

}