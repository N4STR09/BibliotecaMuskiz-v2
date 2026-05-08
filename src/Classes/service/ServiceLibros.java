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

        TitlesUtils.tituloLibros();

        for (Libro l : libros) {
            System.out.println(l.getIdLibro() + " - " + l.getTitulo() + " - Autor ID: " + l.getIdAutor());
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

        System.out.println("---- DETALLES ----");
        System.out.println("Título: " + libro.getTitulo());
        System.out.println("Autor ID: " + libro.getIdAutor());
        System.out.println("Páginas: " + libro.getNumeroPaginas());
        System.out.println("Año: " + libro.getAnioPublicacion());
    }

        public static void estadisticasLibros(Scanner sc) {

        TitlesUtils.tituloEstadisticas();

        System.out.println("\n1. Páginas");
        System.out.println("2. Existencias");
        System.out.println("3. Generales");
        System.out.println("4. Salir");

        int op = InputUtils.leerNumeroMenu(sc, "Opción: ", 4);

        switch (op) {

            case 1 -> {
                System.out.println("Media páginas: " + UtilidadesLibros.mediaPaginas());
                System.out.println("Max páginas: " + UtilidadesLibros.maxPaginas());
                System.out.println("Min páginas: " + UtilidadesLibros.minPaginas());
            }

            case 2 -> {
                System.out.println("Media existencias: " + UtilidadesLibros.mediaExistencias());
                System.out.println("Max existencias: " + UtilidadesLibros.maxExistencias());
                System.out.println("Min existencias: " + UtilidadesLibros.minExistencias());
            }

            case 3 -> {
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