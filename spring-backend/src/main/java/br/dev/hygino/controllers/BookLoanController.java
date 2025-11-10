package br.dev.hygino.controllers;

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
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/loan")
public class BookLoanController {

	private final BookLoanService service;

	public BookLoanController(BookLoanService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<Page<ResponseBookLoanDto>> findAllLoans(Pageable pageable) {
		final Page<ResponseBookLoanDto> res = service.findAllLoans(pageable);
		return ResponseEntity.ok(res);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody @Valid RequestLoanDto dto) {
		try {
			final ResponseBookLoanDto res = service.insert(dto);
			return ResponseEntity.ok(res);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PatchMapping("/{bookId}")
	public ResponseEntity<?> returnBook(@PathVariable Long bookId) {
		final var result = service.returnBook(bookId);
		return ResponseEntity.ok(result);
	}
}
