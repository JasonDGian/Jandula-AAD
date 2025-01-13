package es.iesjandula.aadrepaso.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.iesjandula.aadrepaso.dto.EmpeladoTituloPuestoDto;
import es.iesjandula.aadrepaso.dto.EmpleadoComisionDepartamentoDto;
import es.iesjandula.aadrepaso.dto.EmpleadoContratacionSalarioDto;
import es.iesjandula.aadrepaso.dto.EmpleadoDepartamentoDTO;
import es.iesjandula.aadrepaso.dto.EmpleadoDepartamentoIDDTO;
import es.iesjandula.aadrepaso.dto.EmpleadoFechaContratoDeptoDto;
import es.iesjandula.aadrepaso.dto.EmpleadoGerenteDto;
import es.iesjandula.aadrepaso.dto.EmpleadoPaisDto;
import es.iesjandula.aadrepaso.dto.EmpleadoPuestoGerenteDto;
import es.iesjandula.aadrepaso.dto.EmpleadoSalarioDepartamentoDto;
import es.iesjandula.aadrepaso.dto.GerentesEmpleadosDto;
import es.iesjandula.aadrepaso.entities.Empleados;

@Repository
public interface IEmpleadosRepository extends JpaRepository<Empleados, Long>
{

	@Query("SELECT new es.iesjandula.aadrepaso.dto.EmpleadoDepartamentoDTO(em.nombre, d.nombreDepartamento) FROM Empleados em JOIN em.idDepartamento d")
	public Page<EmpleadoDepartamentoDTO> buscaEmpleadosYNombreDepartamentoPaginados(Pageable pageable);

	@Query("SELECT new es.iesjandula.aadrepaso.dto.EmpeladoTituloPuestoDto( em.nombre, em.apellido, pu.tituloPuesto ) "
			+ "FROM Empleados em " + "JOIN em.idPuesto pu")
	public Page<EmpeladoTituloPuestoDto> buscaEmpleadosConTituloPuesto(Pageable pageable);

	// Mostrar los nombres y apellidos de los empleados y sus respectivos gerentes
	// (nombre y apellidos).
	@Query("SELECT new es.iesjandula.aadrepaso.dto.EmpleadoGerenteDto( em.nombre, em.apellido, je.nombre, je.apellido ) "
			+ "FROM Empleados em " + "JOIN em.idGerente je")
	public Page<EmpleadoGerenteDto> buscaEmpleadoGerente(Pageable pageable);

	// Mostrar todos los empleados que pertenecen a un departamento específico.
	@Query("SELECT new es.iesjandula.aadrepaso.dto.EmpleadoDepartamentoIDDTO(em.nombre, dep.idDepartamento, dep.nombreDepartamento) "
			+ "FROM Empleados em " + "JOIN em.idDepartamento dep "
			+ "WHERE ( :idDepartamento IS NULL OR dep.idDepartamento = :idDepartamento) "
			+ "AND ( :nombreDepartamento IS NULL OR dep.nombreDepartamento LIKE CONCAT('%', :nombreDepartamento, '%'))")
	public Page<EmpleadoDepartamentoIDDTO> encuentraEmpleadosPorDepartamento(
			@Param("idDepartamento") String idDepartamento, @Param("nombreDepartamento") String nombreDepartamento,
			Pageable pageable);

	// Obtener los empleados cuyo salario es mayor a 5000 junto con el nombre del
	// departamento
	@Query("SELECT new es.iesjandula.aadrepaso.dto.EmpleadoSalarioDepartamentoDto( em.nombre, em.salario, dep.nombreDepartamento ) "
			+ "FROM Empleados em " + "JOIN em.idDepartamento dep " + "WHERE em.salario > 5000")
	public Page<EmpleadoSalarioDepartamentoDto> encuentraEmpleadoSalarioDepto(Pageable pageable);

	// Mostrar empleados con título "Programador" y el nombre de su gerente.
	@Query("SELECT new es.iesjandula.aadrepaso.dto.EmpleadoPuestoGerenteDto( em.nombre, em.apellido, pu.tituloPuesto, ge.nombre, ge.apellido ) "
			+ "FROM Empleados em " + "JOIN em.idGerente ge " + "JOIN em.idPuesto pu "
			+ "WHERE pu.tituloPuesto LIKE 'Programador'")
	public Page<EmpleadoPuestoGerenteDto> encuentraProgramadoresGerente(Pageable pageable);

	// Mostrar empleados contratados después del año 2000 con sus departamentos.
	@Query("SELECT new es.iesjandula.aadrepaso.dto.EmpleadoFechaContratoDeptoDto( em.nombre, em.apellido, his.fechaInicio, pu.tituloPuesto ) "
			+ "FROM HistorialPuestos his " + "JOIN his.idEmpleado em " + "JOIN his.idPuesto pu "
			+ "WHERE his.fechaInicio > CAST('1999-12-31' AS DATE)")
	public Page<EmpleadoFechaContratoDeptoDto> encuentraContratados2000(Pageable pageable);

