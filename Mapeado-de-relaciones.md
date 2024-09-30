# Mapeado de relaciones entre clases.
Existen cuatro tipos de mapeado. 
Estos mapeos pueden ser uni-direccionales o bi-direccionales.
- 1:1
- 1:N / N:1
- N:M


## 📍 Relaciones 1:1 - @OneToOne
Para las relaciones 1:1 se utiliza la anotación `@OneToOne` en ambas clases relacionadas.

Para las relaciones 1:1 solo es necesario incluir en la tabla que absorbe la clave foránea la siguiente sintaxis.
```java

//Campo clave foranea realcion 1:1
@OneToOne(cascade = CascadeType.ALL) // especifica que existe una relación uno a uno 
@JoinColumn(name = "fk_id_tabla")
private Jugador jugador;

```
