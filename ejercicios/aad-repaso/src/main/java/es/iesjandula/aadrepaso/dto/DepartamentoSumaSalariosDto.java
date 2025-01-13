package es.iesjandula.aadrepaso.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartamentoSumaSalariosDto
{
	private String nombreDepartamento;
	private BigDecimal sumaSalarios;
}
