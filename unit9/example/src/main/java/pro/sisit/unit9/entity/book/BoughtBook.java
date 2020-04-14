package pro.sisit.unit9.entity.book;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
public class BoughtBook {


    public BoughtBook(long buyerId, long bookId, BigDecimal cost) {
        this.buyerId = buyerId;
        this.bookId = bookId;
        this.cost = cost;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "buyer_id")
    private long buyerId;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "book_id")
    private long bookId;

}
