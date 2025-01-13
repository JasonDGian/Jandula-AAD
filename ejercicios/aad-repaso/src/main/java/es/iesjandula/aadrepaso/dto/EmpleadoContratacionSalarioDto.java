package es.iesjandula.aadrepaso.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoContratacionSalarioDto
{
	private String nombreEmpleado;
	private Date fechaContratacion;
    private BigDecimal salario;
}

