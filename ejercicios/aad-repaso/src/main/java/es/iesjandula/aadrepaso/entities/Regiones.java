package es.iesjandula.aadrepaso.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Regiones
{
	
	@Id
	@Column( name = "id_region", precision = 10, scale = 0)
	private Long idRegion;
	
	@Column( name = "nombre_region", length = 25)
	private String nombreRegion;

	// Reflejo relacion con pais.
	@OneToMany( mappedBy = "idRegion")
	private List<Paises> paises;
	
}
