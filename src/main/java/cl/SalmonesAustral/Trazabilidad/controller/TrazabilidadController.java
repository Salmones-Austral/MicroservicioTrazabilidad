package cl.SalmonesAustral.Trazabilidad.controller;

import org.springframework.web.bind.annotation.*;

import cl.SalmonesAustral.Trazabilidad.client.*;
import cl.SalmonesAustral.Trazabilidad.dto.*;
import cl.SalmonesAustral.Trazabilidad.mapper.TrazabilidadMapper;

import cl.SalmonesAustral.Trazabilidad.model.Trazabilidad;
import cl.SalmonesAustral.Trazabilidad.service.TrazabilidadService;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;


@RestController
@RequestMapping("/api/v1/trazabilidad")
public class TrazabilidadController {

    private final TrazabilidadService trazabilidadService;
    private final CriaderoClient criaderoClient;
    private final JaulaClient jaulaClient;
    private final MortalidadClient mortalidadClient;
    private final SanitarioClient sanitarioClient;

    public TrazabilidadController(
        TrazabilidadService trazabilidadService,
        CriaderoClient criaderoClient,
        JaulaClient jaulaClient,
        MortalidadClient mortalidadClient,
        SanitarioClient sanitarioClient) {
        this.trazabilidadService = trazabilidadService;
        this.criaderoClient = criaderoClient;
        this.jaulaClient = jaulaClient;
        this.mortalidadClient = mortalidadClient;
        this.sanitarioClient = sanitarioClient;
    }
    //Todo
    @GetMapping
    public List<Trazabilidad> getAllTrazabilidad() {
        return trazabilidadService.getTodoTrazabilidad();
    }
    //eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrazabilidad(@PathVariable Integer id) {
        trazabilidadService.deleteTrazabilidad(id);
        return ResponseEntity.noContent().build();
    }
    //filtrar por id
    @GetMapping("/{id}")
    public ResponseEntity<Trazabilidad> getIdTrazabilidad(@PathVariable Integer id) {
        Trazabilidad trazabilidad = trazabilidadService.getIdTrazabilidad(id);
        return ResponseEntity.ok(trazabilidad);
    }
    //crear
    @PostMapping
    public ResponseEntity<?> setTrazabilidadCreate(@Valid @RequestBody CreateTrazabilidadRequest createTrazabilidad, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(error -> 
                errores.put(error.getField(), error.getDefaultMessage())
            );
            return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
        }
        Trazabilidad trazabilidad = TrazabilidadMapper.toTrazabilidadCreate(createTrazabilidad);
        Trazabilidad guardar = trazabilidadService.setTrazabilidad(trazabilidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardar);
    }
    //Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTrazabilidad(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateTrazabilidadRequest updateTrazabilidad, BindingResult result) {

        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(error -> 
                errores.put(error.getField(), error.getDefaultMessage())
            );
            return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
        }
        Trazabilidad trazabilidad = TrazabilidadMapper.toTrazabilidadUpdate(id, updateTrazabilidad);
        Trazabilidad actualizado = trazabilidadService.updateTrazabilidad(trazabilidad);
        return ResponseEntity.ok(actualizado);
    }    
    ////////////////////Metodos custom/////////////////////////////
    @PostMapping("/informes")
    public ResponseEntity<?> generarInforme(@RequestBody CrearInformeRequest request) { 
        try {
            //de client llamamos a los metodos que llaman a los valores que necesitaremos
            String nombreCriadero = criaderoClient.obtenerNombreCriadero(request.getIdCriadero());
            String nombreJaula = jaulaClient.obtenerNombreJaula(request.getIdJaula());
            Double porcentajeMortalidad = mortalidadClient.obtenerPorcentajeMortalidad(request.getIdMortalidad());
            ExSanitarioDto datosSanitarios = sanitarioClient.obtenerDatosSanitario(request.getIdSanitario());
            //Control de errores
            Map<String, String> controlErrores = new HashMap<>();
            if (nombreJaula == null || nombreJaula.contains("Desconocida") || nombreJaula.contains("no encontrado")) {
                controlErrores.put("jaula", "La jaula con ID " + request.getIdJaula() + " no existe.");
            }
            if (nombreCriadero == null || nombreCriadero.contains("Desconocida") || nombreCriadero.contains("no encontrado")) {
                controlErrores.put("criadero", "El criadero con ID " + request.getIdCriadero() + " no existe.");
            }
            if (porcentajeMortalidad == null || porcentajeMortalidad == 0.0) {
                controlErrores.put("mortalidad", "El registro de mortalidad con ID " + request.getIdMortalidad() + " no existe o devolvió un error.");
            }
            if (datosSanitarios == null || datosSanitarios.getId() == null) {
                controlErrores.put("sanitario", "El registro sanitario con ID " + request.getIdSanitario() + " no existe.");
            }
            if (!controlErrores.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(controlErrores);
            }
            //si todo esta correcto, pasa a crear el informe
            CrearInformeResponse informeFinal = new CrearInformeResponse(
                nombreCriadero, 
                nombreJaula, 
                porcentajeMortalidad, 
                datosSanitarios
            );
            return ResponseEntity.ok(informeFinal);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("mensaje", "Ocurrió un error al generar el informe");
            errorResponse.put("detalle", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
