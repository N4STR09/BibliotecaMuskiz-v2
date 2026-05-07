package Classes;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Classes.menu.MenuPrincipal;
import Classes.model.Autor;
import Classes.model.Libro;
import Classes.repository.BibliotecaRepository;
import Classes.setup.Inicializaciones;

public class Main {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        List<Autor> autores = BibliotecaRepository.cargarAutores();
        List<Libro> libros = BibliotecaRepository.cargarLibros();

        if (autores == null || libros == null) {
            autores = new ArrayList<>();
            libros = new ArrayList<>();
            Inicializaciones.inicializar(autores, libros);
        }
        MenuPrincipal.mostrar(sc, autores, libros);
        
        sc.close(); //cerramos el scanner para evitar que IDE nos tire un warning de posible data leakage si no lo cerramos
    }
}