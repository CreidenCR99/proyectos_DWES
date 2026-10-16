package es.educastur.adriancr37.app;

import es.educastur.adriancr37.dao.AlbumDAO;
import es.educastur.adriancr37.dao.AlbumDAOJdbc;
import es.educastur.adriancr37.modelo.Album;
import es.educastur.adriancr37.modelo.Artista;

import java.sql.SQLException;
import java.util.Scanner;

public class PruebaAlbumDAO {

    public static void main(String[] args) {
        AlbumDAO albumDAO = new AlbumDAOJdbc();
        Scanner teclado = new Scanner(System.in);

        try {
            System.out.println("=== C4 · Álbumes de AC/DC (artista 1) ===");
            for (Album album : albumDAO.findByArtista(1)) {
                System.out.println(album);
            }

            System.out.println();
            System.out.println("=== I1 · Insertar un álbum de Iron Maiden (artista 90) ===");
            Artista ironMaiden = new Artista(90, "Iron Maiden");
            Album nuevo = new Album(null, "Directo en La Laboral", ironMaiden);
            boolean insertado = albumDAO.insert(nuevo);
            System.out.println("Insertado? " + insertado + " · id generado: " + nuevo.getId());
            pausa(teclado, "Refresca Album en el panel Database y busca el id " + nuevo.getId());

            System.out.println("=== U1 · Cambiar el título ===");
            nuevo.setTitulo("Directo en La Laboral (Remaster)");
            boolean actualizado = albumDAO.update(nuevo);
            System.out.println("Actualizado? " + actualizado);
            pausa(teclado, "Refresca Album: el título ha cambiado");

            System.out.println("=== D1 · Borrar el álbum ===");
            boolean borrado = albumDAO.delete(nuevo.getId());
            System.out.println("Borrado? " + borrado);
            System.out.println("Refresca Album: el álbum " + nuevo.getId() + " ya no está.");

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void pausa(Scanner teclado, String mensaje) {
        System.out.println(mensaje + ". Pulsa Intro para continuar...");
        teclado.nextLine();
    }
}
