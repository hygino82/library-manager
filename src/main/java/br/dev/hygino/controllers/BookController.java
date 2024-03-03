package br.dev.hygino.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.dev.hygino.dto.BookDTO;
import br.dev.hygino.dto.InsertBookDTO;
import br.dev.hygino.services.BookService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/v1/book")
public class BookController {
	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@PostMapping
	public ResponseEntity<BookDTO> insert(@RequestBody @Valid InsertBookDTO dto) {
		return ResponseEntity.status(201).body(bookService.insert(dto));
	}
	
	@GetMapping
	public ResponseEntity<Page<BookDTO>> findAll(Pageable pageable) {
		return ResponseEntity.status(200).body(bookService.findAll(pageable));
	}
}
