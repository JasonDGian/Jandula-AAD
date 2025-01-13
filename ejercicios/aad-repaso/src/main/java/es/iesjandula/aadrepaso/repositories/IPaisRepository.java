package es.iesjandula.aadrepaso.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.iesjandula.aadrepaso.dto.PaisRecuentoUbicacionDto;
import es.iesjandula.aadrepaso.dto.PaisYRegionDto;
import es.iesjandula.aadrepaso.entities.Paises;

@Repository
public interface IPaisRepository extends JpaRepository<Paises, String>
{
	
	@Query( " SELECT new es.iesjandula.aadrepaso.dto.PaisYRegionDto( p.nombrePais, r.nombreRegion ) "
			+ "FROM Paises p JOIN p.idRegion r" )
	public Page<PaisYRegionDto> encuentraPaisesYRegiones( Pageable pageable );

	//Obtener los países de la región "Europa".
	@Query( " SELECT new es.iesjandula.aadrepaso.dto.PaisYRegionDto( p.nombrePais, r.nombreRegion ) "
			+ "FROM Paises p JOIN p.idRegion r "
			+ "WHERE r.nombreRegion LIKE 'EUROPA'" )
	public Page<PaisYRegionDto> encuentraPaisesEuropeos( Pageable pageable );
	
	
	// Mostrar países ordenados por el nombre de la región a la que pertenecen.
	@Query( " SELECT new es.iesjandula.aadrepaso.dto.PaisYRegionDto( p.nombrePais, r.nombreRegion ) "
			+ "FROM Paises p JOIN p.idRegion r "
			+ "ORDER BY r.nombreRegion" )
	public Page<PaisYRegionDto> encuentraPaisesOrdenaRegion( Pageable pageable );
	
	//  Listar los países y el número de ubicaciones en cada país.
	@Query( " SELECT new es.iesjandula.aadrepaso.dto.PaisRecuentoUbicacionDto( p.nombrePais, COUNT( ubi ) AS recuento ) "
			+ "FROM Ubicaciones ubi JOIN ubi.idPais p "
			+ "GROUP BY nombrePais "
			+ "ORDER BY recuento DESC" )
	public Page<PaisRecuentoUbicacionDto> encuentraNumeroUbicacionesPais( Pageable pageable );
	
}
