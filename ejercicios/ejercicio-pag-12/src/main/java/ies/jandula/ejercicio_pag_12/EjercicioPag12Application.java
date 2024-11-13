package ies.jandula.ejercicio_pag_12;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import ies.jandula.ejercicio_pag_12.service.GenericParser;
import ies.jandula.ejercicio_pag_12.service.IParserAlumno;
import ies.jandula.ejercicio_pag_12.service.IParserAsignatura;
import ies.jandula.ejercicio_pag_12.service.IParserCurso;
import ies.jandula.ejercicio_pag_12.service.IParserDepartamento;
import ies.jandula.ejercicio_pag_12.service.IParserGrado;
import ies.jandula.ejercicio_pag_12.service.IParserMatricula;
import ies.jandula.ejercicio_pag_12.service.IParserProfesor;
import ies.jandula.ejercicio_pag_12.utils.UniversidadServerError;


@SpringBootApplication
public class EjercicioPag12Application implements CommandLineRunner {

    @Autowired
    private IParserAlumno parserAlumno;
    
    @Autowired
    private IParserAsignatura parserAsignatura;
    
    @Autowired
    private IParserCurso parserCurso;
    
    @Autowired
    private IParserDepartamento parserDepartamento;

    @Autowired
    private IParserGrado parserGrado;

    @Autowired
    private IParserMatricula parserMatricula;
    
    @Autowired
    private IParserProfesor parserProfesor;


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

        // genera Array de ficheros.
        File[] files = { 				fileAlumnos, 	fileAsignaturas, 	fileCurso, 		fileDepartamento, 	fileGrado, 		fileMatricula, 		fileProfesor}; 
        GenericParser[] parseador = { 	parserAlumno, 	parserAsignatura, 	parserCurso, 	parserDepartamento, parserGrado, 	parserMatricula, 	parserProfesor  };    
        
        parseaFichero( files[0], parseador[0] );// alumno
        parseaFichero( files[2], parseador[2] );// curso
        parseaFichero( files[4], parseador[4] );// grado
        parseaFichero( files[3], parseador[3] );// depto
        parseaFichero( files[6], parseador[6] );// profesor
        parseaFichero( files[1], parseador[1] );// asignatura
        parseaFichero( files[5], parseador[5] );// matricula
    }
    
    //bucle que opear con ficheros.
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
}
