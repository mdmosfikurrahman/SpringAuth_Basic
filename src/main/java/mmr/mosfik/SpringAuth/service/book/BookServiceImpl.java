package mmr.mosfik.SpringAuth.service.book;

import lombok.RequiredArgsConstructor;
import mmr.mosfik.SpringAuth.dto.BookRequest;
import mmr.mosfik.SpringAuth.exception.ResourceNotFoundException;
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
    public void update(Integer bookId, BookRequest request) {
        Book existingBook = repository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        existingBook.setAuthorName(request.getAuthorName());
        existingBook.setIsbnNumber(request.getIsbnNumber());

        repository.save(existingBook);
    }

    @Override
    public void delete(Integer bookId) {
        Book existingBook = repository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        repository.delete(existingBook);
    }
}
