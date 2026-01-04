package br.dev.hygino.services;

import br.dev.hygino.UserFactory;
import br.dev.hygino.dto.RequestUserDto;
import br.dev.hygino.dto.ResponseMinUserDto;
import br.dev.hygino.dto.ResponseUserDto;
import br.dev.hygino.models.SchoolAttribute;
import br.dev.hygino.services.exceptions.UserAlreadyBorrowedBookException;
import br.dev.hygino.services.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserServiceTestIT {

    @Autowired
    private UserService userService;

    private UUID validId, dependentId, invalidId;

    private RequestUserDto requestUserDto;

    @BeforeEach
    public void setup() {
        validId = UUID.fromString("c7b2c61a-ff37-4a76-94ef-9c4d0b701005");
        dependentId = UUID.fromString("c7b2c61a-ff37-4a76-94ef-9c4d0b701002");
        invalidId = UUID.fromString("45ed9797-f0e5-424a-9a4c-e51952c91d71");

        requestUserDto = UserFactory.createUpdateUserRequest();
    }

    @Test
    @DisplayName("FindUsersByName deve retornar uma página com um único elemento")
    public void findUsersByNameShouldReturnPageWithUniqueElement() {
        final String name = "Rafael";
        final Page<ResponseMinUserDto> result = userService.findUsersByName(name, PageRequest.of(0, 5));

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Rafael Lima", result.getContent().getFirst().name());
        assertEquals("c7b2c61a-ff37-4a76-94ef-9c4d0b701005", result.getContent().getFirst().id().toString());
        assertEquals(SchoolAttribute.SEXTO, result.getContent().getFirst().schoolAttribute());
        assertFalse(result.getContent().getFirst().hasLoan());
    }

    @Test
    @DisplayName("FindUser deve retornar um ResponseUserDto quando o id for válido")
    public void findUserShouldReturnResponseUserDtoWhenValidId() {
        final var result = userService.findUser(validId);

        assertNotNull(result);
        assertEquals("Rafael Lima", result.name());
        assertEquals("rafael.lima@email.com", result.email());
        assertEquals("7876543210", result.phoneNumber());
        assertEquals(validId, result.id());
        assertEquals(SchoolAttribute.SEXTO.name(), result.schoolAttribute());
    }

    @Test
    @DisplayName("FindUser deve lançar UserNotFoundException quando o Id for inválido")
    public void findUserShouldThrowUserNotFoundExceptionWhenInvalidId() {
        final var result = assertThrows(UserNotFoundException.class, () -> userService.findUser(invalidId));

        assertEquals("Não existe usuário com o Id: " + invalidId, result.getMessage());
    }

    @Test
    @DisplayName("Delete deve lançar UserHasBookLoanException quando id for dependente")
    public void deleteShouldThrowUserHasBookLoanExceptionWhenDependentId() {
        final var result = assertThrows(UserAlreadyBorrowedBookException.class, () -> userService.removeUser(dependentId));

        assertEquals("Não é possível excluir o usuário, pois ele está associado a empréstimos.", result.getMessage());
    }

    @Test
    @DisplayName("Delete não deve lançar UserHasBookLoanException quando id não for dependente")
    public void deleteShouNotThrowExceptionWhenUserAsNoLoan() {
        assertDoesNotThrow(() -> userService.removeUser(validId));
    }

    @Test
    @DisplayName("Update deve atualizar Usuário quando o Id e os dados da requisição forem válidos")
    public void updateShouldModifyUserDateWhenIdAndRequestDataIsValid() {
        final var result = userService.updateUser(validId, requestUserDto);

        assertNotNull(result);
        assertEquals("Godofredo Silva", result.name());
        assertEquals(SchoolAttribute.SEGUNDA.name(), result.schoolAttribute());
        assertEquals("godofredo@email.com", result.email());
        assertEquals("4632320045", result.phoneNumber());
        assertFalse(result.hasLoan());
    }

    @Test
    @DisplayName("Update deve lançar UserNotFoundException quando o id for inválido")
    public void updateShouldThrowException() {
        final var result = assertThrows(UserNotFoundException.class,
                () -> userService.updateUser(invalidId, requestUserDto));

        assertEquals("Não existe usuário com o Id: " + invalidId, result.getMessage());
    }

    @Test
    @DisplayName("Insert deve inserir um novo usuário quando os dados forem válidos")
    public void insertShouldReturnResponseUserDtoWhenValidData() {
        final var result = userService.insert(requestUserDto);

        assertNotNull(result);
        assertEquals("Godofredo Silva", result.name());
        assertEquals(SchoolAttribute.SEGUNDA.name(), result.schoolAttribute());
        assertEquals("godofredo@email.com", result.email());
        assertEquals("4632320045", result.phoneNumber());
        assertFalse(result.hasLoan());
    }

    @Test
    @DisplayName("FindAll deve retornar uma página")
    public void findAllShouldReturnPage() {
        final var result = userService.findAllUsers(PageRequest.of(0, 5));

        assertNotNull(result);
        assertEquals("Juvenal Santos", result.getContent().get(0).name());
        assertEquals("Maria Oliveira", result.getContent().get(1).name());
        assertEquals("Carlos Souza", result.getContent().get(2).name());
        assertEquals("Ana Pereira", result.getContent().get(3).name());
        assertEquals("Rafael Lima", result.getContent().get(4).name());
    }

    @Test
    @DisplayName("FindByEmail deve retornar um usuário quando o email é válido")
    public void findByEmailShouldReturnUserWhenValidEmail() {
        final String email = "rafael.lima@email.com";
        final ResponseUserDto result = userService.findUserByEmail(email);

        assertNotNull(result);
        assertEquals(email, result.email());
        assertEquals("Rafael Lima", result.name());
        assertFalse(result.hasLoan());
        assertEquals(SchoolAttribute.SEXTO.name(), result.schoolAttribute());
    }

    @Test
    @DisplayName("FindByEmail deve lançar UserNotFoundException quando o email é inválido")
    public void findByEmailShouldThrowUserNotFoundExceptionWhenInvalidEmail() {
        final String email = "not.valid@email.com";

        final UserNotFoundException result = assertThrows(UserNotFoundException.class,
                () -> userService.findUserByEmail(email));
        assertEquals("User not found!", result.getMessage());
    }
}
