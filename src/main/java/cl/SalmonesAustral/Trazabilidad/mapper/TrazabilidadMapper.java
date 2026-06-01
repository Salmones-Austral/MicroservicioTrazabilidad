package cl.SalmonesAustral.Trazabilidad.mapper;

import cl.SalmonesAustral.Trazabilidad.dto.*;
import cl.SalmonesAustral.Trazabilidad.model.Trazabilidad;

public class TrazabilidadMapper {

    public static Trazabilidad toTrazabilidadCreate(CreateTrazabilidadRequest request) {
        Trazabilidad trazabilidad = new Trazabilidad();
        //el id no va en create al ser autogenerado
        trazabilidad.setIdTrazabilidad(request.idTrazabilidad());
        trazabilidad.setJaulaId(request.jaulaId());
        trazabilidad.setTipoEvento(request.tipoEvento());
        trazabilidad.setDescripcion(request.descripcion());
        trazabilidad.setOrigen(request.origen());
        trazabilidad.setReferenciaId(request.referenciaId());
        trazabilidad.setFecha(request.fecha());
        return trazabilidad;
    }

    public static Trazabilidad toTrazabilidadUpdate(int id,UpdateTrazabilidadRequest request) {
        Trazabilidad trazabilidad = new Trazabilidad();
        //el id sera el endpoint
        trazabilidad.setIdTrazabilidad(request.idTrazabilidad());
        trazabilidad.setJaulaId(request.jaulaId());
        trazabilidad.setTipoEvento(request.tipoEvento());
        trazabilidad.setDescripcion(request.descripcion());
        trazabilidad.setOrigen(request.origen());
        trazabilidad.setReferenciaId(request.referenciaId());
        trazabilidad.setFecha(request.fecha());
        return trazabilidad;
    }

}
