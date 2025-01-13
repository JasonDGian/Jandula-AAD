package es.iesjandula.aadrepaso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.iesjandula.aadrepaso.dto.DepartamentoDto;
import es.iesjandula.aadrepaso.dto.DepartamentoEmpleadosDto;
import es.iesjandula.aadrepaso.dto.DepartamentoSalarioDto;
import es.iesjandula.aadrepaso.dto.DepartamentoSumaSalariosDto;
import es.iesjandula.aadrepaso.dto.DepartamentoUbicacionDto;
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
import es.iesjandula.aadrepaso.dto.PaisPorRegionesDto;
import es.iesjandula.aadrepaso.dto.PaisRecuentoUbicacionDto;
import es.iesjandula.aadrepaso.dto.PaisYRegionDto;
import es.iesjandula.aadrepaso.dto.PuestoEmpleadosDto;
import es.iesjandula.aadrepaso.dto.PuestoSalarioDto;
import es.iesjandula.aadrepaso.dto.RegionesPaisDto;
import es.iesjandula.aadrepaso.dto.UbicacionCiudadPaisDto;
import es.iesjandula.aadrepaso.dto.UbicacionPaisDto;
import es.iesjandula.aadrepaso.repositories.IDepartamentosRepository;
import es.iesjandula.aadrepaso.repositories.IEmpleadosRepository;
import es.iesjandula.aadrepaso.repositories.IPaisRepository;
import es.iesjandula.aadrepaso.repositories.IPuestosRepository;
import es.iesjandula.aadrepaso.repositories.IRegionRepository;
import es.iesjandula.aadrepaso.repositories.IUbicacionRepository;

@RestController
public class Controller
{

	@Autowired
	IEmpleadosRepository iEmpleadosRepository;
	@Autowired
	IPaisRepository iPaisRepository;
	@Autowired
	IUbicacionRepository iUbicacionRepository;
	@Autowired
	IRegionRepository iRegionesRepository;
	@Autowired
	IDepartamentosRepository iDepartamentosRepository;
	@Autowired
	IPuestosRepository iPuestosRepository;
	

	// Obtener todos los empleados y sus respectivos nombres de departamento.
	@GetMapping(value = "/empleadoydepartamento")
	public Page<EmpleadoDepartamentoDTO> getEmpleadoDepartamento(@PageableDefault(size = 5, page = 0) Pageable pageable)
	{
		return this.iEmpleadosRepository.buscaEmpleadosYNombreDepartamentoPaginados(pageable);
	}

	// Listar los nombres de los países y su nombre de región asociado.
	@GetMapping(value = "/nombre-pais-region")
	public Page<PaisYRegionDto> getNombrePaisYRegion(@PageableDefault(size = 5, page = 0) Pageable pageable)
	{
		return this.iPaisRepository.encuentraPaisesYRegiones(pageable);
	}

	// Mostrar los nombres de las ubicaciones junto con las ciudades y países a las
	// que pertenecen
	@GetMapping(value = "/ubicaciones-pais")
	public Page<UbicacionCiudadPaisDto> getUbicacionCiudadPais(@PageableDefault(size = 5, page = 0) Pageable pageable)
	{

		return this.iUbicacionRepository.encuentraUbicacionesCiudadesPaises(pageable);

	}

	// Obtener los nombres y apellidos de los empleados junto con sus títulos de
	// puesto.
	@GetMapping(value = "/empleados-titulos-puestos")
	public Page<EmpeladoTituloPuestoDto> getEmpleadosTitulosPuestos(
			@PageableDefault(size = 5, page = 0) Pageable pageable)
	{
		return this.iEmpleadosRepository.buscaEmpleadosConTituloPuesto(pageable);
	}

	// Mostrar los nombres y apellidos de los empleados y sus respectivos gerentes
	// (nombre y apellidos).
	@GetMapping(value = "/empleados-gerentes")
	public Page<EmpleadoGerenteDto> getEmpleadosGerentes(@PageableDefault(size = 5, page = 0) Pageable pageable)
	{
		return this.iEmpleadosRepository.buscaEmpleadoGerente(pageable);
	}

