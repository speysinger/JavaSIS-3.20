package pro.it.sis.javacourse;

import lombok.Getter;
import lombok.Setter;

abstract public class Weapon {
    abstract void hit(Target target);

    @Getter
    @Setter
    private int physicalDamage;

    @Getter
    @Setter
    private int fireDamage;

    @Getter
    @Setter
    private int iceDamage;
}
