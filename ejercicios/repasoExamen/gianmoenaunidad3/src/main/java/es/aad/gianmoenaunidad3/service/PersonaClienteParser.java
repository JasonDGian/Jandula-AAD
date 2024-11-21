
package es.aad.gianmoenaunidad3.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.aad.gianmoenaunidad3.models.PersonaCliente;
import es.aad.gianmoenaunidad3.repository.IPersonaClienteRepository;

@Service
public class PersonaClienteParser implements IPersonaClienteParser
{
	
	@Autowired
	IPersonaClienteRepository personaClienteRepository;

	@Override
	public void parseaFichero(Scanner scanner)
	{
		scanner.nextLine();
		
		while(scanner.hasNextLine()) {
			
			PersonaCliente cliente = new PersonaCliente();
			
			String[] campos = scanner.nextLine().split(",");
			
			cliente.setDniNif(campos[0]);
			cliente.setNombre(campos[1]);
			cliente.setApellidos(campos[2]);
			cliente.setDireccion(campos[3]);
			cliente.setTelefono(campos[4]);
			
			personaClienteRepository.saveAndFlush(cliente);
			
		}
		
	}

}
