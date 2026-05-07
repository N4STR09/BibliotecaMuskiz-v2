package Classes.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Classes.model.Libro;

public class UtilidadesLibros {
    
    //calculo media de paginas
    public static double mediaPaginas(List<Libro> libros) {
        if (libros == null || libros.isEmpty()) {
            return 0;
        }

        int totalPaginas = 0;
        for (Libro libro : libros) {
            totalPaginas += libro.getNumeroPaginas();
        }

        return (double) totalPaginas / libros.size();
    }

    //calculo maximo de paginas
    public static List<Libro> librosMaxPaginas(List<Libro> libros) {
        List<Libro> max = new ArrayList<>();
        if (libros == null || libros.isEmpty()) return max;

        int maxPaginas = 0;

        for (Libro l : libros) {
            if (l.getNumeroPaginas() > maxPaginas) {
                maxPaginas = l.getNumeroPaginas();
            }
        }

        for (Libro l : libros) {
            if (l.getNumeroPaginas() == maxPaginas) {
                max.add(l);
            }
        }

        return max;
    }

    //calculo minimo de paginas
    public static List<Libro> librosMinimoPaginas(List<Libro> libros) {
    List<Libro> resultado = new ArrayList<>();
    if (libros == null || libros.isEmpty()) return resultado;

    int minPaginas = libros.get(0).getNumeroPaginas();

    for (Libro l : libros) {
        if (l.getNumeroPaginas() < minPaginas) {
            minPaginas = l.getNumeroPaginas();
        }
    }

    for (Libro l : libros) {
        if (l.getNumeroPaginas() == minPaginas) {
            resultado.add(l);
        }
    }

    return resultado;
}


    //calculo media de existencias
    public static double mediaExistencias(List<Libro> libros) {
        if (libros == null || libros.isEmpty()) {
            return 0;
        }

        int totalExistencias = 0;
        for (Libro libro : libros) {
            totalExistencias += libro.getExistencias();
        }

        return (double) totalExistencias / libros.size();
    }

    //calculo maximo de existencias
    public static List<Libro> librosMaxExistencias(List<Libro> libros) {
        List<Libro> max = new ArrayList<>();
        if (libros == null || libros.isEmpty()) return max;

        int maxExistencias = 0;

        for (Libro l : libros) {
            if (l.getExistencias() > maxExistencias) {
                maxExistencias = l.getExistencias();
            }
        }

        for (Libro l : libros) {
            if (l.getExistencias() == maxExistencias) {
                max.add(l);
            }
        }

        return max;
    }


    //calculo minimo de existencias
    public static List<Libro> librosMinExistencias(List<Libro> libros) {
    List<Libro> resultado = new ArrayList<>();
    if (libros == null || libros.isEmpty()) return resultado;

    int minExistencias = libros.get(0).getExistencias();

    for (Libro l : libros) {
        if (l.getExistencias() < minExistencias) {
            minExistencias = l.getExistencias();
        }
    }

    for (Libro l : libros) {
        if (l.getExistencias() == minExistencias) {
            resultado.add(l);
        }
    }

    return resultado;
}


    //calculo de porcentaje de libros disponibles
    public static double porcentajeDisponibles(List<Libro> libros) {
        if (libros == null || libros.isEmpty()) return 0;

        int disponibles = 0;
        for (Libro libro : libros) {
            if (libro.isDisponibilidad()) {
                disponibles++;
            }
        }

        double porcentaje = (double) disponibles / libros.size() * 100;
        return Math.round(porcentaje * 100.0) / 100.0;
    }


    //calculo de cantidad de libros por genero
    public static Map<String, Integer> librosPorGenero(List<Libro> libros) {
        Map<String, Integer> conteo = new HashMap<>();
        if (libros == null || libros.isEmpty()) return conteo;

        for (Libro libro : libros) {
            String tgenero = libro.getGenero();
            conteo.put(tgenero, conteo.getOrDefault(tgenero, 0) + 1);
        }

        return conteo;
    }

    //calculo de total de libros existentes
    public static int totalLibros(List<Libro> libros) {
        if (libros == null) return 0;
        return libros.size();
    }
}
