package es.educastur.adriancr37;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Paso4InsertarGenero {

    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/chinook";
        String usuario = "root";
        String contraseña = "";

        String sql = "INSERT INTO Genre (Name) VALUES (?)";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
                PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setString(1, "Synthwave");
            int filas = sentencia.executeUpdate();
            System.out.println(filas + " fila(s) insertada(s)");

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}