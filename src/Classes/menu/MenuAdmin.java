package src.Classes.menu;

import java.util.Scanner;
import src.Classes.model.Usuario;
import src.Classes.utils.*;

//Comentarios en ServiceLibros

public class MenuAdmin {

    public static void mostrar(Scanner sc, Usuario usuario) {

        int opcion;

        do {
            TitlesUtils.tituloBiblioteca();

            System.out.println("\n1. " + ColoresUtils.MORADO + "Autores" + ColoresUtils.RESET);
            System.out.println("2. " + ColoresUtils.CYAN + "Libros" + ColoresUtils.RESET);
            System.out.println("3. " + ColoresUtils.AZUL + "Usuarios" + ColoresUtils.RESET);
            System.out.println("4. " + ColoresUtils.ROJO + ColoresUtils.NEGRITA + ColoresUtils.SUBRAYADO + "Salir" + ColoresUtils.RESET);

            opcion = InputUtils.leerNumeroMenu(sc, "\nSelecciona una opción: ", 4);

            InputUtils.limpiarPantalla();

            switch (opcion) {

                case 1 -> MenuAutores.mostrar(sc);
                case 2 -> MenuLibros.mostrar(sc);
                case 3 -> MenuUsuarios.mostrar(sc);

                default -> {}
            }

        } while (opcion != 4);
    }
}