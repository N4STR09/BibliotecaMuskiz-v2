package Classes.service;

import java.util.Scanner;

import Classes.model.*;
import Classes.utils.ColoresUtils;
import Classes.utils.InputUtils;
import Classes.utils.TitlesUtils;
import Classes.utils.UtilidadesLibros;

import java.util.List;

public class ServiceLibros {
    public static void añadirLibro(Scanner sc, List<Autor> autores, List<Libro> libros) {
        //solicitud de datos al usuario
        TitlesUtils.tituloNuevoLibro();
        String titulo = InputUtils.leerString(sc, "\nTítulo del libro: ");

        System.out.println("Elige el autor del libro por " + ColoresUtils.ROJO + "ID" + ColoresUtils.RESET + ":\n");
        for (Autor a : autores) {
            System.out.println(ColoresUtils.ROJO + a.getIdAutor() + ColoresUtils.RESET + " - " + a.getNombre());
        }

        int idAutor = InputUtils.leerInt(sc, ColoresUtils.ROJO + "\nID" + ColoresUtils.RESET + " del autor: ");

        Autor autorElegido = null;
        for (Autor a : autores) {
            if (a.getIdAutor() == idAutor) {
                autorElegido = a;
                break;
            }
        }
        
        if (autorElegido == null) {
            System.out.println(ColoresUtils.ROJO + "Autor " + ColoresUtils.NEGRITA + "no" + ColoresUtils.RESET + ColoresUtils.ROJO + " encontrado. Libro " + ColoresUtils.NEGRITA + "no" + ColoresUtils.RESET + ColoresUtils.ROJO + " creado.");
        } else {

            String genero = InputUtils.leerString(sc, "Género del libro: ");
            int existencias = InputUtils.leerInt(sc, "Número de existencias: ");
            int numeroPaginas = InputUtils.leerInt(sc, "Número de páginas: ");
            int anioPublicacion = InputUtils.leerInt(sc, "Año de publicación: ");
            String editorial = InputUtils.leerString(sc, "Editorial: ");
            String isbn = InputUtils.leerString(sc, "ISBN: ");
            String idioma = InputUtils.leerString(sc, "Idioma: ");
            String formato = InputUtils.leerString(sc, "Formato: ");
            String portada = InputUtils.leerString(sc, "Portada del libro: ");
            String descripcion = InputUtils.leerString(sc, "Descripción del libro: ");
            String categoria = InputUtils.leerString(sc, "Categoría del libro: ");
            boolean disponibilidad = InputUtils.leerBoolean(sc, "¿Está disponible?");

            int id = libros.size() + 1; 
            
            //creamos un nuevo libro
            Libro nuevoLibro = new Libro(
                id,
                existencias,
                numeroPaginas,
                titulo,
                autorElegido,
                genero,
                anioPublicacion,
                editorial,
                isbn,
                idioma,
                formato,
                portada,
                descripcion,
                categoria,
                disponibilidad
            );

            libros.add(nuevoLibro);
            InputUtils.limpiarPantalla();
            autorElegido.addLibro(nuevoLibro.getIdLibro());

            System.out.println(ColoresUtils.VERDE + "Libro añadido " + ColoresUtils.NEGRITA + "correctamente." + ColoresUtils.RESET);
            InputUtils.pausa(sc);
            InputUtils.limpiarPantalla();
        }
    }

