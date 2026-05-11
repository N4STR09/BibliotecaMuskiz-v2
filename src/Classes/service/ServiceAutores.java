package src.Classes.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import src.Classes.model.Autor;
import src.Classes.repository.AutorRepository;
import src.Classes.utils.ColoresUtils;
import src.Classes.utils.InputUtils;
import src.Classes.utils.TitlesUtils;
import src.Classes.utils.UtilidadesAutores;

public class ServiceAutores {

    public static void añadirAutor(Scanner sc) {

        TitlesUtils.tituloNuevoAutor();

        String nombre = InputUtils.leerString(sc, "\nNombre del autor: ");
        String nacionalidad = InputUtils.leerString(sc, "Nacionalidad: ");
        LocalDate fechaNacimiento = InputUtils.leerFecha(sc, "Fecha de nacimiento: ");

        boolean defuncion = InputUtils.leerBoolean(
            sc,
            "¿El autor ha " + ColoresUtils.NEGRITA + "fallecido" + ColoresUtils.RESET + "?: "
        );

        LocalDate fechaFallecimiento = null;

        if (defuncion) {

            do {

                fechaFallecimiento = InputUtils.leerFecha(sc, "Fecha de fallecimiento: ");

                if (fechaFallecimiento.isBefore(fechaNacimiento)) {

                    System.out.println(
                        ColoresUtils.ROJO +
                        "La fecha de fallecimiento no puede ser anterior a la de nacimiento." +
                        ColoresUtils.RESET
                    );
                }

            } while (fechaFallecimiento.isBefore(fechaNacimiento));
        }

        String biografia = InputUtils.leerString(sc, "Biografía: ");
        String foto = InputUtils.leerString(sc, "Foto del autor: ");
        String generoLiterario = InputUtils.leerString(sc, "Género literario: ");
        String premios = InputUtils.leerString(sc, "Premios: ");
        String obrasDestacadas = InputUtils.leerString(sc, "Obras destacadas: ");

        Autor nuevoAutor = new Autor(
            0,
            nombre,
            nacionalidad,
            fechaNacimiento,
            defuncion,
            fechaFallecimiento,
            biografia,
            foto,
            generoLiterario,
            premios,
            obrasDestacadas
        );

        AutorRepository.guardarAutor(nuevoAutor);

        System.out.println(
            ColoresUtils.VERDE +
            ColoresUtils.NEGRITA +
            "Autor añadido correctamente." +
            ColoresUtils.RESET
        );

        InputUtils.pausa(sc);
        InputUtils.limpiarPantalla();
    }

    public static void eliminarAutor(Scanner sc) {

        List<Autor> autores = AutorRepository.cargarAutores();

        if (autores.isEmpty()) {

            System.out.println(
                ColoresUtils.ROJO +
                "No hay autores." +
                ColoresUtils.RESET
            );

            InputUtils.pausa(sc);
            InputUtils.limpiarPantalla();
            return;
        }

        TitlesUtils.tituloEliminarAutor();

        for (Autor a : autores) {
            System.out.println(a.getIdAutor() + " - " + a.getNombre());
        }

        int id = InputUtils.leerInt(sc, "\nID del autor a eliminar: ");

        boolean existe = autores.stream()
                .anyMatch(a -> a.getIdAutor() == id);

        if (!existe) {

            System.out.println(
                ColoresUtils.ROJO +
                "No existe un autor con ese ID." +
                ColoresUtils.RESET
            );

        } else {

            AutorRepository.eliminarAutor(id);

            System.out.println(
                ColoresUtils.VERDE +
                "Autor eliminado correctamente." +
                ColoresUtils.RESET
            );
        }

        InputUtils.pausa(sc);
        InputUtils.limpiarPantalla();
    }

