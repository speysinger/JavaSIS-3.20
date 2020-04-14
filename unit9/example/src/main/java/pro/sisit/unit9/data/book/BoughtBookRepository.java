package pro.sisit.unit9.data.book;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.sisit.unit9.entity.book.BoughtBook;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoughtBookRepository extends CrudRepository<BoughtBook, Long> {
    BoughtBook save(BoughtBook boughtBook);

    List<BoughtBook> findAll();
}
