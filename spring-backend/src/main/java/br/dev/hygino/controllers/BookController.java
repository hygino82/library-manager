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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.dev.hygino.dto.RequestBookDto;
import br.dev.hygino.dto.ResponseBookDetailsDto;
import br.dev.hygino.dto.ResponseBookDto;
import br.dev.hygino.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/book")
@Tag(name = "Livros", description = "Operações relacionadas aos livros")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Busca de livros", description = "Retorna uma página de livros buscando pelo título e autor")
    public ResponseEntity<Page<ResponseBookDto>> findAll(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            Pageable pageable) {

        String searchTitle = (title == null || title.isBlank()) ? "" : title.trim();
        String searchAuthor = (author == null || author.isBlank()) ? "" : author.trim();

        Page<ResponseBookDto> res = service.findAll(searchTitle, searchAuthor, pageable);
        return ResponseEntity.ok(res);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Buscar livro", description = "Busca um livro pelo Id")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            final ResponseBookDetailsDto res = service.findById(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Inserir livro", description = "Adiciona um livro")
    public ResponseEntity<ResponseBookDto> insert(@Valid @RequestBody RequestBookDto dto) {
        final ResponseBookDto res = service.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    /*@PatchMapping("/{id}")
    public ResponseEntity<?> returnBook(@PathVariable Long id) {
        try {
            final ResponseBookDto res = service.returnBook(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }*/

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar livro", description = "Atualizar dados de um livro")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody RequestBookDto dto) {
        try {
            final ResponseBookDto res = service.update(id, dto);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover livro", description = "Remove um livro pelo Id")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        service.remove(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
