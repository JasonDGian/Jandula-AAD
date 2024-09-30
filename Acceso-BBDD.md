# 📌 Conectar una BBDD a un proyecto Spring Boot.
Para conectar una base de datos a un proyecto Spring Boot es necesario incluir las dependencias indicadas.   
En este ejemplo para la base de datos usamos un servidor de MySQL con la dependencia de `MySQL Driver` y `Spring Data JPA`.   
   
![imagen](https://github.com/user-attachments/assets/9162e9af-88d6-4077-80e9-552f0e10e986)

**¿Que es Spring Data JPA?**   
Spring Data JPA Es un Api de persistencia de datos de Spring Boot que facilita la creación de repositorios para realizar operaciones de persistencia (CRUD) sin necesidad de 
escribir manualmente consultas SQL o implementar las interfaces JPA básicas. Es estrictamente necesaria? NO, pero facilita muchisimo el trabajo y minimiza lineas de código a 
escribir para interactuar con la base de datos.

   
## 🔹 Fichero de configuración _application.yaml_ .

>[!IMPORTANT]
>Una vez lanzado el proyecto en eclipse, borramos el fichero `project.properties` para utilizar en su lugar un fichero `application.yml`
>   
>![imagen](https://github.com/user-attachments/assets/2e848b19-92b0-43db-97cb-6071d8bd9a0d)

    
Los ficheros de propiedades son los que permiten configurar el arranque de nuestra aplicación en Spring Boot. Sin ellos, no podríamos especificar dónde está nuestra BBDD, 
en qué puerto arranca nuestro microservicio, qué nivel de logs tendrá nuestra aplicación, etcétera. Es posible tener varios ficheros de configuración para distintos "contextos" 
o entornos, pero es necesario distinguirlos. Para diferenciarlos, se suelen usar nombres como `application-HOME.yml` o `application-HOSTING.yml`, y según el contexto en que se lance 
la aplicación, se especifica qué fichero de configuración utilizar.
    
>[!Caution]
>En una aplicación Spring Boot, solo puede haber un único archivo `application.yaml`, `application.yml` o `application.properties` en el mismo nivel. Esto significa que si existe un
`application.yml` no podrán existir con el mismo nombre otros ficheros aunque tengan extensión distinta.
   

## 🔹 Flujo de trabajo para configurar una conexión.
1. Crear la base de datos en el servidor.
   - Asegurarse del nombre de la base de datos.
2. Configurar los parametros de conexión en el fichero de configuración yaml.
3. Crear los objetos de persistencia en Java.


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
