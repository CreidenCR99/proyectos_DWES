package es.educastur.adriancr37.app;

import es.educastur.adriancr37.dao.ArtistaDAO;
import es.educastur.adriancr37.dao.ArtistaDAOJdbc;
import es.educastur.adriancr37.modelo.Artista;

import java.sql.SQLException;
import java.util.Map;

public class PruebaArtistaDAO {

    public static void main(String[] args) {
        ArtistaDAO artistaDAO = new ArtistaDAOJdbc();

        try {
            System.out.println("=== C3 · Artistas que contienen \"iron\" ===");
            for (Artista artista : artistaDAO.buscarPorNombre("iron")) {
                System.out.println(artista);
            }

            System.out.println();
            System.out.println("=== C7 · Los 5 artistas más vendidos ===");
            Map<String, Integer> ventas = artistaDAO.masVendidos(5);
            for (Map.Entry<String, Integer> fila : ventas.entrySet()) {
                System.out.println(fila.getKey() + ": " + fila.getValue() + " unidades");
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
