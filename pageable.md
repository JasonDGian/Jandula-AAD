# 📌 Recuperación de datos paginados.
Es posible recuperar lotes de datos paginados, limitando su numero de elementos por pagina e indicando que página especificamente deseamos recuperar entre otras cosas.
**Para ello deberemos realizar los siguientnes pasos.**
0. Definir el modelo o DTO: Crea un objeto que represente los datos que deseas listar.
1. Método en el repositorio: Implementa un método con soporte para Pageable.
2. Controlador o servicio: Consume el método, recibe parámetros de paginación, y procesa el resultado.
3. Llamada al servicio o endpoint: Utiliza parámetros como page y size para personalizar la solicitud.
  
## 🔸 1. Crear el metodo que devuelve un listado de elementos paginados.
El primer paso es definir un método en tu repositorio que devuelva un listado paginado, idealmente usando un DTO para representar los datos.   
    
**Ejemplo de método en el repositorio:**        
```java
@Query("SELECT new es.iesjandula.aadrepaso.dto.EmpleadoDepartamentoDTO(em.nombre, d.nombreDepartamento) " +
       "FROM Empleados em JOIN em.idDepartamento d")
public Page<EmpleadoDepartamentoDTO> buscaEmpleadosYNombreDepartamentoPaginados(Pageable pageable);

```

## 🔸 2. Crear un Controlador o Servicio que Consuma el Método.
El controlador o servicio se encargará de consumir el método del repositorio y manejar los datos paginados utilizando el objeto Pageable.    
    
**Ejemplo controlador:**  
Define un endpoint que reciba los parámetros de paginación y retorne los datos paginados.
```java
	@GetMapping( value = "/empleadoydepartamento" )
	public Page<EmpleadoDepartamentoDTO> getEmpleadoDepartamento( 
			@PageableDefault( size = 5 ,page = 0 ) Pageable pageable)  
	{
		return iEmpleadosRepository.buscaEmpleadosYNombreDepartamentoPaginados( pageable );
	}
```

>[!Tip]
>`@PageableDefault`: Establece valores predeterminados para el tamaño de página (`size`) y el número de página (`page`).
    
 **Ejemplo servicio:**  
 Crea un servicio para procesar la lógica de paginación y manejar el resultado.   
 ```java
int tamanoPagina = 5;
int numeroPagina = 0;
Pageable pageable = PageRequest.of(numeroPagina, tamanoPagina);

System.out.println("------------------- IMPRESORA -------------------");

// Obtener datos paginados
Page<EmpleadoDepartamentoDTO> pagina = iEmpleRepo.buscaEmpleadosYNombreDepartamentoPaginados(pageable);

if (pagina.hasContent()) {
    pagina.forEach(x -> System.out.println(x.toString()));
} else {
    System.out.println("No se encontraron resultados para la página solicitada.");
}
```

>[!NOTE]
>Condicional para procesar datos paginados
> La estructura `if-else` verifica si una página contiene datos:
> - **`pagina.hasContent()`**: Comprueba si la página tiene elementos.
> - **`pagina.forEach(...)`**: Itera sobre los elementos si existen y los procesa.
> - **`else`**: Muestra un mensaje indicando que la página está vacía.
