package ies.jandula.ejercicio_pag_12;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import ies.jandula.ejercicio_pag_12.service.IParserAlumno;
import ies.jandula.ejercicio_pag_12.utils.UniversidadServerError;


@SpringBootApplication
public class EjercicioPag12Application implements CommandLineRunner {

    @Autowired
    private IParserAlumno parserAlumno;

    public static void main(String[] args) {
        SpringApplication.run(EjercicioPag12Application.class, args);
    }

    
    @Transactional(readOnly=false)
    public void run(String... args) throws Exception {
    	
    	
        // Assuming you have a CSV file named "alumnos.csv" in the resources folder or an accessible path
        File fileAlumnos = new File("res"+File.separator+"alumno.csv");  // Update this path if necessary
        File fileAsignaturas = new File( "res"+File.separator + "asignatura.csv" );
        File fileCurso = new File( "res"+File.separator+"curso.csv" );
        File fileDepartamento = new File( "res"+File.separator+"departamento.csv" );
        File fileGrado = new File ( "res" + File.separator + "grado.csv" );
        File fileMatricula = new File ( "res" + File.separator + "matricula.csv" );
        File fileProfesor = new File ( "res" + File.separator + "profesor.csv" );

        // Check if the file exists to avoid errors
        
        if (fileAlumnos.exists()) {
            try (Scanner scanner = new Scanner(new FileInputStream(fileAlumnos))) {
                // Llama al parser de alumnos
                this.parserAlumno.parseaFichero(scanner);
                System.out.println("CSV file parsed successfully.");
            } catch (UniversidadServerError e) {
                System.err.println("Error while parsing CSV file: " + e.getMessage());
            }
        } else {
            System.err.println("File not found: " + fileAlumnos.getAbsolutePath());
        }
        

        if ( fileAsignaturas.exists() ) {
        	try ( scanner = new Scanner( new FileInputStream(fileAsignaturas) );)
        }
    }
}
