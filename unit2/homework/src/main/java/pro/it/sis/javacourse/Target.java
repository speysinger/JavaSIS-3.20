package pro.it.sis.javacourse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
abstract public class Target {

    private int physicalDamage;

    private int fireDamage;

    private int iceDamage;
}
