package src.Classes.model;

import java.time.LocalDate;

public class Prestamo {

    private int codPrestamo;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    private int codEjemplar;
    private int idUsuario;

    public Prestamo(int codPrestamo,
                    LocalDate fechaPrestamo,
                    LocalDate fechaDevolucion,
                    int codEjemplar,
                    int idUsuario) {

        this.codPrestamo = codPrestamo;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.codEjemplar = codEjemplar;
        this.idUsuario = idUsuario;
    }

    //getters y setters
    public int getCodPrestamo() {
        return codPrestamo;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public int getCodEjemplar() {
        return codEjemplar;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setCodPrestamo(int codPrestamo) {
        this.codPrestamo = codPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public void setCodEjemplar(int codEjemplar) {
        this.codEjemplar = codEjemplar;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "codPrestamo=" + codPrestamo +
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucion=" + fechaDevolucion +
                ", codEjemplar=" + codEjemplar +
                ", idUsuario=" + idUsuario +
                '}';
    }
}