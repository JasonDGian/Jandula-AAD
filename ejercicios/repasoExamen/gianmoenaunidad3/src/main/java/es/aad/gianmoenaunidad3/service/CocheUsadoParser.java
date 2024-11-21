package es.aad.gianmoenaunidad3.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import es.aad.gianmoenaunidad3.models.CocheUsado;
import es.aad.gianmoenaunidad3.repository.ICocheUsadoRepository;

public class CocheUsadoParser implements ICocheUsadoParser
{
	
	@Autowired
	ICocheUsadoRepository cocheUsadoRepository;
	
	@Override
	public void parseaFichero(Scanner scanner)
	{
		
		// Saltamos a siguiente linea para evitar cabeceras.
		scanner.nextLine();
		
		// Mientras que el escáner tenga una siguiente linea.
		while( scanner.hasNextLine() ) {
			
			String[] campos = scanner.nextLine().split(",");
			
			CocheUsado cocheUsado = new CocheUsado();
			
			// campos coche
			cocheUsado.setMatricula(campos[0]);
			cocheUsado.setMarca(campos[1]);
			cocheUsado.setMarca(campos[2]);
			cocheUsado.setColor(campos[3]);
			
			// campos coche nuevo.
			cocheUsado.setKilometrosRecorridos( Integer.valueOf( campos[4] ) );
			
			cocheUsadoRepository.saveAndFlush(cocheUsado);
			
		}
	}

}
