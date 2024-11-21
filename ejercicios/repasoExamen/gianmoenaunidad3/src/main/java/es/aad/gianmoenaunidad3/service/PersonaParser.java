package es.aad.gianmoenaunidad3.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.aad.gianmoenaunidad3.models.Persona;
import es.aad.gianmoenaunidad3.repository.IPersonaRepository;

@Service
public class PersonaParser implements IPersonaParser
{

	@Autowired
	IPersonaRepository personaRepository;
	
	@Override
	public void parseaFichero(Scanner scanner)
	{
		scanner.nextLine();

		while(scanner.hasNextLine()) {
			
			Persona persona = new Persona();
			
			String[] campos = scanner.nextLine().split(",");
			
			persona.setDniNif(campos[0]);
			persona.setNombre(campos[1]);
			persona.setApellidos(campos[2]);
			
			personaRepository.saveAndFlush(persona);	
						
		}
	}
	

}
