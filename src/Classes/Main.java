package src.Classes;

import java.util.List;
import java.util.Scanner;

import src.Classes.menu.MenuPrincipal;
import src.Classes.model.Autor;
import src.Classes.model.Libro;
import src.Classes.repository.AutorRepository;
import src.Classes.repository.LibroRepository;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Autor> autores = AutorRepository.cargarAutores();
        List<Libro> libros = LibroRepository.cargarLibros();

        MenuPrincipal.mostrar(sc, autores, libros);

        sc.close();
    }
}