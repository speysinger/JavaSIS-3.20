package pro.sisit.model;

import lombok.Getter;
import pro.sisit.adapter.CSVAdopted;

import java.util.Objects;

@Getter
public class Author implements CSVAdopted {

    private String name;
    private String birthPlace;

    public Author(){}

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
    public int hashCode() {
        return Objects.hash(getName(), getBirthPlace());
    }

    @Override
    public String getCSVLine() {
        return (name + "," + birthPlace);
    }

    @Override
    public void setCSVObject(String data) {
        String[] values = data.split(",");

        this.name = values[0];
        this.birthPlace = values[1];
    }
}
