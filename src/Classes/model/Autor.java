package src.Classes.model;

import java.time.LocalDate;
import java.util.Objects;

public class Autor extends Persona {

    private int idAutor;
    private String nacionalidad;
    private String biografia;
    private String foto;
    private String generoLiterario;
    private String premios;
    private String obrasDestacadas;

    // CONSTRUCTOR (SIN listas de libros)
    public Autor(
            int idAutor,
            String nombre,
            String nacionalidad,
            LocalDate fechaNacimiento,
            boolean defuncion,
            LocalDate fechaFallecimiento,
            String biografia,
            String foto,
            String generoLiterario,
            String premios,
            String obrasDestacadas
    ) {
        super(nombre, fechaNacimiento, defuncion, fechaFallecimiento);
        this.idAutor = idAutor;
        this.nacionalidad = nacionalidad;
        this.biografia = biografia;
        this.foto = foto;
        this.generoLiterario = generoLiterario;
        this.premios = premios;
        this.obrasDestacadas = obrasDestacadas;
    }

    // GETTERS Y SETTERS
    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String getGeneroLiterario() {
        return generoLiterario;
    }

    public String getPremios() {
        return premios;
    }

    public String getObrasDestacadas() {
        return obrasDestacadas;
    }

    public String getBiografia() {
        return biografia;
    }

    public String getFoto() {
        return foto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Autor)) return false;
        Autor autor = (Autor) o;
        return idAutor == autor.idAutor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAutor);
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + idAutor +
                ", nombre='" + getNombre() + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                '}';
    }
}