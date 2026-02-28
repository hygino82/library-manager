package br.dev.hygino;

import br.dev.hygino.dao.UserDao;
import br.dev.hygino.dto.InsertUserDto;

public class BibliotecaEscolar {
    
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        InsertUserDto dto = new InsertUserDto("Godofredo Hernandes", "SETIMO");
        userDao.salvar(dto);
    }
}
