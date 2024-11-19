package es.aad.gianmoenaunidad3.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PersonaCliente extends Persona
{
	private String direccion;
	
	private String telefono;
	
	@OneToMany(mappedBy = "cliente")
	private List<Coche> vehiculosAdquiridos;
	
	@OneToOne(mappedBy = "cliente")
	private SorteoEspecial sorteo;
}
