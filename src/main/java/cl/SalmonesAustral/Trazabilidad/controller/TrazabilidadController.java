package cl.SalmonesAustral.Trazabilidad.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.SalmonesAustral.Trazabilidad.dto.*;
import cl.SalmonesAustral.Trazabilidad.mapper.TrazabilidadMapper;

import cl.SalmonesAustral.Trazabilidad.model.Trazabilidad;
import cl.SalmonesAustral.Trazabilidad.service.TrazabilidadService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/v1/trazabilidad")
public class TrazabilidadController {

    private final TrazabilidadService trazabilidadService;

    public TrazabilidadController(TrazabilidadService trazabilidadService) {
        this.trazabilidadService = trazabilidadService;
    }

    @GetMapping
    public List<Trazabilidad> getAllTrazabilidad() {
        return trazabilidadService.getAllTrazabilidad();
    }
    //dto Crear
    @PostMapping
    public Trazabilidad setTrazabilidadCreate(@RequestBody CreateTrazabilidadRequest createTrazabilidad) {
        Trazabilidad trazabilidad = TrazabilidadMapper.toTrazabilidadCreate(createTrazabilidad);
        trazabilidad.getId();
        trazabilidad.getIdTrazabilidad();
        trazabilidad.getJaulaId();
        trazabilidad.getTipoEvento();
        trazabilidad.getDescripcion();
        trazabilidad.getOrigen();
        trazabilidad.getReferenciaId();
        trazabilidad.getFecha();
        this.trazabilidadService.setIdTrazabilidad(trazabilidad);
        return trazabilidad;
    }
    //dto Actualizar
    @PutMapping("/{id}")
    public Trazabilidad updateTrazabilidad(
        @PathVariable("id") int id, 
        @RequestBody UpdateTrazabilidadRequest updateTrazabilidad) {
        Trazabilidad trazabilidad = TrazabilidadMapper.toTrazabilidadUpdate(id, updateTrazabilidad);
        trazabilidad.getId();
        trazabilidad.getIdTrazabilidad();
        trazabilidad.getJaulaId();
        trazabilidad.getTipoEvento();
        trazabilidad.getDescripcion();
        trazabilidad.getOrigen();
        trazabilidad.getReferenciaId();
        trazabilidad.getFecha();
        this.trazabilidadService.setIdTrazabilidad(trazabilidad);
        return trazabilidad;
    }
    
}
