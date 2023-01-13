import actor.Actor;
import actor.Inventory;
import items.Contained;
import items.Item;
import items.ItemMaker;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ItemMaker.loadData();

        for (Map.Entry<Integer, Item> armor : ItemMaker.list(Item.ItemType.ARMOR).entrySet()) {
            System.out.println(armor.getValue());
        }

        Actor actor = new Actor(new Inventory());
        for (Item item : ItemMaker.list(Item.ItemType.ARMOR).values()) {
            actor.addItem((Contained) item);
        }

        actor.showStorage();
        actor.showEquipment();
        actor.equip(100001);
        actor.showStorage();
        actor.showEquipment();
        actor.unEquip(Inventory.SlotType.HEAD);
        actor.showStorage();
        actor.showEquipment();
    }
}