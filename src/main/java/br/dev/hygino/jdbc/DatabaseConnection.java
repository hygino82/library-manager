package br.dev.hygino.jdbc;

import br.dev.hygino.exceptions.DatabaseException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost/biblioteca";
    private static final String USER = "root";
    private static final String PASSWORD = "89631139";

    private DatabaseConnection() {
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao conectar ao banco de dados!");
        }
    }
}
