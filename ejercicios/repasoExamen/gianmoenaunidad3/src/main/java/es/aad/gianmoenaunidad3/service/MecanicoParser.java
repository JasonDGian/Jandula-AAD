package es.aad.gianmoenaunidad3.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.aad.gianmoenaunidad3.models.Mecanico;
import es.aad.gianmoenaunidad3.repository.IMecanicoRepository;

@Service
public class MecanicoParser implements IMecanicoParser
{

	@Autowired
	IMecanicoRepository mecanicoRepository;
	
	@Override
	public void parseaFichero(Scanner scanner)
	{
		// Ignorar la primera linea de cabeceras.
		scanner.nextLine();
		
		// Mientras que el fichero tenga una linea siguiente.
		while ( scanner.hasNextLine() ) {
			
			// Instanciamos objeto.
			Mecanico mecanico = new Mecanico();
			
			// Separamos en campos.
			String[] campos = scanner.nextLine().split(",");
			
			mecanico.setDniNif(campos[0]);
			mecanico.setNombre(campos[1]);
			mecanico.setApellidos(campos[2]);
			
			// Bloque de parseo fecha.
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate fechaContrato = LocalDate.parse( campos[3], formatter);
			
			mecanico.setFechaContratacion(fechaContrato);
			
			mecanico.setSalario(Integer.valueOf(campos[4]));
			
			mecanicoRepository.saveAndFlush(mecanico);
			
		}
	}

}
