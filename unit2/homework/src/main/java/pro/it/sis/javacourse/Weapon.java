package pro.it.sis.javacourse;

import lombok.AccessLevel;
import lombok.Setter;

abstract public class Weapon {
    abstract void hit(Target target);

    @Setter(AccessLevel.PROTECTED) protected int physicalDamage;

    @Setter(AccessLevel.PROTECTED) protected int fireDamage;

    @Setter(AccessLevel.PROTECTED) protected int iceDamage;
}
