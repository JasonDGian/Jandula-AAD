package ies.jandula.ejercicio_pag_12.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.jandula.ejercicio_pag_12.entity.Departamento;
import ies.jandula.ejercicio_pag_12.entity.Profesor;
import ies.jandula.ejercicio_pag_12.repositories.IDepartamentoRepository;
import ies.jandula.ejercicio_pag_12.repositories.IProfesorRepository;
import ies.jandula.ejercicio_pag_12.utils.Constants;
import ies.jandula.ejercicio_pag_12.utils.UniversidadServerError;

@Service
public class ParserProfesor implements IParserProfesor
{

	@Autowired
	IProfesorRepository profeRepo;
	
	@Autowired
	IDepartamentoRepository deptoRepo;
	
	@Override
	public void parseaFichero(Scanner scanner) throws UniversidadServerError
	{
		
		scanner.nextLine();
		
		while ( scanner.hasNextLine() ) 
		{
			
			String[] campos = scanner.nextLine().split( Constants.DELIMITADOR_CSV);
			
			Profesor profe = new Profesor();
			
			profe.setId(  Long.valueOf(campos[0]) );
			profe.setNif( campos[1]);
			profe.setNombre(campos[2]);
			profe.setApellido1( campos[3]);
			profe.setApellido2( campos[4]);
			profe.setCiudad(campos[5]);
			profe.setDireccion(  campos[6]);
			profe.setTelefono( campos[7] );
			profe.setFechaNacimiento( LocalDate.parse( campos[8] , DateTimeFormatter.ofPattern("dd/MM/yyyy")) );
			profe.setSexo( campos[9]);
			
			Optional<Departamento> depto = this.deptoRepo.findById( Long.valueOf( campos[10] ));
			if (depto.isPresent()) {
				profe.setDepartamento( depto.get());
			}
			
			this.profeRepo.saveAndFlush( profe );
			
		}
		
		
	}

}
