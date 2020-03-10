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

        Target efreet = new Efreet();
        Weapon swordOfNight = new SwordOfNight();
        swordOfNight.hit(efreet);

        assertEquals(100, efreet.getPhysicalDamage());
    }

    @Test
    public void testDamageAccumulation() {

        Target iceGiant = new IceGiant();
        Weapon flamingAsphalt = new FlamingAsphalt();
        flamingAsphalt.hit(iceGiant);

        assertEquals(100, iceGiant.getPhysicalDamage());

        Weapon swordOfNight = new SwordOfNight();
        swordOfNight.hit(iceGiant);

        int expectedPhysicalDamage = flamingAsphalt.getPhysicalDamage() + swordOfNight.getPhysicalDamage();

        assertEquals(expectedPhysicalDamage, iceGiant.getPhysicalDamage());

    }

    @Test
    public void testFireResist() {

        Target efreet = new Efreet();
        Weapon flamingAsphalt = new FlamingAsphalt();
        flamingAsphalt.hit(efreet);

        assertEquals(0, efreet.getFireDamage());
    }

    @Test
    public void testIceResist() {

        Target iceGiant = new IceGiant();
        Weapon swordOfNight = new SwordOfNight();
        swordOfNight.hit(iceGiant);

        assertEquals(0, iceGiant.getIceDamage());
    }

    @Test
    public void testFireDamage() {

        Target iceGiant = new IceGiant();
        Weapon flamingAsphalt = new FlamingAsphalt();
        flamingAsphalt.hit(iceGiant);

        assertEquals(50, iceGiant.getFireDamage());
    }

    @Test
    public void testIceDamage() {

        Target efreet = new Efreet();
        Weapon swordOfNight = new SwordOfNight();
        swordOfNight.hit(efreet);

        assertEquals(50, efreet.getIceDamage());
    }
}