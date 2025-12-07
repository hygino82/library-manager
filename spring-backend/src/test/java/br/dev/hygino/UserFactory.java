package br.dev.hygino;

import java.util.UUID;

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
		return new User(userEntityWithoutLoanId, "Gorete Medeiros", SchoolAtribute.SETIMO, "goretinha@email.com", "4712345678",
				false);
	}

	public static User createUserEntityWithBookLoan() {
		return new User(userEntityWithLoanId, "Juvenal Mendes", SchoolAtribute.OITAVO, "juvenal@email.com", "4687459731",
				true);
	}
}
