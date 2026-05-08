package src.Classes.menu;

import java.util.Scanner;

import src.Classes.service.ServiceAutores;
import src.Classes.utils.ColoresUtils;
import src.Classes.utils.InputUtils;
import src.Classes.utils.TitlesUtils;

public class MenuAutores {
    public static void mostrar(Scanner sc) {
        
        TitlesUtils.tituloAutores();
        System.out.println("\n1. " + ColoresUtils.VERDE + "Añadir"+ ColoresUtils.RESET);
        System.out.println("2. " + ColoresUtils.ROJO + "Eliminar" + ColoresUtils.RESET);
        System.out.println("3. " + ColoresUtils.AZUL + "Visualizar" + ColoresUtils.RESET);
        System.out.println("4. " + ColoresUtils.AMARILLO + "Estadísticas" + ColoresUtils.RESET);
        System.out.println("5. " + ColoresUtils.ROJO + ColoresUtils.NEGRITA + ColoresUtils.SUBRAYADO + "Salir" + ColoresUtils.RESET);
        int accion = InputUtils.leerNumeroMenu(sc, "\nSelecciona una opción: ", 5);
        InputUtils.limpiarPantalla();

        switch (accion) {
            case 1:
                ServiceAutores.añadirAutor(sc);
                break;
            case 2:
                ServiceAutores.eliminarAutor(sc);
                break;
            case 3:
                ServiceAutores.visualizarAutores(sc);
                break;
            case 4: 
                ServiceAutores.estadisticasAutores(sc);
                break;
            default:
                break;
        }
    }
}
