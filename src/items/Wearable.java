package items;

import actor.Inventory;

public interface Wearable extends Contained{
    Inventory.SlotType getSlotType();
}
