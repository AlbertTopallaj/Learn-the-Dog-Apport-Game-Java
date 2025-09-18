import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        List<Items> inventory = new ArrayList<>();

        Items stick = Items.GameItems.STICK;

        Items.pickUpItem(stick, inventory);

        Items clue = Items.GameItems.FEED_CLUE;
        Items.pickUpItem(clue, inventory);

        System.out.println("\nInventory innehåller:");
        for(Items item : inventory){

            System.out.println("- " + item.getItem_name() + ": " + item.getDescription());

        }




    }
}