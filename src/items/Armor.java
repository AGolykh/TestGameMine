package items;

public class Armor extends Item {
    Slot slot;
    Material material;
    Rarity rarity;
    Property property;

    public Armor(String name,
                 String description,
                 Slot slot,
                 Material material,
                 String properties) {
        super(name, description);
        this.slot = slot;
        this.material = material;
        this.property = new Property(properties);
        this.stack = false;
        this.itemType = ItemType.ARMOR;
        this.weight = makeWeight(this.material, this.slot);
        this.rarity = rarity.makeRarity(property);
        this.cost = makeCost(this.material, this.slot, this.rarity);
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

    public enum Material {
        CLOTH, LEATHER, IRON, STEEL;

        public static Material of(String material) {
            return switch (material) {
                case "CLOTH" -> CLOTH;
                case "LEATHER" -> LEATHER;
                case "IRON" -> IRON;
                case "STEEL" -> STEEL;
                default -> null;
            };
        }

        public static double rate(Material material) {
            return switch (material) {
                case CLOTH -> 1.0;
                case LEATHER -> 1.5;
                case IRON -> 3.0;
                case STEEL -> 4.0;
            };
        }
    }

    public enum Slot {
        HEAD, SHOULDERS, CHEST, HAND, BELT, LEGS, FEET;

        public static Slot of(String slot) {
            return switch (slot) {
                case "helmet" -> HEAD;
                case "shoulders" -> SHOULDERS;
                case "chest" -> CHEST;
                case "gloves" -> HAND;
                case "belt" -> BELT;
                case "legs" -> LEGS;
                case "feet" -> FEET;
                default -> null;
            };
        }

        public static double rate(Slot slot) {
            return switch (slot) {
                case HAND, BELT, FEET -> 2.0;
                case HEAD, SHOULDERS -> 3.0;
                case CHEST, LEGS -> 4.0;
            };
        }
    }
}



