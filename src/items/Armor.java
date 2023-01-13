package items;

import actor.Inventory;

public class Armor extends Item implements Wearable, Contained {
    Material material;
    Inventory.SlotType slotType;

    public Armor(Integer id,
                 String name,
                 String description,
                 Inventory.SlotType slotType,
                 Material material,
                 String properties) {
        super(id, name, description);
        this.slotType = slotType;
        this.material = material;
        this.property = new Property(properties);
        this.stack = false;
        this.itemType = ItemType.ARMOR;
        this.weight = makeWeight(this.material, this.slotType);
        this.rarity = ItemType.Rarity.makeRarity(property);
        this.cost = makeCost(this.material, this.slotType, this.rarity);
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    protected double makeWeight(Armor.Material material, Inventory.SlotType slotType) {
        return Armor.Material.rate(material)
                * Inventory.SlotType.rate(slotType);
    }

    protected double makeCost(Armor.Material material, Inventory.SlotType slotType, ItemType.Rarity rarity) {
        return Armor.Material.rate(material)
                * Inventory.SlotType.rate(slotType)
                * ItemType.Rarity.rate(rarity);
    }

    @Override
    public String toString() {
        return "Armor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", slotType=" + slotType +
                ", material=" + material +
                ", rarity=" + rarity +
                ", cost=" + cost +
                ", weight=" + weight +
                ", property=" + property +
                '}';
    }

    @Override
    public Inventory.SlotType getSlotType() {
        return this.slotType;
    }

    @Override
    public Integer getObjectId() {
        return this.objectId;
    }

    @Override
    public boolean isStack() {
        return this.stack;
    }

    public enum Material {
        CLOTH, LEATHER, IRON, STEEL;

        public static double rate(Material material) {
            return switch (material) {
                case CLOTH -> 1.0;
                case LEATHER -> 1.5;
                case IRON -> 3.0;
                case STEEL -> 4.0;
            };
        }
    }
}



