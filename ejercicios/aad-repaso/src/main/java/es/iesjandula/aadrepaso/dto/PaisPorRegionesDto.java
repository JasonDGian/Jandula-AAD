package es.iesjandula.aadrepaso.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaisPorRegionesDto
{
	
	public Long idRegion;
	public String nombreRegion;
	public Long numeroPaises;
  
}
