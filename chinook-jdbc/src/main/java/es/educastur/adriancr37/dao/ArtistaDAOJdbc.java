package es.educastur.adriancr37.dao;

import es.educastur.adriancr37.conexion.ConexionBD;
import es.educastur.adriancr37.modelo.Artista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ArtistaDAOJdbc implements ArtistaDAO {

    @Override
    public List<Artista> buscarPorNombre(String texto) throws SQLException {
        String sql = "SELECT ArtistId, Name FROM Artist WHERE Name LIKE ? ORDER BY Name";
        List<Artista> artistas = new ArrayList<>();

        try (Connection conexion = ConexionBD.getConnection();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, "%" + texto + "%");

            try (ResultSet resultado = sentencia.executeQuery()) {
                while (resultado.next()) {
                    artistas.add(new Artista(resultado.getInt("ArtistId"), resultado.getString("Name")));
                }
            }
        }
        return artistas;
    }

    @Override
    public Map<String, Integer> masVendidos(int n) throws SQLException {
        String sql = "SELECT ar.Name, SUM(il.Quantity) AS Unidades "
                + "FROM InvoiceLine il "
                + "JOIN Track t ON t.TrackId = il.TrackId "
                + "JOIN Album al ON al.AlbumId = t.AlbumId "
                + "JOIN Artist ar ON ar.ArtistId = al.ArtistId "
                + "GROUP BY ar.ArtistId, ar.Name "
                + "ORDER BY Unidades DESC, ar.Name "
                + "LIMIT ?";
        Map<String, Integer> ventas = new LinkedHashMap<>();

        try (Connection conexion = ConexionBD.getConnection();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, n);

            try (ResultSet resultado = sentencia.executeQuery()) {
                while (resultado.next()) {
                    ventas.put(resultado.getString("Name"), resultado.getInt("Unidades"));
                }
            }
        }
        return ventas;
    }
}