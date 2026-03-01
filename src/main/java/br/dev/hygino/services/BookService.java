package br.dev.hygino.services;

import br.dev.hygino.dao.BookDao;
import br.dev.hygino.dto.InsertBookDto;
import br.dev.hygino.dto.ResponseBookDto;
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
}
