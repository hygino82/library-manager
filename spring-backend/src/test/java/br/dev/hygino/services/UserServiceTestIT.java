package br.dev.hygino.services;

import br.dev.hygino.dto.ResponseUserDto;
import br.dev.hygino.mappers.UserMapper;
import br.dev.hygino.mappers.UserMapperImpl;
import br.dev.hygino.models.SchoolAtribute;
import br.dev.hygino.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class UserServiceTestIT {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("FindUsersByName deve retornar uma página com um único elemento")
    public void findUsersByNameShouldReturnPageWithUniqueElement() {
        final String name = "Rafael";
        final Page<ResponseUserDto> result = userService.findUsersByName(name, PageRequest.of(0, 5));

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Rafael Lima", result.getContent().getFirst().name());
        assertEquals("rafael.lima@email.com", result.getContent().getFirst().email());
        assertEquals("7876543210", result.getContent().getFirst().phoneNumber());
        assertEquals("c7b2c61a-ff37-4a76-94ef-9c4d0b701005", result.getContent().getFirst().id().toString());
        assertEquals(SchoolAtribute.SEXTO.name(), result.getContent().getFirst().schoolAtribute());
    }

}
