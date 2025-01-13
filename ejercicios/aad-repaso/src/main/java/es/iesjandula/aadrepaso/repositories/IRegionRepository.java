package es.iesjandula.aadrepaso.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.iesjandula.aadrepaso.dto.PaisPorRegionesDto;
import es.iesjandula.aadrepaso.dto.RegionesPaisDto;
import es.iesjandula.aadrepaso.entities.Regiones;

@Repository
public interface IRegionRepository extends JpaRepository<Regiones, Long>
{
	
	@Query ( "SELECT new es.iesjandula.aadrepaso.dto.PaisPorRegionesDto( re.idRegion, re.nombreRegion, COUNT(pa.nombrePais) ) "
			+ "FROM Paises pa "
			+ "JOIN pa.idRegion re "
			+ "GROUP BY re.idRegion, re.nombreRegion" )
	public Page<PaisPorRegionesDto> recuentoPaisesPorRegion( Pageable pageable );
	
	// Obtener regiones ordenadas por cantidad de países asociados
	@Query ( "SELECT new es.iesjandula.aadrepaso.dto.RegionesPaisDto( re.idRegion, re.nombreRegion, COUNT(pa) as recuentoPaises ) "
			+ "FROM Paises pa "
			+ "JOIN pa.idRegion re "
			+ "GROUP BY re.idRegion, re.nombreRegion "
			+ "ORDER BY recuentoPaises DESC" )
	public Page<RegionesPaisDto> recuentoPaisesPorRegionOrdenado( Pageable pageable );
	
	// Listar regiones con el número total de países en cada una.
	@Query ( "SELECT new es.iesjandula.aadrepaso.dto.RegionesPaisDto( re.idRegion, re.nombreRegion, COUNT(pa) as recuentoPaises ) "
			+ "FROM Paises pa "
			+ "JOIN pa.idRegion re "
			+ "GROUP BY re.idRegion, re.nombreRegion" )
	public Page<RegionesPaisDto> listarRegionesConRecuentoPais( Pageable pageable );
	
}
