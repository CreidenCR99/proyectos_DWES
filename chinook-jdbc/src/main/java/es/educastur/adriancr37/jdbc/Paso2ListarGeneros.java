package es.educastur.adriancr37.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Paso2ListarGeneros {
    public static void main(String[] args) {
        String sql = "SELECT GenreId, Name FROM Genre ORDER BY Name";

        try (Connection conexion = PoolConexiones.getConnection();
             Statement sentencia = conexion.createStatement();
             ResultSet resultado = sentencia.executeQuery(sql)) {

            while (resultado.next()) {
                int id = resultado.getInt("GenreId");
                String nombre = resultado.getString("Name");
                System.out.println(id + " - " + nombre);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
