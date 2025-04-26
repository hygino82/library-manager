package br.dev.hygino.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.dto.RequestUserDto;
import br.dev.hygino.dto.ResponseUserDto;
import br.dev.hygino.models.User;
import br.dev.hygino.repositories.UserRepository;
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
		return userRepository.findAll(pageable).map(ResponseUserDto::new);
	}

	@Transactional
	public ResponseUserDto insert(@Valid RequestUserDto dto) {
		User entity = new User();
		dtoToEntity(dto, entity);
		entity = userRepository.save(entity);
		return new ResponseUserDto(entity);
	}

	private void dtoToEntity(@Valid RequestUserDto dto, User entity) {
		entity.setEmail(dto.email());
		entity.setName(dto.name());
		entity.setPhoneNumber(dto.phoneNumber());
		entity.setSchoolAtribute(dto.schoolAtribute());
	}

	@Transactional
	public ResponseUserDto updateUser(Long id, @Valid RequestUserDto dto) {
		try {
			User entity = userRepository.getReferenceById(id);
			dtoToEntity(dto, entity);
			entity = userRepository.save(entity);
			return new ResponseUserDto(entity);
		} catch (EntityNotFoundException e) {
			throw new IllegalArgumentException("Não existe usuario com o Id: " + id);
		}
	}

	@Transactional(readOnly = true)
	public ResponseUserDto findUser(Long id) {
		final User entity = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Não existe usuario com o Id: " + id));
		return new ResponseUserDto(entity);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void removeUser(Long id) {
		userRepository.deleteById(id);
	}
}
