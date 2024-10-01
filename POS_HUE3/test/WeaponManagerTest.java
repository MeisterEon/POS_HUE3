import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class WeaponManagerTest {

    @Test
    void sortByDamage() {
        List<Weapon> excpectedList = List.of(
                new Weapon("Carsomyr", CombatType.MELEE, DamageType.SLASHING, 17, 5, 14, 20000),
                new Weapon("Crom Faeyr", CombatType.MELEE, DamageType.BLUNT, 16, 1, 25, 15500),
                new Weapon("Varscona", CombatType.MELEE, DamageType.SLASHING, 11, 3, 5, 4250),
                new Weapon("Tuigan Bow", CombatType.RANGED, DamageType.MISSILE, 1, 5, 6, 3500));

        WeaponManager weaponManager = new WeaponManager("weapons_test.csv");
        List<Weapon> actualList = weaponManager.sortWeaponListByDamage(weaponManager.readFile());
        assertArrayEquals(excpectedList.toArray(), actualList.toArray());
    }

    @Test
    void sortByAlphabet() {
        List<Weapon> excpectedList = List.of(
                new Weapon("Crom Faeyr", CombatType.MELEE, DamageType.BLUNT, 16, 1, 25, 15500),
                new Weapon("Carsomyr", CombatType.MELEE, DamageType.SLASHING, 17, 5, 14, 20000),
                new Weapon("Varscona", CombatType.MELEE, DamageType.SLASHING, 11, 3, 5, 4250),
                new Weapon("Tuigan Bow", CombatType.RANGED, DamageType.MISSILE, 1, 5, 6, 3500));

        WeaponManager weaponManager = new WeaponManager("weapons_test.csv");
        List<Weapon> actualList = weaponManager.sortWeaponListByAlphabet(weaponManager.readFile());
        assertArrayEquals(excpectedList.toArray(), actualList.toArray());
    }

}