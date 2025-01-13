package es.iesjandula.aadrepaso.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Empleados {

    @Id
    @Column(name = "id_empleado")
    private Long idEmpleado;

    @Column(name = "nombre", length = 20)
    private String nombre;

    @Column(name = "apellido", length = 25, nullable = false)
    private String apellido;

    @Column(name = "email", length = 25, nullable = false)
    private String email;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "fecha_contrato", nullable = false)
    private Date fechaContrato;

    @ManyToOne
    @JoinColumn(name = "id_puesto", referencedColumnName = "id_puesto")
    private Puestos idPuesto;
       
    @Column(name = "salario", precision = 8, scale = 2)
    private BigDecimal salario;

    @Column(name = "comision_pct", precision = 2, scale = 2)
    private BigDecimal comisionPct;

    @ManyToOne
    @JoinColumn(name = "id_gerente", referencedColumnName = "id_empleado")
    private Empleados idGerente;

    @ManyToOne
    @JoinColumn(name = "id_departamento", referencedColumnName = "id_departamento")
    private Departamentos idDepartamento;
    
    @OneToMany( mappedBy = "idEmpleado")
    private List<HistorialPuestos> historialPuestos;
    
    
}
