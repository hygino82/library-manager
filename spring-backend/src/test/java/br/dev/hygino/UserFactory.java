package br.dev.hygino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import br.dev.hygino.dto.MinBookLoanResponseDto;
import br.dev.hygino.dto.RequestUserDto;
import br.dev.hygino.dto.ResponseUserDto;
import br.dev.hygino.models.SchoolAtribute;
import br.dev.hygino.models.User;

public final class UserFactory {

    public static UUID userEntityWithLoanId = UUID.fromString("35fb65bd-43f9-4794-8b8f-8e7c3e143c04");
    public static UUID userEntityWithoutLoanId = UUID.fromString("7feda9c2-4c7d-41e2-9547-a190ff68fc98");

    public static UUID userWithLoanId1 = UUID.fromString("b87cd236-fc77-4d49-bae8-8250ad9b8c16");
    public static UUID userWithLoanId2 = UUID.fromString("cb5cb302-3de0-4979-a098-948bd5e70a2e");

    private UserFactory() {
    }

    public static User createUserEntityWithoutBookLoan() {
        return new User(userEntityWithoutLoanId, "Gorete Medeiros", SchoolAtribute.SETIMO, "goretinha@email.com",
                "4712345678", false);
    }

    public static User createUserEntityWithBookLoan() {
        return new User(userEntityWithLoanId, "Juvenal Mendes", SchoolAtribute.OITAVO, "juvenal@email.com",
                "4687459731", true);
    }

    public static RequestUserDto createUserRequest() {
        return new RequestUserDto("Gorete Medeiros", SchoolAtribute.SETIMO, "goretinha@email.com", "4712345678");
    }

    public static ResponseUserDto createUserResponse() {
        return new ResponseUserDto(
                userEntityWithoutLoanId,
                "Gorete Medeiros",
                SchoolAtribute.SETIMO.name(),
                "goretinha@email.com",
                "4712345678",
                false,
                new ArrayList<MinBookLoanResponseDto>()
        );
    }

    public static List<User> createUserList() {
        return new ArrayList<>(
                Arrays.asList(
                        new User(
                                userEntityWithoutLoanId,
                                "Gorete Medeiros",
                                SchoolAtribute.SETIMO,
                                "goretinha@email.com",
                                "4712345678",
                                false
                        ),
                        new User(
                                UUID.fromString("6526bccf-132a-4c77-99c8-10734130a1b8"),
                                "Roberto Silva",
                                SchoolAtribute.NONO,
                                "roberto.s@email.com",
                                "4787654321",
                                false
                        ),
                        new User(
                                UUID.fromString("84e9b2d0-837d-4347-87b3-1495d3ee117d"),
                                "Ana Clara",
                                SchoolAtribute.TERCEIRA,
                                "ana.clara@email.com",
                                "4711223344",
                                false
                        ),
                        new User(
                                UUID.fromString("1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d"),
                                "Maria Eduarda",
                                SchoolAtribute.SEXTO,
                                "maria.eduarda@email.com",
                                "4799887766",
                                false
                        ),
                        new User(
                                UUID.fromString("d8c7b6a5-4f3e-2d1c-0b9a-8e7d6f5c4b3a"),
                                "João Pedro",
                                SchoolAtribute.PRIMEIRA,
                                "joao.pedro@email.com",
                                "4722334455",
                                false
                        ),
                        new User(
                                UUID.fromString("a1b2c3d4-e5f6-a7b8-c9d0-e1f2a3b4c5d6"),
                                "Camila Luz",
                                SchoolAtribute.SETIMO,
                                "camila.luz@email.com",
                                "4766778899",
                                false
                        )
                )
        );
    }
}
