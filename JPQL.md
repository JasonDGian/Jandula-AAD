# 📌 Funciones JPQL.
En el lenguaje de Spring Data JPA podemos utilizar funciones en las consultas personalizadas, a continuación un listado de funciones utiles.

## 📍 Funciones de Agregación

#### 🔸 COUNT
Cuenta el número de registros que coinciden con una condición.
```jpql
SELECT COUNT(e) FROM Employee e
```

#### 🔸 SUM
Calcula la suma de valores numéricos de una columna.
```jpql
SELECT SUM(e.salary) FROM Employee e
```

#### 🔸 AVG
Calcula el promedio de valores numéricos de una columna.
```jpql
SELECT AVG(e.salary) FROM Employee e
```
#### 🔸 MAX
Obtiene el valor máximo de una columna.
```jpql
SELECT MAX(e.salary) FROM Employee e
```
#### 🔸 MIN
Obtiene el valor mínimo de una columna.
```jpql
SELECT MIN(e.salary) FROM Employee e
```


## 📍 Funciones de Manipulación de Strings
6. CONCAT: Combina dos o más cadenas.
Ejemplo: SELECT CONCAT(e.firstName, ' ', e.lastName) FROM Employee e

    SUBSTRING: Extrae una parte de una cadena.
    Ejemplo: SELECT SUBSTRING(e.firstName, 1, 3) FROM Employee e

    TRIM: Elimina espacios al inicio o al final de una cadena.
    Ejemplo: SELECT TRIM(e.firstName) FROM Employee e

    UPPER: Convierte una cadena a mayúsculas.
    Ejemplo: SELECT UPPER(e.firstName) FROM Employee e

    LOWER: Convierte una cadena a minúsculas.
    Ejemplo: SELECT LOWER(e.firstName) FROM Employee e

    LENGTH: Devuelve la longitud de una cadena.
    Ejemplo: SELECT LENGTH(e.firstName) FROM Employee e

Funciones de Fechas
12. CURRENT_DATE: Obtiene la fecha actual.
Ejemplo: SELECT CURRENT_DATE FROM Employee e

    CURRENT_TIME: Obtiene la hora actual.
    Ejemplo: SELECT CURRENT_TIME FROM Employee e

    CURRENT_TIMESTAMP: Obtiene la fecha y hora actual.
    Ejemplo: SELECT CURRENT_TIMESTAMP FROM Employee e

Funciones Matemáticas
15. ABS: Devuelve el valor absoluto.
Ejemplo: SELECT ABS(e.salary) FROM Employee e

    SQRT: Calcula la raíz cuadrada.
    Ejemplo: SELECT SQRT(e.salary) FROM Employee e

    MOD: Calcula el resto de una división.
    Ejemplo: SELECT MOD(e.salary, 1000) FROM Employee e

Otras Funciones
18. COALESCE: Devuelve el primer valor no nulo de una lista de expresiones.
Ejemplo: SELECT COALESCE(e.manager, 'No Manager') FROM Employee e

    NULLIF: Compara dos valores y devuelve NULL si son iguales; de lo contrario, devuelve el primer valor.
    Ejemplo: SELECT NULLIF(e.salary, 0) FROM Employee e
