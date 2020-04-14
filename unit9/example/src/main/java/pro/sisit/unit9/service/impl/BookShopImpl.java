package pro.sisit.unit9.service.impl;

import org.springframework.stereotype.Service;
import pro.sisit.unit9.entity.Buyer;
import pro.sisit.unit9.entity.book.Book;
import pro.sisit.unit9.service.BookShop;
import pro.sisit.unit9.service.cashbox.CashboxTerminal;
import pro.sisit.unit9.service.clients.BuyerHandler;
import pro.sisit.unit9.service.storehouse.StoreHouse;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BookShopImpl implements BookShop {

    private final CashboxTerminal cashboxTerminal;
    private final BuyerHandler buyerHandler;
    private final StoreHouse storeHouse;


    public BookShopImpl(CashboxTerminal cashboxTerminal, BuyerHandler buyerHandler, StoreHouse storeHouse) {
        this.cashboxTerminal = cashboxTerminal;
        this.buyerHandler = buyerHandler;
        this.storeHouse = storeHouse;
    }

    @Override
    public void buyBook(Buyer buyer, Book book, BigDecimal cost) {
        cashboxTerminal.buyBook(buyer, book, cost);
    }

    @Override
    public BigDecimal bookProfit(Book book) {
        return cashboxTerminal.bookProfit(book);
    }

    @Override
    public BigDecimal profitByBuyer(Buyer buyer) {
        return cashboxTerminal.profitByBuyer(buyer);
    }

    @Override
    public Book getBook(String title, Integer year) {
        return storeHouse.getBook(title, year);
    }

    @Override
    public Buyer getBuyer(String name, String address) {
        return buyerHandler.getBuyer(name, address);
    }


    @Override
    public Buyer addBuyer(Buyer buyer) {
        return buyerHandler.save(buyer);
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
