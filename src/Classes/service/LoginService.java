package src.Classes.service;

import java.util.Scanner;
import src.Classes.model.Usuario;
import src.Classes.repository.UsuarioRepository;
import src.Classes.utils.TitlesUtils;

public class LoginService {

    public static Usuario login(Scanner sc) {
        
        TitlesUtils.tituloBiblioteca();
        TitlesUtils.tituloLogin();

        System.out.print("DNI: ");
        String dni = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        Usuario u = UsuarioRepository.login(dni, password);

        if (u != null && u.isActivo()) {
            System.out.println("Login correcto");
            return u;
        }

        System.out.println("Acceso denegado");
        return null;
    }
}
