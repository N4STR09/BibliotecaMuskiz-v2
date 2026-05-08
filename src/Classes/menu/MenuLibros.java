package src.Classes.menu;

import java.util.Scanner;

import src.Classes.service.ServiceLibros;
import src.Classes.utils.InputUtils;
import src.Classes.utils.TitlesUtils;

public class MenuLibros {

    public static void mostrar(Scanner sc) {

        TitlesUtils.tituloLibros();

        System.out.println("\n1. Añadir");
        System.out.println("2. Eliminar");
        System.out.println("3. Visualizar");
        System.out.println("4. Estadísticas");
        System.out.println("5. Salir");

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