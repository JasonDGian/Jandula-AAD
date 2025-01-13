package es.iesjandula.aadrepaso.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartamentoUbicacionDto
{
	String nombreDepartamento;
	private Long idUbicacion;
	private String ciudad;
}


