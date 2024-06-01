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

import br.dev.hygino.dto.StudentDTO;
import br.dev.hygino.dto.StudentInsertDTO;
import br.dev.hygino.services.StudentService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentDTO> insert(@RequestBody @Valid StudentInsertDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.insert(dto));
    }

    @GetMapping
    ResponseEntity<Page<StudentDTO>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@PathVariable Long id, @RequestBody @Valid StudentInsertDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        studentService.remove(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
