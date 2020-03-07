package pro.it.sis.javacourse;

import org.junit.Test;

import static org.junit.Assert.*;

public class WeaponTest {

    @Test
    public void testPhysicalDamage() {

        Target iceGiant = new IceGiant();
        Weapon flamingAsphalt = new FlamingAsphalt();
        flamingAsphalt.hit(iceGiant);

        assertEquals(100, iceGiant.getPhysicalDamage());

        Weapon swordOfNight = new SwordOfNight();
        swordOfNight.hit(iceGiant);

        assertEquals(100, iceGiant.getPhysicalDamage());
    }

    @Test
    public void testFireResist() {

        Target t = new Efreet();
        Weapon w = new FlamingAsphalt();
        w.hit(t);

        assertEquals(0, t.getFireDamage());
    }

    @Test
    public void testIceResist() {

        Target t = new IceGiant();
        Weapon w = new SwordOfNight();
        w.hit(t);

        assertEquals(0, t.getIceDamage());
    }

    @Test
    public void testFireDamage() {

        Target t = new IceGiant();
        Weapon w = new FlamingAsphalt();
        w.hit(t);

        assertEquals(50, t.getFireDamage());
    }

    @Test
    public void testIceDamage() {

        Target t = new Efreet();
        Weapon w = new SwordOfNight();
        w.hit(t);

        assertEquals(50, t.getIceDamage());
    }
}