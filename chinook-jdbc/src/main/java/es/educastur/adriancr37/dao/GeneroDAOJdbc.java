package es.educastur.adriancr37.dao;

import es.educastur.adriancr37.conexion.ConexionBD;
import es.educastur.adriancr37.modelo.Genero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GeneroDAOJdbc implements GeneroDAO {

    @Override
    public List<Genero> findAll() throws SQLException {
        String sql = "SELECT GenreId, Name FROM Genre ORDER BY Name";
        List<Genero> generos = new ArrayList<>();

        try (Connection conexion = ConexionBD.getConnection();
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                generos.add(new Genero(resultado.getInt("GenreId"), resultado.getString("Name")));
            }
        }
        return generos;
    }

    @Override
    public Map<String, Integer> contarPistas() throws SQLException {
        String sql = "SELECT g.Name, COUNT(t.TrackId) AS NumPistas "
                + "FROM Genre g LEFT JOIN Track t ON t.GenreId = g.GenreId "
                + "GROUP BY g.GenreId, g.Name "
                + "ORDER BY NumPistas DESC, g.Name";
        Map<String, Integer> recuento = new LinkedHashMap<>();

        try (Connection conexion = ConexionBD.getConnection();
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                recuento.put(resultado.getString("Name"), resultado.getInt("NumPistas"));
            }
        }
        return recuento;
    }
}
