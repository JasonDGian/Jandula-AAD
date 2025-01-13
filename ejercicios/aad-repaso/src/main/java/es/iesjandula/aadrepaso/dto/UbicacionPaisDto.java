package es.iesjandula.aadrepaso.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UbicacionPaisDto
{
	Long idUbicacion;
	String direccion;
	String ciudad;
	String pais;
}
