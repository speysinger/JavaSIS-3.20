package pro.sisit.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public class AnimalOwner {
    private String name;
    private String animalName;
    private String phoneNumber;

    public AnimalOwner(String data) {
        String[] values = data.split(",");

        this.name = values[0];
        this.animalName = values[1];
        this.phoneNumber = values[2];
    }

    public AnimalOwner(String name, String animalName, String phoneNumber) {
        this.name = name;
        this.animalName = animalName;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AnimalOwner animalOwner = (AnimalOwner) o;
        return getName().equals(animalOwner.getName()) &&
                getAnimalName().equals(animalOwner.getAnimalName()) &&
                getPhoneNumber().equals(animalOwner.getPhoneNumber());
    }

    @Override
    public String toString() {
        return (name + "," + animalName + "," + phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAnimalName(), getPhoneNumber());
    }
}
