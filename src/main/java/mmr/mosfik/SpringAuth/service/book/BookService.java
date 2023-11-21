package mmr.mosfik.SpringAuth.service.book;

import mmr.mosfik.SpringAuth.dto.BookRequest;
import mmr.mosfik.SpringAuth.model.Book;

import java.util.List;

public interface BookService {
    void save(BookRequest request);
    List<Book> findAll();
    void update(Integer bookId, BookRequest request);
    void delete(Integer bookId);
}
