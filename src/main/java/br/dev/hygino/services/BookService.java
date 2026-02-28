package br.dev.hygino.services;

import br.dev.hygino.dao.BookDao;
import br.dev.hygino.dto.InsertBookDto;

public class BookService {
    private final BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void insertBook(InsertBookDto dto){
        bookDao.insertBook(dto);
    }
}
