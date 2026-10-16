package es.educastur.adriancr37.app;

import es.educastur.adriancr37.dao.PistaDAO;
import es.educastur.adriancr37.dao.PistaDAOJdbc;
import es.educastur.adriancr37.modelo.Pista;

import java.sql.SQLException;
import java.util.List;

public class PruebaPistaDAO {

    public static void main(String[] args) {
        PistaDAO pistaDAO = new PistaDAOJdbc();

        try {
            System.out.println("=== C2 · Pistas del género 1 (Rock) ===");
            List<Pista> rock = pistaDAO.findByGenero(1);
            System.out.println("Total: " + rock.size() + " pistas. Las 5 primeras:");
            for (Pista pista : rock.subList(0, 5)) {
                System.out.println(pista);
            }

            System.out.println();
            System.out.println("=== C5 · Pistas del álbum 1 ===");
            for (Pista pista : pistaDAO.findByAlbum(1)) {
                System.out.println(pista);
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
