package ies.jandula.matriculas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.jandula.matriculas.repositories.IAlumnoRepository;

@Service
public class ParserAlumno
{
	
	@Autowired
	private IAlumnoRepository alumnoRepositorio;
	
	@Override
	public void parseaFichero( Scanner scanner )

}
