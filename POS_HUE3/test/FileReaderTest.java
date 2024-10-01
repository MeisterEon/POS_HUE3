import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

    @Test
    void readFile() {
        List<Weapon> excpectedList = Arrays.asList(
                new Weapon("Varscona", WeaponType.MELEE, DamageType.SLASHING, 11, 3, 5, 4250),
                new Weapon("Tuigan Bow", WeaponType.RANGED, DamageType.MISSILE, 1, 5, 6, 3500));

        List<Weapon> actualList = new FileReader("weapons_test.csv").readFile();
        assertArrayEquals(excpectedList.toArray(), actualList.toArray());
    }

}