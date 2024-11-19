### 📌 Excepción custom
[Excepcion link apuntes](https://github.com/JasonDGian/Jandula-AAD/blob/main/curstom-exception.md)


### 📌 Parsear CSV.
1. Crear entidades, repositorios etc...
2. Crear **interfaz** `IEntidadParser`
3. Crear clase `@Service` con la implementacion del parseo.
 - Autowired de repositorio para guardar datos.
 - Metodo de parseo.
 - Save and flush.
4. Implementar metodo en endpoint (si necesario) con gestion de excepciones.

**Ejemplo implementacion de parseo**
```java
@Service
public class ParserAlumno implements IParserAlumno
{
	@Autowired
	private IAlumnoRepository alumnoRepo;
	
	@Override
	public void parseaFichero ( Scanner scanner ) throws UniversidadServerError{
		
		// Saltamos linea de cabeceras.
		scanner.nextLine();
		
		// Mientras que el fichero tenga una linea disponible.
		while ( scanner.hasNextLine() ) {
			
			String[] campos = scanner.nextLine().split(Constants.DELIMITADOR_CSV);
			System.out.print( campos.toString() );
			
			// Generamos una nueva instancia donde guardar la información sacada del CSV.
			Alumno alumno = new Alumno();
			
			// A continuación, y prestando atención a la estructura del fichero, 
			// recuperamos los campos y los asignamos al atributo corerspondiente..
			alumno.setId( Long.valueOf( campos[0] ) );
			alumno.setNif( campos[1] );
			alumno.setNombre( campos[2]);
			alumno.setApellido1( campos[3] );
			alumno.setApellido2( campos[4]);
			alumno.setCiudad( campos[5]);
			alumno.setDireccion( campos[6] );
			alumno.setTelefono( campos[7] );
			alumno.setFechaNacimiento(
				LocalDate.parse(campos[8], DateTimeFormatter.ofPattern("dd/MM/yyyy")  ) );
			alumno.setSexo( campos[9] );
			
			// Guardamos la entidad en bases de datos.
			this.alumnoRepo.saveAndFlush(alumno);
		}
	} 
}
```

**Metodo de parseo generico**
```java
//Inyeccion de parser.
@Autowired
private IParserAlumno parserAlumno;

// Fichero
File fileAlumnos = new File("res"+File.separator+"alumno.csv");

//Metodo de parseo con interfaz generica.
private void parseaFichero( File fichero , GenericParser parser ) throws FileNotFoundException {

// si el fichero existe.
if (fichero.exists()) {
	// Intento de uso de objeto scanner ( try with? )
    try (Scanner scanner = new Scanner(new FileInputStream(fichero))) {
	
	// Llama al parser de alumnos
	parser.parseaFichero(scanner);
	System.out.println("CSV file parsed successfully.");
    } catch (UniversidadServerError e) {
	System.err.println("Error while parsing CSV file: " + e.getMessage());
    }
} else {
    System.err.println("File not found: " + fichero.getAbsolutePath());
}

}
```

### 📌 Parseo mediante EndPoint.
1. Configurar el endpoint para la recepcion del fichero.
2. Invocar el metodo de parseo.

**Configurar el endpoint para la recpcion del fichero**.
```java
@RequestMapping( value = "/send/csv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<?> sendXmlToObjects( @RequestPart MultipartFile xmlFile )
```

### 📌 Relacion bidireccional 1:1
**Tabla que carga con la relacion.**
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SorteoEspecial
{
	private int importe;
	
	@Id
	private int anio;
	
	// Carga con la relacion.
	@OneToOne
	private PersonaCliente cliente;
}
```
**Referencia bidireccional JAVA**
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PersonaCliente extends Persona
{
	private String direccion;
	
	private String telefono;
	
	@OneToMany(mappedBy = "cliente")
	private List<Coche> vehiculosAdquiridos;

	// Referencia bidireccional.
	@OneToOne(mappedBy = "cliente") // <- nombre del atributo que carga con la relacion
	private SorteoEspecial sorteo;
}
```

### 📌 Relacion bidireccional  1:M
```java
```

### 📌 Relacion bidireccional  M:M
Cuando producimos una tabla nueva en una relacion M:M podemos hacerla bidirecional de este modo.
**Tabla spawn**
```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass( MecanicoReparaCocheId.class )
public class MecanicoReparaCoche
{
	@Id
	@ManyToOne // Absorcion de la clave primaria.
	private Coche coche;
	
	@Id
	@ManyToOne // Absorcion de la clave primaria.
	private Mecanico mecanico;
	
	@Id
	private Date fechaReparacion;
	
	@Id
	private float horas;
}
```
**Entidad mecanico**
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Mecanico extends Persona
{
	private Date fechaContratacion;
	
	private int salario;

	// Inversión de la relacion en la tabla spawn
	@OneToMany( mappedBy = "mecanico" ) 
	private List<MecanicoReparaCoche> reparaciones;
}
```

**Entidad coche**
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Coche
{
	@Id
	private String matricula;
	
	private String marca;
	
	private String modelo;
	
	private String color;
	
	// Inversión de la relacion en la tabla spawn
	@OneToMany(mappedBy = "coche")
	private List<MecanicoReparaCoche> reparacion;
}
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
