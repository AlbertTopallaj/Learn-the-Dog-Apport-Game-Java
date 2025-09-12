import java.util.List;

public class Items {

    public String item_name;
    public String description;
    public boolean canPickUp;

    public Items (String item_name, String description, boolean canPickUp){

          this.item_name = item_name;
          this.description = description;
          this.canPickUp = canPickUp;

    }
    public String getItem_name() {

        return item_name;

    }

    public String getDescription() {

        return description;

    }

    public boolean canPickUp() {

        return canPickUp;

    }

    public class GameItems {

        public static final Items FEED_CLUE = new Items("ledtråd_mata",
                "Hunden är hungrig, testa att mata hunden med något gott!", false);


        public static final Items WHAT_TO_FEED_CLUE = new Items("ledtråd_köttbullar",
                "Hunden älskar köttbullar, Tant Agda brukar ha de. Kanske borde du fråga henne om hon har några extra", false);


        public static final Items APPORT_CLUE = new Items("ledtråd-apport",
                "Desto mer god mat hunden äter, desto mer ökar chansen att hunden lär sig apport", false);

        public static final Items STICK = new Items("Pinne",
                "En helt vanlig pinne", true);


    }

        public static void pickUpItem(Items item, List<Items>inventory){

                if(item.canPickUp()){

                    inventory.add(item);
                    System.out.println("Plockade upp: "+ item.getItem_name());

                } else {

                    System.out.println("Kan inte plocka upp detta föremål");

                }

        }


}