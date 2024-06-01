package br.dev.hygino.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.dto.StudentDTO;
import br.dev.hygino.dto.StudentInsertDTO;
import br.dev.hygino.entities.Student;
import br.dev.hygino.repositories.StudentRepository;
import br.dev.hygino.services.exceptions.StudentNotFoundException;
import jakarta.validation.Valid;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public StudentDTO insert(@Valid StudentInsertDTO dto) {
        Student studentEntity = new Student();
        copyDtoToEntity(dto, studentEntity);
        return new StudentDTO(studentRepository.save(studentEntity));
    }

    private void copyDtoToEntity(@Valid StudentInsertDTO dto, Student entity) {
        entity.setName(dto.name());
        entity.setAddress(dto.address());
        entity.setPhone(dto.phone());
        entity.setEmail(dto.email());
        entity.setClassName(dto.className());
    }

    @Transactional(readOnly = true)
    public Page<StudentDTO> findAll(Pageable pageable) {
        Page<Student> page = studentRepository.findAll(pageable);
        return page.map(StudentDTO::new);
    }

    @Transactional(readOnly = true)
    public StudentDTO findById(Long id) {
        Student student = studentRepository.findStudentById(id)
                .orElseThrow(() -> new StudentNotFoundException("O aluno com o Id: " + id + " não foi encontrado"));
        return new StudentDTO(student);
    }
}
