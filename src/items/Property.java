package items;

import java.util.Map;
import java.util.TreeMap;

public class Property {
    Map<PropertyType, Double> properties = new TreeMap<>();

    public Property(String value) {
        String[] property = value.split(",");
        for (int i = 0; i < PropertyType.length(); i++) {
            properties.put(PropertyType.of(i), Double.parseDouble(property[i]));
        }
    }

    public Map<PropertyType, Double> getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return properties.toString();
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
