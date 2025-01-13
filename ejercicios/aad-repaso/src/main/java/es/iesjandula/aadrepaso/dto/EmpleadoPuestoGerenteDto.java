package es.iesjandula.aadrepaso.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoPuestoGerenteDto
{
	String nombreEmpleado;
	String apellidoEmpleado;
	String puesto;
	String nombreGerente;
	String apellidoGerente;
}
