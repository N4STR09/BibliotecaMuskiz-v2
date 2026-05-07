package Classes.model;

import java.time.LocalDate;

public class Usuario extends Persona {

    private int idUsuario;
    private boolean activo;
    private String dni;
    private int numeroSeguridadSocial;
    private Penalizacion penalizacion;

    public Usuario(int idUsuario, String nombre, LocalDate fechaNacimiento, boolean defuncion, LocalDate fechaFallecimiento, boolean activo, String dni, int numeroSeguridadSocial, Penalizacion penalizacion) {
        super(nombre, fechaNacimiento, defuncion, fechaFallecimiento);
        this.idUsuario = idUsuario;
        this.activo = activo;
        this.dni = dni;
        this.numeroSeguridadSocial = numeroSeguridadSocial;
    }

    //metodos

    public void desactivarPorDefuncion() {
        if (isDefuncion()) {
            setActivo(false);
        }
    }

    //getters y setters
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getNumeroSeguridadSocial() {
        return numeroSeguridadSocial;
    }

    public void setNumeroSeguridadSocial(int numeroSeguridadSocial) {
        this.numeroSeguridadSocial = numeroSeguridadSocial;
    }

    public Penalizacion getPenalizacionObj() {
        return penalizacion;
    }
    public int getCodPenalizacion() {
        return penalizacion.getCodPenalizacion();
    }
}
