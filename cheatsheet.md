### 📌 Relacion bidireccional 1:1
```java
```

### 📌 Relacion bidireccional  1:M
```java
```

### 📌 Relacion bidireccional  M:M
```java
```

### 📌 ID compuesto.
**Clase ID**
```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MecanicoReparaCocheId
{
	
	private PersonaCliente cliente;
	
	private Coche coche;

}
```

**Clase que implementa ID Compuesto**
```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass( MecanicoReparaCocheId.class )
public class MecanicoReparaCoche
{
	
	@Id
	private Coche coche;
	
	@Id
	private PersonaCliente cliente;
	
	@Id
	private Date fechaReparacion;
	
	private float horas;
	
}
```

### 📌 Herencias
Especificacion de herencia: 
- @Inheritance(strategy = InheritanceType.JOINED)
- @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
- @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)    
        
**Clase padre:**
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona
{
	@Id
	private String dniNif;
	
	private String nombre;
	
	private String apellidos;

}
```
**Clase hija:**
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PersonaCliente extends Persona
{
	private String direccion;
	
	private String telefono;
}
```

### 📌 
