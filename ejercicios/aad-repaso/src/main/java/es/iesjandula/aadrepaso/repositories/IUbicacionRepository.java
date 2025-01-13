package es.iesjandula.aadrepaso.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.iesjandula.aadrepaso.dto.PaisYRegionDto;
import es.iesjandula.aadrepaso.dto.UbicacionCiudadPaisDto;
import es.iesjandula.aadrepaso.dto.UbicacionPaisDto;
import es.iesjandula.aadrepaso.entities.Ubicaciones;

@Repository
public interface IUbicacionRepository extends JpaRepository<Ubicaciones, Long >
{
	
	// Mostrar los nombres de las ubicaciones junto con las ciudades y países a las que pertenecen.
	@Query ("SELECT new es.iesjandula.aadrepaso.dto.UbicacionCiudadPaisDto( u.idUbicacion, u.ciudad, p.nombrePais ) "
			+ "FROM Ubicaciones u "
			+ "JOIN u.idPais p")
	public Page<UbicacionCiudadPaisDto> encuentraUbicacionesCiudadesPaises( Pageable pageable );
	
	// Obtener ubicaciones en países que empiezan con la letra "C".
	@Query( " SELECT new es.iesjandula.aadrepaso.dto.UbicacionPaisDto( u.idUbicacion, u.direccion, u.ciudad, p.nombrePais ) "
			+ "FROM Ubicaciones u JOIN u.idPais p "
			+ "WHERE p.nombrePais LIKE 'C%' ")
	public Page<UbicacionPaisDto> encuentraUbicacionesPaisesInicialC( Pageable pageable );
	
	//  Listar ubicaciones ordenadas por ciudad ascendente y nombre del país.
	@Query( " SELECT new es.iesjandula.aadrepaso.dto.UbicacionPaisDto( u.idUbicacion, u.direccion, u.ciudad, p.nombrePais ) "
			+ "FROM Ubicaciones u JOIN u.idPais p "
			+ "	ORDER BY u.ciudad ASC, nombrePais ASC")
	public Page<UbicacionPaisDto> encuentraUbicacionesOrdenadasCiudadNombrePais( Pageable pageable );
}
