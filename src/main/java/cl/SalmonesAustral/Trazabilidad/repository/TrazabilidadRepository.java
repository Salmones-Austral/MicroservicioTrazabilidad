package cl.SalmonesAustral.Trazabilidad.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import cl.SalmonesAustral.Trazabilidad.model.Trazabilidad;

@Repository
public interface TrazabilidadRepository extends JpaRepository<Trazabilidad, Integer> {

}
