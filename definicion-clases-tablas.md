# 📌 Definición de entidades con claves compuestas usando @IdClass..
Para crear entidades de claves compuestas con este mecanismo de JPA, **debemos definir una nueva clase que recoja solo los atributos identificativos de la entidad**. En esta clase no van anotados limites o restricciones. 
Los limites como longitud, posibilidad de nulo etc.. deberán de ser anotados en la clase con anotacion `@Entity`.   

>[!WARNING]
>En la clase entity se recogen todos los campos presentes en la clase compuesta y se le aplican las restricciones necesarias.
   
**Ejemplo de clase `@Entity` con clave compuesta por `@IdClass`.**

```java
@Data // Crea los setters, getters, hash e equals.
@AllArgsConstructor
@NoArgsConstructor

// Señala la clase como una entidad para bases de datos.
@Entity

// Anotación que permite cambiar el nombre de la tabla según deseado.
@Table(name = "ejemplo_entidad")

// Especifica la clase compuesta que contiene los atributos identificativos de la entidad.
@IdClass(EjemploEntidadId.class)

// Clase que recoge los atributos de la entidad.
public class EjemploEntidad
{
  @Id
  @Column( name = "fecha_creacion")
  private Date fecha;

  @Id
  @Column( name = "nombre_producto", nullable = false )
  private String nombre;

  @Column
  private String descripcion;

}
```
   
**Clase de Clave compuesta**
```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EjemploEntidadId implements Serializable
{
  private static final long serialVersionUID = 1L;
  private Date fecha;
  private String nombre;
}
```


