package pro.sisit.unit9.service.cashbox.impl;

import org.springframework.stereotype.Service;
import pro.sisit.unit9.data.book.BoughtBookRepository;
import pro.sisit.unit9.entity.Buyer;
import pro.sisit.unit9.entity.book.Book;
import pro.sisit.unit9.entity.book.BoughtBook;
import pro.sisit.unit9.service.cashbox.CashboxTerminal;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CashboxTerminalImpl implements CashboxTerminal {

    private final BoughtBookRepository boughtBookRepository;

    public CashboxTerminalImpl(BoughtBookRepository boughtBookRepository) {
        this.boughtBookRepository = boughtBookRepository;
    }

    @Override
    public void buyBook(Buyer buyer, Book book, BigDecimal cost) {

        BoughtBook boughtBook = new BoughtBook(
                buyer.getId(),
                book.getId(),
                cost
        );
        boughtBookRepository.save(boughtBook);
    }
}
