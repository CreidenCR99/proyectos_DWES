package es.educastur.adriancr37.dao;

import es.educastur.adriancr37.modelo.Album;

import java.sql.SQLException;
import java.util.List;

public interface AlbumDAO {

    // C4: álbumes de un artista
    List<Album> findByArtista(int artistaId) throws SQLException;

    // I1: inserta el álbum y le asigna el id generado
    boolean insert(Album album) throws SQLException;

    // U1: cambia el título y el artista del álbum
    boolean update(Album album) throws SQLException;

    // D1: borra el álbum con ese id
    boolean delete(int albumId) throws SQLException;
}
