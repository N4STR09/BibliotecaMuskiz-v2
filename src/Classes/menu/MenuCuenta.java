package src.Classes.menu;

import java.util.Scanner;

import src.Classes.model.Usuario;
import src.Classes.utils.*;
import src.Classes.service.ServiceCuenta;

public class MenuCuenta {

    public static void mostrar(Scanner sc, Usuario usuario) {

        int opcion;

        do {
            TitlesUtils.tituloBiblioteca();

            System.out.println("\n1. " + ColoresUtils.AZUL + "Modificar datos" + ColoresUtils.RESET);
            System.out.println("2. " + ColoresUtils.MORADO + "Cambiar contraseña" + ColoresUtils.RESET);
            System.out.println("3. " + ColoresUtils.ROJO + ColoresUtils.NEGRITA + "Salir" + ColoresUtils.RESET);

            opcion = InputUtils.leerNumeroMenu(sc, "\nSelecciona una opción: ", 3);

            InputUtils.limpiarPantalla();

            switch (opcion) {

                case 1 -> ServiceCuenta.modificarDatos(sc, usuario);
                case 2 -> ServiceCuenta.cambiarPassword(sc, usuario);

                default -> {}
            }

        } while (opcion != 3);
    }
}