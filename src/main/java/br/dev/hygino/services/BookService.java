package br.dev.hygino.services;

import br.dev.hygino.dto.ResponseBookDto;
import br.dev.hygino.models.Book;
import br.dev.hygino.models.BookStatus;
import br.dev.hygino.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true)
    public Page<ResponseBookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(ResponseBookDto::new);
    }

    @Transactional(readOnly = true)
    public ResponseBookDto findById(Long id) {
        final Book res = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não existe livro com o id: " + id));
        return new ResponseBookDto(res);
    }

    @Transactional
    public ResponseBookDto returnBook(Long id) {
        try {
            Book res = bookRepository.getReferenceById(id);
            res.setBookStatus(BookStatus.AVALIABLE);
            res = bookRepository.save(res);
            return new ResponseBookDto(res);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Não encontrodo Livro com id: " + id);
        }
    }
}
