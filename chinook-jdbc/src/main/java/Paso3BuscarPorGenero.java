package es.educastur.adriancr37;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Paso3BuscarPorGenero {

    public static void main(String[] args) {

        String url = "jdbc:mariadb://localhost:3306/chinook";
        String usuario = "root";
        String contraseña = "";
        int generoId = 1; // Rock, en el Chinook estándar 

        String sql = "SELECT Name FROM Track WHERE GenreId = ? ORDER BY Name LIMIT 15";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
             PreparedStatement sentencia = conexion.prepareStatement(sql)
        ) {
            sentencia.setInt(1, generoId);

            try (ResultSet resultado = sentencia.executeQuery()) {
                while (resultado.next()) {
                    System.out.println(resultado.getString("Name"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}