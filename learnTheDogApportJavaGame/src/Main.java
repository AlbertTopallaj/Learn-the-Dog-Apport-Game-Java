import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        List<Item> inventory = new ArrayList<>();

        Item stick = Item.GameItems.STICK;

        Item.pickUpItem(stick, inventory);

        Item clue = Item.GameItems.FEED_CLUE;
        Item.pickUpItem(clue, inventory);

        System.out.println("\nInventory innehåller:");
        for(Item item : inventory){

            System.out.println("- " + item.getItem_name() + ": " + item.getDescription());

        }

    }
}