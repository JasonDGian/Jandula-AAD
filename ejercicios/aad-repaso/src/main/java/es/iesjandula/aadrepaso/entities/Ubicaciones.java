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
public class Ubicaciones
{

	@Id
	@Column( name = "id_ubicacion", precision = 4, scale = 0)
	private Long idUbicacion;
	
	@Column( name = "direccion", length = 40)
	private String direccion;
	
	@Column( name = "codigo_postal", length = 12)
	private String codigoPostal;
	
	@Column( name = "ciudad", length = 30, nullable = false)
	private String ciudad;
	
	@Column( name = "estado_provincia")
	private String estadoProvincia;
	
	@JoinColumn( name = "id_pais" )
	@ManyToOne
	private Paises idPais;
	
	// Reflejo relacion.
	@OneToMany( mappedBy = "idUbicacion")
	private List<Departamentos> departamentos;
		
}
