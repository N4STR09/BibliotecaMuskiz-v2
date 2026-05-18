package src.Classes;

import java.util.Scanner;

import src.Classes.menu.MenuAdmin;
import src.Classes.menu.MenuPrincipal;
import src.Classes.model.Usuario;
import src.Classes.service.LoginService;
import src.Classes.setup.DatabaseInitializer;
import src.Classes.utils.ColoresUtils;
import src.Classes.utils.InputUtils;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DatabaseInitializer.init();

        Usuario usuario = null;
        int intentos = 0;

        while (usuario == null && intentos < 3) {
            
            usuario = LoginService.login(sc);

            if (usuario == null) {
                intentos++;
                System.out.println(ColoresUtils.ROJO + "Credenciales incorrectas. Intento " + intentos + "/3" + ColoresUtils.RESET);
            }
        }

        if (usuario == null) {
            System.out.println(ColoresUtils.ROJO + "\nDemasiados intentos fallidos. Cerrando programa..." + ColoresUtils.RESET);
            sc.close();
            return;
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