package src.Classes.menu;

import java.util.Scanner;

import src.Classes.model.Usuario;
import src.Classes.utils.*;
import src.Classes.service.ServicePrestamos;

public class MenuPrestamos {

    public static void mostrar(Scanner sc, Usuario usuario) {

        int opcion;

        do {
            TitlesUtils.tituloBiblioteca();

            System.out.println("\n1. " + ColoresUtils.VERDE + "Solicitar préstamo" + ColoresUtils.RESET);
            System.out.println("2. " + ColoresUtils.CYAN + "Ver préstamos activos" + ColoresUtils.RESET);
            System.out.println("3. " + ColoresUtils.AMARILLO + "Gestionar préstamos" + ColoresUtils.RESET);
            System.out.println("4. " + ColoresUtils.ROJO + ColoresUtils.NEGRITA + "Salir" + ColoresUtils.RESET);

            opcion = InputUtils.leerNumeroMenu(sc, "\nSelecciona una opción: ", 4);

            InputUtils.limpiarPantalla();

            switch (opcion) {

                case 1 -> ServicePrestamos.solicitarPrestamo(sc, usuario);

                case 2 -> ServicePrestamos.verPrestamosActivos(sc, usuario);

                case 3 -> ServicePrestamos.gestionarPrestamos(sc, usuario);

                case 4 -> {
                    // salir limpio
                }

                default -> System.out.println("Opción inválida");
            }

        } while (opcion != 4);
    }
}