package br.dev.hygino.dto;

import br.dev.hygino.models.User;

public record ResponseUserDto(
		Long id,
		String name,
		String schoolAtribute,
		String email,
		String phoneNumber) {

	public ResponseUserDto(User obj) {
		this(
				obj.getId(),
				obj.getName(), 
				obj.getSchoolAtribute().name(), 
				obj.getEmail(), 
				obj.getPhoneNumber());
	}
}