    public static void eliminarLibro(Scanner sc, List<Autor> autores, List<Libro> libros) {
        if (libros.isEmpty()) {
            System.out.println(ColoresUtils.ROJO + ColoresUtils.NEGRITA + "No " + ColoresUtils.RESET + ColoresUtils.ROJO + "hay libros para eliminar." + ColoresUtils.RESET);
        } else {
            TitlesUtils.tituloEliminarLibro();
            for (Libro a : libros) {
                System.out.println(ColoresUtils.ROJO + a.getIdLibro() + ColoresUtils.RESET + " - " + a.getTitulo());
            }

            System.out.print("\nIntroduce el " + ColoresUtils.NEGRITA + ColoresUtils.ROJO + "ID" + ColoresUtils.RESET + " del libro a eliminar: ");
            int idLibroEliminar = sc.nextInt();
            InputUtils.limpiarPantalla();
            boolean eliminado = false;

            for (int i = 0; i < libros.size(); i++) {
                if (libros.get(i).getIdLibro() == idLibroEliminar) {
                    libros.remove(i);
                    eliminado = true;
                    System.out.println(ColoresUtils.VERDE + ColoresUtils.NEGRITA + "Libro eliminado correctamente." + ColoresUtils.RESET);
                    break;
                }
            }

            if (!eliminado) {
                System.out.println(ColoresUtils.ROJO + ColoresUtils.NEGRITA + "No se encontró ningún libro con ese ID." + ColoresUtils.RESET);
            }
        }
        sc.nextLine();
        InputUtils.pausa(sc);
        InputUtils.limpiarPantalla();
    }

    public static void mostrarLibros(Scanner sc, List<Autor> autores, List<Libro> libros) {
        TitlesUtils.tituloLibros();
        for (Libro a : libros) {
            System.out.println(a.getIdLibro() + " - "+a.getTitulo() + " - " + a.getAutor()); //printeamos lo que nos devuelva los getters
        }

        int id = InputUtils.leerInt(sc, "Introduce el " + ColoresUtils.SUBRAYADO + "ID del libro" + ColoresUtils.RESET + " para ver más detalles " + ColoresUtils.NEGRITA + "(0 para salir): " + ColoresUtils.RESET);

        if (id != 0) {
            Libro libroSeleccionado = null;
            for (Libro a : libros) {
                if (a.getIdLibro() == id) {
                    libroSeleccionado = a;
                    break;
                }
            }

            if (libroSeleccionado != null) {
                System.out.println(ColoresUtils.NEGRITA + ColoresUtils.CYAN + "---- DETALLES DEL LIBRO ----" + ColoresUtils.RESET);
                System.out.println("\nTítulo: " + libroSeleccionado.getTitulo());
                System.out.println("Autor: " + libroSeleccionado.getNombreAutor());
                System.out.println("Género: " + libroSeleccionado.getGenero());
                System.out.println("Número de páginas: " + libroSeleccionado.getNumeroPaginas());
                System.out.println("Año de publicación: " + libroSeleccionado.getAnioPublicacion());
                System.out.println("Editorial: " + libroSeleccionado.getEditorial());
                System.out.println("ISBN: " + libroSeleccionado.getIsbn());
                System.out.println("Idioma: " + libroSeleccionado.getIdioma());
                System.out.println("Formato: " + libroSeleccionado.getFormato());
                System.out.println("Existencias: " + libroSeleccionado.getExistencias());
                System.out.println("Disponibilidad: " + (libroSeleccionado.isDisponibilidad() ? ColoresUtils.VERDE + "Disponible" + ColoresUtils.RESET : ColoresUtils.ROJO + "No disponible" + ColoresUtils.RESET));
                System.out.println("Descripción: " + libroSeleccionado.getDescripcion());
                System.out.println("Categoría: " + libroSeleccionado.getCategoria());
            } else {
                System.out.println(ColoresUtils.ROJO + ColoresUtils.NEGRITA + "No se encontró ningún libro con ese ID." + ColoresUtils.RESET);
            }
        }
        InputUtils.pausa(sc);
        InputUtils.limpiarPantalla();
    }

