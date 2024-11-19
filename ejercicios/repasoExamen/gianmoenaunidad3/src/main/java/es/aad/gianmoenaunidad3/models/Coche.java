package es.aad.gianmoenaunidad3.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Coche
{
	
	@Id
	private String matricula;
	
	private String marca;
	
	private String modelo;
	
	private String color;
	
	@ManyToOne
	private PersonaCliente cliente;
	
	@OneToMany(mappedBy = "coche")
	private List<MecanicoReparaCoche> reparacion;
}
