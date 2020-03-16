package pro.sisit.model;

import lombok.Getter;
import pro.sisit.adapter.CSVAdopted;

import java.util.Objects;

@Getter
public class Book implements CSVAdopted {

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
    public String getCSVLine() {
        return (name + "," + author + "," + genre + "," + isbn);
    }

    @Override
    public void setCSVObject(String data) {
        String[] values = data.split(",");

        this.name = values[0];
        this.author = values[1];
        this.genre = values[2];
        this.isbn = values[3];
    }
}
