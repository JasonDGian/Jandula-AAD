package ies.jandula.ejercicio_pag_12.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.jandula.ejercicio_pag_12.entity.Curso;
import ies.jandula.ejercicio_pag_12.repositories.ICursoRepository;
import ies.jandula.ejercicio_pag_12.utils.Constants;
import ies.jandula.ejercicio_pag_12.utils.UniversidadServerError;

@Service
public class ParserCurso implements IParserCurso
{
	@Autowired
	ICursoRepository cursoRepo;

	@Override
	public void parseaFichero(Scanner scanner) throws UniversidadServerError
	{
		// Saltamos linea de cabeceras.
		scanner.nextLine();
		
		// Mientras que el fichero tenga una linea disponible.
		while ( scanner.hasNextLine() ) {
			
			String[] campos = scanner.nextLine().split(Constants.DELIMITADOR_CSV);
			
			// Generamos una nueva instancia donde guardar la información sacada del CSV.
			Curso curso = new Curso();
			
			// A continuación, y prestando atención a la estructura del fichero, 
			// recuperamos los campos y los asignamos al atributo corerspondiente..
			curso.setId( Long.valueOf( campos[0]));
			curso.setAny_inicio( Integer.valueOf( campos[1] ) );  
			curso.setAnyo_fin( Integer.valueOf ( campos[2] ) );
			
			// Guardamos la entidad en bases de datos.
			this.cursoRepo.saveAndFlush(curso);
		}
	}
}
