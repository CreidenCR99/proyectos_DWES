package es.educastur.adriancr37.app;

import es.educastur.adriancr37.dao.AlbumDAO;
import es.educastur.adriancr37.dao.AlbumDAOJdbc;
import es.educastur.adriancr37.dao.ArtistaDAO;
import es.educastur.adriancr37.dao.ArtistaDAOJdbc;
import es.educastur.adriancr37.dao.GeneroDAO;
import es.educastur.adriancr37.dao.GeneroDAOJdbc;
import es.educastur.adriancr37.dao.PistaDAO;
import es.educastur.adriancr37.dao.PistaDAOJdbc;
import es.educastur.adriancr37.modelo.Album;
import es.educastur.adriancr37.modelo.Artista;
import es.educastur.adriancr37.modelo.Genero;
import es.educastur.adriancr37.modelo.Pista;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Scanner teclado = new Scanner(System.in);

    private static final GeneroDAO generoDAO = new GeneroDAOJdbc();
    private static final ArtistaDAO artistaDAO = new ArtistaDAOJdbc();
    private static final AlbumDAO albumDAO = new AlbumDAOJdbc();
    private static final PistaDAO pistaDAO = new PistaDAOJdbc();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elige una opción: ");
            try {
                switch (opcion) {
                    case 1 -> listarGeneros();
                    case 2 -> contarPistasPorGenero();
                    case 3 -> buscarArtistas();
                    case 4 -> artistasMasVendidos();
                    case 5 -> albumesDeArtista();
                    case 6 -> pistasDeGenero();
                    case 7 -> pistasDeAlbum();
                    case 8 -> insertarAlbum();
                    case 9 -> cambiarTituloAlbum();
                    case 10 -> borrarAlbum();
                    case 0 -> System.out.println("Hasta pronto.");
                    default -> System.out.println("Esa opción no existe.");
                }
            } catch (SQLException e) {
                System.out.println("No se ha podido completar la operación: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("========= CHINOOK =========");
        System.out.println(" 1. Listar géneros");
        System.out.println(" 2. Géneros con su número de pistas");
        System.out.println(" 3. Buscar artistas");
        System.out.println(" 4. Artistas más vendidos");
        System.out.println(" 5. Álbumes de un artista");
        System.out.println(" 6. Pistas de un género");
        System.out.println(" 7. Pistas de un álbum");
        System.out.println(" 8. Añadir un álbum");
        System.out.println(" 9. Cambiar el título de un álbum");
        System.out.println("10. Borrar un álbum");
        System.out.println(" 0. Salir");
        System.out.println("===========================");
    }

    // ---------- Ejemplos resueltos ----------

    private static void listarGeneros() throws SQLException {
        for (Genero genero : generoDAO.findAll()) {
            System.out.println(genero);
        }
    }

    private static void buscarArtistas() throws SQLException {
        String texto = leerTexto("Texto a buscar: ");
        List<Artista> artistas = artistaDAO.buscarPorNombre(texto);
        if (artistas.isEmpty()) {
            System.out.println("Ningún artista contiene \"" + texto + "\".");
        }
        for (Artista artista : artistas) {
            System.out.println(artista);
        }
    }

    private static void insertarAlbum() throws SQLException {
        String titulo = leerTexto("Título del álbum: ");
        int artistaId = leerEntero("Id del artista: ");
        Album album = new Album(null, titulo, new Artista(artistaId, null));
        if (albumDAO.insert(album)) {
            System.out.println("Álbum guardado con el id " + album.getId() + ".");
        }
    }

    // ---------- Te toca a ti ----------

    private static void contarPistasPorGenero() throws SQLException {
        // TODO opción 2: recorre generoDAO.contarPistas() e imprime "Género: número"
        for (Map.Entry<String, Integer> fila : generoDAO.contarPistas().entrySet()) {
            System.out.println(fila.getKey() + ": " + fila.getValue());
        }
    }

    private static void artistasMasVendidos() throws SQLException {
        // TODO opción 4: pide cuántos artistas quiere ver y usa
        // artistaDAO.masVendidos(n)
        int n = leerEntero("Cuántos artistas quieres ver?: ");
        for (Map.Entry<String, Integer> fila : artistaDAO.masVendidos(n).entrySet()) {
            System.out.println(fila.getKey() + ": " + fila.getValue() + " unidades");
        }
    }

    private static void albumesDeArtista() throws SQLException {
        // TODO opción 5: pide el id del artista y muestra albumDAO.findByArtista(id)
        int id = leerEntero("Id del artista: ");
        List<Album> albumes = albumDAO.findByArtista(id);
        if (albumes.isEmpty()) {
            System.out.println("No hay álbumes para ese artista.");
        }
        for (Album album : albumes) {
            System.out.println(album);
        }
    }

    private static void pistasDeGenero() throws SQLException {
        // TODO opción 6: pide el id del género, muestra cuántas pistas hay y las 20
        // primeras
        int id = leerEntero("Id del género: ");
        List<Pista> pistas = pistaDAO.findByGenero(id);
        System.out.println(
                "Total: " + pistas.size() + " pistas. Mostrando las " + Math.min(20, pistas.size()) + " primeras:");
        for (Pista pista : pistas.subList(0, Math.min(20, pistas.size()))) {
            System.out.println(pista);
        }
    }

    private static void pistasDeAlbum() throws SQLException {
        // TODO opción 7: pide el id del álbum y muestra pistaDAO.findByAlbum(id)
        int id = leerEntero("Id del álbum: ");
        List<Pista> pistas = pistaDAO.findByAlbum(id);
        if (pistas.isEmpty()) {
            System.out.println("No hay pistas para ese álbum.");
        }
        for (Pista pista : pistas) {
            System.out.println(pista);
        }
    }

    private static void cambiarTituloAlbum() throws SQLException {
        // TODO opción 9: pide id del álbum, id de su artista y nuevo título; usa
        // albumDAO.update(...)
        int id = leerEntero("Id del álbum: ");
        int artistaId = leerEntero("Id de su artista: ");
        String titulo = leerTexto("Nuevo título: ");
        Album album = new Album(id, titulo, new Artista(artistaId, null));
        if (albumDAO.update(album)) {
            System.out.println("Álbum actualizado.");
        } else {
            System.out.println("No existe ningún álbum con el id " + id + ".");
        }
    }

    private static void borrarAlbum() throws SQLException {
        // TODO opción 10: pide el id del álbum y usa albumDAO.delete(id); avisa si no
        // existía
        int id = leerEntero("Id del álbum que quieres borrar: ");
        if (albumDAO.delete(id)) {
            System.out.println("Álbum borrado.");
        } else {
            System.out.println("No existe ningún álbum con el id " + id + ".");
        }
    }

    // ---------- Lectura de teclado ----------

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return teclado.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(mensaje));
            } catch (NumberFormatException e) {
                System.out.println("Escribe un número entero.");
            }
        }
    }
}
