package es.iesjandula.aadrepaso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import es.iesjandula.aadrepaso.dto.EmpleadoDepartamentoDTO;
import es.iesjandula.aadrepaso.repositories.IEmpleadosRepository;

@Service
public class Impresora
{
	
	@Autowired
	IEmpleadosRepository iEmpleRepo;
	
	@Component
	public class StartupTask {

	    @EventListener(ApplicationReadyEvent.class)
	    public void onApplicationReady() {
	    	
	    	int tamanoPagina = 5;
	    	int numeroPagina = 0;
	    	Pageable pageable = PageRequest.of(numeroPagina, tamanoPagina);
	    	
	        System.out.println("------------------- IMPRESORA -------------------");

	        
	        // Obtener todos los empleados y sus respectivos nombres de departamento.
	        Page<EmpleadoDepartamentoDTO> pagina = iEmpleRepo.buscaEmpleadosYNombreDepartamentoPaginados(pageable );
	        
	        if (pagina.hasContent()) {
	            pagina.forEach(x -> System.out.println(x.toString()));
	        } else {
	            System.out.println("No se encontraron resultados para la página solicitada.");
	        }
	        
	        // Listar los nombres de los países y su nombre de región asociado.
	        
	    }
	}

}
