package Classes.repository;

import Classes.setup.*;
import Classes.utils.ColoresUtils;
import Classes.model.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

public class BibliotecaRepository {

    private static final String AUTORES_FILE = "./biblioteca-muskiz/BibliotecaMuskiz/data/autores.json";
    private static final String LIBROS_FILE  = "./biblioteca-muskiz/BibliotecaMuskiz/data/libros.json";

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();


    //autores
    public static void guardarAutores(List<Autor> autores) {
        guardarLista(AUTORES_FILE, autores);
    }

    public static List<Autor> cargarAutores() {
        Type type = new TypeToken<List<Autor>>() {}.getType();
        return cargarLista(AUTORES_FILE, type);
    }


    //libros
    public static void guardarLibros(List<Libro> libros) {
        guardarLista(LIBROS_FILE, libros);
    }

    public static List<Libro> cargarLibros() {
        Type type = new TypeToken<List<Libro>>() {}.getType();
        return cargarLista(LIBROS_FILE, type);
    }


    //generico
    private static <T> void guardarLista(String ruta, T data) {
        try {
            File archivo = new File(ruta);
            archivo.getParentFile().mkdirs(); // crea /data si no existe

            try (Writer writer = new FileWriter(archivo)) {
                gson.toJson(data, writer);
            }
        } catch (IOException e) {
            System.out.println(ColoresUtils.ROJO + "Error" + ColoresUtils.RESET + "guardando " + ruta);
        }
    }

    private static <T> T cargarLista(String ruta, Type type) {
        File archivo = new File(ruta);
        if (!archivo.exists()) return null;

        try (Reader reader = new FileReader(archivo)) {
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            System.out.println(ColoresUtils.ROJO + "Error" + ColoresUtils.RESET + "leyendo " + ruta);
            return null;
        }
    }
}
