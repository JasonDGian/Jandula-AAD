package es.aad.gianmoenaunidad3.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.aad.gianmoenaunidad3.models.CocheNuevo;
import es.aad.gianmoenaunidad3.repository.ICocheNuevoRepository;

@Service
public class CocheNuevoParser implements ICocheNuevoParser
{

	@Autowired
	ICocheNuevoRepository cocheNuevoRepository;
	
	@Override
	public void parseaFichero(Scanner scanner)
	{
		
		// Saltamos a siguiente linea para evitar cabeceras.
		scanner.nextLine();
		
		// Mientras que el escáner tenga una siguiente linea.
		while( scanner.hasNextLine() ) {
			
			String[] campos = scanner.nextLine().split(",");
			
			CocheNuevo cocheNuevo = new CocheNuevo();
			
			// campos coche
			cocheNuevo.setMatricula(campos[0]);
			cocheNuevo.setMarca(campos[1]);
			cocheNuevo.setMarca(campos[2]);
			cocheNuevo.setColor(campos[3]);
			
			// campos coche nuevo.
			cocheNuevo.setExistencias(Integer.valueOf(campos[4]));
			
			cocheNuevoRepository.saveAndFlush(cocheNuevo);
			
		}
	}
	

}
