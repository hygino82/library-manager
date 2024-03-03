package br.dev.hygino.controllers;

import org.springframework.web.bind.annotation.*;

import br.dev.hygino.dto.BookDTO;
import br.dev.hygino.dto.InsertBookDTO;
import br.dev.hygino.services.BookService;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

    @GetMapping("/{id}")
    ResponseEntity<BookDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(bookService.findById(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<BookDTO> update(@PathVariable("id") Long id, @RequestBody @Valid InsertBookDTO dto) {
        return ResponseEntity.status(200).body(bookService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable("id") Long id) {
        bookService.remove(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
