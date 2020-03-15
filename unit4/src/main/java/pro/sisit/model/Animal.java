package pro.sisit.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Animal {

    private String name;
    private String kindOfAnimal;
    private String birthPlace;
    private String dailySleep;

    public Animal(String data) {
        String[] values = data.split(",");

        this.name = values[0];
        this.kindOfAnimal = values[1];
        this.birthPlace = values[2];
        this.dailySleep = values[3];
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
    public String toString() {
        return (name + "," + kindOfAnimal + "," + birthPlace + "," + dailySleep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getKindOfAnimal(), getBirthPlace(), getDailySleep());
    }
}
