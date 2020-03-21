package pro.sisit.model;

import lombok.Getter;
import pro.sisit.adapter.ObjectConverter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
public class Book implements ObjectConverter {
    private String name;
    private String author;
    private String genre;
    private String isbn;

    public Book(){}

    public Book(String name, String author, String genre, String isbn) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return getName().equals(book.getName()) &&
                getAuthor().equals(book.getAuthor()) &&
                getGenre().equals(book.getGenre()) &&
                getIsbn().equals(book.getIsbn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAuthor(), getGenre(), getIsbn());
    }

    @Override
    public List<String> getListOfFields() {
        return Arrays.asList(this.name,
                this.author,
                this.genre,
                this.isbn);
    }

    @Override
    public void initializeObject(List<String> fields) {
        this.name = fields.get(0);
        this.author = fields.get(1);
        this.genre = fields.get(2);
        this.isbn = fields.get(3);
    }
}
