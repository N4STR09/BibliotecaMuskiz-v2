package src.Classes.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import src.Classes.model.Usuario;
import src.Classes.repository.UsuarioRepository;
import src.Classes.utils.ColoresUtils;
import src.Classes.utils.InputUtils;
import src.Classes.utils.TitlesUtils;

public class ServiceUsuarios {

    //añadir usuario
    public static void añadirUsuario(Scanner sc) {

        String nombre = InputUtils.leerString(sc, "Nombre: ");
        String dni = InputUtils.leerPassword(sc, "DNI: ");
        int nss = InputUtils.leerInt(sc, "Número Seguridad Social: ");
        String password = InputUtils.leerPassword(sc, "Password: ");

        LocalDate fechaNacimiento = InputUtils.leerFecha(sc, "Fecha nacimiento ");

        Integer codPenalizacion = null;

        Usuario u = new Usuario(
                0,
                nombre,
                fechaNacimiento,
                false,
                null,
                true,
                dni,
                nss,
                password,
                codPenalizacion,
                false
        );

        UsuarioRepository.guardarUsuario(u);

        System.out.println("Usuario añadido correctamente.");

        InputUtils.pausa(sc);
        InputUtils.limpiarPantalla();
    }

    //eliminar usuario
    public static void eliminarUsuario(Scanner sc) {

        List<Usuario> usuarios = UsuarioRepository.cargarUsuarios();

        if (usuarios.isEmpty()) {

            System.out.println(
                    ColoresUtils.ROJO +
                    "No hay usuarios." +
                    ColoresUtils.RESET
            );

            InputUtils.pausa(sc);
            InputUtils.limpiarPantalla();
            return;
        }

        TitlesUtils.tituloNuevoUsuario();

        for (Usuario u : usuarios) {
            System.out.println(u.getIdUsuario() + " - " + u.getNombre() + " - " + u.getDni());
        }

        int id = InputUtils.leerInt(sc, "\nID del usuario a eliminar: ");

        boolean existe = usuarios.stream()
                .anyMatch(u -> u.getIdUsuario() == id);

        if (!existe) {

            System.out.println(
                    ColoresUtils.ROJO +
                    "No existe un usuario con ese ID." +
                    ColoresUtils.RESET
            );

        } else {

            UsuarioRepository.eliminarUsuario(id);

            System.out.println(
                    ColoresUtils.VERDE +
                    "Usuario eliminado correctamente." +
                    ColoresUtils.RESET
            );
        }

        InputUtils.pausa(sc);
        InputUtils.limpiarPantalla();
    }

    //mostrar usuario
    public static void mostrarUsuarios(Scanner sc) {

        //TitlesUtils.tituloUsuarios();

        var usuarios = UsuarioRepository.cargarUsuarios();

        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios.");
        } else {
            for (Usuario u : usuarios) {
                System.out.println(
                        u.getIdUsuario() + " - " +
                        u.getNombre() + " - " +
                        u.getDni() + " - " +
                        (u.isActivo() ? "Activo" : "Inactivo")
                );
            }
        }

        InputUtils.pausa(sc);
        InputUtils.limpiarPantalla();
    }
}