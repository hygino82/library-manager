package br.dev.hygino.services;

import br.dev.hygino.dao.BookDao;
import br.dev.hygino.dto.InsertBookDto;
import br.dev.hygino.dto.ResponseBookDto;
import br.dev.hygino.dto.UpdateBookDto;
import br.dev.hygino.exceptions.DatabaseException;
import java.util.List;

public class BookService {

    private final BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public boolean insertBook(InsertBookDto dto) {
        try {
            return bookDao.insertBook(dto);
        } catch (DatabaseException e) {
            throw e;
        }
    }

    public List<ResponseBookDto> getBooks(String title) {
        try {
            return bookDao.findBooks(title)
                    .stream()
                    .map(ResponseBookDto::new)
                    .toList();
        } catch (DatabaseException e) {
            throw e;
        }
    }

    public ResponseBookDto getBookById(long id) {
        try {
            final var result = bookDao.getBookById(id)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
            return new ResponseBookDto(result);
        } catch (DatabaseException e) {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }

    public boolean updateBook(UpdateBookDto updateBook) {
        try {
            return bookDao.updateBook(updateBook);
        } catch (DatabaseException e) {
            throw e;
        }
    }

    public boolean removeBook(long id) {
        try {
            return bookDao.removeBook(id);
        } catch (DatabaseException e) {
            throw e;
        }
    }

    public boolean changeLoanStatus(long id, boolean status) {
        try {
            return bookDao.changeLoanStatus(id, status);
        } catch (DatabaseException e) {
            throw e;
        }
    }
}
