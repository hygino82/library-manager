package br.dev.hygino;

import br.dev.hygino.dao.UserDao;
import br.dev.hygino.dto.ResponseUserMinDto;
import br.dev.hygino.services.UserService;
import java.util.List;

public class BibliotecaEscolar {

    public static void main(String[] args) {

        //final InsertUserDto dto = new InsertUserDto("Godofredo Hernandes", "SETIMO");
        final UserService service = new UserService(new UserDao());

        final List<ResponseUserMinDto> users = service.getUsers("");

        users.forEach(System.out::println);
    }
}
