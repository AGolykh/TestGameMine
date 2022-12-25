package items;

import items.armors.*;
import items.exceptions.ManagerSaveException;

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

    private ItemMaker() {
    }

    //Добавление брони
    public static void addArmor(Armor armor) {
        armor.setId(makeId(ItemType.ARMOR, armor.getId()));
        armorsList.put(armor.getId(), armor);
        System.out.println("Броня " + armor.getId() + " добавлена.");
    }

    public static void addWeapon(Weapon weapon) {
        armor.setId(makeId(ItemType.ARMOR, armor.getId()));
        armorsList.put(armor.getId(), armor);
        System.out.println("Броня " + armor.getId() + " добавлена.");
    }

    public static void addArmor(Armor armor) {
        armor.setId(makeId(ItemType.ARMOR, armor.getId()));
        armorsList.put(armor.getId(), armor);
        System.out.println("Броня " + armor.getId() + " добавлена.");
    }

    //Создание id предмета
    private static Integer makeId(ItemType itemType, Integer id) {
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

    //Создание веса брони
    public static double makeWeight(Material material, Slot slot) {
        return Material.rate(material)
                * Slot.rate(slot);
    }

    //Создание категории редкости
    public static Rarity makeRarity(TreeMap<Property, Double> property) {
        Rarity rarity = Rarity.NORMAL;
        double sumOfProperties = 0.0;
        for (Double value : property.values()) {
            sumOfProperties += value;
        }
        double avgOfProperties = sumOfProperties / property.size();
        if (avgOfProperties > 30) rarity = Rarity.LEGENDARY;
        else if (avgOfProperties > 20) rarity = Rarity.EPIC;
        else if (avgOfProperties > 10) rarity = Rarity.RARE;
        return rarity;
    }

    //Создание стоимости
    public static double makeCost(Material material, Slot slot, Rarity rarity) {
        return Material.rate(material)
                * Slot.rate(slot)
                * Rarity.rate(rarity);
    }

    //Создание списка свойств
    public static TreeMap<Property, Double> makeProperty(String properties) {
        TreeMap<Property, Double> result = new TreeMap<>();
        String[] property = properties.split(",");
        for (int i = 0; i < Property.length(); i++) {
            result.put(Property.of(i), Double.parseDouble(property[i]));
        }
        return result;
    }

    // Создание списка брони
    public static void makePresets(ItemType itemType) {
        try (FileReader reader = new FileReader(path, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(reader)) {
            while (br.ready() && (!br.readLine().isEmpty())) {
                ItemType readType = ItemType.of(br.readLine().split(";")[1]);
                switch (itemType) {
                    case ARMOR -> ItemMaker.addArmor(Converter.armorFromString(br.readLine()));
                    //case WEAPON -> ItemMaker.addArmor(Converter.armorFromString(br.readLine()));
                    //case USING -> ItemMaker.addArmor(Converter.armorFromString(br.readLine()));
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

    private static class Converter {
        //Получение класса брони из строки
        private static Armor armorFromString(String value) {
            String[] item = value.split(";");
            Armor result = new Armor(item[1],
                    item[2],
                    Slot.of(item[3]),
                    Material.of(item[4]),
                    item[5]);
            result.setId(Integer.parseInt(item[0]));
            return result;
        }
    }
}
