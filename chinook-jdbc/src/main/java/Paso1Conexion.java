package es.educastur.adriancr37;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Paso1Conexion {

    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/chinook";
        String usuario = "root";
        String contraseña = "";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña)) {
            System.out.println("Conectado correctamente a: " + conexion.getCatalog());
        } catch (SQLException e) {
            System.err.println("Error al conectar: " + e.getMessage());
        }
    }
}