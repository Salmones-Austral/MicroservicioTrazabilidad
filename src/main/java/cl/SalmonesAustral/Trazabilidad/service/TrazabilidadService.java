package cl.SalmonesAustral.Trazabilidad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.SalmonesAustral.Trazabilidad.repository.TrazabilidadRepository;
import cl.SalmonesAustral.Trazabilidad.model.Trazabilidad;

@Service
public class TrazabilidadService {
    @Autowired
    private TrazabilidadRepository trazabilidadRepository;
    //obtener todo
    public List<Trazabilidad> getTodoTrazabilidad() {
        return trazabilidadRepository.findAll();
    }
    //borrar
    public void deleteTrazabilidad(int id) {
        trazabilidadRepository.deleteById(id);
    }
    //obtener por id o nulo
    public Trazabilidad getIdTrazabilidad(int variable)
    {
        return trazabilidadRepository.findById(variable).orElse(null);
    }
    //guardar o actualizar
    public void setIdTrazabilidad(Trazabilidad trazabilidad){
        trazabilidadRepository.save(trazabilidad);
    }

}
