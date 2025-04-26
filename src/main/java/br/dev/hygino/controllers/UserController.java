package br.dev.hygino.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.hygino.dto.RequestUserDto;
import br.dev.hygino.dto.ResponseUserDto;
import br.dev.hygino.services.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<Page<ResponseUserDto>> findAllUsers(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAllUsers(pageable));
	}

	@PostMapping
	public ResponseEntity<ResponseUserDto> insertUser(@RequestBody @Valid RequestUserDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid RequestUserDto dto) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(service.updateUser(id, dto));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findUser(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.findUser(id));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removeUser(@PathVariable Long id) {
		service.removeUser(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
