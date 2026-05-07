package Classes.model;

import java.time.LocalDate;

public class Prestamo {
    
    private int codPrestamo;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private Ejemplar ejemplar;
    private Usuario usuario;

    public Prestamo(int codPrestamo, LocalDate fechaPrestamo, LocalDate fechaDevolucion, Ejemplar ejemplar, Usuario usuario) {
        this.codPrestamo = codPrestamo;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.ejemplar = ejemplar;
        this.usuario = usuario;
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

    public Ejemplar getEjemplarObj() {
        return ejemplar;
    }

    public int getCodEjemplar() {
        return ejemplar.getCodEjemplar();
    }

    public Usuario getUsuarioObj() {
        return usuario;
    }

    public int getIdUsuario() {
        return usuario.getIdUsuario();
    }
}
