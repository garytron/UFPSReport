package Util;

import java.util.Date;

/**
 * Created by acer on 18/09/2017.
 */

public class Reporte {
    private Integer id_reporte;
    private Integer clase;
    private String estado;
    private Date fecha;
    private String instrumento;

    public Integer getClase() {
        return clase;
    }

    public void setClase(Integer clase) {
        this.clase = clase;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(String instrumento) {
        this.instrumento = instrumento;
    }

    public String getInstrumentoNombre() {
        return instrumentoNombre;
    }

    public void setInstrumentoNombre(String instrumentoNombre) {
        this.instrumentoNombre = instrumentoNombre;
    }

    public String getTipoDescripcion() {
        return tipoDescripcion;
    }

    public void setTipoDescripcion(String tipoDescripcion) {
        this.tipoDescripcion = tipoDescripcion;
    }

    private String instrumentoNombre;
    private String tipoDescripcion;

    public Integer getId_reporte() {
        return id_reporte;
    }

    public void setId_reporte(Integer id_reporte) {
        this.id_reporte = id_reporte;
    }

    @Override
    public String toString() {
        return
                "Reporte Nro=" + id_reporte +
                ", Fecha=" + fecha.getDay()+" de" +
                        fecha.getMonth()+"  "+fecha.getHours()+":"+fecha.getMinutes() +
                ", Instrumento='" + instrumento +
                " Nro='" + instrumentoNombre  +
                ",tipoDescripcion=" + tipoDescripcion + '\''
                ;
    }
}
