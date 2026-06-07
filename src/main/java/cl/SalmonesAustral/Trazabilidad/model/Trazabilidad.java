package cl.SalmonesAustral.Trazabilidad.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TablaTrazabilidad")
public class Trazabilidad {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="tipoEvento",nullable=false, length = 100)
    private String tipoEvento;

    @Column(name="descripcion",nullable=false, length = 255)
    private String descripcion;
    
    @Column(name="origen",nullable=false, length = 100)
    private String origen;

    @Column(name="referenciaId",nullable=false, length = 20)
    private int referenciaId;

    @Column(name="fecha",nullable=false, length = 20)
    private String fecha;
/////////////////////////////////////////////////////////
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id= id;
    }

    public String getTipoEvento(){
        return tipoEvento;
    }
    public void setTipoEvento(String tipoEvento){
        this.tipoEvento= tipoEvento;
    }

    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String descripcion){
        this.descripcion= descripcion;
    }

    public String getOrigen(){
        return origen;
    }
    public void setOrigen(String origen){
        this.origen= origen;
    }

    public int getReferenciaId(){
        return referenciaId;
    }
    public void setReferenciaId(int referenciaId){
        this.referenciaId= referenciaId;
    }

    public String getFecha(){
        return fecha;
    }
    public void setFecha(String fecha){
        this.fecha= fecha;
    }
}
