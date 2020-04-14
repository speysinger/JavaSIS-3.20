package pro.sisit.unit9.service;

import pro.sisit.unit9.entity.Buyer;
import pro.sisit.unit9.entity.book.Book;

import java.math.BigDecimal;
import java.util.List;

public interface BookShop {
    void buyBook(Buyer buyer, Book book, BigDecimal cost);

    BigDecimal bookProfit(Book book);

    BigDecimal profitByBuyer(Buyer buyer);

    Book getBook(String title, Integer year);

    Buyer getBuyer(String name, String address);

    Buyer addBuyer(Buyer buyer);

    Book addBook(Book book);

    List<Book> findAllBooks();
}
