package es.iesjandula.aadrepaso.entities.ids;

import java.util.Date;

import es.iesjandula.aadrepaso.entities.Empleados;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistorialPuestosId
{
	
	private Empleados idEmpleado;
	
	private Date fechaInicio;
	

}
