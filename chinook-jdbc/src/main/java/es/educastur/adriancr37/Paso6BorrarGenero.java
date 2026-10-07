package es.educastur.adriancr37;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Paso6BorrarGenero {

    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/chinook";
        String usuario = "root";
        String contraseña = "";

        String sql = "DELETE FROM Genre WHERE Name = ?";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
                PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setString(1, "Synthwave retro");
            int filas = sentencia.executeUpdate();
            System.out.println(filas + " fila(s) borrada(s)");

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}