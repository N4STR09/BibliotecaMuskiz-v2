package Classes.service;

import java.util.List;
import java.util.Scanner;

import Classes.model.*;
import Classes.utils.ColoresUtils;
import Classes.utils.InputUtils;
import Classes.utils.TitlesUtils;
import Classes.utils.UtilidadesAutores;

import java.time.LocalDate;

public class ServiceAutores {
    
    public static void añadirAutor(Scanner sc, List<Autor> autores, List<Libro> libros) {
        
        TitlesUtils.tituloNuevoAutor();
        //solicitud de datos al usuario
        String nombre = InputUtils.leerString(sc, "\nNombre del autor: ");

        String nacionalidad = InputUtils.leerString(sc, "Nacionalidad: ");

        LocalDate fechaNacimiento = InputUtils.leerFecha(sc, "Fecha de nacimiento: ");

        //vamos a crear un autor que este vivo
        boolean defuncion = InputUtils.leerBoolean(sc, "¿El autor ha " + ColoresUtils.NEGRITA + "fallecido" + ColoresUtils.RESET + "?: ");
        
        LocalDate fechaFallecimiento = null;
        if (defuncion) {
            do {
                fechaFallecimiento = InputUtils.leerFecha(sc, "Fecha de fallecimiento: ");
                if (fechaFallecimiento.isBefore(fechaNacimiento)) {
                    System.out.println("La fecha de fallecimiento " + ColoresUtils.ROJO + "no puede ser anterior" + ColoresUtils.RESET + " a la fecha de nacimiento.");
                }
            } while (fechaFallecimiento.isBefore(fechaNacimiento));
        }
        
        String biografia = InputUtils.leerString(sc, "Biografía: ");
        String foto = InputUtils.leerString(sc, "Foto del autor: ");
        String generoLiterario = InputUtils.leerString(sc, "Género literario: ");
        String premios = InputUtils.leerString(sc, "Premios obtenidos: ");
        String obrasDestacadas = InputUtils.leerString(sc, "Obras destacadas: ");

        int id = autores.size() + 1; //asignacion de ID automatica

        //creamos un nuevo autor
        Autor nuevoAutor = new Autor(
            id,
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

        autores.add(nuevoAutor); //lo añadimos a la lista
        InputUtils.limpiarPantalla();
        System.out.println(ColoresUtils.VERDE + ColoresUtils.NEGRITA + "Autor añadido correctamente." + ColoresUtils.RESET);
        InputUtils.pausa(sc);
        InputUtils.limpiarPantalla();
    }

    public static void eliminarAutor(Scanner sc, List<Autor> autores, List<Libro> libros) {
        if (autores.isEmpty()) {
            System.out.println(ColoresUtils.ROJO + ColoresUtils.NEGRITA + "No hay autores para eliminar." + ColoresUtils.RESET);
            InputUtils.pausa(sc);
            InputUtils.limpiarPantalla();
        } else {
            TitlesUtils.tituloEliminarAutor();
            for (Autor a : autores) {
                System.out.println(a.getIdAutor() + " - " + a.getNombre());
            }

            System.out.print("\nIntroduce el" + ColoresUtils.NEGRITA + ColoresUtils.ROJO + " ID " + ColoresUtils.RESET + ColoresUtils.ROJO + "del autor a eliminar: " + ColoresUtils.RESET);
            int id = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            boolean eliminado = false;

            for (int i = 0; i < autores.size(); i++) {
                if (autores.get(i).getIdAutor() == id) {
                    autores.remove(i);
                    eliminado = true;
                    System.out.println(ColoresUtils.VERDE + ColoresUtils.NEGRITA + "\nAutor eliminado correctamente." + ColoresUtils.RESET);
                    InputUtils.pausa(sc);
                    InputUtils.limpiarPantalla();
                    break;
                }
            }

            if (!eliminado) {
                System.out.println(ColoresUtils.ROJO + ColoresUtils.NEGRITA + "\nNo se encontró ningún autor con ese ID." + ColoresUtils.RESET);
            }
        }
    }

    public static void visualizarAutores(Scanner sc, List<Autor> autores, List<Libro> libros) {
        TitlesUtils.tituloAutores();

        for (Autor a : autores) {
            System.out.println(ColoresUtils.ROJO + a.getIdAutor() + ColoresUtils.RESET + " - " + a.getNombre()); //printeamos lo que nos devuelva los getters
        }
        int id = InputUtils.leerInt(sc, "\nIntroduce el " + ColoresUtils.ROJO + "ID" + ColoresUtils.RESET + " del autor para ver detalles " + ColoresUtils.NEGRITA + "(0 para salir): " + ColoresUtils.RESET);

        if (id != 0) {
            boolean encontrado = false;

            for (Autor a : autores) {
                if (a.getIdAutor() == id) {
                    System.out.println(ColoresUtils.NEGRITA + "\n---- DETALLES DEL AUTOR ----" + ColoresUtils.RESET);
                    System.out.println("ID: " + a.getIdAutor());
                    System.out.println("Nombre: " + a.getNombre());
                    System.out.println("Nacionalidad: " + a.getNacionalidad());
                    System.out.println("Fecha de Nacimiento: " + a.getFechaNacimiento());
                    System.out.println("Defunción: " + (a.isDefuncion() ? ColoresUtils.VERDE + "Sí" + ColoresUtils.RESET : ColoresUtils.ROJO + "No" + ColoresUtils.RESET));
                    if (a.isDefuncion()) {
                        System.out.println("Fecha de Fallecimiento: " + a.getFechaFallecimiento());
                    }
                    System.out.println("Biografía: " + a.getBiografia());
                    System.out.println("Foto: " + a.getFoto());
                    System.out.println("Género Literario: " + a.getGeneroLiterario());
                    System.out.println("Premios: " + a.getPremios());
                    System.out.println("Obras Destacadas: " + a.getObrasDestacadas());
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                System.out.println(ColoresUtils.ROJO + ColoresUtils.NEGRITA + "\nNo se encontró ningún autor con ese ID." + ColoresUtils.RESET);
            }
        }
        InputUtils.pausa(sc);
        InputUtils.limpiarPantalla();
    }

    public static void estadisticasAutores(Scanner sc, List<Autor> autores, List<Libro> libros) {
        TitlesUtils.tituloEstadisticas();
        System.out.println("\n1. " + ColoresUtils.AZUL + "Libros por autor" + ColoresUtils.RESET);
        System.out.println("2. " + ColoresUtils.MORADO + "Páginas por libro " + ColoresUtils.RESET);
        System.out.println("3. " + ColoresUtils.NARANJA + "Edades " + ColoresUtils.RESET);
        System.out.println("4. " + ColoresUtils.VERDE + "Total " + ColoresUtils.RESET);
        System.out.println("5. " + ColoresUtils.ROJO + ColoresUtils.NEGRITA + ColoresUtils.SUBRAYADO + "Salir" + ColoresUtils.RESET);
        int tipo = InputUtils.leerNumeroMenu(sc, "\nSelecciona una opción: ", 5);
        InputUtils.limpiarPantalla();
        int stat;

        switch (tipo) {
            case 1:
                TitlesUtils.tituloEstadisticas();
                System.out.println("\n1. Autor con " + ColoresUtils.CYAN + "más libros" + ColoresUtils.RESET + " en la biblioteca");
                System.out.println("2. Autor con " + ColoresUtils.NARANJA + "menos libros" + ColoresUtils.RESET + " en la biblioteca");
                System.out.println("3. " + ColoresUtils.ROJO + ColoresUtils.NEGRITA + ColoresUtils.SUBRAYADO + "Salir" + ColoresUtils.RESET);
                stat = InputUtils.leerNumeroMenu(sc, "\nSelecciona una opción: ", 3);
                InputUtils.limpiarPantalla();

                switch (stat) {
                    case 1:
                        List<Autor> maxAutores = UtilidadesAutores.autoresConMasLibros(autores);

                        if (maxAutores.isEmpty()) {
                            System.out.println(ColoresUtils.ROJO + "No hay autores en la biblioteca." + ColoresUtils.RESET);
                        } else {
                            int maxLibros = maxAutores.get(0).getNumeroLibros();

                            System.out.println("Autor/es con " + ColoresUtils.CYAN + "más libros " + ColoresUtils.RESET + ColoresUtils.NEGRITA + "(" + maxLibros + " libros)" + ColoresUtils.RESET);

                            for (Autor a : maxAutores) {
                                System.out.println("- " + a.getNombre());
                            }
                        }
                        InputUtils.pausa(sc);
                        InputUtils.limpiarPantalla();
                        break;
                    case 2:
                        List<Autor> minAutores = UtilidadesAutores.autoresConMenosLibros(autores);
                        
                        if (minAutores.isEmpty()) {
                            System.out.println(ColoresUtils.ROJO + ColoresUtils.NEGRITA + "No hay autores en la biblioteca." + ColoresUtils.RESET);
                        } else {
                            int minLibros = minAutores.get(0).getNumeroLibros();

                            System.out.println("Autor/es con " + ColoresUtils.NARANJA + "menos libros " + ColoresUtils.RESET + ColoresUtils.NEGRITA + "(" + minLibros + " libros)" + ColoresUtils.RESET);

                            for (Autor a : minAutores) {
                                System.out.println("- " + a.getNombre());
                            }
                        }
                        InputUtils.pausa(sc);
                        InputUtils.limpiarPantalla();
                        break;
                    default:
                        System.out.println(ColoresUtils.NEGRITA + ColoresUtils.ROJO + "Opción no valida." + ColoresUtils.RESET);
                        break;
                }
                break;
            case 2:
                TitlesUtils.tituloEstadisticas();
                System.out.println("\n1. Libro " + ColoresUtils.CYAN + "más Largo" + ColoresUtils.RESET);
                System.out.println("2. Libro " + ColoresUtils.NARANJA + "más Corto" + ColoresUtils.RESET);
                System.out.println("3. "  + ColoresUtils.ROJO + ColoresUtils.NEGRITA + ColoresUtils.SUBRAYADO + "Salir" + ColoresUtils.RESET);
                stat = InputUtils.leerNumeroMenu(sc, "\nSelecciona una opción: ", 3);
                InputUtils.limpiarPantalla();

                switch (stat) {
                    case 1:
                        List<Autor> autoresMaxLibro = UtilidadesAutores.autoresLibroMasLargo(autores, libros);

                        if (autoresMaxLibro == null || autoresMaxLibro.isEmpty() || libros == null || libros.isEmpty()) {
                            System.out.println(ColoresUtils.ROJO + ColoresUtils.NEGRITA + "No hay autores o libros en la biblioteca." + ColoresUtils.RESET);
                        } else {

                            int maxPaginas = libros.stream()
                                    .mapToInt(Libro::getNumeroPaginas)
                                    .max()
                                    .orElse(0);

                            System.out.println("Autor/es con el libro " + ColoresUtils.CYAN + "más largo " + ColoresUtils.RESET + ColoresUtils.NEGRITA + "(" + maxPaginas + " páginas):" + ColoresUtils.RESET);
                            for (Autor a : autoresMaxLibro) {
                                System.out.println("- " + a.getNombre());
                            }
                        }
                        InputUtils.pausa(sc);
                        InputUtils.limpiarPantalla();
                        break;
                    case 2:
                        List<Autor> autoresMinLibro = UtilidadesAutores.autoresLibroMasCorto(autores, libros);

                        if (autoresMinLibro == null || autoresMinLibro.isEmpty() || libros == null || libros.isEmpty()) {
                            System.out.println(ColoresUtils.ROJO + ColoresUtils.NEGRITA + "No hay autores o libros en la biblioteca." + ColoresUtils.RESET);
                        } else {

                            int minPaginas = libros.stream()
                                    .mapToInt(Libro::getNumeroPaginas)
                                    .min()
                                    .orElse(0);

                            System.out.println("Autor/es con el libro " + ColoresUtils.NARANJA + "más corto " + ColoresUtils.RESET + ColoresUtils.NEGRITA + "(" + minPaginas + " páginas):" + ColoresUtils.RESET);
                            for (Autor a : autoresMinLibro) {
                                System.out.println("- " + a.getNombre());
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
                System.out.println("\n1. Autor " + ColoresUtils.CYAN + "más viejo" + ColoresUtils.RESET);
                System.out.println("2. Autor " + ColoresUtils.NARANJA + "más joven" + ColoresUtils.RESET);
                System.out.println("3. " + ColoresUtils.MORADO + "Edad media" + ColoresUtils.RESET + " de los autores");
                System.out.println("4. "  + ColoresUtils.ROJO + ColoresUtils.NEGRITA + ColoresUtils.SUBRAYADO + "Salir" + ColoresUtils.RESET);
                stat = InputUtils.leerNumeroMenu(sc, "\nSelecciona una opción: ", 4);
                InputUtils.limpiarPantalla();

                switch (stat) {
                    case 1:
                        List<Autor> viejos = UtilidadesAutores.autoresMasViejos(autores);

                        if (viejos.isEmpty()) {
                            System.out.println(ColoresUtils.ROJO + "No hay autores en la biblioteca." + ColoresUtils.RESET);
                        } else {
                            System.out.println(ColoresUtils.CYAN + "Autor/es más viejo/s " + ColoresUtils.RESET + ColoresUtils.NEGRITA + "(" + viejos.get(0).getEdad() + " años)" + ColoresUtils.RESET);
                            for (Autor a : viejos) {
                                System.out.println("- " + a.getNombre());
                            }
                        }
                        InputUtils.pausa(sc);
                        InputUtils.limpiarPantalla();
                        break;
                    case 2:
                        List<Autor> jovenes = UtilidadesAutores.autoresMasJovenes(autores);

                        if (jovenes.isEmpty()) {
                            System.out.println(ColoresUtils.ROJO + ColoresUtils.NEGRITA + "No hay autores en la biblioteca." + ColoresUtils.RESET);
                        } else {
                            System.out.println(ColoresUtils.NARANJA + "Autor/es más joven/es " + ColoresUtils.RESET + ColoresUtils.NEGRITA + "(" + jovenes.get(0).getEdad() + " años)" + ColoresUtils.RESET);
                            for (Autor a : jovenes) {
                                System.out.println("- " + a.getNombre());
                            }
                        }
                        InputUtils.pausa(sc);
                        InputUtils.limpiarPantalla();
                        break;
                    case 3:
                        double media = UtilidadesAutores.edadMediaAutores(autores);
                        System.out.println("La " + ColoresUtils.MORADO + "edad media" + ColoresUtils.RESET + " de los autores es " + ColoresUtils.NEGRITA + media + ColoresUtils.RESET + "años");
                        InputUtils.pausa(sc);
                        InputUtils.limpiarPantalla();
                        break;
                    default:
                        break;
                }
                break;
            case 4: 
                int total = UtilidadesAutores.totalAutores(autores);
                System.out.println("El " + ColoresUtils.NEGRITA + "total" + ColoresUtils.RESET + " de autores en la biblioteca es " + ColoresUtils.VERDE + ColoresUtils.NEGRITA + total + ColoresUtils.RESET);
                InputUtils.pausa(sc);
                InputUtils.limpiarPantalla();
                break;
            default:
                break;
        }
    }
}