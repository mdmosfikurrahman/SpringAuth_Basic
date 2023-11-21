package mmr.mosfik.SpringAuth.repository;

import mmr.mosfik.SpringAuth.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
