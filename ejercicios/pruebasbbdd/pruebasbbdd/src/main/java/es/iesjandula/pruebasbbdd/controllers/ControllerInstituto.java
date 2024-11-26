package es.iesjandula.pruebasbbdd.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import es.iesjandula.pruebasbbdd.repositories.IAlumnoRepository;
import jakarta.annotation.PostConstruct;

public class ControllerInstituto
{

	@Autowired
	IAlumnoRepository alumnoRepository;

	@PostConstruct
	public void init()
	{
		System.out.println(this.alumnoRepository.findByEdadBetween(10, 6));
		System.out.println(this.alumnoRepository.findByAprobadoFalse());

	}

}
