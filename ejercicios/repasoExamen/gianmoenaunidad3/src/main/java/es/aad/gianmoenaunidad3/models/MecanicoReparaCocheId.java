package es.aad.gianmoenaunidad3.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MecanicoReparaCocheId
{
	private Coche coche;
	
	private Mecanico mecanico;
	
	private Date fechaReparacion;
	
	private float horas;
}
