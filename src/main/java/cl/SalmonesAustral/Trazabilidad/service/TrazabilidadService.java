package cl.SalmonesAustral.Trazabilidad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.SalmonesAustral.Trazabilidad.repository.TrazabilidadRepository;
import cl.SalmonesAustral.Trazabilidad.exception.ResourceNotFoundException;
import cl.SalmonesAustral.Trazabilidad.model.Trazabilidad;

@Service
public class TrazabilidadService {
    @Autowired
    private TrazabilidadRepository trazabilidadRepo;
    //obtener todo
    public List<Trazabilidad> getTodoTrazabilidad() {
        return trazabilidadRepo.findAll();
    }
    //obtener por id
    public Trazabilidad getIdTrazabilidad(int id){
        return trazabilidadRepo.findById(id).
        orElseThrow(() -> new ResourceNotFoundException("Trazabilidad no existente con este id: " + id));
    }
    //guardar
    public Trazabilidad setTrazabilidad(Trazabilidad trazabilidad){
        return trazabilidadRepo.save(trazabilidad);
    }
    //actualizar
    public Trazabilidad updateTrazabilidad(Trazabilidad trazabilidad){
        if(!trazabilidadRepo.existsById(trazabilidad.getId())){
            throw new ResourceNotFoundException("En trazabilidad no existe este id: " + trazabilidad.getId());
        }
        return trazabilidadRepo.save(trazabilidad);
    }
    //borrar
    public void deleteTrazabilidad(int id) {
        if(!trazabilidadRepo.existsById(id)){
            throw new ResourceNotFoundException("En trazabilidad no existe este id: " + id);
        }
        trazabilidadRepo.deleteById(id);
    }
}