	// Mostrar las regiones y la cantidad de países que pertenecen a cada región.
	@GetMapping(value = "/paises-por-region" )
	public Page<PaisPorRegionesDto> getPaisesPorRegion( @PageableDefault(size = 5, page = 0) Pageable pageable ){
		return this.iRegionesRepository.recuentoPaisesPorRegion(pageable);
	}
	
	
	// Obtener los departamentos con sus ubicaciones correspondientes.
	@GetMapping(value = "/ubicacion-departamentos" )
	public Page<DepartamentoUbicacionDto> getDepartamentoUbicacion( @PageableDefault(size = 5, page = 0) Pageable pageable ){
		return this.iDepartamentosRepository.encuentraDepartamentosUbicacion(pageable);
	}
	
	@GetMapping( value = "/busca-empleado-departamento")
	public Page<EmpleadoDepartamentoIDDTO> buscaEmpleadoPorDepartamento( 
			@RequestParam( name = "idDepartamento", required = false ) String idDepartamento, 
			@RequestParam( name = "nombreDepartamento", required = false) String nombreDepartamento, 
			@PageableDefault( size = 5 , page = 0 ) Pageable pageable  ){
		return this.iEmpleadosRepository.encuentraEmpleadosPorDepartamento(idDepartamento, nombreDepartamento, pageable);
	}
	
	//  Listar los puestos con empleados asignados.
	@GetMapping(value = "/puestos-con-empleados" )
	public Page<PuestoEmpleadosDto> getPuestosConEmpleados( @PageableDefault(size = 5, page = 0) Pageable pageable ){
		return this.iPuestosRepository.encuentraRecuentoEmpleadosPorPuesto(pageable);
	}
	

	//  Obtener los empleados cuyo salario es mayor a 5000 junto con el nombre del departamento.
	@GetMapping(value = "/encuentra-empleado-salario" )
	public Page<EmpleadoSalarioDepartamentoDto> encuentraEmpleadoSalarioDeptos( @PageableDefault(size = 5, page = 0) Pageable pageable ){
		return this.iEmpleadosRepository.encuentraEmpleadoSalarioDepto(pageable);
	}
	
	// Mostrar empleados con título "Programador" y el nombre de su gerente.
	@GetMapping(value = "/encuentra-programadores" )
	public Page<EmpleadoPuestoGerenteDto> encuentraProgramadores( @PageableDefault(size = 5, page = 0) Pageable pageable ){
		return this.iEmpleadosRepository.encuentraProgramadoresGerente(pageable);
	}
	
	// Obtener los países de la región "Europa".
	@GetMapping(value = "/encuentra-paises-europa" )
	public Page<PaisYRegionDto> encuentraPaisesEuropeos( @PageableDefault(size = 5, page = 0) Pageable pageable ){
		return this.iPaisRepository.encuentraPaisesEuropeos(pageable);
	}

	// Mostrar empleados contratados después del año 2000 con sus departamentos.
	@GetMapping(value = "/encuentra-contratados-tras-2000" )
	public Page<EmpleadoFechaContratoDeptoDto> encuentraContratadosPartir2000( @PageableDefault(size = 5, page = 0) Pageable pageable ){
		return this.iEmpleadosRepository.encuentraContratados2000(pageable);
	}
	
	@GetMapping(value = "/encuentra-ubicaciones-paises-inicial-c" )
	public Page<UbicacionPaisDto> encuentraUbicacionesPaisesInicialC( @PageableDefault(size = 5, page = 0) Pageable pageable ){
		return this.iUbicacionRepository.encuentraUbicacionesPaisesInicialC(pageable);
	}
	
	// Mostrar los empleados cuyo apellido termina en "son" y su puesto
	@GetMapping(value = "/encuentra-empleados-son" )
	public Page<EmpeladoTituloPuestoDto> buscaEmpleadosSonConTituloPuesto( @PageableDefault(size = 5, page = 0) Pageable pageable ){
		return this.iEmpleadosRepository.buscaEmpleadosSonConTituloPuesto(pageable);
	}
	 
	// Listar empleados que no tienen gerente asignado.
	@GetMapping(value = "/empleados-gerente-nulo")
	public Page<EmpleadoGerenteDto> getEmpleadosGerenteNulo(@PageableDefault(size = 5, page = 0) Pageable pageable)
	{
		return this.iEmpleadosRepository.buscaEmpleadoGerenteNulo(pageable);
	}

