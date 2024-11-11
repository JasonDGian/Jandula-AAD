package ies.jandula.ejercicio.entidades;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * Clase que define el identificador compuesto de una entidad.
 */
public class EmpleadoTrabajaProyectoID implements Serializable
{
	private static final long serialVersionUID = 1L;

	private EmpleadoEntity empleado;

	private ProyectoEntity proyecto;
	
	private Date fechaIncorporacion;
	
	private boolean esCoordinador;	
}
