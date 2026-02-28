package br.dev.hygino.dao;

import br.dev.hygino.exceptions.ResourceNotFoundException;
import br.dev.hygino.dto.InsertUserDto;
import br.dev.hygino.jdbc.DatabaseConnection;
import br.dev.hygino.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;

public final class UserDao {

    private final Connection connection;

    public UserDao() {
        this.connection = new DatabaseConnection().getConnection();
    }

    public void salvarNovoUsuario(InsertUserDto dto) {

        final String sql = """
                      INSERT INTO tb_user(name, attribute, contact)
                      VALUES (?,?,?);
                      """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // 1. Atribuir os valores do objeto Cliente aos parâmetros da SQL
            stmt.setString(1, dto.name());
            stmt.setString(2, dto.attribute());
            stmt.setString(3, dto.contact());

            // 2. Executar a consulta
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Usuário " + dto.name() + " salvo com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar usuário: " + e.getMessage());
        }
    }

    public List<User> findUsers(String name) {
        List<User> users = new ArrayList<>();
        var sql = """
        SELECT * FROM tb_user
        WHERE UPPER(name) LIKE CONCAT('%', UPPER(?), '%')
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, name == null ? "" : name);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setAttribute(rs.getString("attribute"));
                user.setAcitiveLoan(rs.getBoolean("active_loan"));
                user.setContact(rs.getString("contact"));
                users.add(user);
            }

            return users;

        } catch (SQLException e) {
            throw new ResourceNotFoundException(
                    "Erro ao buscar usuários: " + e.getMessage()
            );
        }
    }

    public Optional<User> getUserById(long id) {
        final var sql = """
                      SELECT * FROM tb_user 
                      WHERE id = ?
                      """;
        Optional<User> result = Optional.empty();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setAttribute(rs.getString("attribute"));
                user.setAcitiveLoan(rs.getBoolean("active_loan"));
                user.setContact(rs.getString("contact"));
                result = Optional.of(user);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar usuário: " + e.getMessage());
        }

        return result;
    }
}
