### 📌 Application yaml.
```yaml
spring:
  jpa.hibernate.ddl-auto: create # Configura cómo se debe gestionar el esquema de la base de datos.
  datasource:
    url: jdbc:mysql://localhost:3306/universidad # URL de conexión a la base de datos MySQL.
    username: root # Nombre de usuario para conectarse a la base de datos.
    password: 1234 # Contraseña correspondiente al usuario especificado.
    hikari: # Hikari es un mecanismo de eficiencia de conexiones. Cnoocido como "Pool de conexiones".
    connection-timeout: 60000 # Establece el tiempo máximo de espera en milisegundos (60 segundos) para obtener una conexión antes de lanzar un error.
    maximum-pool-size: 5 # Especifica el número máximo de conexiones permitidas en el pool de conexiones simultáneas.

server:
  port: 8085 # Define el puerto en el que se ejecutará la aplicación Spring Boot.

logging: #Sección de configuración del sistema de logs en Spring.
  level:
    com.iesjandula: INFO # Define el nivel de log para el paquete 'com.iesjandula'(recursivo).
  file:
    name: C:\logs\holaMundo.log # ruta y el nombre del archivo donde se guardarán los logs.
    max-size: 1MB # Define el tamaño máximo que puede tener el archivo de log antes de que se cree uno nuevo.
    max-history: 20 # Especifica el número máximo de archivos históricos de logs que se guardarán.
    total-size-cap: 10MB # Indica el tamaño total máximo permitido para todos los archivos de log acumulados.
```


### 📌 Excepción custom
Excepcion personalizada con codigos HTTP custom.
Entre los códigos de error HTTP existe un rango disponible para uso personalizado que podemos emplear con las excepciones que definimos dentro de nuestra aplicación.

Los códigos de error disponibles son los siguientes:      
- Errores en lado **cliente**: 490 - 499   
- Errores en lado **servidor**: 590 - 599


#### 📍 Añadir dependencia al POM.
   
```xml
<dependency>
	<groupId>org.apache.commons</groupId>
	<artifactId>commons-lang3</artifactId>
</dependency>
```
   
   
#### 🔸 Crear la clase de excepción en paquete `.utils`
En este paquete se encuentra un metodo que mapeará el mensaje de la excepción, de haberlo, a formato transferible `Json`.
   
```java
package ies.jandula.matriculas.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class MatriculasServerError extends Exception
{
	/**
	 * Auto-generated ID
	 */
	private static final long serialVersionUID = 8144321039123138732L;

	private int id;

	private String message;

	private Exception exception;

	// Constructor completo
	public MatriculasServerError(int id, String message, Exception exception)
	{
		super();
		this.id = id;
		this.message = message;
		this.exception = exception;
	}

	// Constructor sin la Excepcion
	public CUSTOMServerError(int id, String message)
	{
		super();
		this.id = id;
		this.message = message;
	}

	// Metodo que devuelve un Mapa con la Excepción propia
	public Map<String, String> getMapError()
	{
		Map<String, String> mapError = new HashMap<String, String>();

		mapError.put("id", "" + id);
		mapError.put("message", message);

		if (this.exception != null)
		{
			String stacktrace = ExceptionUtils.getStackTrace(this.exception); // -> requiere una dependencia de Apache commons.  org.apache.commons.lang3.exception.ExceptionUtils;
			mapError.put("exception", stacktrace);
		}
		
		return mapError;
	}
}
```
   
#### 🔸 Responder con un error en forma de mapa para las respuestas REST.
```java
catch (ParserConfigurationException exception)
{
	// Establece un mensaje para el error.
	String error = "Parser Configuration Exception";
	// Loguea el error con el mensaje dado.
	log.error(error, exception);
	// Crea el nuevo objeto de errror.
	HorariosError horariosException = new HorariosError(400, exception.getLocalizedMessage(), exception);
	// Devuelve el objeto en el cuerpo de la respuesta como objeto mapa -> Tira de metodo toMap();
	return ResponseEntity.status(400).body(horariosException.getMapError());
}
```

### 📌 Parsear CSV.
1. Crear entidades, repositorios etc...
2. Crear **interfaz** `IEntidadParser`
3. Crear clase `@Service` con la implementacion del parseo.
 - Autowired de repositorio para guardar datos.
 - Metodo de parseo.
 - Save and flush.
4. Implementar metodo en endpoint (si necesario) con gestion de excepciones.

