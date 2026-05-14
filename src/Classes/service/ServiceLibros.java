package src.Classes.service;

import java.util.Scanner;

import src.Classes.model.*;
import src.Classes.repository.AutorRepository;
import src.Classes.repository.LibroRepository;
import src.Classes.utils.ColoresUtils;
import src.Classes.utils.InputUtils;
import src.Classes.utils.TitlesUtils;
import src.Classes.utils.UtilidadesLibros;

import java.util.List;
import java.util.Map;

public class ServiceLibros {

    public static void añadirLibro(Scanner sc) {

        TitlesUtils.tituloNuevoLibro();

        String titulo = InputUtils.leerString(sc, "\nTítulo del libro: ");

        List<Autor> listaAutores = AutorRepository.cargarAutores();

        System.out.println("Elige el autor por ID:\n");
        for (Autor a : listaAutores) {
            System.out.println(a.getIdAutor() + " - " + a.getNombre());
        }

        int idAutor = InputUtils.leerInt(sc, "ID autor: ");

        boolean existeAutor = listaAutores.stream()
                .anyMatch(a -> a.getIdAutor() == idAutor);

        if (!existeAutor) {
            System.out.println("Autor no encontrado.");
            return;
        }

        int existencias = InputUtils.leerInt(sc, "Existencias: ");
        int paginas = InputUtils.leerInt(sc, "Páginas: ");
        int anio = InputUtils.leerInt(sc, "Año publicación: ");
        String editorial = InputUtils.leerString(sc, "Editorial: ");
        String isbn = InputUtils.leerString(sc, "ISBN: ");
        String idioma = InputUtils.leerString(sc, "Idioma: ");
        String formato = InputUtils.leerString(sc, "Formato: ");
        String portada = InputUtils.leerString(sc, "Portada: ");
        String descripcion = InputUtils.leerString(sc, "Descripción: ");
        String categoria = InputUtils.leerString(sc, "Categoría: ");
        String genero = InputUtils.leerString(sc, "Género: ");
        boolean disponibilidad = InputUtils.leerBoolean(sc, "Disponible?");

        Libro libro = new Libro(
                0,
                existencias,
                paginas,
                titulo,
                idAutor,
                genero,
                anio,
                editorial,
                isbn,
                idioma,
                formato,
                portada,
                descripcion,
                categoria,
                disponibilidad
        );

        LibroRepository.guardarLibro(libro);

        System.out.println(ColoresUtils.VERDE + "Libro añadido correctamente" + ColoresUtils.RESET);
    }

    public static void eliminarLibro(Scanner sc) {

        List<Libro> libros = LibroRepository.cargarLibros();

        if (libros.isEmpty()) {
            System.out.println("No hay libros.");
            return;
        }

        TitlesUtils.tituloEliminarLibro();

        for (Libro l : libros) {
            System.out.println(l.getIdLibro() + " - " + l.getTitulo());
        }

        int id = InputUtils.leerInt(sc, "ID libro: ");

        LibroRepository.eliminarLibro(id);

        System.out.println("Libro eliminado.");
    }

    public static void mostrarLibros(Scanner sc) {

        List<Libro> libros = LibroRepository.cargarLibros();
        List<Autor> autores = AutorRepository.cargarAutores();

        TitlesUtils.tituloLibros();

        for (Libro l : libros) {

            String nombreAutor = autores.stream()
                    .filter(a -> a.getIdAutor() == l.getIdAutor())
                    .map(Autor::getNombre)
                    .findFirst()
                    .orElse("Desconocido");

            System.out.println(
                    l.getIdLibro() + " - " +
                    l.getTitulo() + " - " +
                    nombreAutor
            );
        }

        int id = InputUtils.leerInt(sc, "ID (0 salir): ");

        if (id == 0) return;

        Libro libro = libros.stream()
                .filter(l -> l.getIdLibro() == id)
                .findFirst()
                .orElse(null);

        if (libro == null) {
            System.out.println("No encontrado.");
            return;
        }

        String nombreAutor = autores.stream()
                .filter(a -> a.getIdAutor() == libro.getIdAutor())
                .map(Autor::getNombre)
                .findFirst()
                .orElse("Desconocido");

        TitlesUtils.tituloDetalles();
        System.out.println("Título: " + libro.getTitulo());
        System.out.println("Autor: " + nombreAutor);
        System.out.println("Páginas: " + libro.getNumeroPaginas());
        System.out.println("Año: " + libro.getAnioPublicacion());
    }

        public static void estadisticasLibros(Scanner sc) {

        TitlesUtils.tituloEstadisticas();

        System.out.println("\n1. " + ColoresUtils.AMARILLO_MOSTAZA +"Páginas" + ColoresUtils.RESET);
        System.out.println("2. " + ColoresUtils.VERDE +"Existencias" + ColoresUtils.RESET);
        System.out.println("3. " + ColoresUtils.AZUL + "Generales" + ColoresUtils.RESET);
        System.out.println("4. " + ColoresUtils.ROJO + ColoresUtils.NEGRITA + ColoresUtils.SUBRAYADO + "Salir" + ColoresUtils.RESET);

        int op = InputUtils.leerNumeroMenu(sc, "Opción: ", 4);

        switch (op) {

            case 1 -> {
                //Poner limpiar pantlla aquí para que no se mezcle con el menú
                System.out.println(ColoresUtils.CYAN_CLARO + "Media" + ColoresUtils.RESET + " páginas: " + UtilidadesLibros.mediaPaginas());
                System.out.println(ColoresUtils.MORADO_CLARO + "Max" + ColoresUtils.RESET + " páginas: " + UtilidadesLibros.maxPaginas());
                System.out.println(ColoresUtils.VERDE_BOSQUE + "Min" + ColoresUtils.RESET + " páginas: " + UtilidadesLibros.minPaginas());
            }

            case 2 -> {
                //Poner limpiar pantlla aquí para que no se mezcle con el menú
                System.out.println(ColoresUtils.CYAN_CLARO + "Media" + ColoresUtils.RESET + " existencias: " + UtilidadesLibros.mediaExistencias());
                System.out.println(ColoresUtils.MORADO_CLARO + "Max" + ColoresUtils.RESET + " existencias: " + UtilidadesLibros.maxExistencias());
                System.out.println(ColoresUtils.VERDE_BOSQUE + "Min" + ColoresUtils.RESET + " existencias: " + UtilidadesLibros.minExistencias());
            }

            case 3 -> {
                //Poner limpiar pantlla aquí para que no se mezcle con el menú
                System.out.println("Disponibilidad %: " + UtilidadesLibros.porcentajeDisponibles());
                System.out.println("Total libros: " + UtilidadesLibros.totalLibros());

                Map<String, Integer> porGenero = UtilidadesLibros.librosPorGenero();

                for (String g : porGenero.keySet()) {
                    System.out.println(g + ": " + porGenero.get(g));
                }
            }

            default -> {}
        }

        InputUtils.pausa(sc);
        InputUtils.limpiarPantalla();
    }
}