package br.dev.hygino.services;

import br.dev.hygino.UserFactory;
import br.dev.hygino.dto.RequestUserDto;
import br.dev.hygino.mappers.UserMapper;
import br.dev.hygino.models.SchoolAttribute;
import br.dev.hygino.models.User;
import br.dev.hygino.repositories.UserRepository;
import br.dev.hygino.services.exceptions.UserAlreadyBorrowedBookException;
import br.dev.hygino.services.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public final class UserServiceTest {

    private RequestUserDto userInsert;
    private UUID nonExistingId, userIdWithoutLoan, dependentId;

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private PageRequest pageRequest;
    private RequestUserDto updateUserRequest;

    @BeforeEach
    void setUp() {
        userInsert = UserFactory.createUserRequest();
        userIdWithoutLoan = UUID.fromString("7feda9c2-4c7d-41e2-9547-a190ff68fc98");
        nonExistingId = UUID.fromString("328273d5-8683-4eff-86da-104118e75677");
        dependentId = UUID.fromString("35fb65bd-43f9-4794-8b8f-8e7c3e143c04");
        updateUserRequest = UserFactory.createUpdateUserRequest();

        final User userWithLoans = UserFactory.createUserEntityWithBookLoan();
        final User userUpdated = UserFactory.createUpdatedUserEntity();
        final User userEntityWithoutLoans = UserFactory.createUserEntityWithoutBookLoan();
        final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
        final List<User> userlist = UserFactory.createUserList();

        pageRequest = PageRequest.of(0, 6);

        when(userRepository.save(any())).thenReturn(userEntityWithoutLoans);

        when(userRepository.findById(userIdWithoutLoan)).thenReturn(Optional.of(userEntityWithoutLoans));
        when(userRepository.findById(nonExistingId)).thenReturn(Optional.empty());
        when(userRepository.findById(dependentId)).thenReturn(Optional.of(userWithLoans));

        when(userRepository.findUsersByName(null, pageRequest))
                .thenReturn(
                        new PageImpl<>(
                                userlist,
                                pageRequest,
                                userlist.size()
                        )
                );

        when(userRepository.getReferenceById(userIdWithoutLoan)).thenReturn(userEntityWithoutLoans);
        when(userRepository.save(userUpdated)).thenReturn(userUpdated);

        doThrow(DataIntegrityViolationException.class)
                .when(userRepository)
                .delete(userWithLoans);

        userService = new UserService(userRepository, userMapper);
    }

    @Test
    @DisplayName("Insert deve retornar um usuário sem livros emprestados quando os dados inseridos forem válidos")
    public void insertShouldReturnResponseUserDtoWhenValidDataWithoutLoans() {
        final var result = userService.insert(userInsert);

        assertNotNull(result);
        assertEquals(userIdWithoutLoan, result.id());
        assertFalse(result.hasLoan());
        assertEquals(0, result.loans().size());
    }

    @Test
    @DisplayName("FindUser deve retornar um ResponseUserDto quando o Id for válido")
    public void findUserShouldReturnResponseUserDtoWhenValidId() {
        final var result = userService.findUser(userIdWithoutLoan);

        assertNotNull(result);
        assertEquals(userIdWithoutLoan, result.id());
        assertEquals("Gorete Medeiros", result.name());
        assertFalse(result.hasLoan());
        assertEquals(SchoolAttribute.SETIMO.name(), result.schoolAttribute());
        assertEquals("goretinha@email.com", result.email());
        assertEquals("4712345678", result.phoneNumber());
    }

    @Test
    @DisplayName("FindUser deve lançar UserNotFoundException o Id for inválido")
    public void findUserShouldThrowUserNotFoundExceptionWhenInvalidId() {
        final var result = assertThrows(UserNotFoundException.class, () -> userService.findUser(nonExistingId));

        assertEquals("Não existe usuário com o Id: " + nonExistingId, result.getMessage());
    }

    /*@Test
    @DisplayName("FindUsersByName deve retornar Page<User>")
    public void findUsersByNameShouldReturnPage() {
        final Page<ResponseUserDto> result = userService.findUsersByName(null, pageRequest);
        assertNotNull(result);
        assertEquals(6, result.getTotalElements());
        assertEquals("Gorete Medeiros", result.getContent().getFirst().name(), "O nome do primeiro usuário deve ser 'Gorete Medeiros'.");
        assertEquals("Camila Luz", result.getContent().getLast().name(), "O nome do último usuário deve ser 'Camila Luz'.");
    }*/

    @Test
    @DisplayName("Update deve retornar os dados atualizados")
    public void updateShouldReturnUpdatedUserWhenValidDataAndId() {
        final var result = userService.updateUser(userIdWithoutLoan, updateUserRequest);

        assertNotNull(result);
        assertEquals(userIdWithoutLoan, result.id());
        assertEquals("Godofredo Silva", result.name());
        assertEquals("godofredo@email.com", result.email());
        assertEquals(SchoolAttribute.SEGUNDA.name(), result.schoolAttribute());
        assertEquals("4632320045", result.phoneNumber());
        assertFalse(result.hasLoan());
    }

    @Test
    @DisplayName("RemoveUser deve lançar UserHasBookLoanException quando o usuário tiver empréstimos")
    public void removeUserShouldThrowUserHasBookLoanExceptionWhenDependentId() {
        final var result = assertThrows(UserAlreadyBorrowedBookException.class, () -> userService.removeUser(dependentId));
        assertEquals("Não é possível excluir o usuário, pois ele está associado a empréstimos.", result.getMessage());
    }

    @Test
    @DisplayName("RemoveUser não deve lançar UserHasBookLoanException quando o usuário não tiver empréstimos")
    public void removeUserShouldDoNotThrowUserHasBookLoanExceptionWhenUserHasNoLoans() {
        assertDoesNotThrow(() -> userService.removeUser(userIdWithoutLoan));
    }
}