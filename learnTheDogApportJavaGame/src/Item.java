public class Item {

    // HIDDEN ITEMS - LÖST, SÅVIDA INTE ANNAT SÄGS.


    private String item_name;
    private String description;
    private boolean canPickUp;
    private boolean isHidden;

    public Item(String item_name, String description, boolean canPickUp, boolean isHidden){
        this.item_name = item_name;
        this.description = description;
        this.canPickUp = canPickUp;
        this.isHidden = isHidden;
    }

    public String getItem_name() { return item_name; }
    public String getDescription() { return description; }
    public boolean canPickUp() { return canPickUp; }
    public boolean isHidden()  { return isHidden; }

    // Statisk klass med alla spelobjekt
    public static class GameItems {
        public static final Item FEED_CLUE = new Item(
                "ledtråd_mata",
                "Hunden är hungrig, testa att mata hunden med något gott!",
                false,
                false
        );

        public static final Item WHAT_TO_FEED_CLUE = new Item(
                "ledtråd_köttbullar",
                "Hunden älskar köttbullar, Tant Agda brukar ha de.",
                false,
                false
        );

        public static final Item APPORT_CLUE = new Item(

          "ledtråd_apport",
          "Kommandot apport är något älskar att utföra. Men alla hundar kan inte det. Hur ska du lösa det?",
                false,
                true


        );

        public static final Item STICK = new Item(
                "Pinne",
                "En helt vanlig pinne",
                true,
                false
        );

        public static final Item DOGTOY = new Item(

                "Hundleksak",
                "En leksak som kan få hunden på gott humör",
                true,
                true

        );

    }

    // Metod för att plocka upp item
    public static void pickUpItem(Item item, java.util.List<Item> inventory){
        if(item.canPickUp()){
            inventory.add(item);
            System.out.println("Plockade upp: "+ item.getItem_name());
        } else {
            System.out.println("Kan inte plocka upp detta föremål");
        }
    }
}
