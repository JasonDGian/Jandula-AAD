package ies.jandula.ejercicio_pag_12.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.jandula.ejercicio_pag_12.entity.Grado;
import ies.jandula.ejercicio_pag_12.repositories.IGradoRepository;
import ies.jandula.ejercicio_pag_12.utils.Constants;
import ies.jandula.ejercicio_pag_12.utils.UniversidadServerError;

@Service
public class ParserGrado implements IParserGrado
{
	
	@Autowired
	IGradoRepository gradoRepo;

	@Override
	public void parseaFichero(Scanner scanner) throws UniversidadServerError
	{
		// Ignoramos cabecera.
		scanner.nextLine();
		
		while( scanner.hasNextLine() ) {
			
			String[] campos = scanner.nextLine().split(Constants.DELIMITADOR_CSV);			
		
			Grado grado = new Grado();
			
			grado.setId( Long.valueOf( campos[0]) );
			grado.setNombre( campos[1]);
			
			this.gradoRepo.saveAndFlush(grado);
		
		}
		
	}

}
