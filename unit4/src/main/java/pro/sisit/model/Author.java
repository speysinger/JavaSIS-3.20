package pro.sisit.model;

import lombok.Getter;
import pro.sisit.adapter.ObjectConverter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
public class Author implements ObjectConverter {

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
    public List<String> getListOfFields() {
        return Arrays.asList(this.name,
                this.birthPlace);
    }

    @Override
    public void initializeObject(List<String> fields) {
        this.name = fields.get(0);
        this.birthPlace = fields.get(1);
    }
}
