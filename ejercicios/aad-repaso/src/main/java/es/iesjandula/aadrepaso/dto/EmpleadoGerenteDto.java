package es.iesjandula.aadrepaso.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoGerenteDto
{
	String nombreEmpleado;
	String apellidoEmpleado;
	String nombreGerente;
	String apellidoGerente;
}
