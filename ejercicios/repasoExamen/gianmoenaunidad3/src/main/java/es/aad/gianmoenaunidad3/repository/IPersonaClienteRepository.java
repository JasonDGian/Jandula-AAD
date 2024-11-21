package es.aad.gianmoenaunidad3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.aad.gianmoenaunidad3.models.PersonaCliente;
@Repository
public interface IPersonaClienteRepository extends JpaRepository<PersonaCliente, String>
{

}
