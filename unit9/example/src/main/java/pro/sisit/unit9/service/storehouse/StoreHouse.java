package pro.sisit.unit9.service.storehouse;

import pro.sisit.unit9.entity.book.Book;

import java.util.List;

/**
 * Интерфейс предназначется для работы с сущностью книги в базе данных
 */
public interface StoreHouse {
    public Book addBook(Book book);

    public List<Book> findAll();

    public Book getBook(String title, Integer year);
}
