package es.iesjandula.aadrepaso.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Paises
{

	@Id
	@Column( name = "id_pais", length = 2)
	private String idPais;
	
	@Column( name = "nombre_pais", length = 40)
	private String nombrePais;
	
	@ManyToOne
	@JoinColumn( name = "id_region")
	private Regiones idRegion;
	
	// Reflejo relacion con Ubicacion.
	@OneToMany( mappedBy = "idPais")
	private List<Ubicaciones> ubicaciones;
		
}
