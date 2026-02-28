package br.dev.hygino.dao;

import br.dev.hygino.dto.InsertBookDto;
import br.dev.hygino.jdbc.DatabaseConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookDao {
    private final Connection connection;

    public BookDao() {
        this.connection = new DatabaseConnection().getConnection();
    }

    public void insertBook(InsertBookDto dto) {
        final var sql = """
                INSERT INTO tb_book(title,author,code,pages,publisher,edition)
                VALUES (?,?,?,?,?,?)
                """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // 1. Atribuir os valores do objeto Livro aos parâmetros da SQL
            stmt.setString(1, dto.title());
            stmt.setString(2, dto.author());
            stmt.setString(3, dto.code());
            stmt.setInt(4, dto.pages());
            stmt.setString(5, dto.publisher());
            stmt.setInt(6, dto.edition());

            // 2. Executar a consulta
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Livro " + dto.title() + " salvo com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar usuário: " + e.getMessage());
        }
    }
}
