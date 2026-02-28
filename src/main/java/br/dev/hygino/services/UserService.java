package br.dev.hygino.services;

import br.dev.hygino.dao.UserDao;
import br.dev.hygino.dto.ResponseUserMinDto;
import java.util.List;

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
}
