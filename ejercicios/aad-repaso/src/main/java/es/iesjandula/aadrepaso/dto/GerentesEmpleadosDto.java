package es.iesjandula.aadrepaso.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GerentesEmpleadosDto
{
	private String nombreGerente;
	private Long recuentoEmpleados;
}
