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

import br.dev.hygino.dto.StaffDTO;
import br.dev.hygino.dto.StaffInsertDTO;
import br.dev.hygino.services.StaffService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping
    public ResponseEntity<StaffDTO> insert(@RequestBody @Valid StaffInsertDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(staffService.insert(dto));
    }

    @GetMapping
    ResponseEntity<Page<StaffDTO>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(staffService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(staffService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StaffDTO> update(@PathVariable Long id, @RequestBody @Valid StaffInsertDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(staffService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        staffService.remove(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
