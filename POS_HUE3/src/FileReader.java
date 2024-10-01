import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {
    private final String FILE_NAME;

    public FileReader(String FILE_NAME) {
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
                            WeaponType.valueOf(s[1]),
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

        list.sort(Comparator.comparingInt(Weapon::getDamage));
        return list;
    }
}
