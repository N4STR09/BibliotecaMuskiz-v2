package src.Classes.menu;

import java.util.Scanner;

import src.Classes.model.Usuario;
import src.Classes.utils.*;
import src.Classes.service.*;

public class MenuPrincipal {

    public static void mostrar(Scanner sc, Usuario usuario) {

        int opcion;

        do {
            TitlesUtils.tituloBiblioteca();

            System.out.println("\n1. " + ColoresUtils.CYAN + "Libros disponibles" + ColoresUtils.RESET);
            System.out.println("2. " + ColoresUtils.MORADO + "Préstamos" + ColoresUtils.RESET);
            System.out.println("3. " + ColoresUtils.AZUL + "Cambiar contraseña" + ColoresUtils.RESET);
            System.out.println("4. " + ColoresUtils.ROJO + ColoresUtils.NEGRITA + "Salir" + ColoresUtils.RESET);

            opcion = InputUtils.leerNumeroMenu(sc, "\nSelecciona una opción: ", 4);

            InputUtils.limpiarPantalla();

            switch (opcion) {

                case 1 -> ServiceLibros.mostrarLibros(sc);
                case 2 -> MenuPrestamos.mostrar(sc, usuario);
                case 3 -> MenuCuenta.mostrar(sc, usuario);

                default -> {}
            }

        } while (opcion != 4);
    }
}