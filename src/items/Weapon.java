package items;

import actor.Inventory;

public class Weapon extends Item implements Wearable, Contained {
    WeaponType weaponType;
    Inventory.SlotType slotType;

    public Weapon(Integer id,
                  String name,
                  String description,
                  WeaponType type,
                  Inventory.SlotType slotType,
                  String properties) {
        super(id, name, description);
        this.weaponType = type;
        this.slotType = slotType;
        this.property = new Property(properties);
        this.stack = false;
        this.itemType = ItemType.WEAPON;
        this.weight = makeWeight(this.weaponType);
        this.rarity = ItemType.Rarity.makeRarity(property);
        this.cost = makeCost(this.weaponType, this.rarity);
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    protected double makeWeight(Weapon.WeaponType weaponType) {
        return Weapon.WeaponType.rate(weaponType);
    }

    protected double makeCost(Weapon.WeaponType weaponType, ItemType.Rarity rarity) {
        return Weapon.WeaponType.rate(weaponType)
                * ItemType.Rarity.rate(rarity);
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

    public enum WeaponType {
        SWORD, MACE;

        public static double rate(WeaponType weaponType) {
            return switch (weaponType) {
                case SWORD -> 2.0;
                case MACE -> 3.0;
            };
        }
    }
}
