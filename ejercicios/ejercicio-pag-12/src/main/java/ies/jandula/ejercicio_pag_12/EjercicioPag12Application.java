package ies.jandula.ejercicio_pag_12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ies.jandula.ejercicio_pag_12.service.ParserAlumno;
import ies.jandula.ejercicio_pag_12.utils.UniversidadServerError;
import jakarta.transaction.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

@SpringBootApplication
public class EjercicioPag12Application implements CommandLineRunner {

    @Autowired
    private ParserAlumno parserAlumno;

    public static void main(String[] args) {
        SpringApplication.run(EjercicioPag12Application.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Assuming you have a CSV file named "alumnos.csv" in the resources folder or an accessible path
        File file = new File("res"+File.separator+"alumno.csv");  // Update this path if necessary

        // Check if the file exists to avoid errors
        if (file.exists()) {
            try (Scanner scanner = new Scanner(new FileInputStream(file))) {
                // Call the parseaFichero method
                parserAlumno.parseaFichero(scanner);
                System.out.println("CSV file parsed successfully.");
            } catch (UniversidadServerError e) {
                System.err.println("Error while parsing CSV file: " + e.getMessage());
            }
        } else {
            System.err.println("File not found: " + file.getAbsolutePath());
        }
    }
}
