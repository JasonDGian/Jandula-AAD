package ies.jandula.matriculas.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="datos_bruto_alumno_matricula")
public class DatosBrutosAlumnoMatriculaEntity
{
	@Id
	private Integer Id;
	
	@Column( length = 50, nullable = false)
	private String nombre;
	
	@Column( length = 100, nullable = false)
	private String apellidos;
	
	@Column( length = 100, nullable = false)
	private String asignatura;
	
    // Relación ManyToOne con CursoEtapaEntity
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "curso", referencedColumnName = "curso", nullable = false),
        @JoinColumn(name = "etapa", referencedColumnName = "etapa", nullable = false)
    })
    private CursoEtapaEntity cursoEtapa;
    
    //https://medium.com/@bhagyajayashani/composite-key-handling-using-idclass-annotation-in-spring-boot-java-26f40bbd38a2
}
