package es.iesjandula.aadrepaso.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoComisionDepartamentoDto
{
	private String nombreEmpleado;
	private String apellidoEmpleado;
	private BigDecimal comisionPct;
	private String nombreDepartamento;
	
}
