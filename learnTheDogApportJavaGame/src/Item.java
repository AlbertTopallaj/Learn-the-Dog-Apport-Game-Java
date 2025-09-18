public class Item {

    private String item_name;
    private String description;
    private boolean canPickUp;

    public Item(String item_name, String description, boolean canPickUp){
        this.item_name = item_name;
        this.description = description;
        this.canPickUp = canPickUp;
    }

    public String getItem_name() { return item_name; }
    public String getDescription() { return description; }
    public boolean canPickUp() { return canPickUp; }

    // Statisk klass med alla spelobjekt
    public static class GameItems {
        public static final Item FEED_CLUE = new Item(
                "ledtråd_mata",
                "Hunden är hungrig, testa att mata hunden med något gott!",
                false
        );

        public static final Item WHAT_TO_FEED_CLUE = new Item(
                "ledtråd_köttbullar",
                "Hunden älskar köttbullar, Tant Agda brukar ha de.",
                false
        );

        public static final Item STICK = new Item(
                "Pinne",
                "En helt vanlig pinne",
                true
        );

        public static final Item MEATBALL = new Item(
                "Köttbulle 5g",
                "En saftig köttbulle",
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
