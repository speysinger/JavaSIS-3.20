package pro.sisit.model;

import lombok.Getter;
import pro.sisit.adapter.ObjectConverter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
public class Animal implements ObjectConverter {

    private String name;
    private String kindOfAnimal;
    private String birthPlace;
    private String dailySleep;

    public Animal() {
    }

    public Animal(String name, String kindOfAnimal, String birthPlace, String dailySleep) {
        this.name = name;
        this.kindOfAnimal = kindOfAnimal;
        this.birthPlace = birthPlace;
        this.dailySleep = dailySleep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Animal animal = (Animal) o;
        return getName().equals(animal.getName()) &&
                getKindOfAnimal().equals(animal.getKindOfAnimal()) &&
                getBirthPlace().equals(animal.getBirthPlace()) &&
                getDailySleep().equals(animal.getDailySleep());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getKindOfAnimal(), getBirthPlace(), getDailySleep());
    }

    @Override
    public List<String> getListOfFields() {
        return Arrays.asList(this.name,
                this.kindOfAnimal,
                this.birthPlace,
                this.dailySleep);
    }

    @Override
    public void initializeObject(List<String> fields) {
        this.name = fields.get(0);
        this.kindOfAnimal = fields.get(1);
        this.birthPlace = fields.get(2);
        this.dailySleep = fields.get(3);
    }
}
