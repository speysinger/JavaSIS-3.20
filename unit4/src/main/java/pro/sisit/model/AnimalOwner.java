package pro.sisit.model;

import lombok.Getter;
import pro.sisit.adapter.ObjectConverter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
public class AnimalOwner implements ObjectConverter {
    private String name;
    private String animalName;
    private String phoneNumber;

    public AnimalOwner(){}

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
    public int hashCode() {
        return Objects.hash(getName(), getAnimalName(), getPhoneNumber());
    }

    @Override
    public List<String> getListOfFields() {
       return Arrays.asList(this.name,
               this.animalName,
               this.phoneNumber);
    }

    @Override
    public void initializeObject(List<String> fields) {
        this.name = fields.get(0);
        this.animalName = fields.get(1);
        this.phoneNumber = fields.get(2);
    }
}
