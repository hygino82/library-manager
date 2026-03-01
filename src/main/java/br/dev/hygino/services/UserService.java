package br.dev.hygino.services;

import br.dev.hygino.dao.UserDao;
import br.dev.hygino.dto.InsertUserDto;
import br.dev.hygino.dto.ResponseUserMinDto;
import br.dev.hygino.dto.UpdateUserDto;
import br.dev.hygino.exceptions.DatabaseException;

import java.util.List;

public final class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<ResponseUserMinDto> getUsers(String name) {
        try {
            return userDao.findUsers(name)
                    .stream()
                    .map(ResponseUserMinDto::new)
                    .toList();
        } catch (DatabaseException e) {
            throw e;
        }
    }

    public boolean insertUser(InsertUserDto dto) {
        try {
            return userDao.salvarNovoUsuario(dto);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public ResponseUserMinDto getUserById(long id) {
        final var result = userDao.getUserById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        return new ResponseUserMinDto(result);
    }

    public boolean updateUser(UpdateUserDto dto) {
        try {
            return userDao.updateUser(dto);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public boolean changeLoanStatus(long id, boolean status) {
        try {
            return userDao.changeLoanStatus(id, status);
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
