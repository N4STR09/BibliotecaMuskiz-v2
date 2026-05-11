package src.Classes.model;

import java.time.LocalDate;

public class Usuario extends Persona {

    private int idUsuario;
    private boolean activo;
    private String dni;
    private int numeroSeguridadSocial;
    private String password;
    private Integer codPenalizacion;
    private boolean admin;

    public Usuario(int idUsuario, String nombre, LocalDate fechaNacimiento, boolean defuncion,
                LocalDate fechaFallecimiento, boolean activo, String dni,
                int numeroSeguridadSocial, String password, Integer codPenalizacion, boolean admin) {

        super(nombre, fechaNacimiento, defuncion, fechaFallecimiento);
        this.idUsuario = idUsuario;
        this.activo = activo;
        this.dni = dni;
        this.numeroSeguridadSocial = numeroSeguridadSocial;
        this.password = password;
        this.codPenalizacion = codPenalizacion;
        this.admin = admin;
    }

    public void desactivarPorDefuncion() {
        if (isDefuncion()) {
            this.activo = false;
        }
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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

    public String getPassword() {
        return password;
    }

    public Integer getCodPenalizacion() {
        return codPenalizacion;
    }

    public void setCodPenalizacion(Integer codPenalizacion) {
        this.codPenalizacion = codPenalizacion;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre=" + getNombre() +
                ", activo=" + activo +
                ", dni='" + dni + '\'' +
                ", nss=" + numeroSeguridadSocial +
                ", codPenalizacion=" + codPenalizacion +
                '}';
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}