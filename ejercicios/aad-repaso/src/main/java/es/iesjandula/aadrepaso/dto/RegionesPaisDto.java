package es.iesjandula.aadrepaso.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionesPaisDto
{
	private Long idRegion;
	private String nombreRegion;
	private long recuentoPaises;
}
