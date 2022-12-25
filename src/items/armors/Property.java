package items.armors;

public enum Property {
    HP, MP, SP, STRENGHT, AGILITY, DEFENSE;

    private static final Property[] ENUMS = Property.values();

    public static Property of(int property) {
        return ENUMS[property];
    }

    public static int length() {
        return ENUMS.length;
    }


}
