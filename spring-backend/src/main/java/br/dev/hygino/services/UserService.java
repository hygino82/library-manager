package br.dev.hygino.services;

import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.dto.RequestUserDto;
import br.dev.hygino.dto.ResponseUserDto;
import br.dev.hygino.models.User;
import br.dev.hygino.repositories.UserRepository;
import br.dev.hygino.services.exceptions.UserHasBookLoanException;
import br.dev.hygino.services.exceptions.UserNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class UserService {
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional(readOnly = true)
	public Page<ResponseUserDto> findAllUsers(Pageable pageable) {
		return userRepository.findAll(pageable).map(ResponseUserDto::from);
	}

	@Transactional
	public ResponseUserDto insert(@Valid RequestUserDto dto) {
		User entity = new User();
		dtoToEntity(dto, entity);
		entity = userRepository.save(entity);
		return ResponseUserDto.from(entity);
	}

	private void dtoToEntity(@Valid RequestUserDto dto, User entity) {
		entity.setEmail(dto.email());
		entity.setName(dto.name());
		entity.setPhoneNumber(dto.phoneNumber());
		entity.setSchoolAtribute(dto.schoolAtribute());
	}

	@Transactional
	public ResponseUserDto updateUser(UUID id, @Valid RequestUserDto dto) {
		try {
			User entity = userRepository.getReferenceById(id);
			dtoToEntity(dto, entity);
			entity = userRepository.save(entity);
			return ResponseUserDto.from(entity);
		} catch (EntityNotFoundException e) {
			throw new IllegalArgumentException("Não existe usuario com o Id: " + id);
		}
	}

	@Transactional(readOnly = true)
	public ResponseUserDto findUser(UUID id) {
		final User entity = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Não existe usuario com o Id: " + id));
		return ResponseUserDto.from(entity);
	}

	@Transactional
	public void removeUser(UUID id) {

		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Usuário não encontrado: " + id));

		try {
			userRepository.delete(user);
			userRepository.flush(); // <-- faz a exceção acontecer AQUI
		} catch (DataIntegrityViolationException e) {
			throw new UserHasBookLoanException(
					"Não é possível excluir o usuário, pois ele está associado a empréstimos.");
		}
	}

	@Transactional(readOnly = true)
	public Page<ResponseUserDto> findUsersByName(String name, Pageable pageable) {
		Page<User> res = userRepository.findUsersByName(name, pageable);
		return res.map(ResponseUserDto::from);
	}
}
