package actor;

import items.Contained;
import items.Wearable;

public class Actor {
    Inventory inventory;
    public Actor(Inventory inventory) {
        this.inventory = inventory;
    }

    public void addItem(Contained item) {
        inventory.add(item);
    }

    public void equip(Integer objectId) {
        inventory.equip((Wearable) inventory.get(objectId));
    }

    public void unEquip(Inventory.SlotType slotType) {
        inventory.unEquip(slotType);
    }

    public void showStorage() {
       inventory.showStorage();
    }

    public void showEquipment() {
        inventory.showEquipment();
    }
}
