package Classes.menu;

import java.util.List;
import java.util.Scanner;
import Classes.model.*;
import Classes.repository.BibliotecaRepository;
import Classes.utils.*;

public class MenuPrincipal {
    public static void mostrar(Scanner sc, List<Autor> autores, List<Libro> libros) {
        int opcion;

        do {
            TitlesUtils.tituloBiblioteca();
            System.out.println("\n1. " + ColoresUtils.MORADO + "Autores" + ColoresUtils.RESET);
            System.out.println("2. " + ColoresUtils.CYAN + "Libros" + ColoresUtils.RESET);
            System.out.println("3. " + ColoresUtils.ROJO + ColoresUtils.NEGRITA + ColoresUtils.SUBRAYADO + "Salir" + ColoresUtils.RESET);
            opcion = InputUtils.leerNumeroMenu(sc, "\nSelecciona una opción: ", 3);
            InputUtils.limpiarPantalla();

            switch (opcion) {
                case 1:
                    MenuAutores.mostrar(sc, autores, libros);
                    break;
                case 2:
                    MenuLibros.mostrar(sc, autores, libros);
                default:
                    break;
            }
        } while (opcion != 3);
        
        BibliotecaRepository.guardarAutores(autores);
        BibliotecaRepository.guardarLibros(libros);

    }
}
