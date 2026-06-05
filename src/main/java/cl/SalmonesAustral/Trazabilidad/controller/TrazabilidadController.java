package cl.SalmonesAustral.Trazabilidad.controller;

import org.springframework.web.bind.annotation.*;

import cl.SalmonesAustral.Trazabilidad.dto.*;
import cl.SalmonesAustral.Trazabilidad.mapper.TrazabilidadMapper;

import cl.SalmonesAustral.Trazabilidad.model.Trazabilidad;
import cl.SalmonesAustral.Trazabilidad.service.TrazabilidadService;

import java.util.List;




@RestController
@RequestMapping("/api/v1/trazabilidad")
public class TrazabilidadController {

    private final TrazabilidadService trazabilidadService;

    public TrazabilidadController(TrazabilidadService trazabilidadService) {
        this.trazabilidadService = trazabilidadService;
    }
    //obtener todo
    @GetMapping
    public List<Trazabilidad> getAllTrazabilidad() {
        return trazabilidadService.getTodoTrazabilidad();
    }
    //borrar
    @DeleteMapping("/{id}")
    public void deleteTrazabilidad(@PathVariable("id") int id) {
        trazabilidadService.deleteTrazabilidad(id);
    }
    //encontrar por id(tabla)
    @GetMapping("/{id}")
    public Trazabilidad getTrazabilidadById(@PathVariable("id") int id) {
        return trazabilidadService.getIdTrazabilidad(id);
    }

    //dto + Crear
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
        this.trazabilidadService.setIdTrazabilidad(trazabilidad);   //actualizar
        return trazabilidad;
    }
    //dto + Actualizar
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
        this.trazabilidadService.setIdTrazabilidad(trazabilidad);   //actualizar
        return trazabilidad;
    }
    
}
