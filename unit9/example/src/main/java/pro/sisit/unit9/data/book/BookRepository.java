package pro.sisit.unit9.data.book;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.sisit.unit9.entity.book.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    Book save(Book book);

    List<Book> findByTitleAndYear(String title, Integer year);

    List<Book> findAll();

}
