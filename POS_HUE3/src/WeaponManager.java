import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WeaponManager {
    private final String FILE_NAME;

    public WeaponManager(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
    }

    public List<Weapon> readFile() {
        List<Weapon> list;

        try {
            list = Files.lines(new File(FILE_NAME).toPath())
                    .skip(1)
                    .map(s -> s.split(";"))
                    .map(s -> new Weapon(
                            s[0],
                            CombatType.valueOf(s[1]),
                            DamageType.valueOf(s[2]),
                            Integer.parseInt(s[3]),
                            Integer.parseInt(s[4]),
                            Integer.parseInt(s[5]),
                            Integer.parseInt(s[6])
                    ))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return list;
    }

    public List<Weapon> sortWeaponListByDamage(List<Weapon> weaponList) {
        weaponList.sort(((o1, o2) -> o2.getDamage() - o1.getDamage()));
        return weaponList;
    }

    public List<Weapon> sortWeaponListByAlphabet(List<Weapon> weaponList) {
        weaponList.sort((Comparator.comparing((Weapon o) -> o.getCombatType().toString()).thenComparing(o -> o.getDamageType().toString()).thenComparing(Weapon::getName)));
        return weaponList;
    }
}
