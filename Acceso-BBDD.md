# Conectar una BBDD a un proyecto Spring Boot.
1. Configuración de proyecto - dependencias necesarias.
2. Parametros de conexión en el fichero de configuración yaml.
3. Objetos de persistencia en Java.


# 📌 Configuración de proyecto - dependencias necesarias.
Para conectar una base de datos a nuestra aplicación deberemos un driver (o conector) adecuado y la api de persistencia de java.
- Para un servidor `MySQL` incluiremos la dependencia de `MySQL Driver` como controlador.
- Como API de persistencia incluiremos `Spring Data JPA`.
    
<p align="center" >
   <img src="https://github.com/user-attachments/assets/5b1b30de-d123-4724-aeec-212e421a26d1"><img>
</p>
   
>**NOTA: ¿Que es Spring Data JPA?**   
>Spring Data JPA Es un Api de persistencia de datos de Spring Boot que facilita la creación de repositorios para realizar operaciones de persistencia (CRUD) sin necesidad de 
escribir manualmente consultas SQL o implementar las interfaces JPA básicas. Es estrictamente necesaria? NO, pero facilita muchisimo el trabajo y minimiza lineas de código a 
escribir para interactuar con la base de datos.
   
     
# 📌 Configuración de la conexión en fichero _application.yaml_ .
>[!IMPORTANT]
>Una vez lanzado el proyecto en eclipse, borramos el fichero `project.properties` para utilizar en su lugar un fichero `application.yml`
>   
>![imagen](https://github.com/user-attachments/assets/2e848b19-92b0-43db-97cb-6071d8bd9a0d)

# 📌 Creación de modelos o entidades.



    
Los ficheros de propiedades son los que permiten configurar el arranque de nuestra aplicación en Spring Boot. Sin ellos, no podríamos especificar dónde está nuestra BBDD, 
en qué puerto arranca nuestro microservicio, qué nivel de logs tendrá nuestra aplicación, etcétera. Es posible tener varios ficheros de configuración para distintos "contextos" 
o entornos, pero es necesario distinguirlos. Para diferenciarlos, se suelen usar nombres como `application-HOME.yml` o `application-HOSTING.yml`, y según el contexto en que se lance 
la aplicación, se especifica qué fichero de configuración utilizar.
    
>[!Caution]
>En una aplicación Spring Boot, solo puede haber un único archivo `application.yaml`, `application.yml` o `application.properties` en el mismo nivel. Esto significa que si existe un
`application.yml` no podrán existir con el mismo nombre otros ficheros aunque tengan extensión distinta.
   


### ▫️ Motor de base de datos.
Con esto me refiero a un motor como MySQL en docker o alguna instancia disponible en red.
Es crucial conocer bien el nombre de la base de datos que deseamos atacar.
Además deberemos conocer los datos de acceso (usuario + contraseña).

### ▫️ Parametros de conexión en application.yaml
Conociendo usuario, contraseña y nombre de la base de datos podemos configurar el fichero `application.yaml` para la conexión.

**Ejemplo de configuración minima**
```yaml
spring:
  jpa.hibernate.ddl-auto: create
  datasource:
    url: jdbc:mysql://localhost:3306/mi_base_datos
    username: root
    password: 1234
```

### ▫️ Entidades / Modelos.
Las entidades o modelos son clases de Java que se _mapean_ a tablas en la base de datos mediante anotaciones de **Spring Data JPA**.
     
Para crear un modelo de tabla, usamos la anotación `@Entity`, que marca la clase como una entidad mapeada a una tabla relacional. Cada instancia de la clase representa una fila en dicha tabla.

```java
@Entity
public class TestObject
{
   // Ejemplo de clase entidad-tabla vacía.
}
```





---


**Ejemplo de yaml explicado**
IMPORTANTE: Nota como **NO SON TABULACIONES**. Cada indentación se realiza con un `doble espacio`.
```yaml
spring:
  jpa.hibernate.ddl-auto: create
  datasource:
    url: jbdc:mysql://localhost:3306/mi_base_datos
    username: root
    password: 1234
    hikari:
    connection-timeout: 60000
    maximum-pool-size: 5
server:
  port:8085
```
   
`jpa.hibernate.ddl-auto: create`   
Configura cómo Hibernate debe manejar el esquema de la base de datos al iniciar la aplicación. En este caso, el valor "create" significa que Hibernate eliminará y volverá a crear las tablas cada vez que se inicie la aplicación. (Otros valores posibles incluyen update, validate, etc.). Es útil en desarrollo, pero peligroso en producción ya que borra datos.
