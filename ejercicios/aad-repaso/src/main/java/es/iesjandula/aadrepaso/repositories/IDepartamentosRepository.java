package es.iesjandula.aadrepaso.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.iesjandula.aadrepaso.dto.DepartamentoDto;
import es.iesjandula.aadrepaso.dto.DepartamentoEmpleadosDto;
import es.iesjandula.aadrepaso.dto.DepartamentoSalarioDto;
import es.iesjandula.aadrepaso.dto.DepartamentoSumaSalariosDto;
import es.iesjandula.aadrepaso.dto.DepartamentoUbicacionDto;
import es.iesjandula.aadrepaso.entities.Departamentos;

@Repository
public interface IDepartamentosRepository extends JpaRepository<Departamentos, Long>
{

	@Query( "SELECT new es.iesjandula.aadrepaso.dto.DepartamentoUbicacionDto( de.nombreDepartamento, ub.idUbicacion, ub.ciudad )"
			+ "FROM Departamentos de "
			+ "JOIN de.idUbicacion ub" )
	public Page <DepartamentoUbicacionDto> encuentraDepartamentosUbicacion( Pageable pageable );
	
	// Mostrar departamentos que tienen empleados con salario mayor a 10000.
	@Query(" SELECT new es.iesjandula.aadrepaso.dto.DepartamentoDto( dep.idDepartamento, dep.nombreDepartamento) "
			+ "FROM Empleados emp "
			+ "JOIN emp.idDepartamento dep "
			+ "WHERE emp.salario > 9999 "
			+ "GROUP BY dep.idDepartamento, dep.nombreDepartamento")
	public Page <DepartamentoDto> encuentraDepartamentosSalario10000( Pageable pageable );
	
	
	// Listar los departamentos ordenados por la cantidad de empleados
	@Query(" SELECT new es.iesjandula.aadrepaso.dto.DepartamentoEmpleadosDto( dep.nombreDepartamento, COUNT(emp) ) "
			+ "FROM Empleados emp "
			+ "JOIN emp.idDepartamento dep "
			+ "GROUP BY dep.nombreDepartamento")
	public Page <DepartamentoEmpleadosDto> encuentraDeptosORdenaPorEmpleados( Pageable pageable );
	
	
	// Mostrar el salario promedio de cada departamento
	@Query("SELECT new es.iesjandula.aadrepaso.dto.DepartamentoSalarioDto(dep.nombreDepartamento, AVG(em.salario) )"
			+ "FROM Empleados em "
			+ "JOIN em.idDepartamento dep "
			+ "GROUP BY dep.nombreDepartamento")
	public Page <DepartamentoSalarioDto> encuentraSalarioMedioDepartamentos( Pageable pageable );
	
	// Obtener los nombres de departamentos con más de 5 empleados.
	@Query(" SELECT new es.iesjandula.aadrepaso.dto.DepartamentoEmpleadosDto( dep.nombreDepartamento, COUNT(emp) ) "
			+ "FROM Empleados emp "
			+ "JOIN emp.idDepartamento dep "
			+ "GROUP BY dep.nombreDepartamento "
			+ "HAVING COUNT(emp) > 5")
	public Page <DepartamentoEmpleadosDto> encuentraDeptosCon5OMasEmpleados( Pageable pageable );
	
	
	// Mostrar la suma de salarios por departamento.
	@Query("SELECT new es.iesjandula.aadrepaso.dto.DepartamentoSumaSalariosDto( dep.nombreDepartamento, SUM(em.salario) ) "
			+ "FROM Empleados em "
			+ "JOIN em.idDepartamento dep "
			+ "GROUP BY dep.nombreDepartamento")
	public Page<DepartamentoSumaSalariosDto> encuentraSumaSalariosDeptos( Pageable pageable );
	
}
