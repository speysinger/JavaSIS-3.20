package pro.it.sis.javacourse;

public class SwordOfNight extends Weapon {

    public SwordOfNight(){

        setPhysicalDamage(100);
        setIceDamage(50);
        setFireDamage(0);

    }

    @Override
    public void hit(Target target)
    {
        target.setPhysicalDamage(getPhysicalDamage());
        target.setFireDamage(getFireDamage());
        target.setIceDamage(getIceDamage());
    }

}