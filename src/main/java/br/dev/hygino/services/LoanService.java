package br.dev.hygino.services;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.dto.LoanDTO;
import br.dev.hygino.dto.LoanInsertDTO;
import br.dev.hygino.entities.Book;
import br.dev.hygino.entities.Loan;
import br.dev.hygino.entities.Student;
import br.dev.hygino.repositories.BookRepository;
import br.dev.hygino.repositories.LoanRepository;
import br.dev.hygino.repositories.StudentRepository;
import br.dev.hygino.services.exceptions.BookNotFoundException;
import br.dev.hygino.services.exceptions.LoanNotFoundException;
import br.dev.hygino.services.exceptions.StudentNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class LoanService {

    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;
    private final LoanRepository loanRepository;

    public LoanService(BookRepository bookRepository, StudentRepository studentRepository,
            LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
        this.loanRepository = loanRepository;
    }

    @Transactional(readOnly = true)
    public Page<LoanDTO> findAll(Pageable pageable) {
        Page<Loan> page = loanRepository.findAll(pageable);
        return page.map(LoanDTO::new);
    }

    @Transactional(readOnly = true)
    public LoanDTO findById(Long id) {
        Loan entity = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Empréstimo com o Id: " + id + " não encontrado"));
        return new LoanDTO(entity);
    }

    @Transactional
    public LoanDTO insert(LoanInsertDTO dto) {
        Loan entity = new Loan();
        copyToEntity(dto, entity);
        return new LoanDTO(loanRepository.save(entity));
    }

    @Transactional
    public LoanDTO update(Long id, LoanInsertDTO dto) {
        try {
            Loan entity = loanRepository.getReferenceById(id);
            copyToEntity(dto, entity);
            return new LoanDTO(loanRepository.save(entity));
        } catch (EntityNotFoundException e) {
            throw new LoanNotFoundException("Empréstimo com o Id: " + id + " não encontrado");
        }
    }

    @Transactional
    private void copyToEntity(LoanInsertDTO dto, Loan entity) {
        Student student = studentRepository.findStudentById(dto.studentId())
                .orElseThrow(() -> new StudentNotFoundException(
                        "O aluno com o Id: " + dto.studentId() + " não foi encontrado"));

        Book book = bookRepository.findById(dto.bookId())
                .orElseThrow(() -> new BookNotFoundException("Livro com o Id: " + dto.bookId() + " não encontrado"));
        entity.setLoanDate(new Date());
        entity.setReturnDate(dto.returnDate());

        entity.setBook(book);
        entity.setStudent(student);
    }

    public void remove(long id) {
        Loan entity = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Empréstimo com o Id: " + id + " não encontrado"));
        loanRepository.delete(entity);
    }
}
