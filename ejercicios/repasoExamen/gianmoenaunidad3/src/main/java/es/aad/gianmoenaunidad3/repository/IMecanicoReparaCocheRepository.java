package es.aad.gianmoenaunidad3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.aad.gianmoenaunidad3.models.MecanicoReparaCoche;
import es.aad.gianmoenaunidad3.models.MecanicoReparaCocheId;

@Repository
public interface IMecanicoReparaCocheRepository extends JpaRepository<MecanicoReparaCoche, MecanicoReparaCocheId>
{

}
