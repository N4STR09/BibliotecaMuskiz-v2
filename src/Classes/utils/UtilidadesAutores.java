package Classes.utils;

import java.util.ArrayList;
import java.util.List;

import Classes.model.Autor;
import Classes.model.Libro;

public class UtilidadesAutores {

    //autor con más libros
    public static List<Autor> autoresConMasLibros(List<Autor> autores) {
        List<Autor> resultado = new ArrayList<>();
        if (autores == null || autores.isEmpty()) return resultado;

        int maxLibros = 0;

        //calcular maximo
        for (Autor a : autores) {
            int numLibros = a.getLibrosEscritos().size();
            if (numLibros > maxLibros) {
                maxLibros = numLibros;
            }
        }

        //obtener autores con maximo
        for (Autor a : autores) {
            if (a.getLibrosEscritos().size() == maxLibros) {
                resultado.add(a);
            }
        }

        return resultado;
    }

    //autor con menos libros
    public static List<Autor> autoresConMenosLibros(List<Autor> autores) {
        List<Autor> resultado = new ArrayList<>();
        if (autores == null || autores.isEmpty()) return resultado;

        int minLibros = 100000;

        for (Autor a : autores) {
            int numLibros = a.getLibrosEscritos().size();
            if (numLibros < minLibros) {
                minLibros = numLibros;
            }
        }

        for (Autor a : autores) {
            if (a.getLibrosEscritos().size() == minLibros) {
                resultado.add(a);
            }
        }

        return resultado;
    }

    //autor con el libros mas largo
    public static List<Autor> autoresLibroMasLargo(List<Autor> autores, List<Libro> libros) {
        List<Autor> resultado = new ArrayList<>();
        if (autores == null || autores.isEmpty() || libros == null || libros.isEmpty()) return resultado;

        int maxPaginas = -1;

        //numero maximo de paginas
        for (Autor a : autores) {
            for (int idLibro : a.getLibrosEscritos()) {
                Libro l = libros.stream()
                                .filter(lib -> lib.getIdLibro() == idLibro)
                                .findFirst()
                                .orElse(null);
                if (l != null && l.getNumeroPaginas() > maxPaginas) {
                    maxPaginas = l.getNumeroPaginas();
                }
            }
        }

        //autores con un libro con max
        for (Autor a : autores) {
            for (int idLibro : a.getLibrosEscritos()) {
                Libro l = libros.stream()
                                .filter(lib -> lib.getIdLibro() == idLibro)
                                .findFirst()
                                .orElse(null);
                if (l != null && l.getNumeroPaginas() == maxPaginas) {
                    resultado.add(a);
                    break;
                }
            }
        }

        return resultado;
    }


    //autor con el libros mas corto
    public static List<Autor> autoresLibroMasCorto(List<Autor> autores, List<Libro> libros) {
        List<Autor> resultado = new ArrayList<>();
        if (autores == null || autores.isEmpty() || libros == null || libros.isEmpty()) return resultado;

        int minPaginas = 1000000;

        for (Autor a : autores) {
            for (int idLibro : a.getLibrosEscritos()) {
                Libro l = libros.stream()
                                .filter(lib -> lib.getIdLibro() == idLibro)
                                .findFirst()
                                .orElse(null);
                if (l != null && l.getNumeroPaginas() < minPaginas) {
                    minPaginas = l.getNumeroPaginas();
                }
            }
        }

        for (Autor a : autores) {
            for (int idLibro : a.getLibrosEscritos()) {
                Libro l = libros.stream()
                                .filter(lib -> lib.getIdLibro() == idLibro)
                                .findFirst()
                                .orElse(null);
                if (l != null && l.getNumeroPaginas() == minPaginas) {
                    resultado.add(a);
                    break;
                }
            }
        }

        return resultado;
    }


    //autor mas viejo
    public static List<Autor> autoresMasViejos(List<Autor> autores) {
        List<Autor> resultado = new ArrayList<>();
        if (autores == null || autores.isEmpty()) return resultado;

        int maxEdad = -1;

        //edad maxima
        for (Autor a : autores) {
            int edad = a.getEdad();
            if (edad > maxEdad) {
                maxEdad = edad;
            }
        }

        //autores con edad maxima
        for (Autor a : autores) {
            if (a.getEdad() == maxEdad) {
                resultado.add(a);
            }
        }

        return resultado;
    }


    //autor más joven
    public static List<Autor> autoresMasJovenes(List<Autor> autores) {
        List<Autor> resultado = new ArrayList<>();
        if (autores == null || autores.isEmpty()) return resultado;

        int minEdad = 1000;

        for (Autor a : autores) {
            int edad = a.getEdad();
            if (edad < minEdad) {
                minEdad = edad;
            }
        }

        for (Autor a : autores) {
            if (a.getEdad() == minEdad) {
                resultado.add(a);
            }
        }

        return resultado;
    }

    //edad media autores
    public static double edadMediaAutores(List<Autor> autores) {
        if (autores == null || autores.isEmpty()) return 0;

        double total = 0;

        for (Autor a : autores) {
            total += a.getEdad();
        }

        return total / autores.size();
    }

    //total de Autores en la biblioteca
    public static int totalAutores(List<Autor> autores) {
        if (autores == null) return 0;
        return autores.size();
    }
}