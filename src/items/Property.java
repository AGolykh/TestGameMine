package items;

import java.util.HashMap;

public class Property {
    HashMap<PropertyType, Double> properties = new HashMap<>();

    public Property(String value) {
        String[] property = value.split(",");
        for (int i = 0; i < PropertyType.length(); i++) {
            properties.put(PropertyType.of(i), Double.parseDouble(property[i]));
        }
    }

    public HashMap<PropertyType, Double> getProperties() {
        return properties;
    }

    private enum PropertyType {
        HP, MP, SP, STRENGHT, AGILITY, DEFENSE;

        private static final PropertyType[] ENUMS = PropertyType.values();

        public static PropertyType of(int property) {
            return ENUMS[property];
        }

        public static int length() {
            return ENUMS.length;
        }
    }
}
