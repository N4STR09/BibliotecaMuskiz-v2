package Classes.model;

import java.time.LocalDate;

public class Penalizacion {
    private int codPenalizacion;
    private String tipoPenalizacion;
    private int tiempoPenalizacion;
    private LocalDate fechaPenalizacion;
    private boolean multa;
    private int cantidadMulta;

    public Penalizacion(int codPenalizacion, String tipoPenalizacion, int tiempoPenalizacion, LocalDate fechaPenalizacion, boolean multa, int cantidadMulta) {
        this.codPenalizacion = codPenalizacion;
        this.tipoPenalizacion = tipoPenalizacion;
        this.tiempoPenalizacion = tiempoPenalizacion;
        this.fechaPenalizacion = fechaPenalizacion;
        this.multa = multa;
        this.cantidadMulta = cantidadMulta;
    }

    //metodos

    public void noMulta() {
        if (isMulta()) {
            setCantidadMulta(0);
        }
    }

    //getters y setters
    public boolean isMulta() {
        return multa;
    }
    
    public int getCodPenalizacion() {
        return codPenalizacion;
    }

    public String getTipoPenalizacion() {
        return tipoPenalizacion;
    }

    public int getTiempoPenalizacion() {
        return tiempoPenalizacion;
    }

    public LocalDate getFechaPenalizacion() {
        return fechaPenalizacion;
    }

    public int getCantidadMulta() {
        return cantidadMulta;
    }

    public void setCantidadMulta(int cantidadMulta) {
        this.cantidadMulta = cantidadMulta;
    }
}
