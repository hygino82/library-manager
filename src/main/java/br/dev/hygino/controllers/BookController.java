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

import br.dev.hygino.dto.BookDTO;
import br.dev.hygino.dto.BookInsertDTO;
import br.dev.hygino.services.BookService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookDTO> insert(@RequestBody @Valid BookInsertDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.insert(dto));
    }

    @GetMapping
    ResponseEntity<Page<BookDTO>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable Long id, @RequestBody @Valid BookInsertDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        bookService.remove(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
