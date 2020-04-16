package pro.sisit.unit9.entity.book;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@NoArgsConstructor
public class Book {


    public Book(String title, String description, Integer year) {
        this.title = title;
        this.description = description;
        this.year = year;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private Integer year;
}
