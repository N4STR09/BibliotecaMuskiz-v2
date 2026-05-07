package Classes.model;

public class Ejemplar {
    
    private int codEjemplar;
    private String estado;
    private Libro libro;

    public Ejemplar(int codEjemplar, String estado, Libro libro) {
        this.codEjemplar = codEjemplar;
        this.estado = estado;
        this.libro = libro;
    }

    //getters y setters
    public int getCodEjemplar() {
        return codEjemplar;
    }

    public String estado() {
        return estado;
    }

    public Libro getLibroObj() {
        return libro;
    }

    public int getIdLibro() {
        return libro.getIdLibro();
    }
}
