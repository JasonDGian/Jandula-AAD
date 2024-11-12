package ies.jandula.ejercicio_pag_12.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import ies.jandula.ejercicio_pag_12.entity.Departamento;
import ies.jandula.ejercicio_pag_12.repositories.IDepartamentoRepository;
import ies.jandula.ejercicio_pag_12.utils.Constants;
import ies.jandula.ejercicio_pag_12.utils.UniversidadServerError;

public class ParserDepartamento implements IParserDepartamento
{
	
	@Autowired
	IDepartamentoRepository departamentoRepo;

	@Override
	public void parseaFichero(Scanner scanner) throws UniversidadServerError
	{
		
		// PAsamos a la siguiente linea para ignorar las cabeceras.
		scanner.nextLine();
		
		while( scanner.hasNextLine() ) {
			
			String[] campos = scanner.nextLine().split(Constants.DELIMITADOR_CSV);
					
			Departamento depart = new Departamento();
			
			depart.setId( Long.valueOf(campos[0]));
			depart.setNombre(campos[1]);
			
			this.departamentoRepo.saveAndFlush(depart);		
			
		}
		
	}

}
