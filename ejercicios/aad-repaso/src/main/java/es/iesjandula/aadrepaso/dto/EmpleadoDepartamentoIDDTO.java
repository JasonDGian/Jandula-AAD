package es.iesjandula.aadrepaso.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoDepartamentoIDDTO
{
    private String nombre;
    
    private Long idDepartamento;
    
    private String nombreDepartamento;
}
