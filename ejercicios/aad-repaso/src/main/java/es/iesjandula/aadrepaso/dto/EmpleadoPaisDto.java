package es.iesjandula.aadrepaso.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoPaisDto
{
	private String nombre;
	private String apellido;
	private String pais;
    private BigDecimal salario;
}
