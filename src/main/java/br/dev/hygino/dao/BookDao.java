package br.dev.hygino.dao;

import br.dev.hygino.dto.InsertBookDto;
import br.dev.hygino.dto.UpdateBookDto;
import br.dev.hygino.exceptions.DatabaseException;
import br.dev.hygino.jdbc.DatabaseConnection;
import br.dev.hygino.models.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDao implements ReturnBook {

    public boolean insertBook(InsertBookDto dto) {
        final var sql = """
                INSERT INTO tb_book(title,author,code,pages,publisher,edition)
                VALUES (?,?,?,?,?,?)
                """;
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            // 1. Atribuir os valores do objeto Livro aos parâmetros da SQL
            stmt.setString(1, dto.title());
            stmt.setString(2, dto.author());
            stmt.setString(3, dto.code());
            stmt.setInt(4, dto.pages());
            stmt.setString(5, dto.publisher());
            stmt.setInt(6, dto.edition());

            // 2. Executar a consulta
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao salvar usuário!");
        }
    }

    public List<Book> findBooks(String title) {
        List<Book> books = new ArrayList<>();
        var sql = """
                SELECT * FROM tb_book
                WHERE UPPER(title) LIKE CONCAT('%', UPPER(?), '%')
                """;

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, title == null ? "" : title);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getLong("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setPages(rs.getInt("pages"));
                book.setEdition(rs.getInt("edition"));
                book.setActiveLoan(rs.getBoolean("active_loan"));
                book.setCode(rs.getString("code"));
                books.add(book);
            }

            return books;

        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar livros!");
        }
    }

    public Optional<Book> getBookById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }

        final String sql = """
               SELECT id, title, author, publisher, pages, edition, active_loan, code
               FROM tb_book
               WHERE id = ?
                """;

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Book book = new Book();
                    book.setId(rs.getLong("id"));
                    book.setTitle(rs.getString("title"));
                    book.setAuthor(rs.getString("author"));
                    book.setPublisher(rs.getString("publisher"));
                    book.setPages(rs.getInt("pages"));
                    book.setEdition(rs.getInt("edition"));
                    book.setActiveLoan(rs.getBoolean("active_loan"));
                    book.setCode(rs.getString("code"));

                    return Optional.of(book);
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar livro!");
        }

        return Optional.empty();
    }

    public boolean removeBook(long id) {
        final String sql = """
                DELETE FROM tb_book
                WHERE id = ?
                """;

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);

            int affectedRows = stmt.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException e) {
            throw new DatabaseException("Erro ao remover livro!");
        }
    }

    public boolean updateBook(UpdateBookDto dto) {
        if (dto.id() <= 0) {
            throw new IllegalArgumentException("ID inválido!");
        }
        final String sql = """
                            UPDATE tb_book
                            SET title = ?,
                                author = ?,
                                code = ?,
                                pages = ?,
                                publisher = ?,
                                edition = ?
                            WHERE id = ?
                           """;

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dto.title());
            stmt.setString(2, dto.author());
            stmt.setString(3, dto.code());
            stmt.setInt(4, dto.pages());
            stmt.setString(5, dto.publisher());
            stmt.setInt(6, dto.edition());
            stmt.setLong(7, dto.id());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DatabaseException("Erro ao atualizar livro!");
        }
    }

    @Override
    public boolean changeLoanStatus(long id, boolean status) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }

        final String sql = """
                UPDATE tb_book
                SET active_loan = ?
                WHERE id = ?
                """;
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, status);
            stmt.setLong(2, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao atualizar o status do livro!");
        }
    }
}
