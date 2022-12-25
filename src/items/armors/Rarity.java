package items.armors;

public enum Rarity {
    NORMAL, RARE, EPIC, LEGENDARY;

    public static double rate(Rarity rarity) {
        return switch (rarity) {
            case NORMAL -> 2.5;
            case RARE -> 4.5;
            case EPIC -> 6.0;
            case LEGENDARY -> 7.5;
        };
    }
}