	// Mostrar los empleados cuyo apellido termina en "son" y su puesto.
	@Query("SELECT new es.iesjandula.aadrepaso.dto.EmpeladoTituloPuestoDto( em.nombre, em.apellido, pu.tituloPuesto ) "
			+ "FROM Empleados em " + "JOIN em.idPuesto pu WHERE em.nombre LIKE '%son'")
	public Page<EmpeladoTituloPuestoDto> buscaEmpleadosSonConTituloPuesto(Pageable pageable);

	// Listar empleados que no tienen gerente asignado
	@Query("SELECT new es.iesjandula.aadrepaso.dto.EmpleadoGerenteDto( em.nombre, em.apellido, 'gerente nulo', 'gerente nulo' ) "
			+ "FROM Empleados em WHERE em.idGerente IS NULL")
	public Page<EmpleadoGerenteDto> buscaEmpleadoGerenteNulo(Pageable pageable);

	// Obtener empleados con comisiones asignadas y sus departamentos.
	@Query("SELECT new es.iesjandula.aadrepaso.dto.EmpleadoComisionDepartamentoDto( em.nombre, em.apellido, em.comisionPct, em.idDepartamento.nombreDepartamento ) "
			+ "FROM Empleados em "
			+ "WHERE em.comisionPct IS NOT NULL")
	public Page<EmpleadoComisionDepartamentoDto> buscaEmpleadosComisionAsignada(Pageable pageable);

	// Mostrar empleados del departamento "Ventas" que ganan más de 7000.
	@Query("SELECT new es.iesjandula.aadrepaso.dto.EmpleadoSalarioDepartamentoDto( em.nombre, em.salario, em.idDepartamento.nombreDepartamento ) "
			+ "FROM Empleados em " 
			+ "WHERE em.salario > 7000 "
			+ "AND LOWER(em.idDepartamento.nombreDepartamento) LIKE 'ventas' ")
	public Page<EmpleadoSalarioDepartamentoDto> buscaEmpleadosSalario7kVentas(Pageable pageable);

	// Listar empleados ordenados por salario descendente y nombre del departamento
	@Query("SELECT new es.iesjandula.aadrepaso.dto.EmpleadoSalarioDepartamentoDto( em.nombre, em.salario, em.idDepartamento.nombreDepartamento ) "
			+ "FROM Empleados em " 
			+ "ORDER BY em.salario DESC")
	public Page<EmpleadoSalarioDepartamentoDto> buscaEmpleadosSalarioDeptoOrd(Pageable pageable);

	// Mostrar empleados ordenados por fecha de contratación y salario.
	@Query("SELECT new es.iesjandula.aadrepaso.dto.EmpleadoContratacionSalarioDto( em.nombre, em.fechaContrato, em.salario) "
			+ "FROM Empleados em " + "ORDER BY em.fechaContrato ASC, em.salario ASC")
	public Page<EmpleadoContratacionSalarioDto> encuentraEmpleadosOrdenadoContratoSalario(Pageable pageable);

	// Mostrar empleados y su gerente ordenados por el apellido del gerente
	@Query("SELECT new es.iesjandula.aadrepaso.dto.EmpleadoGerenteDto( em.nombre, em.apellido, em.idGerente.nombre, em.idGerente.apellido ) "
			+ "FROM Empleados em " 
			+ "ORDER BY em.idGerente.apellido ASC")
	public Page<EmpleadoGerenteDto> buscaEmpleadoOrdenadoGerente(Pageable pageable);

	// Mostrar empleados del país "IT" ordenados por salario.
//	@Query("SELECT new es.iesjandula.aadrepaso.dto.EmpleadoPaisDto( em.nombre, em.apellido, pa.nombrePais, em.salario ) "
//			+ "FROM Empleados em " 
//			+ "JOIN em.idDepartamento dep "
//			+ "JOIN dep.idUbicacion ubi "
//			+ "JOIN ubi.idPais pa " 
//			+ "ORDER BY em.salario DESC")
	@Query("SELECT new es.iesjandula.aadrepaso.dto.EmpleadoPaisDto( em.nombre, em.apellido, em.idDepartamento.idUbicacion.idPais.nombrePais, em.salario ) "
			+ "FROM Empleados em " 
			+ "ORDER BY em.salario DESC")
	public Page<EmpleadoPaisDto> encuentraEmpleadosPaisOrdSalario(Pageable pageable);

	
	// Mostrar empleados sin gerente, ordenados alfabéticamente
	@Query("SELECT new es.iesjandula.aadrepaso.dto.EmpleadoGerenteDto( em.nombre, em.apellido, 'gerente nulo', 'gerente nulo' ) "
			+ "FROM Empleados em WHERE em.idGerente IS NULL ORDER BY em.apellido, em.nombre")
	public Page<EmpleadoGerenteDto> buscaEmpleadoGerenteNuloOrdenado(Pageable pageable);
	

	// ---------------------   CLICK MENTAL USO DE ORIENTACIÓN A OBJETOS. 
	// Listar empleados agrupados por gerente y contar cuantos empleados tiene cada uno.
	@Query("SELECT  new es.iesjandula.aadrepaso.dto.GerentesEmpleadosDto( em.idGerente.nombre, COUNT(em) ) "
			+ "FROM Empleados em "
			+ "GROUP BY em.idGerente")
	public Page<GerentesEmpleadosDto> recuentoEmpleadosPorGerente(Pageable pageable);
	
}
