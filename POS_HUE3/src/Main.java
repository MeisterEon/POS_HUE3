import java.io.FilterOutputStream;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Printable printable = System.out::println;
        WeaponManager weaponManager = new WeaponManager("weapons.csv");
        List<Weapon> list = weaponManager.readFile();
        weaponManager.sortWeaponListByDamage(list);
        printable.print(list);

        printable = weapons -> {
            try {
                Field[] fields = Weapon.class.getDeclaredFields();
                StringBuilder stringBuilder = new StringBuilder();
                int[] maxWidth = new int[7];
                weapons.stream().max(Comparator.comparingInt(o -> o.getName().length())).ifPresent(weapon -> maxWidth[0] = weapon.getName().length());
                weapons.stream().max(Comparator.comparingInt(o -> o.getCombatType().toString().length())).ifPresent(weapon -> maxWidth[1] = weapon.getCombatType().toString().length());
                weapons.stream().max(Comparator.comparingInt(o -> o.getDamageType().toString().length())).ifPresent(weapon -> maxWidth[2] = weapon.getDamageType().toString().length());
                weapons.stream().max(Comparator.comparingInt(o -> Integer.toString(o.getDamage()).length())).ifPresent(weapon -> maxWidth[3] = Integer.toString(weapon.getDamage()).length());
                weapons.stream().max(Comparator.comparingInt(o -> Integer.toString(o.getSpeed()).length())).ifPresent(weapon -> maxWidth[4] = Integer.toString(weapon.getSpeed()).length());
                weapons.stream().max(Comparator.comparingInt(o -> Integer.toString(o.getStrength()).length())).ifPresent(weapon -> maxWidth[5] = Integer.toString(weapon.getStrength()).length());
                weapons.stream().max(Comparator.comparingInt(o -> Integer.toString(o.getValue()).length())).ifPresent(weapon -> maxWidth[6] = Integer.toString(weapon.getValue()).length());

                for (int i = 0; i < maxWidth.length; i++) {
                    maxWidth[i] = Integer.max(maxWidth[i], fields[i].getName().length()) + 2;
                }

                for (int i : maxWidth) {
                    stringBuilder.append("+");
                    for (int j = 0; j < i; j++) {
                        stringBuilder.append("-");
                    }
                }
                stringBuilder.append("+\n");

                for (int i = 0; i < maxWidth.length; i++) {
                    stringBuilder.append("|");
                    int difference = maxWidth[i] - fields[i].getName().length();
                    if (difference % 2 == 0) {
                        difference = difference / 2;
                        stringBuilder.append(" ".repeat(difference));
                        stringBuilder.append(fields[i].getName());
                        stringBuilder.append(" ".repeat(difference));
                    } else {
                        difference = difference / 2;
                        stringBuilder.append(" ".repeat(difference));
                        stringBuilder.append(fields[i].getName());
                        stringBuilder.append(" ".repeat(difference + 1));
                    }
                }
                stringBuilder.append("|\n");

                for (int i : maxWidth) {
                    stringBuilder.append("+");
                    for (int j = 0; j < i; j++) {
                        stringBuilder.append("-");
                    }
                }
                stringBuilder.append("+\n");

                for (int i = 0; i < weapons.size(); i++) {
                    for (int j = 0; j < maxWidth.length; j++) {
                        stringBuilder.append("|");
                        int difference = maxWidth[j] - fields[j].get(weapons.get(i)).toString().length();
                        if (difference % 2 == 0) {
                            difference = difference / 2;
                            stringBuilder.append(" ".repeat(difference));
                            stringBuilder.append(fields[j].get(weapons.get(i)).toString());
                            stringBuilder.append(" ".repeat(difference));
                        } else {
                            difference = difference / 2;
                            stringBuilder.append(" ".repeat(difference));
                            stringBuilder.append(fields[j].get(weapons.get(i)).toString());
                            stringBuilder.append(" ".repeat(difference + 1));
                        }
                    }
                    stringBuilder.append("|\n");
                }

                for (int i : maxWidth) {
                    stringBuilder.append("+");
                    for (int j = 0; j < i; j++) {
                        stringBuilder.append("-");
                    }
                }
                stringBuilder.append("+");

                System.out.println(stringBuilder);
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        };

        printable.print(list);
    }
}
