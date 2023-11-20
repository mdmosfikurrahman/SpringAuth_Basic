package mmr.mosfik.SpringAuth.service;

import lombok.RequiredArgsConstructor;
import mmr.mosfik.SpringAuth.dto.BookRequest;
import mmr.mosfik.SpringAuth.model.Book;
import mmr.mosfik.SpringAuth.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    public void save(BookRequest request) {
        var book = Book.builder()
                .authorName(request.getAuthorName())
                .isbnNumber(request.getIsbnNumber())
                .build();
        repository.save(book);
    }

    public List<Book> bookList(){
        return repository.findAll();
    }
}