    public static void visualizarAutores(Scanner sc) {

        List<Autor> autores = AutorRepository.cargarAutores();

        if (autores.isEmpty()) {

            System.out.println(
                ColoresUtils.ROJO +
                "No hay autores registrados." +
                ColoresUtils.RESET
            );

            InputUtils.pausa(sc);
            InputUtils.limpiarPantalla();
            return;
        }

        TitlesUtils.tituloAutores();

        for (Autor a : autores) {
            System.out.println(
                ColoresUtils.ROJO +
                a.getIdAutor() +
                ColoresUtils.RESET +
                " - " +
                a.getNombre()
            );
        }

        int id = InputUtils.leerInt(
            sc,
            "\nID del autor (0 para salir): "
        );

        if (id == 0) {
            InputUtils.limpiarPantalla();
            return;
        }

        boolean encontrado = false;

        for (Autor a : autores) {

            if (a.getIdAutor() == id) {

                System.out.println(
                    ColoresUtils.NEGRITA +
                    "\n---- DETALLES DEL AUTOR ----" +
                    ColoresUtils.RESET
                );

                System.out.println("ID: " + a.getIdAutor());
                System.out.println("Nombre: " + a.getNombre());
                System.out.println("Nacionalidad: " + a.getNacionalidad());
                System.out.println("Fecha de nacimiento: " + a.getFechaNacimiento());

                System.out.println(
                    "Defunción: " +
                    (a.isDefuncion()
                        ? ColoresUtils.VERDE + "Sí"
                        : ColoresUtils.ROJO + "No")
                    + ColoresUtils.RESET
                );

                if (a.isDefuncion()) {
                    System.out.println(
                        "Fecha de fallecimiento: " +
                        a.getFechaFallecimiento()
                    );
                }

                System.out.println("Biografía: " + a.getBiografia());
                System.out.println("Foto: " + a.getFoto());
                System.out.println("Género literario: " + a.getGeneroLiterario());
                System.out.println("Premios: " + a.getPremios());
                System.out.println("Obras destacadas: " + a.getObrasDestacadas());

                encontrado = true;
                break;
            }
        }

        if (!encontrado) {

            System.out.println(
                ColoresUtils.ROJO +
                "Autor no encontrado." +
                ColoresUtils.RESET
            );
        }

        InputUtils.pausa(sc);
        InputUtils.limpiarPantalla();
    }

    public static void estadisticasAutores(Scanner sc) {

        TitlesUtils.tituloEstadisticas();

        System.out.println("\n1. Autor/es con más libros");
        System.out.println("2. Autor/es con el libro más largo");
        System.out.println("3. Autor/es más viejos");
        System.out.println("4. Total autores");
        System.out.println("5. " + ColoresUtils.ROJO + ColoresUtils.NEGRITA + "Salir" + ColoresUtils.RESET);

        int opcion = InputUtils.leerNumeroMenu(sc, "\nSelecciona una opción: ", 5);

        InputUtils.limpiarPantalla();

        switch (opcion) {

            case 1 -> {

                List<Autor> autoresMax = UtilidadesAutores.autoresConMasLibros();

                if (autoresMax.isEmpty()) {
                    System.out.println("No hay autores.");
                    break;
                }

                System.out.println("Autor/es con más libros:");

                for (Autor a : autoresMax) {
                    System.out.println("- " + a.getNombre());
                }
            }

            case 2 -> {

                List<Autor> autoresMaxLibro = UtilidadesAutores.autoresConLibroMasLargo();

                if (autoresMaxLibro.isEmpty()) {
                    System.out.println("No hay datos.");
                    break;
                }

                System.out.println("Autor/es con el libro más largo:");

                for (Autor a : autoresMaxLibro) {
                    System.out.println("- " + a.getNombre());
                }
            }

            case 3 -> {

                List<Autor> viejos = UtilidadesAutores.autoresMasViejos();

                if (viejos.isEmpty()) {
                    System.out.println("No hay autores.");
                    break;
                }

                System.out.println("Autor/es más viejos:");

                for (Autor a : viejos) {
                    System.out.println("- " + a.getNombre() + " (" + a.getEdad() + " años)");
                }
            }

            case 4 -> {

                int total = UtilidadesAutores.totalAutores();

                System.out.println("Total de autores: " + total);
            }

            default -> {}
        }

        InputUtils.pausa(sc);
        InputUtils.limpiarPantalla();
    }
}