	// Mostrar departamentos que tienen empleados con salario mayor a 10000.
	@GetMapping(value = "/departamentos-salario-10000")
	public Page<DepartamentoDto> encuentraDepartamentosSalario10000(@PageableDefault(size = 5, page = 0) Pageable pageable)
	{
		return this.iDepartamentosRepository.encuentraDepartamentosSalario10000(pageable);
	}
	
	// Obtener empleados con comisiones asignadas y sus departamentos.
	@GetMapping(value = "/empleados-comision-asignada")
	public Page<EmpleadoComisionDepartamentoDto> buscaEmpleadosComisionAsignada(@PageableDefault(size = 5, page = 0) Pageable pageable)
	{
		return this.iEmpleadosRepository.buscaEmpleadosComisionAsignada(pageable);
	}
	
	// Mostrar empleados del departamento "Ventas" que ganan más de 7000
	@GetMapping(value = "/empleados-salario-ventas")
	public Page<EmpleadoSalarioDepartamentoDto> buscaEmpleadosSalario7kVentas(@PageableDefault(size = 5, page = 0) Pageable pageable)
	{
		return this.iEmpleadosRepository.buscaEmpleadosSalario7kVentas(pageable);
	}
	
	// Listar empleados ordenados por salario descendente y nombre del departamento
	@GetMapping(value = "/empleados-salario-departamento-ordenados")
	public Page<EmpleadoSalarioDepartamentoDto> buscaEmpleadosSalarioDeptoOrd(@PageableDefault(size = 5, page = 0) Pageable pageable)
	{
		return this.iEmpleadosRepository.buscaEmpleadosSalarioDeptoOrd(pageable);
	}
	
	// Mostrar países ordenados por el nombre de la región a la que pertenecen.
	@GetMapping(value = "/encuentra-paises-ordena-region" )
	public Page<PaisYRegionDto> encuentraPaisesOrdenaRegion( @PageableDefault(size = 5, page = 0) Pageable pageable ){
		return this.iPaisRepository.encuentraPaisesOrdenaRegion(pageable);
	}
	
	// Listar ubicaciones ordenadas por ciudad ascendente y nombre del país.
	@GetMapping(value = "/encuentra-ubicaciones-ordenado-ciudad-pais" )
	public Page<UbicacionPaisDto> encuentraUbicacionesOrdenadasCiudadNombrePais( @PageableDefault(size = 5, page = 0) Pageable pageable ){
		return this.iUbicacionRepository.encuentraUbicacionesOrdenadasCiudadNombrePais(pageable);
	}
	
	// Mostrar empleados ordenados por fecha de contratación y salario.
	@GetMapping(value = "/encuentra-empleados-ordena-contrato-salario" )
	public Page<EmpleadoContratacionSalarioDto> encuentraEmpleadosOrdenadoContratoSalario( @PageableDefault(size = 5, page = 0) Pageable pageable ){
		return this.iEmpleadosRepository.encuentraEmpleadosOrdenadoContratoSalario(pageable);
	}
	
	// Obtener puestos ordenados por salario máximo descendente
	@GetMapping(value = "/encuentra-puestos-ordena-salario-max" )
	public Page<PuestoSalarioDto> encuentraPuestosOrdenaSalarioMax( @PageableDefault(size = 5, page = 0) Pageable pageable ){
		return this.iPuestosRepository.encuentraPuestosOrdenaSalarioMax(pageable);
	}
	
	//  Mostrar empleados y su gerente ordenados por el apellido del gerente
	@GetMapping(value = "/empleados-ordenados-gerente")
	public Page<EmpleadoGerenteDto> buscaEmpleadoOrdenadoGerente(@PageableDefault(size = 5, page = 0) Pageable pageable)
	{
		return this.iEmpleadosRepository.buscaEmpleadoOrdenadoGerente(pageable);
	}
	
	
	// Listar los departamentos ordenados por la cantidad de empleados.
	@GetMapping(value = "/empleados-por-depto")
	public Page<DepartamentoEmpleadosDto> encuentraDeptosORdenaPorEmpleados(@PageableDefault(size = 5, page = 0) Pageable pageable)
	{
		return this.iDepartamentosRepository.encuentraDeptosORdenaPorEmpleados(pageable);
	}
	
