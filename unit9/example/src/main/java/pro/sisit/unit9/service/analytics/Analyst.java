package pro.sisit.unit9.service.analytics;

import pro.sisit.unit9.entity.Buyer;
import pro.sisit.unit9.entity.book.Book;

import java.math.BigDecimal;

public interface Analyst {

    BigDecimal bookProfit(Book book);

    BigDecimal profitByBuyer(Buyer buyer);
}
