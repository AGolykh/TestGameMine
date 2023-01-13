package actor;

import items.Contained;
import items.ItemMaker;
import items.Wearable;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Inventory {
    private final Map<SlotType, Wearable> equipment = new HashMap<>();
    private final Map<Integer, Contained> storage = new TreeMap<>();
    private final Integer INVENTORY_SIZE = 16;

    public Inventory() {
        for (SlotType slot : SlotType.values()) {
            equipment.put(slot, null);
        }
    }

    public void add(Contained item) {
        if (storage.size() == INVENTORY_SIZE) {
            throw new NullPointerException("Инвентарь полон.");
        }
        item.setObjectId(ItemMaker.objectId++);
        storage.put(item.getObjectId(), item);
    }

    public Contained get(Integer objectId) {
        return storage.get(objectId);
    }

    public void remove(Integer objectId) {
        if (!storage.containsKey(objectId)) {
            throw new NullPointerException("Предмет отсутствует.");
        }
        storage.remove(objectId);
    }

    public void equip(Wearable item) {
        if (equipment.get(item.getSlotType()) != null) {
            unEquip(item.getSlotType());
        }
        storage.remove(item.getObjectId());
        equipment.put(item.getSlotType(), item);
    }

    public void unEquip(SlotType slotType) {
        Wearable item = equipment.get(slotType);
        equipment.put(slotType, null);
        storage.put(item.getObjectId(), item);
    }

    public void showStorage() {
        for (Map.Entry<Integer, Contained> itemEntry : storage.entrySet()) {
            System.out.println("id " + itemEntry.getKey() + " : " + itemEntry.getValue());
        }
    }

    public void showEquipment() {
        for (Map.Entry<SlotType, Wearable> equipEntry : equipment.entrySet()) {
            System.out.println(equipEntry.getKey() + " : " + equipEntry.getValue());
        }
    }

    public enum SlotType {
        HEAD, SHOULDERS, CHEST, HAND, BELT, LEGS, FEET,LEFT_HAND, RIGHT_HAND, TWO_HAND;

        public static double rate(SlotType slot) {
            return switch (slot) {
                case HAND, BELT, FEET -> 2.0;
                case HEAD, SHOULDERS, LEFT_HAND -> 3.0;
                case CHEST, LEGS, RIGHT_HAND -> 4.0;
                case TWO_HAND -> 7.0;
            };
        }
    }
}
