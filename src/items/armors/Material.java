package items.armors;

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
