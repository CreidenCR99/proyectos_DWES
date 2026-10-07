package es.educastur.adriancr37;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Paso2ListarGeneros {
    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/chinook";
        String usuario = "root";
        String contraseña = "";
        String sql = "SELECT GenreId, Name FROM Genre ORDER BY Name";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
             Statement sentencia = conexion.createStatement()) {

            boolean hayResultado = sentencia.execute(sql);
            System.out.println("execute() devuelve un ResultSet? " + hayResultado);

            if (hayResultado) {
                try (ResultSet resultado = sentencia.getResultSet()) {
                    while (resultado.next()) {
                        System.out.println(resultado.getInt("GenreId") + " - " + resultado.getString("Name"));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}