[Parseo apuntes](https://github.com/JasonDGian/Jandula-AAD/blob/main/parseo-ficheros.md)

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
		
		// Mientras que el fichero tenga una 'siguiente linea' disponible.
		while ( scanner.hasNextLine() ) {
			
			String[] campos = scanner.nextLine().split(Constants.DELIMITADOR_CSV); // Consatnte personalizada de DELIMITADOR_CSV
			Log.debug( "Campos recibidos {}", Arrays.toString(campos) );

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

			try {
			alumno.setFechaNacimiento(
				LocalDate.parse(campos[8], DateTimeFormatter.ofPattern("dd/MM/yyyy")  ) );
			alumno.setSexo( campos[9] );
			}
			catch ( Exception e){
				throw new UniversidadServerException(490, "Error en parseo de fecha {}.", campos[8].toString);
			}
			// Guardamos la entidad en bases de datos.
			this.alumnoRepo.saveAndFlush(alumno);
		}
	} 
}
```

**Metodo de parseo generico**
```java
// Metodo que recibe un fichero y un parser para dicho tipo de fichero. 
private void parseaFichero(File fichero, GenericParser parser) throws UniversidadServerError
{
	// Si el fichero no existe, lanza error.
	if (fichero == null || !fichero.exists()) {
	    throw new UniversidadServerError(491, "El fichero indicado en el parser no existe.");
	}
	
	try (Scanner scanner = new Scanner(fichero)) {
		
	    // Llama al parser del objeto.
	    parser.parseaFichero(scanner);
	    log.info( "Fichero parseado. {}", fichero.getName().toString() );
	} catch (FileNotFoundException e) {
		// En caso de error levantar excepcion personalizada.
	    throw new UniversidadServerError(500, "Error al abrir el fichero.", e);
	}
}
```

### 📌 Parsear CSV con herencias.
1. Crear un metodo en la clase de implmentacion del PARSER de la clase padre que reciba un array de valores para inicializar una instancia y devuelva esta instancia incializada.    
	```java
		public Coche parseoCocheNuevo( String[] valores ){
			Coche coche = new Coche();
		 	coche.setMatricula(valores[1]);
		 	coche.setMarca(valores[2]);
			coche.setColor(valores[3]);
 			return coche;
	 	}
	```
2. Crear un constructor en el hijo que se base una instancia del padre para configurar los campos heredados.    
	- Este constructor se alimenta de la instancia creada por el metodo del punto 1.    
 	```java
	 	public CocheNuevo()
	 	{
	 		super(coche);
	 	}
 	```    
      
3. Crear metodo de parseo que utilice el metodo de la case padre que devuelve una instancia (para Jesús: ESTA ISNTANCIA, ES DE LA CLASE PADRE.).       
	**Dentro de la logica de parseo de la clase hija**    
   	```java
		// Llamada al parser de la clase padre que devuelve una instancia.
		Coche coche = this.iParseCoche.parseoCoche(valores); // valores = String[]

    		// Llamada al constructor de la clase hija que recibe la superclase.
    		NuevoCoche nuevoCoche = NuevoCoche(coche);

    		// Asigna los valores a los atributos no comunes.
    		nuevoCoche.setUnidades(valores[8]);
    		nuevoCoche.setFuselaje(valores[9]);
    	```
   

### 📌 Parseo mediante EndPoint.
1. Configurar el endpoint para la recepcion del fichero.
2. Invocar el metodo de parseo.

**Configurar el endpoint para la recpcion del fichero**.
```java
@RequestMapping(
    value = "/send/csv",
    method = RequestMethod.POST,
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE
)
public ResponseEntity<?> sendCsvToObjects(@RequestParam("file") MultipartFile file) {
    try {
        // Aquí podrías procesar el archivo
        String contenido = new String(file.getBytes());
        return ResponseEntity.ok("Archivo recibido: " + file.getOriginalFilename());
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Error al procesar el archivo: " + e.getMessage());
    }
}
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

### 📌 Claves foráneas compuestas.
[CFC Apuntes](https://github.com/JasonDGian/Jandula-AAD/blob/main/claves-y-relaciones.md)

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

### 📌 Recibir y manejar fichero CSV por endpoint.
```java
	@PostMapping(value = "/movies", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> uploadMovies( @RequestParam("ficheroCSV") MultipartFile ficheroCSV )
	{
		try
		{
			// Control de presencia de fichero.
			if (ficheroCSV == null || ficheroCSV.isEmpty())
			{
				throw new Exception("Fichero vacio o nulo.");
			}
			// Control de tipo fichero.
			if (!(ficheroCSV.getContentType().equalsIgnoreCase("text/csv") ))
			{
				throw new Exception("El fichero no corresponde al tipo esperado.");
			}
			
			// Crea un fichero temporal.
			File tempFile = File.createTempFile("tempFile", ".csv");
			
			ficheroCSV.transferTo(tempFile); // Transfiere al fichero temporal.	
			
			Scanner scanner = new Scanner(tempFile); // Utiliza el nuevo fichero generado.
			
			scanner.nextLine(); // Ignora cabecera.
			
			// Por cada campo encontrado imprimer por pantalla.
			if ( scanner.hasNext() ) {
				String[] campos = scanner.nextLine().split(",");
				for ( String campo : campos ) {
					System.out.println(campo);
				}
			}
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return ResponseEntity.ok("Done");
	}
```