    public static void estadisticasLibros(Scanner sc, List<Autor> autores, List<Libro> libros) {
        TitlesUtils.tituloEstadisticas();
        System.out.println("\n1. " + ColoresUtils.AZUL + "Páginas" + ColoresUtils.RESET);
        System.out.println("2. " + ColoresUtils.MORADO + "Existencias" + ColoresUtils.RESET);
        System.out.println("3. " + ColoresUtils.NEGRITA + "Otros" + ColoresUtils.RESET);
        System.out.println("4. "  + ColoresUtils.ROJO + ColoresUtils.NEGRITA + ColoresUtils.SUBRAYADO + "Salir" + ColoresUtils.RESET);
        int tipo = InputUtils.leerNumeroMenu(sc, "\nSelecciona una opción: ", 4);
        InputUtils.limpiarPantalla();
        int stat;

        switch (tipo) {
            case 1:
                TitlesUtils.tituloEstadisticas();
                System.out.println("\n1. " + ColoresUtils.MORADO + "Media" + ColoresUtils.RESET + " de Páginas");
                System.out.println("2. " + ColoresUtils.CYAN + "Máximo" + ColoresUtils.RESET + " de Páginas");
                System.out.println("3. " + ColoresUtils.NARANJA + "Mínimo" + ColoresUtils.RESET + " de Páginas");
                System.out.println("4. "  + ColoresUtils.ROJO + ColoresUtils.NEGRITA + ColoresUtils.SUBRAYADO + "Salir" + ColoresUtils.RESET);
                stat = InputUtils.leerNumeroMenu(sc, "\nSelecciona una opción: ", 4);
                InputUtils.limpiarPantalla();

                switch (stat) {
                    case 1:
                        double media = UtilidadesLibros.mediaPaginas(libros);
                        System.out.println(ColoresUtils.MORADO + "Media" + ColoresUtils.RESET + " de páginas: " + media);
                        InputUtils.pausa(sc);
                        InputUtils.limpiarPantalla();
                        break;
                    case 2:
                        List<Libro> librosMax = UtilidadesLibros.librosMaxPaginas(libros);

                        if (librosMax.isEmpty()) {
                            System.out.println(ColoresUtils.ROJO + ColoresUtils.NEGRITA + "No hay libros en la biblioteca." + ColoresUtils.RESET);
                        } else {
                            int paginas = librosMax.get(0).getNumeroPaginas();
                            System.out.println("Libro/s con " + ColoresUtils.CYAN + "más páginas" + ColoresUtils.RESET + ColoresUtils.NEGRITA + " (" + paginas + " páginas):" + ColoresUtils.RESET);

                            for (Libro l : librosMax) {
                                System.out.println("- " + l.getTitulo() + " (" + l.getAutorObj().getNombre() + ")");
                            }
                        }
                        InputUtils.pausa(sc);
                        InputUtils.limpiarPantalla();
                        break;
                    case 3:
                        List<Libro> librosMin = UtilidadesLibros.librosMinimoPaginas(libros);

                        if (librosMin.isEmpty()) {
                            System.out.println(ColoresUtils.ROJO + ColoresUtils.NEGRITA + "No hay libros en la biblioteca." + ColoresUtils.RESET);
                        } else {
                            int paginas = librosMin.get(0).getNumeroPaginas();
                            System.out.println("Libro/s con " + ColoresUtils.NARANJA + "menos páginas" + ColoresUtils.RESET + ColoresUtils.NEGRITA + " (" + paginas + " páginas):" + ColoresUtils.RESET);

                            for (Libro l : librosMin) {
                                System.out.println("- " + l.getTitulo() + " (" + l.getAutorObj().getNombre() + ")");
                            }
                        }
                        InputUtils.pausa(sc);
                        InputUtils.limpiarPantalla();
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                TitlesUtils.tituloEstadisticas();
                System.out.println("\n1. " + ColoresUtils.MORADO + "Media" + ColoresUtils.RESET + " de Existencias");
                System.out.println("2. " + ColoresUtils.CYAN + "Máximo" + ColoresUtils.RESET + " de Existencias");
                System.out.println("3. " + ColoresUtils.NARANJA + "Mínimo" + ColoresUtils.RESET + " de Existencias");
                System.out.println("4. "  + ColoresUtils.ROJO + ColoresUtils.NEGRITA + ColoresUtils.SUBRAYADO + "Salir" + ColoresUtils.RESET);
                stat = InputUtils.leerNumeroMenu(sc, "Selecciona una opción: ", 4);
                InputUtils.limpiarPantalla();

                switch (stat) {
                    case 1:
                        double media = UtilidadesLibros.mediaExistencias(libros);
                        System.out.println(ColoresUtils.MORADO + "Media" + ColoresUtils.RESET + " de existencias: " + ColoresUtils.NEGRITA + media + ColoresUtils.RESET);
                        InputUtils.pausa(sc);
                        InputUtils.limpiarPantalla();
                        break;
                    case 2:
                        List<Libro> librosMax = UtilidadesLibros.librosMaxExistencias(libros);

                        if (librosMax.isEmpty()) {
                            System.out.println(ColoresUtils.ROJO + ColoresUtils.NEGRITA + "No hay libros en la biblioteca." + ColoresUtils.RESET);
                        } else {
                            int existencias = librosMax.get(0).getExistencias();
                            System.out.println("Libro/s con " + ColoresUtils.CYAN + "más existencias" + ColoresUtils.RESET + ColoresUtils.NEGRITA + " (" + existencias + " unidades):" + ColoresUtils.RESET);

                            for (Libro l : librosMax) {
                                System.out.println("- " + l.getTitulo());
                            }
                        }
                        InputUtils.pausa(sc);
                        InputUtils.limpiarPantalla();
                        break;
                    case 3:
                        List<Libro> librosMin = UtilidadesLibros.librosMinExistencias(libros);

                        if (librosMin.isEmpty()) {
                            System.out.println(ColoresUtils.ROJO + ColoresUtils.NEGRITA + "No hay libros en la biblioteca." + ColoresUtils.RESET);
                        } else {
                            int existencias = librosMin.get(0).getExistencias();
                            System.out.println("Libro/s " + ColoresUtils.NARANJA + " con menos existencias " + ColoresUtils.RESET + ColoresUtils.NEGRITA + "(" + existencias + " unidades):" + ColoresUtils.RESET);

                            for (Libro l : librosMin) {
                                System.out.println("- " + l.getTitulo());
                            }
                        }
                        InputUtils.pausa(sc);
                        InputUtils.limpiarPantalla();
                        break;
                    default:
                        break;
                }
                break;

            case 3:
                TitlesUtils.tituloEstadisticas();
                System.out.println("\n1. Porcentaje de " + ColoresUtils.AMARILLO + "libros disponibles" + ColoresUtils.RESET);
                System.out.println("2. Cantidad de " + ColoresUtils.AZUL + "libros por género" + ColoresUtils.RESET);
                System.out.println("3. Número " + ColoresUtils.ROSA + ColoresUtils.NEGRITA + "total " + ColoresUtils.RESET + ColoresUtils.ROSA + "de libros" + ColoresUtils.RESET);
                System.out.println("4. "  + ColoresUtils.ROJO + ColoresUtils.NEGRITA + ColoresUtils.SUBRAYADO + "Salir" + ColoresUtils.RESET);
                stat = InputUtils.leerNumeroMenu(sc, "Selecciona una opción: ", 4);
                InputUtils.limpiarPantalla();

                switch (stat) {
                    case 1:
                        double porcentaje = UtilidadesLibros.porcentajeDisponibles(libros);
                        System.out.println("Porcentaje de " + ColoresUtils.AMARILLO + "libros disponibles: " + ColoresUtils.RESET + ColoresUtils.NEGRITA + porcentaje + "%" + ColoresUtils.RESET);
                        InputUtils.pausa(sc);
                        InputUtils.limpiarPantalla();
                        break;
                    case 2:
                        System.out.println("Cantidad de " + ColoresUtils.AZUL + "libros por género:" + ColoresUtils.RESET);
                        var porGenero = UtilidadesLibros.librosPorGenero(libros);
                        for (String tgenero : porGenero.keySet()) {
                            System.out.println(tgenero + ": " + porGenero.get(tgenero));
                        }
                        InputUtils.pausa(sc);
                        InputUtils.limpiarPantalla();
                        break;
                    case 3:
                        int total = UtilidadesLibros.totalLibros(libros);
                        System.out.println("Número " + ColoresUtils.ROSA + "total de libros: " + ColoresUtils.RESET + ColoresUtils.NEGRITA + total + ColoresUtils.RESET);
                        InputUtils.pausa(sc);
                        InputUtils.limpiarPantalla();
                        break;
                    default:
                        break;
                    }
                break;
            default:
                break;
        }
    }
}