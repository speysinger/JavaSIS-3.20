package pro.sisit.unit9.service.impl;

import org.springframework.stereotype.Service;
import pro.sisit.unit9.entity.Buyer;
import pro.sisit.unit9.entity.book.Book;
import pro.sisit.unit9.service.BookShop;
import pro.sisit.unit9.service.analytics.Analyst;
import pro.sisit.unit9.service.cashbox.CashboxTerminal;
import pro.sisit.unit9.service.clients.BuyerService;
import pro.sisit.unit9.service.storehouse.StoreHouse;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BookShopImpl implements BookShop {

    private final CashboxTerminal cashboxTerminal;
    private final BuyerService buyerService;
    private final StoreHouse storeHouse;
    private final Analyst analyst;


    public BookShopImpl(CashboxTerminal cashboxTerminal, BuyerService buyerService, StoreHouse storeHouse, Analyst analyst) {
        this.cashboxTerminal = cashboxTerminal;
        this.buyerService = buyerService;
        this.storeHouse = storeHouse;
        this.analyst = analyst;
    }

    @Override
    public void buyBook(Buyer buyer, Book book, BigDecimal cost) {
        cashboxTerminal.buyBook(buyer, book, cost);
    }

    @Override
    public BigDecimal bookProfit(Book book) {
        return analyst.bookProfit(book);
    }

    @Override
    public BigDecimal profitByBuyer(Buyer buyer) {
        return analyst.profitByBuyer(buyer);
    }

    @Override
    public Book getBook(String title, Integer year) {
        return storeHouse.getBook(title, year);
    }

    @Override
    public Buyer getBuyer(String name, String address) {
        return buyerService.getBuyer(name, address);
    }


    @Override
    public Buyer addBuyer(Buyer buyer) {
        return buyerService.save(buyer);
    }

    @Override
    public Book addBook(Book book) {
        return storeHouse.addBook(book);
    }

    @Override
    public List<Book> findAllBooks() {
        return storeHouse.findAll();
    }
}
