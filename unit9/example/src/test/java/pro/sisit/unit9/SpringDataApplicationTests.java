package pro.sisit.unit9;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pro.sisit.unit9.entity.Buyer;
import pro.sisit.unit9.entity.book.Book;
import pro.sisit.unit9.service.BookShop;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataApplicationTests {

    @Autowired
    private BookShop bookShop;

    @Before
    public void init() {
        Book book = new Book(
                "Приключения Тома Сойера",
                "Увлекательные приключения Тома Сойера",
                1876
        );
        book = bookShop.addBook(book);

        Book book2 = new Book(
                "Михаил Строгов",
                "Михаил Строгов - курьер на службе у царя...",
                1876
        );
        book2 = bookShop.addBook(book2);

        Book book3 = new Book(
                "Приключения Гекльберри Финна",
                "Описание...",
                1884);
        book3 = bookShop.addBook(book3);

        Buyer buyer1 = new Buyer(
                "Иван",
                "Ул.пушкина");
        buyer1 = bookShop.addBuyer(buyer1);

        Buyer buyer2 = new Buyer(
                "Алексей",
                "Ул.8-Июля");
        buyer2 = bookShop.addBuyer(buyer2);

        Buyer buyer3 = new Buyer(
                "Павел",
                "Ул.Победы");
        buyer3 = bookShop.addBuyer(buyer3);

        bookShop.buyBook(buyer1, book, BigDecimal.valueOf(150));
        bookShop.buyBook(buyer2, book, BigDecimal.valueOf(151));
        bookShop.buyBook(buyer3, book, BigDecimal.valueOf(152));

        bookShop.buyBook(buyer1, book2, BigDecimal.valueOf(500));
        bookShop.buyBook(buyer1, book3, BigDecimal.valueOf(1000));

    }

    @Test
    public void testBookProfit() {

        Book book = bookShop.getBook("Приключения Тома Сойера", 1876);
        BigDecimal profit = bookShop.bookProfit(book);
        profit = profit.stripTrailingZeros();

        BigDecimal expectedProfit = BigDecimal.valueOf(453);

        Assert.assertEquals(expectedProfit, profit);
    }

    @Test
    public void testProfitByBuyer() {
        Buyer buyer = bookShop.getBuyer("Иван", "Ул.пушкина");

        BigDecimal profit = bookShop.profitByBuyer(buyer);
        profit = profit.stripTrailingZeros();

        BigDecimal expectedProfit = BigDecimal.valueOf(1.65E+3).stripTrailingZeros();

        Assert.assertEquals(expectedProfit, profit);
    }


}
