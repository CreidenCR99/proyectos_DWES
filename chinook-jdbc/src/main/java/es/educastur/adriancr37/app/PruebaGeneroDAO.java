package es.educastur.adriancr37.app;

import es.educastur.adriancr37.dao.GeneroDAO;
import es.educastur.adriancr37.dao.GeneroDAOJdbc;
import es.educastur.adriancr37.modelo.Genero;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PruebaGeneroDAO {

    public static void main(String[] args) {
        GeneroDAO generoDAO = new GeneroDAOJdbc();

        try {
            System.out.println("=== C1 · Géneros ===");
            List<Genero> generos = generoDAO.findAll();
            for (Genero genero : generos) {
                System.out.println(genero);
            }
            System.out.println("Total: " + generos.size() + " géneros");

            System.out.println();
            System.out.println("=== C6 · Pistas por género ===");
            Map<String, Integer> recuento = generoDAO.contarPistas();
            for (Map.Entry<String, Integer> fila : recuento.entrySet()) {
                System.out.println(fila.getKey() + ": " + fila.getValue());
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
