package src.Classes.menu;

import java.util.Scanner;

import src.Classes.service.ServiceLibros;
import src.Classes.utils.ColoresUtils;
import src.Classes.utils.InputUtils;
import src.Classes.utils.TitlesUtils;

public class MenuLibros {

    public static void mostrar(Scanner sc) {

        TitlesUtils.tituloLibros();

        System.out.println("\n1. " + ColoresUtils.VERDE + "Añadir" + ColoresUtils.RESET);
        System.out.println("2. " + ColoresUtils.ROJO + "Eliminar" + ColoresUtils.RESET);
        System.out.println("3. " + ColoresUtils.AZUL + "Visualizar" + ColoresUtils.RESET);
        System.out.println("4. " + ColoresUtils.AMARILLO + "Estadísticas" + ColoresUtils.RESET);
        System.out.println("5. " + ColoresUtils.ROJO + ColoresUtils.NEGRITA + ColoresUtils.SUBRAYADO + "Salir" + ColoresUtils.RESET);

        int accion = InputUtils.leerNumeroMenu(sc, "Selecciona una opción: ", 5);
        InputUtils.limpiarPantalla();

        switch (accion) {
            case 1 -> ServiceLibros.añadirLibro(sc);
            case 2 -> ServiceLibros.eliminarLibro(sc);
            case 3 -> ServiceLibros.mostrarLibros(sc);
            case 4 -> ServiceLibros.estadisticasLibros(sc);
            default -> {}
        }

        
    }
}