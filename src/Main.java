import items.Item;
import items.ItemMaker;
import items.Armor;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ItemMaker.makePresets(Item.ItemType.ARMOR);

        for (Map.Entry<Integer, Armor> armor : ItemMaker.getArmorList().entrySet()) {
            System.out.println(armor.getValue());
        }
    }
}