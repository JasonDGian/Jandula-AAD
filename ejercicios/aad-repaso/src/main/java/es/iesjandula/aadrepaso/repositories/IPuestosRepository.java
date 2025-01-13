package es.iesjandula.aadrepaso.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.iesjandula.aadrepaso.dto.PuestoEmpleadosDto;
import es.iesjandula.aadrepaso.dto.PuestoSalarioDto;
import es.iesjandula.aadrepaso.entities.Puestos;

@Repository
public interface IPuestosRepository extends JpaRepository<Puestos, Long>
{
	
	// Listar los puestos con empleados asignados.
	@Query( "SELECT new es.iesjandula.aadrepaso.dto.PuestoEmpleadosDto( pu.idPuesto, pu.tituloPuesto, COUNT(em) ) "
			+ "FROM Empleados em "
			+ "JOIN em.idPuesto pu "			
			+ "GROUP BY pu.idPuesto, pu.tituloPuesto" )
	public Page<PuestoEmpleadosDto> encuentraRecuentoEmpleadosPorPuesto( Pageable pageable );
	
	
	//  Obtener puestos ordenados por salario máximo descendente.
	@Query( "SELECT new es.iesjandula.aadrepaso.dto.PuestoSalarioDto( pu.tituloPuesto, pu.salarioMax) "
			+ "FROM Puestos pu "		
			+ "ORDER BY pu.salarioMax ASC" )
	public Page<PuestoSalarioDto> encuentraPuestosOrdenaSalarioMax( Pageable pageable );
	
	// Mostrar la cantidad de empleados por título de puesto.
	// REPEDITO.
}
