# 📌 Conectar una BBDD a un proyecto Spring Boot.
1. Configuración de proyecto - dependencias necesarias.
2. Parametros de conexión en el fichero de configuración yaml.
3. Objetos de persistencia en Java.

## 🔹 Pre-requisitos.
- Disponer de un motor de BBDD o privilegios para instalar uno (puede ser contenedorizado).
- Conocer el nombre de la base de datos.
- Conocer los credenciales de acceso (usuario, contraseña) del usuario con privilegios necesarios.


# 📍 Configuración de proyecto - dependencias necesarias.
Para conectar una base de datos a nuestra aplicación necisaremos un _driver_ (o conector) adecuado y la API de persistencia de java.
- Para un servidor `MySQL` incluiremos la dependencia de `MySQL Driver` como controlador.
- Como API de persistencia incluiremos `Spring Data JPA`.
    
<p align="center" >
   <img src="https://github.com/user-attachments/assets/5b1b30de-d123-4724-aeec-212e421a26d1"><img>
</p>
   
>**NOTA: ¿Que es Spring Data JPA?**   
>Spring Data JPA Es un Api de persistencia de datos de Spring Boot que facilita la creación de repositorios para realizar operaciones de persistencia (CRUD) sin necesidad de 
escribir manualmente consultas SQL o implementar las interfaces JPA básicas. Es estrictamente necesaria? NO, pero facilita muchisimo el trabajo y minimiza lineas de código a 
escribir para interactuar con la base de datos.
   
     
# 📍 Configuración de la conexión en fichero _application.yaml_ .
>[!IMPORTANT]
>Una vez lanzado el proyecto en eclipse, borramos el fichero `project.properties` para utilizar en su lugar un fichero `application.yml`
>   
>![imagen](https://github.com/user-attachments/assets/2e848b19-92b0-43db-97cb-6071d8bd9a0d)


**Ejemplo de yaml explicado**   
IMPORTANTE: Nota como **NO SON TABULACIONES**. Cada indentación se realiza con un `doble espacio`.   
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

>[!Caution]
>En una aplicación Spring Boot, solo puede haber un único archivo `application.yaml`, `application.yml` o `application.properties` en el mismo nivel. Esto significa que si existe un
`application.yml` no podrán existir con el mismo nombre otros ficheros aunque tengan extensión distinta. *Si pueden existir varios ficheros de configuracion con nombre distinto, conocidos como perfiles de ejecucion*.
     
# 📍 Creación de modelos o entidades.
Los modelos son clases JAVA que son mapeadas a objetos de bases de datos. Para esta operación es necesario utilizar ciertas anotaciones especiales de Spring Data JPA.
- **@Entity:** Marca la clase como una tabla en la base de datos.
- **@Table:** Define el nombre de la tabla.
- **@Id:** Indica la clave primaria.
- **@Column:** Define propiedades específicas de las columnas (longitud, unicidad, obligatoriedad).
- **@ManyToOne:** Define una relación muchos-a-uno con otra entidad (clave foránea).
- **@OneToMany:** Define una relación uno-a-muchos con otra entidad (clave foránea).
- **@OneToOne:** Define una relación uno-a-uno con otra entidad (clave foránea).

Para más info sobre como mapear relaciones consulta el documento [mapeado de relaciones](https://github.com/JasonDGian/Jandula-AAD/blob/main/Mapeado-de-relaciones.md).

## 🔹 Definición de atributos de clase.
Es fundamental usar camelCase para los nombres de los atributos de la clase, ya que Spring los analiza para generar los nombres de las columnas, convirtiendo el camelCase a snake_case.

## 🔹 Constructor y metodos de acceso.
Es esencial crear un constructor vacío y los métodos de acceso (get/set) para que Spring pueda transferir información entre la base de datos y el programa.

## 🔹 Definición de relaciones.
//TODO 
Elaborar sobre las distintas relaciones.

## 🔹 Ejemplo de un modelo.
```java
@Entity
@Table(name="profesor")
public class Profesor {

    @Id
    @Column(length=10)
    private Long id;

    @Column(length = 9, unique = true)
    private String nif;

    @Column(length = 25, nullable = false)
    private String nombre;

    @ManyToOne
    private Departamento idDepartamento;

    // Constructor vacío
    public Profesor() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNif() { return nif; }
    public void setNif(String nif) { this.nif = nif; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Departamento getIdDepartamento() { return idDepartamento; }
    public void setIdDepartamento(Departamento idDepartamento) { this.idDepartamento = idDepartamento; }

    @Override
    public String toString() {
        return "String generico";
    }
}
```


