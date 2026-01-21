package br.dev.hygino.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.dev.hygino.dto.RequestUserDto;
import br.dev.hygino.dto.ResponseUserDto;
import br.dev.hygino.mappers.UserMapper;
import br.dev.hygino.models.Role;
import br.dev.hygino.models.User;
import br.dev.hygino.repositories.RoleRepository;
import br.dev.hygino.repositories.UserRepository;
import jakarta.validation.Valid;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper,
            BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public ResponseUserDto createUser(@Valid RequestUserDto dto) {
        final var userFromDb = userRepository.findByUsername(dto.username());

        if (userFromDb.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }

        var user = new User();
        dtoToEntity(dto, user);
        userRepository.save(user);

        return userMapper.toResponseUserDto(user);
    }

    private void dtoToEntity(RequestUserDto dto, User user) {
        user.setName(dto.name());
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setSchoolAttribute(dto.schoolAttribute());

        Role roleBasic = roleRepository
                .findByName(Role.Values.BASIC.name())
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName(Role.Values.BASIC.name());
                    return roleRepository.save(role);
                });

        Set<Role> roles = new HashSet<>();
        roles.add(roleBasic);
        user.setRoles(roles);
    }

    @Transactional(readOnly = true)
    public Page<ResponseUserDto> findAll(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);

        return page.map(userMapper::toResponseUserDto);
    }
}
