package es.aad.gianmoenaunidad3.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CocheUsado extends Coche
{
	
	private int kilometrosRecorridos;

}
