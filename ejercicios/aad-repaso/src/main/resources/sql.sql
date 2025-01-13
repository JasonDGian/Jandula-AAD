SELECT * 
FROM puestos pu 
WHERE pu.id_puesto NOT IN (
	SELECT p.id_puesto 
	FROM puestos p 
	JOIN empleados e ON e.id_puesto = p.id_puesto
	) 