package src.Classes.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import src.Classes.model.Usuario;
import src.Classes.repository.ConexionBD;
import src.Classes.utils.ColoresUtils;
import src.Classes.utils.InputUtils;
import src.Classes.utils.TitlesUtils;

public class ServiceCuenta {

    //modificar datos
    public static void modificarDatos(Scanner sc, Usuario u) {

        int opcion;

        do {

            TitlesUtils.tituloDatosActuales();
            System.out.println("1. " + ColoresUtils.AZUL + "Nombre: " + ColoresUtils.RESET + u.getNombre());
            System.out.println("2. " + ColoresUtils.AMARILLO + "DNI: " + ColoresUtils.RESET + u.getDni());
            System.out.println("3. " + ColoresUtils.NARANJA + "Activo: " + ColoresUtils.RESET + u.isActivo());
            System.out.println("4. " + ColoresUtils.ROJO + ColoresUtils.NEGRITA + ColoresUtils.SUBRAYADO +  "Salir" + ColoresUtils.RESET);

            opcion = InputUtils.leerNumeroMenu(sc, "\n¿Qué quieres modificar?: ", 4);

            switch (opcion) {

                case 1 -> {
                    String nuevoNombre = InputUtils.leerString(sc, "Nuevo nombre: ");
                    actualizarCampo(u.getIdUsuario(), "nombre", nuevoNombre);
                    u.setNombre(nuevoNombre);
                }

                case 2 -> {
                    String nuevoDni = InputUtils.leerPassword(sc, "Nuevo DNI: ");
                    actualizarCampo(u.getIdUsuario(), "dni", nuevoDni);
                    u.setDni(nuevoDni);
                }

                case 3 -> {
                    boolean activo = InputUtils.leerBoolean(sc, "¿Usuario activo?: ");
                    actualizarCampo(u.getIdUsuario(), "activo", activo);
                    u.setActivo(activo);
                }

                default -> {}
            }

        } while (opcion != 4);

        InputUtils.limpiarPantalla();
    }

    //cambiar contraseña
    public static void cambiarPassword(Scanner sc, Usuario u) {

        String actual = InputUtils.leerPassword(sc, "Contraseña actual: ");

        if (!u.getPassword().equals(actual)) {
            System.out.println(ColoresUtils.ROJO + "Contraseña incorrecta" + ColoresUtils.RESET);
            return;
        }

        String nueva = InputUtils.leerPassword(sc, "Nueva contraseña: ");

        actualizarCampo(u.getIdUsuario(), "password", nueva);
        u.setPassword(nueva);

        System.out.println(ColoresUtils.VERDE + "Contraseña actualizada correctamente" + ColoresUtils.RESET);

        InputUtils.pausa(sc);
        InputUtils.limpiarPantalla();
    }

    //update generico
    private static void actualizarCampo(int idUsuario, String campo, Object valor) {

        String sql = "UPDATE usuarios SET " + campo + " = ? WHERE id_usuario = ?";

        try (Connection con = ConexionBD.conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, valor);
            ps.setInt(2, idUsuario);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}