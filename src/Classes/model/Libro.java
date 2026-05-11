package src.Classes.model;

public class Libro {

    private int idLibro;
    private int existencias;
    private int numeroPaginas;
    private String titulo;
    private int idAutor;
    private String genero;
    private int anioPublicacion;
    private String editorial;
    private String isbn;
    private String idioma;
    private String formato;
    private String portada;
    private String descripcion;
    private String categoria;
    private boolean disponibilidad;

    public Libro(int idLibro,
                int existencias,
                int numeroPaginas,
                String titulo,
                int idAutor,
                String genero,
                int anioPublicacion,
                String editorial,
                String isbn,
                String idioma,
                String formato,
                String portada,
                String descripcion,
                String categoria,
                boolean disponibilidad) {

        this.idLibro = idLibro;
        this.existencias = existencias;
        this.numeroPaginas = numeroPaginas;
        this.titulo = titulo;
        this.idAutor = idAutor;
        this.genero = genero;
        this.anioPublicacion = anioPublicacion;
        this.editorial = editorial;
        this.isbn = isbn;
        this.idioma = idioma;
        this.formato = formato;
        this.portada = portada;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.disponibilidad = disponibilidad;
    }

    public Libro(int existencias,
                int numeroPaginas,
                String titulo,
                int idAutor,
                String genero,
                int anioPublicacion,
                String editorial,
                String isbn,
                String idioma,
                String formato,
                String portada,
                String descripcion,
                String categoria,
                boolean disponibilidad) {

        this(0, existencias, numeroPaginas, titulo, idAutor,
            genero, anioPublicacion, editorial, isbn,
            idioma, formato, portada, descripcion,
            categoria, disponibilidad);
    }

    //getters y setters
    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "idLibro=" + idLibro +
                ", titulo='" + titulo + '\'' +
                ", idAutor=" + idAutor +
                ", paginas=" + numeroPaginas +
                '}';
    }
}