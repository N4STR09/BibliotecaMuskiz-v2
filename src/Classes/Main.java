package src.Classes;

import java.util.Scanner;

import src.Classes.menu.MenuAdmin;
import src.Classes.menu.MenuPrincipal;
import src.Classes.model.Usuario;
import src.Classes.service.LoginService;
import src.Classes.utils.InputUtils;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Usuario usuario = null;

        while (usuario == null) {
            usuario = LoginService.login(sc);
        }

        InputUtils.limpiarPantalla();

        if (usuario.isAdmin()) {
            MenuAdmin.mostrar(sc, usuario);
        } else {
            MenuPrincipal.mostrar(sc, usuario);
        }
        
        sc.close();
    }
}