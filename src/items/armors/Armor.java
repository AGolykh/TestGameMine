package items.armors;

import items.Item;
import items.ItemMaker;
import items.ItemType;

import java.util.TreeMap;

public class Armor extends Item {
    Slot slot;
    Material material;
    Rarity rarity;
    TreeMap<Property, Double> property;

    public Armor(String name,
                 String description,
                 Slot slot,
                 Material material,
                 String properties) {
        super(name, description);
        this.material = material;
        this.slot = slot;
        this.stack = false;
        this.weight = ItemMaker.makeWeight(this.material, this.slot);
        this.property = ItemMaker.makeProperty(properties);
        this.rarity = ItemMaker.makeRarity(this.property);
        this.cost = ItemMaker.makeCost(this.material, this.slot, this.rarity);
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public TreeMap<Property, Double> getProperty() {
        return property;
    }

    public void setProperty(TreeMap<Property, Double> property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return "Armor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", slot=" + slot +
                ", material=" + material +
                ", rarity=" + rarity +
                ", cost=" + cost +
                ", weight=" + weight +
                ", property=" + property +
                '}';
    }
}



