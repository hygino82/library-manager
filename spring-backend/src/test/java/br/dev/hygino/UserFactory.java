package br.dev.hygino;

import br.dev.hygino.models.SchoolAtribute;
import br.dev.hygino.models.User;

public final class UserFactory {

    private UserFactory() {
    }

    public static User createUserEntityWithoutBookLoan() {
        return new User(1L, "Juvenal Mendes", SchoolAtribute.OITAVO, "juvenal@email.com", "4612345678", false);
    }
    public static User createUserEntityWithBookLoan() {
        return new User(2L, "Gorete Medeiros", SchoolAtribute.SETIMO, "goretinha@email.com", "4712345678", true);
    }
}
