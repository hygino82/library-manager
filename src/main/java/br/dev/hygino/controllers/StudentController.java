package br.dev.hygino.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
}
