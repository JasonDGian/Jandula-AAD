package ies.jandula.ejercicio_pag_12.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.jandula.ejercicio_pag_12.entity.Alumno;
import ies.jandula.ejercicio_pag_12.repositories.IAlumnoRepository;
import ies.jandula.ejercicio_pag_12.utils.Constants;
import ies.jandula.ejercicio_pag_12.utils.UniversidadServerError;

@Service
public class ParserAlumno implements IParserAlumno
{
	
	
	
	@Autowired
	private IAlumnoRepository alumnoRepo;
	
	@Override
	public void parseaFichero ( Scanner scanner ) throws UniversidadServerError{
		
		// Saltamos linea de cabeceras.
		scanner.nextLine();
		
		// Mientras que el fichero tenga una linea disponible.
		while ( scanner.hasNextLine() ) {
			
			String[] campos = scanner.nextLine().split(Constants.DELIMITADOR_CSV);
			System.out.print( campos.toString() );
			
			// Generamos una nueva instancia donde guardar la información sacada del CSV.
			Alumno alumno = new Alumno();
			
			// A continuación, y prestando atención a la estructura del fichero, 
			// recuperamos los campos y los asignamos al atributo corerspondiente..
			alumno.setId( Long.valueOf( campos[0] ) );
			alumno.setNif( campos[1] );
			alumno.setNombre( campos[2]);
			alumno.setApellido1( campos[3] );
			alumno.setApellido2( campos[4]);
			alumno.setCiudad( campos[5]);
			alumno.setDireccion( campos[6] );
			alumno.setTelefono( campos[7] );
			alumno.setFechaNacimiento( LocalDate.parse(campos[8], DateTimeFormatter.ofPattern("dd/MM/yyyy")  ) );
			alumno.setSexo( campos[9] );
			
			// Guardamos la entidad en bases de datos.
			this.alumnoRepo.saveAndFlush(alumno);
		}
	} 
	
	
}
