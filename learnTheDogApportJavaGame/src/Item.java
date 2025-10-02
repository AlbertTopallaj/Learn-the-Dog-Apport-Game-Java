import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Item {

    // HIDDEN ITEMS - LÖST, SÅVIDA INTE ANNAT SÄGS.

    // GÖR EN ARRAY

    // RETURNERA BEROENDE PÅ

    private String itemName;
    private String description;
    private boolean canPickUp;
    private boolean isHidden;

    public Item(String itemName, String description, boolean canPickUp, boolean isHidden) {
        this.itemName = itemName;
        this.description = description;
        this.canPickUp = canPickUp;
        this.isHidden = isHidden;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public boolean canPickUp() {
        return canPickUp;
    }

    public boolean isHidden() {
        return isHidden;
    }


    public static Item[] items = {
            new Item(
                    "ledtråd_mata",
                    "Hunden är hungrig, testa att mata hunden med något gott!",
                    false,
                    false
            ),

            new Item(
                    "ledtråd_köttbullar",
                    "Hunden älskar köttbullar, Tant Agda brukar ha de.",
                    false,
                    false
            ),

            new Item(

                    "ledtråd_apport",
                    "Kommandot apport är något älskar att utföra. Men alla hundar kan inte det. Hur ska du lösa det?",
                    false,
                    true
            ),

            new Item(
                    "Pinne",
                    "En helt vanlig pinne",
                    true,
                    false
            ),

            new Item(

                    "Hundleksak",
                    "En leksak som kan få hunden på gott humör",
                    true,
                    true
            ),

    };// Gör en funktion som tar in en string på item och gör sedan return på itemet genom listan
// if item namn är lika med användarens input så kommer det itemet hamna i inventory

    public static Item getItem(String itemInput) { // Användaren skriver in itemets namn och plockar då om det
        // Ta in en string, returnera item från rätt lista
        for (Item item : items) {
            if (item.getItemName().equals(itemInput.toLowerCase())) {

                return item;

            }
        }
        return null;
    }
}
