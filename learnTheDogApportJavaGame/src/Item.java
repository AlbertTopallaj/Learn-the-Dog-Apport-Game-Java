public class Item {

    // HIDDEN ITEMS - LÖST, SÅVIDA INTE ANNAT SÄGS.

    // GÖR EN ARRAY

    // RETURNERA BEROENDE PÅ

    private String name;
    private String description;
    private boolean canPickUp;
    private boolean isHidden;

    public Item(String name, String description, boolean canPickUp, boolean isHidden) {
        this.name = name;
        this.description = description;
        this.canPickUp = canPickUp;
        this.isHidden = isHidden;
    }

    public String getName() {
        return name;
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

    // Statisk klass med alla spelobjekt
    public static Item[] items = {
            new Item(
                    "ledtråd-mat",
                    "Hunden är hungrig, testa att mata hunden med något gott!",
                    false,
                    false
            ),

            new Item(
                    "ledtråd-köttbullar",
                    "Hunden älskar köttbullar, Tant Agda brukar ha de.",
                    false,
                    false
            ),

            new Item(

                    "ledtråd-apport",
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
            if (item.getName().equalsIgnoreCase(itemInput)) {

                return item;

            }
        }
        return null;
    }
}
