package items.armors;

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
