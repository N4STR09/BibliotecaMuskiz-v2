package src.Classes.menu;

import java.util.Scanner;

import src.Classes.service.ServiceUsuarios;
import src.Classes.utils.ColoresUtils;
import src.Classes.utils.InputUtils;
import src.Classes.utils.TitlesUtils;

public class MenuUsuarios {

    public static void mostrar(Scanner sc) {

        int opcion;

        do {
            TitlesUtils.tituloUsuarios();

            System.out.println("\n1. " + ColoresUtils.VERDE + "Añadir" + ColoresUtils.RESET);
            System.out.println("2. " + ColoresUtils.ROJO + "Eliminar" + ColoresUtils.RESET);
            System.out.println("3. " + ColoresUtils.AZUL + "Mostrar" + ColoresUtils.RESET);
            System.out.println("4. " + ColoresUtils.ROJO + ColoresUtils.NEGRITA + ColoresUtils.SUBRAYADO + "Salir" + ColoresUtils.RESET);

            opcion = InputUtils.leerNumeroMenu(sc, "\nSelecciona una opción: ", 4);
            InputUtils.limpiarPantalla();

            switch (opcion) {

                case 1 -> ServiceUsuarios.añadirUsuario(sc);

                case 2 -> ServiceUsuarios.eliminarUsuario(sc);

                case 3 -> ServiceUsuarios.mostrarUsuarios(sc);

                case 4 -> {}

                default -> System.out.println(ColoresUtils.ROJO + ColoresUtils.NEGRITA + "Opción no válida." + ColoresUtils.RESET);
            }

        } while (opcion != 4);
    }
}
