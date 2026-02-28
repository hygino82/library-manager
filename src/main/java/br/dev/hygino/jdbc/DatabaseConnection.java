package br.dev.hygino.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public final class DatabaseConnection {
    private final String url = "jdbc:mysql://localhost/biblioteca";
    private final String username = "root";
    private final String password = "89631139";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar se conectar com o banco de dados!\n" + e);
        }
        return null;
    }
}
