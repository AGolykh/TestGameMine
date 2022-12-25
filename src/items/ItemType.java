package items;

public enum ItemType {
    ARMOR, WEAPON, USING;

    public static ItemType of(String value){
        return switch (value) {
            case "ARMOR" -> ARMOR;
            case "WEAPON" -> WEAPON;
            case "USING" -> USING;
            default -> null;
        };
    }
}
