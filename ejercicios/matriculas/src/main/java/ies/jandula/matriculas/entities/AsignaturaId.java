package ies.jandula.matriculas.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaId implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer curso;
	
	private String etapa;
	
	private String grupo;
	
	private String nombre;
}
