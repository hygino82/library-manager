package br.dev.hygino.controllers;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/user")
@Tag(name = "Usuários", description = "Operações relacionadas aos usuários")
public class UserController {

	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping
	@Operation(summary = "Busca de usuários", description = "Retorna uma página de usuários")
	public ResponseEntity<Page<ResponseUserDto>> findUsersByName(@Param(value = "name") String name,
			Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findUsersByName(name, pageable));
	}

	@PostMapping
	@Operation(summary = "Inserir usuário", description = "Adiciona um usuário")
	public ResponseEntity<ResponseUserDto> insertUser(@RequestBody @Valid RequestUserDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(dto));
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar usuário", description = "Atualizar dados de um usuário")
	public ResponseEntity<?> updateUser(@PathVariable UUID id, @RequestBody @Valid RequestUserDto dto) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(service.updateUser(id, dto));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/{id}")
	@Operation(summary = "Buscar usuário", description = "Busca um usuário pelo Id")
	public ResponseEntity<?> findUser(@PathVariable UUID id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.findUser(id));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Remover usuário", description = "Remove um usuário pelo Id")
	public ResponseEntity<?> removeUser(@PathVariable UUID id) {
		service.removeUser(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
