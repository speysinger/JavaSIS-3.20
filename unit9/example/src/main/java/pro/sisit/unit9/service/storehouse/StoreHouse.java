package pro.sisit.unit9.service.storehouse;

import pro.sisit.unit9.entity.book.Book;

import java.util.List;
import java.util.Optional;

public interface StoreHouse {
    public Book addBook(Book book);

    public List<Book> findAll();

    public Book getBook(String title, Integer year);
}
