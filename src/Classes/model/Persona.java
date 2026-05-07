package Classes.model;
import java.time.LocalDate;
import java.time.Period;

public abstract class Persona {

    protected String nombre;
    protected LocalDate fechaNacimiento;
    protected boolean defuncion;
    protected LocalDate fechaFallecimiento;
    protected int edad;

    //constructor
    public Persona(
            String nombre,
            LocalDate fechaNacimiento,
            boolean defuncion,
            LocalDate fechaFallecimiento
    ) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.defuncion = defuncion;
        this.fechaFallecimiento = fechaFallecimiento;
        this.edad = calcularEdad();
    }

    //metodo de calculo de edad
    private int calcularEdad() {
        LocalDate referencia = defuncion && fechaFallecimiento != null
                ? fechaFallecimiento
                : LocalDate.now();

        return Period.between(fechaNacimiento, referencia).getYears();
    }

    // getters
    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public boolean isDefuncion() {
        return defuncion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public LocalDate getFechaFallecimiento() {
        return fechaFallecimiento;
    }

}
