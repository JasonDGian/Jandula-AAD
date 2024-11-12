package ies.jandula.ejercicio_pag_12.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import ies.jandula.ejercicio_pag_12.entity.Alumno;
import ies.jandula.ejercicio_pag_12.entity.Asignatura;
import ies.jandula.ejercicio_pag_12.entity.Curso;
import ies.jandula.ejercicio_pag_12.entity.Matricula;
import ies.jandula.ejercicio_pag_12.repositories.IAlumnoRepository;
import ies.jandula.ejercicio_pag_12.repositories.IAsignaturaRepository;
import ies.jandula.ejercicio_pag_12.repositories.ICursoRepository;
import ies.jandula.ejercicio_pag_12.repositories.IMatriculaRepository;
import ies.jandula.ejercicio_pag_12.utils.Constants;
import ies.jandula.ejercicio_pag_12.utils.UniversidadServerError;

public class ParserMatricula implements IParserMatricula
{
	@Autowired
	IMatriculaRepository matriculaRepo;
	
	@Autowired
	IAlumnoRepository alumnoRepo;
	
	@Autowired
	IAsignaturaRepository asignaturaRepo;
	
	@Autowired
	ICursoRepository cursoRepo;

	@Override
	public void parseaFichero(Scanner scanner) throws UniversidadServerError
	{
		
		//Ignoramos cabeceras
		scanner.nextLine();
		
		while ( scanner.hasNextLine() ) {
			
			String[] campos = scanner.nextLine().split(Constants.DELIMITADOR_CSV);
			
			Matricula matricula = new Matricula();
			
			// si el alumno existe lo guardamos.
			Optional<Alumno> alumno = this.alumnoRepo.findById( Long.valueOf( campos[0]));
			if ( alumno.isPresent() ) {
				matricula.setAlumno(alumno.get());
			}
			
			// si la asignatura existe la guardamos.
			Optional<Asignatura> asignatura = this.asignaturaRepo.findById( Long.valueOf(campos[1]) );
			if ( asignatura.isPresent() ) {
				matricula.setAsignatura(asignatura.get());
			}
			
			// si la asignatura existe la guardamos.
			Optional<Curso> curso = this.cursoRepo.findById( Long.valueOf(campos[2]) );
			if ( curso.isPresent() ) {
				matricula.setCurso(curso.get());
			}

			this.matriculaRepo.saveAndFlush(matricula);
			
		}
		
	}

}
