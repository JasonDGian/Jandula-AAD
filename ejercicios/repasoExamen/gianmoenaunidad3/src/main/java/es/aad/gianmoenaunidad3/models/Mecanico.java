package es.aad.gianmoenaunidad3.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Mecanico extends Persona
{
	private Date fechaContratacion;
	
	private int salario;
	
	@OneToMany( mappedBy = "mecanico" ) 
	private List<MecanicoReparaCoche> reparaciones;
}
