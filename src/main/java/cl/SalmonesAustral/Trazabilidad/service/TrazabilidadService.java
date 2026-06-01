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

    public List<Trazabilidad> getAllTrazabilidad() {
        return trazabilidadRepository.findAll();
    }
    //
    public Trazabilidad getIdTrazabilidad(int variable)
    {
        return trazabilidadRepository.findById(variable).orElse(null);
    }
    //
    public void setIdTrazabilidad(Trazabilidad trazabilidad){
        trazabilidadRepository.save(trazabilidad);
    }

}
