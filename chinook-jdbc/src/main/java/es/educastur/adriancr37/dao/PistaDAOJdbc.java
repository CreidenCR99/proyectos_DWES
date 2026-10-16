package es.educastur.adriancr37.dao;

import es.educastur.adriancr37.conexion.ConexionBD;
import es.educastur.adriancr37.modelo.Genero;
import es.educastur.adriancr37.modelo.Pista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PistaDAOJdbc implements PistaDAO {

    private static final String SELECT_PISTA = "SELECT t.TrackId, t.Name, t.Composer, t.Milliseconds, t.UnitPrice, "
            + "g.GenreId, g.Name AS GenreName "
            + "FROM Track t JOIN Genre g ON g.GenreId = t.GenreId ";

    @Override
    public List<Pista> findByGenero(int generoId) throws SQLException {
        String sql = SELECT_PISTA + "WHERE t.GenreId = ? ORDER BY t.Name";
        return consultar(sql, generoId);
    }

    @Override
    public List<Pista> findByAlbum(int albumId) throws SQLException {
        // TODO 1 resuelto: completa el SQL: filtra por t.AlbumId y ordena por t.TrackId
        String sql = SELECT_PISTA + "WHERE t.AlbumId = ? ORDER BY t.TrackId";
        // TODO 2 resuelto: devuelve el resultado de consultar(...), igual que en
        // findByGenero
        return consultar(sql, albumId);
    }

    private List<Pista> consultar(String sql, int parametro) throws SQLException {
        List<Pista> pistas = new ArrayList<>();

        try (Connection conexion = ConexionBD.getConnection();
                PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, parametro);

            try (ResultSet resultado = sentencia.executeQuery()) {
                while (resultado.next()) {
                    pistas.add(mapearPista(resultado));
                }
            }
        }
        return pistas;
    }

    private Pista mapearPista(ResultSet resultado) throws SQLException {
        Pista pista = new Pista();
        pista.setId(resultado.getInt("TrackId"));
        pista.setNombre(resultado.getString("Name"));
        pista.setCompositor(resultado.getString("Composer"));
        // TODO 3 resuelto: guarda los milisegundos (columna Milliseconds)
        pista.setMilisegundos(resultado.getInt("Milliseconds"));
        // TODO 4 resuelto: guarda el precio (columna UnitPrice) con getBigDecimal
        pista.setPrecioUnitario(resultado.getBigDecimal("UnitPrice"));
        // TODO 5 resuelto: crea un Genero con las columnas GenreId y GenreName y
        // guárdalo en la pista
        pista.setGenero(new Genero(resultado.getInt("GenreId"), resultado.getString("GenreName")));
        return pista;
    }
}
