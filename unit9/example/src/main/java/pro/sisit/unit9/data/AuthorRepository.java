package pro.sisit.unit9.data;

import org.springframework.data.repository.CrudRepository;
import pro.sisit.unit9.entity.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
