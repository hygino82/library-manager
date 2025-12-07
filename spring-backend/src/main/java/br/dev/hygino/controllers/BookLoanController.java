package br.dev.hygino.controllers;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.hygino.dto.RequestLoanDto;
import br.dev.hygino.dto.ResponseBookLoanDto;
import br.dev.hygino.services.BookLoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/loan")
@Tag(name = "Empréstimos", description = "Operações relacionadas ao empréstimo de livros")
public class BookLoanController {

	private final BookLoanService service;

	public BookLoanController(BookLoanService service) {
		this.service = service;
	}

	@GetMapping
	@Operation(summary = "Buscar empréstimos de livros", description = "Busca paginada de empréstimos de livros")
	public ResponseEntity<Page<ResponseBookLoanDto>> findAllLoans(Pageable pageable) {
		final Page<ResponseBookLoanDto> res = service.findAllLoans(pageable);
		return ResponseEntity.ok(res);
	}

	@PostMapping
	@Operation(summary = "Emprestar livro", description = "Realiza o empréstimo de um livro")
	public ResponseEntity<?> insert(@RequestBody @Valid RequestLoanDto dto) {
		try {
			final ResponseBookLoanDto res = service.insert(dto);
			return ResponseEntity.ok(res);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PatchMapping("return/{bookId}")
	@Operation(summary = "Devolver livro", description = "Realiza a devolução de um livro")
	public ResponseEntity<?> returnBook(@PathVariable UUID bookId) {
		final ResponseBookLoanDto result = service.returnBook(bookId);
		return ResponseEntity.ok(result);
	}

	@PatchMapping("renew/{bookId}")
	@Operation(summary = "Renovar livro", description = "Realiza a renovação de um livro")
	public ResponseEntity<?> renewBook(@PathVariable UUID bookId) {
		final ResponseBookLoanDto result = service.renewBook(bookId);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findLoanById(@PathVariable UUID id) {
		final ResponseBookLoanDto res = service.findLoanById(id);
		return ResponseEntity.ok(res);
	}
}
