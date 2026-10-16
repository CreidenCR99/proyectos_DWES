package es.educastur.adriancr37.dao;

import es.educastur.adriancr37.modelo.Artista;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ArtistaDAO {

    // C3: artistas cuyo nombre contiene el texto
    List<Artista> buscarPorNombre(String texto) throws SQLException;

    // C7: los n artistas con más unidades vendidas
    Map<String, Integer> masVendidos(int n) throws SQLException;
}
