# 📌 Como parsear ficheros en Java Spring.
El parseo de ficheros CSV y TSV es una necesidad o caracteristica comun en proyectos java.
   
### 1. Crear la clase Interfaz que recoge los metodos del parser.
Esta clase sirve para el sistema de inversión de control.
```java
package ies.jandula.ejercicio_pag_12.service;

import java.util.Scanner;

import ies.jandula.ejercicio_pag_12.utils.UniversidadServerError;

public interface IParserAlumno
{
	public void parseaFichero(Scanner scanner) throws UniversidadServerError ;
}
```
   
### 2. Crear la clase de implementación del parser y añadir la logica de parseo.
La logica de parseo debe observar la estructura en la que se reciben los ficheros csv etc.. 
```java
package ies.jandula.ejercicio_pag_12.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.jandula.ejercicio_pag_12.entity.Alumno;
import ies.jandula.ejercicio_pag_12.repositories.IAlumnoRepository;
import ies.jandula.ejercicio_pag_12.utils.Constants;
import ies.jandula.ejercicio_pag_12.utils.UniversidadServerError;

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
			alumno.setFechaNacimiento( LocalDate.parse(campos[8]) );
			alumno.setSexo( campos[9] );
			
			// Guardamos la entidad en bases de datos.
			this.alumnoRepo.saveAndFlush(alumno);
		}
	} 
}
```

### 3. Consumir la logica implementada en la clase de entrada ejecución de Spring Boot App.
En la clase principal de ejecución implementamos las llamadas al parser.

```java
package ies.jandula.ejercicio_pag_12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ies.jandula.ejercicio_pag_12.service.IParserAlumno;
import jakarta.transaction.Transactional;

@SpringBootApplication
public class EjercicioPag12Application {

	public static void main(String[] args) {
		SpringApplication.run(EjercicioPag12Application.class, args);
	}
	
	@Transactional (readOnly = false)
	public void run(String... args) throws Exception{
		// aqui llamadas a los parsers.
	}

}
```

# 📌 Asignando atributos que son a su vez entidades.
Para asignar (set) un atributo de una entidad que representa a su vez otra entidad usamos el siguiente mecanismo.

1. Recuperamos la entidad mediante su ID.
2. Realizamos los controles pertinentes y levantamos las excepciones necesarias.
3. Asignamos el valor desenvolviendo el optional.

```java
// Recuperamos la entidad mediante su ID.
Optional<Profesor> profesor = profesorRepo.findById( Long.valueOf(campos[6]) );

// Asignamos el profesor si existe.
if ( profesor.isPresent() ) {
asignatura.setProfesor( profesor.get());
}
```
