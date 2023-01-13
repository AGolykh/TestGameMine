package items;

import actor.Inventory;
import exceptions.ManagerSaveException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ItemMaker {
    private static final String path = System.getProperty("user.dir") + "\\files\\armor.gpk";
    public static int objectId = 100001;
    private static final Map<Integer, Item> items = new HashMap<>();

    private ItemMaker() {
    }

    public static void loadData() {
        importItems();
    }

    public static Item get(int id) {
        return items.get(id);
    }

    public static Map<Integer, Item> list(Item.ItemType itemType) {
        TreeMap<Integer, Item> armorList = new TreeMap<>(Integer::compareTo);
        for (Map.Entry<Integer, Item> integerItemEntry : items.entrySet()) {
            if (integerItemEntry.getValue().getItemType() == itemType) {
                armorList.put(integerItemEntry.getKey(), integerItemEntry.getValue());
            }
        }
        return armorList;
    }

    private static void importItems() {
        try (FileReader reader = new FileReader(path, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(reader)) {
            while (br.ready()) {
                String dataString = br.readLine();
                Item item = converter(dataString);
                items.put(item.getId(), item);
            }
        } catch (IOException e) {
            throw new ManagerSaveException("Произошла ошибка при импорте данных из файла.");
        }
    }

    private static Item converter(String data) {
        Item item = null;
        String[] itemData = data.split(";");
        Item.ItemType itemType = Enum.valueOf(Item.ItemType.class, itemData[1]);
        switch (itemType) {
            case ARMOR -> item = new Armor(Integer.parseInt(itemData[0]),
                    itemData[2],
                    itemData[3],
                    Enum.valueOf(Inventory.SlotType.class, itemData[4]),
                    Enum.valueOf(Armor.Material.class, itemData[5]),
                    itemData[6]);
            case WEAPON -> item = new Weapon(Integer.parseInt(itemData[0]),
                     itemData[2],
                     itemData[3],
                     Enum.valueOf(Weapon.WeaponType.class, itemData[4]),
                     Enum.valueOf(Inventory.SlotType.class, itemData[5]),
                     itemData[6]);
        }
        return item;
    }
}
