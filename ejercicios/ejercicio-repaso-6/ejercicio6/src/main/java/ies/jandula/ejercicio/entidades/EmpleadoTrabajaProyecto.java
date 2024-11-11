package ies.jandula.ejercicio.entidades;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "empleado_trabaja_proyecto")
@IdClass(EmpleadoTrabajaProyectoID.class)
public class EmpleadoTrabajaProyecto
{
	
    @Id
    @ManyToOne
    @JoinColumn(name = "id_empleado", referencedColumnName = "nif")
    private EmpleadoEntity empleado;

    @Id
    @ManyToOne
    @JoinColumn(name = "codigo_proyecto", referencedColumnName = "codigo")
    private ProyectoEntity proyecto;
	
	@Id
	private Date fechaIncorporacion; 	// Atributo de relacion.
	
	@Id
	private boolean esCoordinador;		// Atributo de relacion.
}
