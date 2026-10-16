package es.educastur.adriancr37.dao;

import es.educastur.adriancr37.modelo.Genero;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface GeneroDAO {

    // C1: todos los géneros, en orden alfabético
    List<Genero> findAll() throws SQLException;

    // C6: nombre de cada género y su número de pistas
    Map<String, Integer> contarPistas() throws SQLException;
}
