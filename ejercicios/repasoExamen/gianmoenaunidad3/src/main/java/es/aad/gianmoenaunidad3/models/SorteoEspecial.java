package es.aad.gianmoenaunidad3.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SorteoEspecial
{
	private int importe;
	
	@Id
	private int anio;
	
	// Carga con la relacion.
	@OneToOne
	private PersonaCliente cliente;
}
