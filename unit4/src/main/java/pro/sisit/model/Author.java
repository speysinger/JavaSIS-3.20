package pro.sisit.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Author {

    private String name;
    private String birthPlace;

    public Author(String data) {
        String[] values = data.split(",");

        this.name = values[0];
        this.birthPlace = values[1];
    }

    public Author(String name, String birthPlace) {
        this.name = name;
        this.birthPlace = birthPlace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Author author = (Author) o;
        return getName().equals(author.getName()) &&
                getBirthPlace().equals(author.getBirthPlace());
    }

    @Override
    public String toString() {
        return (name + "," + birthPlace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBirthPlace());
    }
}
