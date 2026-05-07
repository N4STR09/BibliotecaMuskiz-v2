package Classes.menu;

import java.util.List;
import java.util.Scanner;

import Classes.model.*;
import Classes.service.ServiceAutores;
import Classes.utils.ColoresUtils;
import Classes.utils.InputUtils;
import Classes.utils.TitlesUtils;

public class MenuAutores {
    public static void mostrar(Scanner sc, List<Autor> autores, List<Libro> libros) {
        
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
                ServiceAutores.añadirAutor(sc, autores, libros);
                break;
            case 2:
                ServiceAutores.eliminarAutor(sc, autores, libros);
                break;
            case 3:
                ServiceAutores.visualizarAutores(sc, autores, libros);
                break;
            case 4: 
                ServiceAutores.estadisticasAutores(sc, autores, libros);
                break;
            default:
                break;
        }
    }
}
