# 📌 Fichero de configuracion de arranque de la aplicación.
Este es el fichero que lee springboot al arrancar para saber como configurar ciertos parametros.

Para ejecutar en un contexto de ejcucion especifico, de debe de parametrizar el inicio.
Esto se hace 


1. primera ejecuccion.
La primera ejecucion normal para generar las "tablas de ejecucion".


2. run as -> run config -> buscar el lanzador interesado. -> introducir los parametros -> resultado: generacion de un perfil configurado de "run as".
![image](https://github.com/user-attachments/assets/8099f924-c3da-44ca-b11e-d794ab2d0651)
![image](https://github.com/user-attachments/assets/19cc5268-2a7d-45f7-ae58-3ab5e89d2d58)
![image](https://github.com/user-attachments/assets/cd1710af-c206-4a3d-89b6-3097c1c192f8)


Los ficheros de application.yaml pueden ser varios y cada uno para distintos entornos, desarrollo, producttion, testing etc... 
Por defecto, si no especificamos nada en el codigo de la aplicacion, se buscará el "application.yaml" a secas, pero podemos especificar distinso nombres y llamarlos segun el entorno dependiendo del entorno de ejecucion.
La eleccion del fichero de configuración se realiza mediante la especificacion del sufijo del fichero. Esto se hace mediante la especificacion del flag `--spring.profiles.active=PRE`

# 📌 variables en yaml y valores personalizados.
```yaml
propiedadnombre:
  variable1: valor1
  variable2: valor2
```

# propiedades de hibernate
none   
validate   
create   
update   
create-drop

# Logeado desde el yaml

# configuracion de concurrencia
pool
slots
conexiones


# logger
niveles de logueo
como configurar los niveles
## configuracion de log por paqueteria o libreria concreta.

## configuracion del fichero de registro.

