package br.dev.hygino.dao;

import br.dev.hygino.dto.InsertUserDto;
import br.dev.hygino.dto.UpdateUserDto;
import br.dev.hygino.exceptions.DatabaseException;
import br.dev.hygino.jdbc.DatabaseConnection;
import br.dev.hygino.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class UserDao implements ReturnBook {

    public boolean salvarNovoUsuario(InsertUserDto dto) {

        final String sql = """
                INSERT INTO tb_user(name, attribute, contact)
                VALUES (?,?,?);
                """;

        try (Connection conn = DatabaseConnection.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            // 1. Atribuir os valores do objeto Cliente aos parâmetros da SQL
            stmt.setString(1, dto.name());
            stmt.setString(2, dto.attribute());
            stmt.setString(3, dto.contact());

            // 2. Executar a consulta
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao inserir usuário!");
        }
    }

    public List<User> findUsers(String name) {
        List<User> users = new ArrayList<>();
        var sql = """
                SELECT * FROM tb_user
                WHERE UPPER(name) LIKE CONCAT('%', UPPER(?), '%')
                """;

        try (Connection conn = DatabaseConnection.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name == null ? "" : name);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setAttribute(rs.getString("attribute"));
                user.setActiveLoan(rs.getBoolean("active_loan"));
                user.setContact(rs.getString("contact"));
                users.add(user);
            }

            return users;

        } catch (SQLException e) {
            throw new DatabaseException(
                    "Erro ao buscar usuários: " + e.getMessage()
            );
        }
    }

    public Optional<User> getUserById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }

        final String sql = """
                SELECT id, name, attribute, active_loan, contact
                FROM tb_user
                WHERE id = ?
                """;

        try (Connection conn = DatabaseConnection.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getLong("id"));
                    user.setName(rs.getString("name"));
                    user.setAttribute(rs.getString("attribute"));
                    user.setActiveLoan(rs.getBoolean("active_loan"));
                    user.setContact(rs.getString("contact"));

                    return Optional.of(user);
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar usuário: " + e.getMessage());
        }

        return Optional.empty();
    }

    public boolean removeUser(long id) {
        final String sql = """
                DELETE FROM tb_user
                WHERE id = ?
                """;

        try (Connection conn = DatabaseConnection.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);

            int affectedRows = stmt.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException e) {
            throw new DatabaseException("Erro ao remover usuário!");
        }
    }

    public boolean updateUser(UpdateUserDto dto) {
        if (dto.id() <= 0) {
            throw new IllegalArgumentException("ID inválido!");
        }

        final String sql = """
                UPDATE tb_user
                SET name = ?,
                    attribute = ?,
                    contact = ?
                WHERE id = ?
                """;

        try (Connection conn = DatabaseConnection.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dto.name());
            stmt.setString(2, dto.attribute());
            stmt.setString(3, dto.contact());
            stmt.setLong(4, dto.id());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DatabaseException("Erro ao atualizar usuário!");
        }
    }

    @Override
    public boolean changeLoanStatus(long id, boolean status) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }

        final String sql = """
                UPDATE tb_user
                SET active_loan = ?
                WHERE id = ?
                """;
        try (Connection conn = DatabaseConnection.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, status);
            stmt.setLong(2, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao atualizar o status do usuário!");
        }
    }
}
