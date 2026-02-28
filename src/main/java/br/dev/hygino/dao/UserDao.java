package br.dev.hygino.dao;

import br.dev.hygino.dto.InsertUserDto;
import br.dev.hygino.jdbc.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public final class UserDao {

    private final Connection connection;

    public UserDao() {
        this.connection = new DatabaseConnection().getConnection();
    }

    public void salvar(InsertUserDto dto) {

        final String sql = """
                      INSERT INTO tb_user(name, attribute)
                      VALUES (?, ?);
                      """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // 1. Atribuir os valores do objeto Cliente aos parâmetros da SQL
            stmt.setString(1, dto.name());
            stmt.setString(2, dto.attribute());

            // 2. Executar a consulta
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Usuário " + dto.name() + " salvo com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar usuário: " + e.getMessage());
        }
    }
}
