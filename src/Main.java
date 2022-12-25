import items.ItemMaker;
import items.ItemType;

public class Main {
    public static void main(String[] args) {
        ItemMaker.makePresets(ItemType.ARMOR);
        for (int i = 100001; i <= 100008; i++) {
            System.out.println(ItemMaker.getArmor(i));
        }
    }
}