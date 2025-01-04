

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
#### 🔸 CONCAT
Combina dos o más cadenas.
Ejemplo: SELECT CONCAT(e.firstName, ' ', e.lastName) FROM Employee e

#### 🔸 SUBSTRING
Extrae una parte de una cadena.
```jpql
SELECT SUBSTRING(e.firstName, 1, 3) FROM Employee e
```
#### 🔸 TRIM
Elimina espacios al inicio o al final de una cadena.
```jpql
SELECT TRIM(e.firstName) FROM Employee e
```
#### 🔸 UPPER
Convierte una cadena a mayúsculas.
```jpql
SELECT UPPER(e.firstName) FROM Employee e
```
#### 🔸 LOWER
Convierte una cadena a minúsculas.
```jpql
SELECT LOWER(e.firstName) FROM Employee e
```
#### 🔸 LENGTH
Devuelve la longitud de una cadena.
```jpql
SELECT LENGTH(e.firstName) FROM Employee e
```

## 📍 Funciones de Fechas
#### 🔸 CURRENT_DATE
Obtiene la fecha actual.
```jpql
SELECT CURRENT_DATE FROM Employee e
```
#### 🔸 CURRENT_TIME
Obtiene la hora actual.
```jpql
SELECT CURRENT_TIME FROM Employee e
```
#### 🔸 CURRENT_TIMESTAMP
Obtiene la fecha y hora actual.
```jpql
SELECT CURRENT_TIMESTAMP FROM Employee e
```

## 📍 Funciones Matemáticas
#### 🔸 ABS
Devuelve el valor absoluto.
```jpql
SELECT ABS(e.salary) FROM Employee e
```

#### 🔸 SQRT
Calcula la raíz cuadrada.
```jpql
SELECT SQRT(e.salary) FROM Employee e
```

#### 🔸 MOD
Calcula el resto de una división.
```jpql
SELECT MOD(e.salary, 1000) FROM Employee e
```

## 📍 Otras Funciones
#### 🔸 COALESCE
Devuelve el primer valor no nulo de una lista de expresiones.
```jpql
SELECT COALESCE(e.manager, 'No Manager') FROM Employee e
```

#### 🔸 NULLIF
Compara dos valores y devuelve NULL si son iguales; de lo contrario, devuelve el primer valor.
```jpql
SELECT NULLIF(e.salary, 0) FROM Employee e
```

# 📌 Retornos de  las funciones JPQL.
<table border="1">
    <thead>
        <tr>
            <th>Función JPQL</th>
            <th>Tipo de Dato Retornado en Java</th>
            <th>Descripción</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>COUNT</td>
            <td>Long</td>
            <td>Devuelve el número de registros.</td>
        </tr>
        <tr>
            <td>SUM</td>
            <td>Double, Long, BigDecimal</td>
            <td>Devuelve la suma de los valores (según el tipo de columna).</td>
        </tr>
        <tr>
            <td>AVG</td>
            <td>Double</td>
            <td>Devuelve el promedio de los valores.</td>
        </tr>
        <tr>
            <td>MAX</td>
            <td>Mismo tipo que la columna consultada</td>
            <td>Retorna el valor máximo en el conjunto de datos.</td>
        </tr>
        <tr>
            <td>MIN</td>
            <td>Mismo tipo que la columna consultada</td>
            <td>Retorna el valor mínimo en el conjunto de datos.</td>
        </tr>
        <tr>
            <td>LENGTH</td>
            <td>Integer</td>
            <td>Devuelve la longitud de la cadena.</td>
        </tr>
        <tr>
            <td>CURRENT_DATE</td>
            <td>java.sql.Date</td>
            <td>Devuelve la fecha actual (solo día, mes y año).</td>
        </tr>
        <tr>
            <td>CURRENT_TIME</td>
            <td>java.sql.Time</td>
            <td>Devuelve la hora actual (solo horas, minutos, segundos).</td>
        </tr>
        <tr>
            <td>CURRENT_TIMESTAMP</td>
            <td>java.sql.Timestamp</td>
            <td>Devuelve la fecha y hora actual.</td>
        </tr>
        <tr>
            <td>ABS</td>
            <td>Mismo tipo que la columna consultada</td>
            <td>Devuelve el valor absoluto.</td>
        </tr>
        <tr>
            <td>SQRT</td>
            <td>Double</td>
            <td>Devuelve la raíz cuadrada del valor.</td>
        </tr>
        <tr>
            <td>MOD</td>
            <td>Mismo tipo que la columna consultada</td>
            <td>Devuelve el resto de una división.</td>
        </tr>
        <tr>
            <td>COALESCE</td>
            <td>Mismo tipo que las columnas evaluadas</td>
            <td>Devuelve el primer valor no nulo en la lista.</td>
        </tr>
        <tr>
            <td>NULLIF</td>
            <td>Mismo tipo que las columnas evaluadas</td>
            <td>Devuelve NULL si los valores son iguales; de lo contrario, el primer valor.</td>
        </tr>
    </tbody>
</table>
