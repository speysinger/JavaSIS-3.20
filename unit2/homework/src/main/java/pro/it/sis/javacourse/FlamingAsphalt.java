package pro.it.sis.javacourse;

public class FlamingAsphalt extends Weapon {

    public FlamingAsphalt(){

        setPhysicalDamage(100);
        setIceDamage(0);
        setFireDamage(50);

    }

    @Override
    public void hit(Target target)
    {
        target.setPhysicalDamage(getPhysicalDamage());
        target.setFireDamage(getFireDamage());
        target.setIceDamage(getIceDamage());
    }

}
