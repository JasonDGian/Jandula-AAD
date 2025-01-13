package es.iesjandula.aadrepaso.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaisRecuentoUbicacionDto
{
	private String nombrePais;
	private Long recuentoUbicaciones;
}
