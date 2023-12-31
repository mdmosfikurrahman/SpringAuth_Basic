package mmr.mosfik.SpringAuth.controller;

import lombok.RequiredArgsConstructor;
import mmr.mosfik.SpringAuth.dto.BookRequest;
import mmr.mosfik.SpringAuth.model.Book;
import mmr.mosfik.SpringAuth.service.book.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<?> save(@RequestBody BookRequest request) {
        bookService.save(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin:read', 'user:read')")
    public ResponseEntity<List<Book>> findAllBooks() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @PutMapping("/{bookId}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<String> update(@PathVariable Integer bookId, @RequestBody BookRequest request) {
        bookService.update(bookId, request);
        return ResponseEntity.ok("Book updated successfully");
    }

    @DeleteMapping("/{bookId}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<String> delete(@PathVariable Integer bookId) {
        bookService.delete(bookId);
        return ResponseEntity.ok("Book deleted successfully");
    }

}
