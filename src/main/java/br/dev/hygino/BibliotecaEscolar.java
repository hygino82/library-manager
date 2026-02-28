package br.dev.hygino;

import br.dev.hygino.dao.UserDao;
import br.dev.hygino.dto.InsertUserDto;
import br.dev.hygino.dto.ResponseUserMinDto;
import br.dev.hygino.exceptions.ResourceNotFoundException;
import br.dev.hygino.services.UserService;
import java.util.List;
import javax.swing.JOptionPane;

public class BibliotecaEscolar {

    final static UserService service = new UserService(new UserDao());

    public static void main(String[] args) {
        //listarUsuarios();
        //inserirUsuario();
        buscarPorId();
    }

    private static void listarUsuarios() {
        final List<ResponseUserMinDto> users = service.getUsers("");
        users.forEach(System.out::println);
    }

    private static void inserirUsuario() {
        final InsertUserDto dto = new InsertUserDto("Godofredo Hernandes", "4698874201", "SETIMO");
        service.insertUser(dto);
    }

    private static void buscarPorId() {
        try {
            final var result = service.getUserById(3L);
            System.out.println(result);
        } catch (ResourceNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
