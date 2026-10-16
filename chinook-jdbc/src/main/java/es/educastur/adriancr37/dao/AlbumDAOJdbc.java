package es.educastur.adriancr37.dao;

import es.educastur.adriancr37.conexion.ConexionBD;
import es.educastur.adriancr37.modelo.Album;
import es.educastur.adriancr37.modelo.Artista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAOJdbc implements AlbumDAO {

    @Override
    public List<Album> findByArtista(int artistaId) throws SQLException {
        String sql = "SELECT al.AlbumId, al.Title, ar.ArtistId, ar.Name "
                + "FROM Album al JOIN Artist ar ON ar.ArtistId = al.ArtistId "
                + "WHERE ar.ArtistId = ? "
                + "ORDER BY al.Title";
        List<Album> albumes = new ArrayList<>();

        try (Connection conexion = ConexionBD.getConnection();
                PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, artistaId);

            try (ResultSet resultado = sentencia.executeQuery()) {
                while (resultado.next()) {
                    Artista artista = new Artista(resultado.getInt("ArtistId"), resultado.getString("Name"));
                    albumes.add(new Album(resultado.getInt("AlbumId"), resultado.getString("Title"), artista));
                }
            }
        }
        return albumes;
    }

    @Override
    public boolean insert(Album album) throws SQLException {
        String sql = "INSERT INTO Album (Title, ArtistId) VALUES (?, ?)";

        try (Connection conexion = ConexionBD.getConnection();
                PreparedStatement sentencia = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            sentencia.setString(1, album.getTitulo());
            sentencia.setInt(2, album.getArtista().getId());
            int filas = sentencia.executeUpdate();

            try (ResultSet claves = sentencia.getGeneratedKeys()) {
                if (claves.next()) {
                    album.setId(claves.getInt(1));
                }
            }
            return filas == 1;
        }
    }

    @Override
    public boolean update(Album album) throws SQLException {
        String sql = "UPDATE Album SET Title = ?, ArtistId = ? WHERE AlbumId = ?";

        try (Connection conexion = ConexionBD.getConnection();
                PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            // TODO 1 resuelto: rellena los tres ? en orden: título, id del artista e id del
            // álbum
            sentencia.setString(1, album.getTitulo());
            sentencia.setInt(2, album.getArtista().getId());
            sentencia.setInt(3, album.getId());

            // TODO 2 resuelto: ejecuta la sentencia y devuelve true si ha cambiado
            // exactamente 1 fila
            return sentencia.executeUpdate() == 1;
        }
    }

    @Override
    public boolean delete(int albumId) throws SQLException {
        String sql = "DELETE FROM Album WHERE AlbumId = ?";

        // TODO 3 resuelto: abre la conexión y prepara la sentencia en un
        // try-with-resources
        try (Connection conexion = ConexionBD.getConnection();
                PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            // TODO 4 resuelto: rellena el ? con albumId, ejecuta y devuelve true si se ha
            // borrado 1 fila
            sentencia.setInt(1, albumId);
            return sentencia.executeUpdate() == 1;
        }
    }
}
