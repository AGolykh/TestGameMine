package items;

import exceptions.ManagerSaveException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.TreeMap;

public class ItemMaker {
    private static int nextIdArmor = 100001;
    private static int nextIdWeapon = 200001;
    private static int nextIdUsingItem = 300001;
    private static final String dir = System.getProperty("user.dir") + "\\files\\";
    private static final String fileName = "armor.gpk";
    private static final String path = dir + fileName;
    private static final TreeMap<Integer, Armor> armorsList = new TreeMap<>();
    private static final TreeMap<Integer, Weapon> weaponList = new TreeMap<>();

    private ItemMaker() {
    }

    //Добавление брони
    public static void addArmor(Armor armor) {
        armor.setId(makeId(Item.ItemType.ARMOR, armor.getId()));
        armorsList.put(armor.getId(), armor);
        System.out.println("Броня " + armor.getId() + " добавлена.");
    }

    //Добавление оружия
    public static void addWeapon(Weapon weapon) {
        weapon.setId(makeId(Item.ItemType.WEAPON,weapon.getId()));
        weaponList.put(weapon.getId(), weapon);
        System.out.println("Оружие " + weapon.getId() + " добавлено.");
    }

    //Создание id предмета
    private static Integer makeId(Item.ItemType itemType, Integer id) {
        if (id == null) {
            id = switch (itemType) {
                case ARMOR -> nextIdArmor++;
                case WEAPON -> nextIdWeapon++;
                case USING -> nextIdUsingItem++;
            };
        } else {
            switch (itemType) {
                case ARMOR -> nextIdArmor = id;
                case WEAPON -> nextIdWeapon = id;
                case USING -> nextIdUsingItem = id;
            }
        }
        return id;
    }

    // Создание списка брони
    public static void makePresets(Item.ItemType itemType) {
        try (FileReader reader = new FileReader(path, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(reader)) {
            while (br.ready() && (!br.readLine().isEmpty())) {
                Item.ItemType readType = Item.ItemType.of(br.readLine().split(";")[1]);
                switch (itemType) {
                    case ARMOR -> ItemMaker.addArmor(Converter.armorFromString(br.readLine()));
                    case WEAPON -> ItemMaker.addWeapon(Converter.weaponFromString(br.readLine()));
                    //case USING -> ItemMaker.addUsing(Converter.usingFromString(br.readLine()));
                }
            }
        } catch (IOException e) {
            throw new ManagerSaveException("Произошла ошибка при импорте данных из файла.");
        }
    }

    //Получение брони по id
    public static Armor getArmor(int id) {
        return armorsList.get(id);
    }

    public static TreeMap<Integer, Armor> getArmorList() {
        return armorsList;
    }

    //Получение оружия по id
    public static Weapon getWeapon(int id) {
        return weaponList.get(id);
    }

    public static TreeMap<Integer, Weapon> getWeaponList() {
        return weaponList;
    }

    private static class Converter {
        //Получение класса брони из строки
        private static Armor armorFromString(String value) {
            String[] item = value.split(";");
            Armor result = new Armor(item[1],
                    item[2],
                    Armor.Slot.of(item[3]),
                    Armor.Material.of(item[4]),
                    item[5]);
            result.setId(Integer.parseInt(item[0]));
            return result;
        }

        private static Weapon weaponFromString(String value) {
            String[] item = value.split(";");
            Weapon result = new Weapon(item[1],
                    item[2],
                    Weapon.WeaponType.of(item[3]),
                    item[4]);
            result.setId(Integer.parseInt(item[0]));
            return result;
        }
    }
}
