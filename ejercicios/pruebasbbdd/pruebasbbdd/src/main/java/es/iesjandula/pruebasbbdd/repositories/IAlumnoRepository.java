package es.iesjandula.pruebasbbdd.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.pruebasbbdd.dto.AlumnoDto;
import es.iesjandula.pruebasbbdd.entities.Alumno;

@Repository
public interface IAlumnoRepository extends JpaRepository<Alumno, Long>
{

	public Alumno findByNif(String nif);

	public List<Alumno> findByNifContaining(String nif);

	public List<Alumno> findByAprobadoTrue();

	public List<Alumno> findByAprobadoFalse();

	public List<Alumno> findByNifAndEdad(String nif, Integer edad);

	// public List<Alumno> findByDireccionOrderByEdadAsc(String nif);

	// public List<Alumno> finByDireccionOrderByEdadDesc(String nif);

	// anotar que hace exactamente
	public List<Alumno> findTop10ByOrderByEdadAsc();

	// anotar que hace exactamente
	public List<Alumno> findFirst10ByOrderByEdadAsc();

	// Devolverá todas las apariciones distintas entre ellas de ese campo.
	// De aparecer dos nifs iguales uno de ellos no se mostrará.
	public List<Alumno> findDistinctByNif();

	// Controlar que pasa si pasamos antes un numero grande y luego un numero
	// pequeño.
	public List<Alumno> findByEdadBetween(Integer edadInicial, Integer edadFinal);

	// En este meteodo le pedimos que nos devuelva solo dos campos. Estos dos campos
	// están presentes en el DTO.
	// Respetando el orden de aparicion de los atributos en la clase podemos
	// automatizar la instanciacion del objeto desde este metodo.
	public AlumnoDto findNifAndEdadByDireccion(String direccion);

}
