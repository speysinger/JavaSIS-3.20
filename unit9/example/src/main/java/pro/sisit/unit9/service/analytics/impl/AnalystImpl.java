package pro.sisit.unit9.service.analytics.impl;

import pro.sisit.unit9.data.book.BoughtBookRepository;
import pro.sisit.unit9.entity.Buyer;
import pro.sisit.unit9.entity.book.Book;
import pro.sisit.unit9.entity.book.BoughtBook;
import pro.sisit.unit9.service.analytics.Analyst;

import java.math.BigDecimal;
import java.util.List;

public class AnalystImpl implements Analyst {

    private final BoughtBookRepository boughtBookRepository;

    public AnalystImpl(BoughtBookRepository boughtBookRepository) {
        this.boughtBookRepository = boughtBookRepository;
    }

    @Override
    public BigDecimal bookProfit(Book book) {
        List<BoughtBook> boughtBookList = boughtBookRepository.findAll();

        return boughtBookList.
                stream().
                filter(boughtBook -> boughtBook.getBookId() == book.getId()).
                map(BoughtBook::getCost).
                reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal profitByBuyer(Buyer buyer) {
        List<BoughtBook> boughtBookList = boughtBookRepository.findAll();

        return boughtBookList.
                stream().
                filter(boughtBook -> boughtBook.getBuyerId() == buyer.getId()).
                map(BoughtBook::getCost).
                reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
