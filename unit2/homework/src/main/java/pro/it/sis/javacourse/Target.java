package pro.it.sis.javacourse;
import lombok.Getter;

@Getter
abstract public class Target {

    public void setPhysicalDamage(int physicalDamageTaken)
    {
        physicalDamage += physicalDamageTaken;
    }

    public void setFireDamage(int fireDamageTaken)
    {
        fireDamage += fireDamageTaken;
    }

    public void setIceDamage(int iceDamageTaken)
    {
        iceDamage += iceDamageTaken;
    }

    private int physicalDamage = 0;

    private int fireDamage = 0;

    private int iceDamage = 0;
}