	// Mostrar empleados del país "IT" ordenados por salario.
	@GetMapping(value = "/empleados-pais-ord-salario")
	public Page<EmpleadoPaisDto> encuentraEmpleadosPaisSalario(@PageableDefault(size = 5, page = 0) Pageable pageable)
	{
		return this.iEmpleadosRepository.encuentraEmpleadosPaisOrdSalario(pageable);
	}
	
	// . Obtener regiones ordenadas por cantidad de países asociados.
	@GetMapping(value = "/paises-por-region-ordenado" )
	public Page<RegionesPaisDto> recuentoPaisesPorRegionOrdenado( @PageableDefault(size = 5, page = 0) Pageable pageable ){
		return this.iRegionesRepository.recuentoPaisesPorRegionOrdenado(pageable);
	}
	
	// Mostrar empleados sin gerente, ordenados alfabéticamente
	@GetMapping(value = "/empleados-gerente-nulo-ordenado")
	public Page<EmpleadoGerenteDto> getEmpleadosGerenteNuloOrdenado(@PageableDefault(size = 5, page = 0) Pageable pageable)
	{
		return this.iEmpleadosRepository.buscaEmpleadoGerenteNuloOrdenado(pageable);
	}

	
	// Obtener la cantidad de empleados por departamento.
	// este está repetido del 27.
	@GetMapping(value = "/empleados-por-depto-2")
	public Page<DepartamentoEmpleadosDto> encuentraDeptosORdenaPorEmpleados2(@PageableDefault(size = 5, page = 0) Pageable pageable)
	{
		return this.iDepartamentosRepository.encuentraDeptosORdenaPorEmpleados(pageable);
	}
	
	// Mostrar el salario promedio de cada departamento
	@GetMapping(value = "/salario-medio-depto")
	public Page<DepartamentoSalarioDto> encuentraSalarioMedioDepartamentos(@PageableDefault(size = 5, page = 0) Pageable pageable)
	{
		return this.iDepartamentosRepository.encuentraSalarioMedioDepartamentos(pageable);
	}
	
	//  Listar los países y el número de ubicaciones en cada país.
	@GetMapping(value = "/ubicaciones-por-pais" )
	public Page<PaisRecuentoUbicacionDto> encuentraNumeroUbicacionesPais( @PageableDefault(size = 5, page = 0) Pageable pageable ){
		return this.iPaisRepository.encuentraNumeroUbicacionesPais(pageable);
	}
	
	//  Mostrar la cantidad de empleados por título de puesto
	@GetMapping(value = "/empleados-por-puesto")
	public Page<PuestoEmpleadosDto> getEmpleadosPorPuesto(@PageableDefault(size = 5, page = 0) Pageable pageable)
	{
		return this.iPuestosRepository.encuentraRecuentoEmpleadosPorPuesto(pageable);
	}
	
	// Listar regiones con el número total de países en cada una.
	@GetMapping(value = "/lista-regiones-paises" )
	public Page<RegionesPaisDto> listarRegionesConRecuentoPais( @PageableDefault(size = 5, page = 0) Pageable pageable ){
		return this.iRegionesRepository.listarRegionesConRecuentoPais(pageable);
	}
	
	// Obtener los nombres de departamentos con más de 5 empleados.
	@GetMapping( value  = "/lista-deptos-5+-empleados" )
	public Page<DepartamentoEmpleadosDto> encuentraDeptosCon5OMasEmpleados ( @PageableDefault( size = 5, page = 0 ) Pageable pageable ){
		return this.iDepartamentosRepository.encuentraDeptosCon5OMasEmpleados(pageable);
	}
	
	// Mostrar la suma de salarios por departamento.
	@GetMapping( value = "/suma-salarios-departamento" )
	public Page<DepartamentoSumaSalariosDto> encuentraSumaSalariosDeptos ( 
			@PageableDefault( size = 5, page = 0 ) Pageable pageable ){
		return this.iDepartamentosRepository.encuentraSumaSalariosDeptos(pageable);
	} 
	
	// Obtener la cantidad de empleados contratados en cada año.
	
	// Listar empleados agrupados por gerente y contar cuantos empleados tiene cada uno.
	@GetMapping( value = "/empleados-por-gerente" )
	public Page<GerentesEmpleadosDto> recuentoEmpleadosPorGerente ( 
			@PageableDefault( size = 5, page = 0 ) Pageable pageable ){
		return this.iEmpleadosRepository.recuentoEmpleadosPorGerente(pageable);
	} 
	
}
