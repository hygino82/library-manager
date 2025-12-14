package br.dev.hygino.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.dev.hygino.UserFactory;
import br.dev.hygino.dto.RequestUserDto;
import br.dev.hygino.mappers.UserMapper;
import br.dev.hygino.models.SchoolAtribute;
import br.dev.hygino.models.User;
import br.dev.hygino.repositories.UserRepository;
import br.dev.hygino.services.exceptions.UserNotFoundException;

@ExtendWith(SpringExtension.class)
public final class UserServiceTest {

	private User userEntityWithoutLoans;
	private RequestUserDto userInsert;
	private UUID nonExistingId, userIdWithoutLoan, dependentId;

	@InjectMocks
	private UserService userService;

	private UserMapper userMapper;

	@Mock
	private UserRepository userRepository;

	@BeforeEach
	void setUp() {
		userInsert = UserFactory.createUserRequest();
		userIdWithoutLoan = UUID.fromString("7feda9c2-4c7d-41e2-9547-a190ff68fc98");
		nonExistingId = UUID.fromString("328273d5-8683-4eff-86da-104118e75677");
		userEntityWithoutLoans = UserFactory.createUserEntityWithoutBookLoan();
		userMapper = Mappers.getMapper(UserMapper.class);

		when(userRepository.save(any())).thenReturn(userEntityWithoutLoans);

		when(userRepository.findById(userIdWithoutLoan)).thenReturn(Optional.of(userEntityWithoutLoans));
		when(userRepository.findById(nonExistingId)).thenReturn(Optional.empty());

		userService = new UserService(userRepository, userMapper);
	}

	@Test
	@DisplayName("Insert deve retornar um usuário sem livros emprestados quando os dados inseridos forem válidos")
	public void insertSouldReturnResponseUserDtoWhenValidDataWithoutLoans() {
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
		assertEquals(SchoolAtribute.SETIMO.name(), result.schoolAtribute());
		assertEquals("goretinha@email.com", result.email());
		assertEquals("4712345678", result.phoneNumber());
	}

	@Test
	@DisplayName("FindUser deve lançar UserNotFoundException o Id for inválido")
	public void findUserShoulThrowUserNotFoundExceptionWhenInvalidId() {
		final var result = assertThrows(UserNotFoundException.class, () -> userService.findUser(nonExistingId));
		
		assertEquals("Não existe usuario com o Id: " + nonExistingId, result.getMessage());
	}
}
