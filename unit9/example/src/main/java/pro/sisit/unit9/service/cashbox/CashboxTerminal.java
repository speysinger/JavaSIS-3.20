package pro.sisit.unit9.service.cashbox;

import pro.sisit.unit9.entity.Buyer;
import pro.sisit.unit9.entity.book.Book;

import java.math.BigDecimal;

/**
 * Интерфейс предназачается для создания функционала кассового аппарата
 */
public interface CashboxTerminal {
    void buyBook(Buyer buyer, Book book, BigDecimal cost);
}
