# 📍 Dirección de relaciones.
En **Spring Data JPA**, las relaciones entre entidades pueden ser **unidireccionales** o **bidireccionales**, y la diferencia principal yace 
en cómo se _navega_ o accede a las entidades involucradas en la relación.

## 🔹 Relación Unidireccional.
**Solo una de las entidades conoce la relación** y puede acceder a la otra entidad.   
La segunda entidad no sabe nada sobre la primera entidad.
- Solo una de las entidades "conoce" la relación.
- No existe una referencia en sentido inverso (A hacia B pero no al revés).
- Si la entidad A tiene una relación con la entidad B, solo A conoce y puede acceder a B, pero B no sabe nada de A.
- Solo A contiene la clave foránea, lo que significa que la clave que une las dos tablas (entidades) está almacenada solo en la tabla correspondiente a A.

## 🔹 Relación Bidireccional.
En una relación bidireccional, **ambas entidades pueden acceder a la otra**, pero solo una contiene la clave foránea.
- Ambas entidades conocen la relación, lo que permite navegar en ambos sentidos.
- Solo una entidad gestiona la clave foránea, y la otra referencia esta relación con la anotación `mappedBy`.
- El acceso es bidireccional, pero la clave que une las entidades está solo en una tabla.





# 📍 Como mapear relaciones.
Para establecer relaciones entre entidades se hace uso de las anotaciones.


# 📍 Comportamiento de las relaciones.


## 🔹 Relaciones 1:1 - @OneToOne
Para las relaciones 1:1 se utiliza la anotación `@OneToOne` en ambas clases relacionadas.

Para las relaciones 1:1 solo es necesario incluir en la tabla que absorbe la clave foránea la siguiente sintaxis.
```java

//Campo clave foranea realcion 1:1
@OneToOne(cascade = CascadeType.ALL) // especifica que existe una relación uno a uno 
@JoinColumn(name = "fk_id_tabla")
private Jugador jugador;

```


## 📍 Relaciones


## 📍 Relaciones 1:1 - @OneToOne UNIDIRECCIONAL
En las relaciones **Unidireccionales 1:1** solo una de las entidades contiene la relación.
   
<p align="center"> 
  <img src="https://github.com/user-attachments/assets/9a865aa8-5ff9-4f36-a823-37f3d2122052">
</p>   


En la entidad que carga con la relación se añade este snippet:  
**Sintaxis:**   
```java
@OneToOne
@JoinColumn( name = "nombre_columna_FK" ) // Especifica el nombre de la columna de la clave foránea que establecerá la relación entre las tablas.
private Entidad entidad;
```

**Ejemplo**
```java
@OneToOne
@JoinColumn( name = "id_departamento" )
private Departamento departamento;
```

## 📍 Relaciones 1:1 - @OneToOne BIDIRECCIONAL
En las relaciones **Bidireccionales 1:1** solo una de las entidades contiene la relación.

