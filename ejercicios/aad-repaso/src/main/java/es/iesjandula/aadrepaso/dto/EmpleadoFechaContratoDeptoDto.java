package es.iesjandula.aadrepaso.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoFechaContratoDeptoDto
{
	String nombreEmpleado;
	String apellidoEmpleado;
	Date fechaContrato;
	String nombreDepartamento;
}
