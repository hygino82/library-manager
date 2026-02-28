package br.dev.hygino.services;

import br.dev.hygino.dao.UserDao;
import br.dev.hygino.dto.InsertUserDto;
import br.dev.hygino.dto.ResponseUserMinDto;
import br.dev.hygino.dto.UpdateUserDto;
import br.dev.hygino.exceptions.ResourceNotFoundException;

import java.util.List;
import javax.swing.JOptionPane;

public final class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<ResponseUserMinDto> getUsers(String name) {
        return userDao.findUsers(name)
                .stream()
                .map(ResponseUserMinDto::new)
                .toList();
    }

    public void insertUser(InsertUserDto dto) {
        userDao.salvarNovoUsuario(dto);
    }

    public ResponseUserMinDto getUserById(long id) {
        final var result = userDao.getUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));
        return new ResponseUserMinDto(result);
    }

    public boolean updateUser(UpdateUserDto dto) {
        try {
            return userDao.updateUser(dto);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return false;
    }

    public boolean changeLoanStatus(long id, boolean status) {
        try {
            return userDao.changeLoanStatus(id, status);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return false;
    }
}
