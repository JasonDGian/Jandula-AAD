# 📌 Claves foráneas compuestas.
Para especificar claves foraneas compuestas debemos seguir el siguiente proceso.
1. Crear la clase que representa la clave.
2. Crear la clase que representa la entidad.
3. Especificar en la entidad cual es su clase de clave compuesta.
4. Identificar en la entidad los campos que componen la clave compuesta.
   
--- 
   
1. Crear la clase que representa la clave implementando `Serializable`.
```java
package ies.jandula.matriculas.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoEtapaId implements Serializable
{
	private static final long serialVersionUID = 1L;

	private long curso;
	
	private String etapa;
	
}
```
2. Crear la clase que representa la entidad.
```java
package ies.jandula.matriculas.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CursoEtapaEntity
{
	private long curso; //cp1
	private String etapa; //cp2
}
```
3. Especificar en la entidad cual es su clase de clave compuesta.
```java
@Entity
@IdClass(CursoEtapaId.class)
public class CursoEtapaEntity
{
	private long curso;
	private String etapa;
}
```
4. Identificar los campos que componen la clave compuesta.
```java
@Entity
@IdClass(CursoEtapaId.class)
public class CursoEtapaEntity
{
	@Id
	private long curso;
	@Id
	private String etapa;
}
```

# 📌 Mapear relaciones referenciando claves compuestas.
1. Identificar el tipo de relacion.
2. Referenciar la entidad con clave compuesta en la entidad que la mapea.
```java
// Relacion
@ManyToOne
    // Fusion de columnas en un solo campo.
    @JoinColumns({
	// Columnas que se fusionan.
        @JoinColumn(name = "campo1", referencedColumnName = "campo1", nullable = false),
        @JoinColumn(name = "campo2", referencedColumnName = "campo2", nullable = false)
    })
    // Entidad que integra la clave compuesta a la que estamos llamando.
    private EntidadCompuesta entidadCompuesta; // Campo de la relación ManyToOne
```
