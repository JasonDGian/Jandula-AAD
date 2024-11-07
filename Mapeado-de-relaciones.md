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

   
---
# 📌 Tipos de relaciones.  
Las relaciones pueden ser de los siguientes tipos: 
- 1:1 · Unidireccional.
- 1:1 · Bidireccional.
- 1:n · Unidireccional.
- 1:n · Bidireccional.
- n:1 · Unidireccional.
- n:1 · Bidireccional.
- n:m · Unidireccional.
- n:m · Bidireccional.
   
## 📍 Relaciones 1:1 - @OneToOne UNIDIRECCIONAL
En las relaciones **Unidireccionales 1:1** solo una de las entidades contiene la relación y no se declara un `mappedBy`.
   
<!-- <p align="center"> 
  <img src="https://github.com/user-attachments/assets/9a865aa8-5ff9-4f36-a823-37f3d2122052">
</p>   -->

<p align="center"> 
  <img src="https://github.com/user-attachments/assets/f1a9b8c0-e6bc-4d3d-b202-d19415fc116a">
</p>   

En la entidad que carga con la relación se añade este snippet:  
**Sintaxis:**   
```java
@OneToOne
// Especifica el nombre de la columna de la clave foránea que establecerá la relación entre las tablas.
@JoinColumn( name = "nombre_columna_FK" ) 
private Entidad entidad;
```

**Ejemplo**
```java
@OneToOne
@JoinColumn( name = "id_departamento" )
private Departamento departamento;
```

## 📍 Relaciones 1:1 - @OneToOne BIDIRECCIONAL
Solo una contiene la referencia a la clave foránea, mientras que la otra entidad será mapeada utilizando la propiedad `mappedBy`, que indica qué atributo en la entidad opuesta gestiona la relación.

- La entidad que gestiona la relación utiliza `@JoinColumn` para indicar la clave foránea en la base de datos.
- La entidad que no gestiona la relación utiliza `mappedBy` para indicar qué atributo de la otra entidad controla la relación.

Esta estructura bidireccional permite navegar en ambas direcciones entre las dos entidades, manteniendo una conexión lógica en la base de datos.

<p align="center"> 
  <img src="https://github.com/user-attachments/assets/09440764-7d79-421e-b108-dda72d5c514c">
</p> 

**En la entidad que carga con la relación - columna FK.**
**Sintaxis:**   
```java
@OneToOne
@JoinColumn(name = "nombre_columna_FK") // Define la columna que actúa como clave foránea en la tabla.
private EntidadB entidadB;
```

**Ejemplo**
```java
@OneToOne
@JoinColumn(name = "id_departamento") // La tabla de esta entidad tendrá la columna 'id_departamento'.
private Departamento departamento;

```

**En la entidad que NO carga con la relación - mapeado.**
En la entidad opuesta, donde NO se almacena la clave foránea, se usa `mappedBy` para especificar el atributo que controla la relación en la otra entidad.   
**Sintaxis:**   
```java
@OneToOne(mappedBy = "atributo_que_contiene_FK_en_otra_entidad") // Relaciona esta entidad con el atributo que contiene la clave foránea.
private EntidadA entidadA;
```

**Ejemplo**
```java
@OneToOne(mappedBy = "departamento") // 'departamento' es el atributo que contiene la clave foránea en 'Empleado'.
private Empleado empleado;
```
   
## 📍 Relaciones 1:n - @OneToMany UNIDIRECCIONAL   
En este tipo de relación, una entidad puede estar relacionada con múltiples instancias de otra entidad, pero la entidad "muchos" no tiene un vínculo de vuelta con la entidad "uno". 
Esto significa que desde la perspectiva de la entidad "uno", puedes acceder a todas las instancias de la entidad "muchos", pero la entidad "muchos" no puede referirse a la entidad "uno".

<p align="center"> 
  <img src="https://github.com/user-attachments/assets/e96b3dae-5c97-40cc-8210-de093c2d7e70">
</p> 

**En la entidad que carga con  "los muchos".**

**Sintaxis:**   
```java
@OneToMany
@JoinColumn( "id_columna" )
private List<EntidadB> entidadB;
```

**Ejemplo**
```java
@OneToMany
@JoinColumn( "cliente_id" )
private List<Pedido> pedidos;
```
    
## 📍 Relaciones 1:n - @OneToMany BIDIRECCIONAL   
En este tipo de relación, una entidad puede estar relacionada con múltiples instancias de otra entidad. Al mismo tiempo, cada entidad en el lado "muchos" tiene una referencia de vuelta a la entidad "uno". Esta estructura es útil cuando se necesita acceder a ambas partes de la relación desde cualquier lado.
   
<p align="center"> 
  <img src="https://github.com/user-attachments/assets/e062d1b3-1864-4685-b5ea-662888b51053">
</p> 



   


- @OneToOne: Relación uno a uno.
- @OneToMany: Relación uno a muchos.
- @ManyToOne: Relación muchos a uno.
- @ManyToMany: Relación muchos a muchos.
  
- @JoinColumn: Especifica la clave foránea.
- @JoinTable: Define una tabla intermedia en relaciones ManyToMany.
- @MappedBy: Especifica el lado inverso de una relación bidireccional.
  
- @Cascade: Controla las operaciones en cascada.
- @Fetch: Controla la estrategia de carga (lazy o eager).











