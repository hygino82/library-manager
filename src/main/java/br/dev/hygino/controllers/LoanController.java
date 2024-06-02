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

import br.dev.hygino.dto.LoanDTO;
import br.dev.hygino.dto.LoanInsertDTO;
import br.dev.hygino.services.LoanService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/loan")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    ResponseEntity<Page<LoanDTO>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(loanService.findAll(pageable));
    }

    @GetMapping("/{id}")
    ResponseEntity<LoanDTO> findById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(loanService.findById(id));
    }

    @PostMapping
    ResponseEntity<LoanDTO> insert(@RequestBody @Valid LoanInsertDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(loanService.insert(dto));
    }

    @PutMapping("/{id}")
    ResponseEntity<LoanDTO> update(@PathVariable long id, @RequestBody @Valid LoanInsertDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(loanService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> remove(@PathVariable long id) {
        loanService.remove(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
