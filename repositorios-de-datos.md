# 📌 ¿Qué es un repositorio?
Un repositorio es una interfaz que permite realizar operaciones CRUD con los modelos correspondientes. 

**Resumen con los pasos completos:**
- Disponer de un servidor de bases de datos.
- Configurar la conexión a la base de datos en application.yaml o application.properties.
- Crear modelos y entidades.
- Crear repositorios que extiendan JpaRepository
- Crear servicios que utilicen esos repositorios para manejar la lógica de negocio.
- (Opcional) Crear controladores para manejar solicitudes HTTP si estás creando una API o aplicación web.

# 📌 Como crear un repositorio.
Spring Data JPA simplifica muchisimo el proceo de ceración de un repositorio y nos ahora muchisimo código.
   
Para crear un repositorio de un modelo debemos crear una clase de tipo interfaz que extienda de `JpaRepository` definiendo como el tipo al tipo de dato a manejar.
**Ejemplo**
En este caso el modelo se llama Usuario y su ID es de tipo long.
```java
public interface NombreClaseRepository extends JpaRepository<NombreClase, Long>
```

1. **Entidad JPA:** Primero necesitas definir una entidad, que es una clase Java que está mapeada a una tabla de base de datos. Las clases de entidades deben estar anotadas con @Entity, y cada atributo de la clase se mapea a una columna de la tabla.    
2. **Interfaz del Repositorio JPA:** El repositorio es una interfaz que extiende de JpaRepository, la cual es proporcionada por Spring Data JPA. JpaRepository te proporciona una amplia variedad de métodos para realizar operaciones sobre la base de datos sin escribir SQL explícito. Al extender de esta interfaz, tu repositorio adquiere métodos CRUD y otros adicionales de manera automática.   
3. **Operaciones CRUD automáticas:** Al extender de JpaRepository, obtienes métodos CRUD predefinidos como:
- save(): Guarda una entidad en la base de datos (crear o actualizar).
- findById(): Busca una entidad por su ID.
- findAll(): Recupera todas las entidades de la tabla.
- deleteById(): Elimina una entidad por su ID.
- count(): Cuenta el número total de registros en la tabla.



**Ejemplo de repositorio.**   
En este ejemplo, PersonaRepository se conecta con la base de datos a través de JPA y te permite interactuar con la tabla Persona.
```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    // Puedes añadir métodos personalizados si lo necesitas
}
```
