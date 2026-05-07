package Classes.setup;

import java.time.LocalDate;
import java.util.List;

import Classes.model.Autor;
import Classes.model.Libro;

public class Inicializaciones {
        public static void inicializar(List<Autor> autores, List<Libro> libros) {

                Autor autor1 = new Autor(1, "Miguel de Cervantes", "Española", LocalDate.of(1547, 9, 29), true,
                        LocalDate.of(1616, 4, 22), "Escritor del Siglo de Oro", "cervantes.jpg",
                        "Novela", "Ninguno", "Don Quijote");

                Autor autor2 = new Autor(2, "Gabriel García Márquez", "Colombiana", LocalDate.of(1927, 3, 6), true,
                        LocalDate.of(2014, 4, 17), "Autor del realismo mágico", "gabo.jpg",
                        "Novela", "Nobel", "Cien años de soledad");

                Autor autor3 = new Autor(3, "J.K. Rowling", "Británica", LocalDate.of(1965, 7, 31), false,
                        null, "Autora de Harry Potter", "rowling.jpg",
                        "Fantasía", "Ninguno", "Harry Potter");

                Autor autor4 = new Autor(4, "Isabel Allende", "Chilena", LocalDate.of(1942, 8, 2), false,
                        null, "Autora de La casa de los espíritus", "allende.jpg",
                        "Novela", "Ninguno", "La casa de los espíritus");

                Autor autor5 = new Autor(5, "Mario Vargas Llosa", "Peruana", LocalDate.of(1936, 3, 28), false,
                        null, "Escritor y político", "vargas.jpg",
                        "Novela", "Nobel", "La ciudad y los perros");

                autores.add(autor1);
                autores.add(autor2);
                autores.add(autor3);
                autores.add(autor4);
                autores.add(autor5);

        
                libros.add(new Libro(1, 2, 863, "Don Quijote de la Mancha", autor1, "Novela", 1605,
                        "Francisco de Robles", "", "Español", "Físico", "", "", "Literatura", true));

                libros.add(new Libro(2, 1, 350, "Novelas ejemplares", autor1, "Novela", 1613,
                        "Juan de la Cuesta", "", "Español", "Físico", "", "", "Literatura", true));

                libros.add(new Libro(3, 4, 471, "La Galatea", autor1, "Novela", 1585,
                        "Blas de Robles", "", "Español", "Físico", "", "", "Literatura", true));

                libros.add(new Libro(4, 3, 417, "Cien años de soledad", autor2, "Novela", 1967,
                        "Sudamericana", "", "Español", "Físico", "", "", "Literatura", true));

                libros.add(new Libro(5, 2, 120, "Crónica de una muerte anunciada", autor2, "Novela", 1981,
                        "Oveja Negra", "", "Español", "Físico", "", "", "Literatura", true));

                libros.add(new Libro(6, 1, 368, "El amor en los tiempos del cólera", autor2, "Novela", 1985,
                        "Oveja Negra", "", "Español", "Físico", "", "", "Literatura", true));

                libros.add(new Libro(7, 2, 192, "El coronel no tiene quien le escriba", autor2, "Novela", 1961,
                        "Sudamericana", "", "Español", "Físico", "", "", "Literatura", true));

                libros.add(new Libro(8, 3, 450, "Harry Potter y el Cáliz de Fuego", autor3, "Fantasía", 2002,
                        "Bloomsbury", "", "Español", "Físico", "", "", "Literatura", true));

                libros.add(new Libro(9, 5, 223, "Harry Potter y la piedra filosofal", autor3, "Fantasía", 1997,
                        "Bloomsbury", "", "Español", "Físico", "", "", "Literatura", true));

                libros.add(new Libro(10, 4, 251, "Harry Potter y la cámara secreta", autor3, "Fantasía", 1998,
                        "Bloomsbury", "", "Español", "Físico", "", "", "Literatura", true));

                libros.add(new Libro(11, 3, 317, "Harry Potter y el prisionero de Azkaban", autor3, "Fantasía", 1999,
                        "Bloomsbury", "", "Español", "Físico", "", "", "Literatura", true));

                libros.add(new Libro(12, 2, 636, "Harry Potter y la Orden del Fénix", autor3, "Fantasía", 2003,
                        "Bloomsbury", "", "Español", "Físico", "", "", "Literatura", true));

                libros.add(new Libro(13, 2, 432, "La casa de los espíritus", autor4, "Novela", 1982,
                        "Plaza & Janés", "", "Español", "Físico", "", "", "Literatura", true));

                libros.add(new Libro(14, 1, 368, "Eva Luna", autor4, "Novela", 1987,
                        "Plaza & Janés", "", "Español", "Físico", "", "", "Literatura", true));

                libros.add(new Libro(15, 2, 416, "Paula", autor4, "Memorias", 1994,
                        "Plaza & Janés", "", "Español", "Físico", "", "", "Literatura", true));

                libros.add(new Libro(16, 3, 384, "Hija de la fortuna", autor4, "Novela", 1999,
                        "Plaza & Janés", "", "Español", "Físico", "", "", "Literatura", true));

                libros.add(new Libro(17, 2, 432, "La ciudad y los perros", autor5, "Novela", 1963,
                        "Seix Barral", "", "Español", "Físico", "", "", "Literatura", true));

                libros.add(new Libro(18, 1, 376, "Conversación en La Catedral", autor5, "Novela", 1969,
                        "Seix Barral", "", "Español", "Físico", "", "", "Literatura", true));

                libros.add(new Libro(19, 2, 304, "Pantaleón y las visitadoras", autor5, "Novela", 1973,
                        "Seix Barral", "", "Español", "Físico", "", "", "Literatura", true));

                libros.add(new Libro(20, 1, 288, "La fiesta del chivo", autor5, "Novela", 2000,
                        "Alfaguara", "", "Español", "Físico", "", "", "Literatura", true));

                //relacion autor-libros
                for (Libro libro : libros) {
                        libro.getAutorObj().addLibro(libro.getIdLibro());
                }
        }
}

