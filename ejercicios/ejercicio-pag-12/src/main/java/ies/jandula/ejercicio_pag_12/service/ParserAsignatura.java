package ies.jandula.ejercicio_pag_12.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.jandula.ejercicio_pag_12.entity.Asignatura;
import ies.jandula.ejercicio_pag_12.entity.Grado;
import ies.jandula.ejercicio_pag_12.entity.Profesor;
import ies.jandula.ejercicio_pag_12.repositories.IAsignaturaRepository;
import ies.jandula.ejercicio_pag_12.repositories.IGradoRepository;
import ies.jandula.ejercicio_pag_12.repositories.IProfesorRepository;
import ies.jandula.ejercicio_pag_12.utils.Constants;
import ies.jandula.ejercicio_pag_12.utils.UniversidadServerError;

@Service
public class ParserAsignatura implements IParserAsignatura
{
	
	@Autowired
	private IAsignaturaRepository asignaturaRepo;
	
	@Autowired
	private IProfesorRepository profesorRepo;
	
	@Autowired 
	IGradoRepository gradoRepo;
	
	@Override
	public void parseaFichero(Scanner scanner) throws UniversidadServerError
	{
		// Saltamos linea de cabeceras.
		scanner.hasNextLine();
		
		// Mientras que el fichero tenga una linea disponible.
		while ( scanner.hasNextLine() ) {
			
			String[] campos = scanner.nextLine().split(Constants.DELIMITADOR_CSV);
			
			// Generamos una nueva instancia donde guardar la información sacada del CSV.
			Asignatura asignatura= new Asignatura();
			
			// A continuación, y prestando atención a la estructura del fichero, 
			// recuperamos los campos y los asignamos al atributo corerspondiente..
			asignatura.setId( Long.valueOf( campos[0] ) );
			asignatura.setNombre( campos[1]);
			asignatura.setCreditos( Double.valueOf( campos[2] ));
			asignatura.setTipo(campos[3]);
			asignatura.setCurso( Integer.valueOf( campos[4] ));
			asignatura.setCuatrimestre( Integer.valueOf( campos[5]));
			
			// Recuperamos la entidad mediante su ID.
			Optional<Profesor> profesor = profesorRepo.findById( Long.valueOf(campos[6]) );
			
			// Asignamos el profesor si existe.
			if ( profesor.isPresent() ) {
			asignatura.setProfesor( profesor.get());
			}
			
			Optional<Grado> grado =  gradoRepo.findById( Long.valueOf(campos[7]) );
			if ( grado.isPresent() ) {
			asignatura.setGrado( grado.get());
			}
			
			// Guardamos la entidad en bases de datos.
			this.asignaturaRepo.saveAndFlush(asignatura);
		}
	}

}
