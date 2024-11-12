package ies.jandula.ejercicio_pag_12.service;

import java.util.Scanner;

import ies.jandula.ejercicio_pag_12.utils.UniversidadServerError;

public interface IParserCurso
{
	public void parseaFichero(Scanner scanner) throws UniversidadServerError;
}
