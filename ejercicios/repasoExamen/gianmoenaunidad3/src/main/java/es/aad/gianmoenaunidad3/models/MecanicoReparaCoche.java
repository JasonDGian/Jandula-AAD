package es.aad.gianmoenaunidad3.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass( MecanicoReparaCocheId.class )
public class MecanicoReparaCoche
{
	
	@Id
	@ManyToOne
	private Coche coche;
	
	@Id
	@ManyToOne
	private Mecanico mecanico;
	
	@Id
	private Date fechaReparacion;
	
	@Id
	private float horas;
	
}
