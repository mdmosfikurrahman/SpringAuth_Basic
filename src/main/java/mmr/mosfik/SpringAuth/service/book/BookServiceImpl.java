package mmr.mosfik.SpringAuth.service.book;

import lombok.RequiredArgsConstructor;
import mmr.mosfik.SpringAuth.dto.BookRequest;
import mmr.mosfik.SpringAuth.model.Book;
import mmr.mosfik.SpringAuth.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    public void save(BookRequest request) {
        var book = Book.builder()
                .bookId(request.getBookId())
                .authorName(request.getAuthorName())
                .isbnNumber(request.getIsbnNumber())
                .build();
        repository.save(book);
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    public String put() {
        return "PUT:: admin updated this";
    }

    @Override
    public String delete() {
        return "DELETE:: admin deleted this";
    }
}
