package pro.sisit.unit9.service.storehouse.impl;

import org.springframework.stereotype.Service;
import pro.sisit.unit9.data.book.BookRepository;
import pro.sisit.unit9.entity.book.Book;
import pro.sisit.unit9.service.storehouse.StoreHouse;

import java.util.List;
import java.util.Optional;

@Service
public class StoreHouseImpl implements StoreHouse {

    private final BookRepository bookRepository;

    public StoreHouseImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBook(String title, Integer year) {
        return bookRepository.findByTitleAndYear(title, year).
                stream().
                findFirst().
                orElseThrow(() -> new RuntimeException("Книга не найдена"));
    }
}
