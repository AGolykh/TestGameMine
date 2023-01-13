package items;

public abstract class Item {
    protected Integer objectId;
    protected Integer id;
    protected String name;
    protected String description;
    protected boolean stack;
    protected double cost;
    protected double weight;
    protected ItemType itemType;
    protected Property property;
    protected ItemType.Rarity rarity;

    public Item(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStack(boolean stack) {
        this.stack = stack;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public ItemType.Rarity getRarity() {
        return rarity;
    }

    public void setRarity(ItemType.Rarity rarity) {
        this.rarity = rarity;
    }

    public enum ItemType {
        ARMOR, WEAPON, USING;

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

            //Создание категории редкости
            public static Rarity makeRarity(Property property) {
                Rarity rarity = Rarity.NORMAL;
                double sumOfProperties = 0.0;
                for (Double value : property.getProperties().values()) {
                    sumOfProperties += value;
                }
                double avgOfProperties = sumOfProperties / property.getProperties().size();
                if (avgOfProperties > 30) rarity = Rarity.LEGENDARY;
                else if (avgOfProperties > 20) rarity = Rarity.EPIC;
                else if (avgOfProperties > 10) rarity = Rarity.RARE;
                return rarity;
            }
        }
    }
}
