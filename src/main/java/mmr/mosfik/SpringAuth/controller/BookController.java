package mmr.mosfik.SpringAuth.controller;

import lombok.RequiredArgsConstructor;
import mmr.mosfik.SpringAuth.dto.BookRequest;
import mmr.mosfik.SpringAuth.model.Book;
import mmr.mosfik.SpringAuth.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody BookRequest request
    ) {
        bookService.save(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAllBooks() {
        return ResponseEntity.ok(bookService.bookList());
    }
}