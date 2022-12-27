package items;

public class Weapon extends Item {
    WeaponType weaponType;
    Rarity rarity;
    Property property;

    public Weapon(String name,
                  String description,
                  WeaponType type,
                  String properties) {
        super(name, description);
        this.weaponType = type;
        this.property = new Property(properties);
        this.stack = false;
        this.itemType = ItemType.WEAPON;
        this.weight = makeWeight(this.weaponType);
        this.rarity = rarity.makeRarity(property);
        this.cost = makeCost(this.weaponType, this.rarity);
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public enum WeaponType {
        SWORD, MACE;

        public static WeaponType of(String slot) {
            return switch (slot) {
                case "SWORD" -> SWORD;
                case "MACE" -> MACE;
                default -> null;
            };
        }

        public static double rate(WeaponType weaponType) {
            return switch (weaponType) {
                case SWORD -> 2.0;
                case MACE -> 3.0;
            };
        }
    }
}
