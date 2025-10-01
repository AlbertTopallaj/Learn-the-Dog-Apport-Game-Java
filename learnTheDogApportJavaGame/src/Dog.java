import java.util.ArrayList;
import java.util.Random;

//Start av kod
// int apportThresholdTry. variable för att tillåta hunden att göra apport. använd if sats.
// int randomMagicNumberFactor. Variable som randomizeras e.x: 0-10?
// int winThreshold. Variable som happiness + randomMagicNumberFactor måste vara högre än för at vinna spelet.
// int happiness. ökar när hunden äter, (använder sig av treat.getEffect()). e.x: happiness += treat.getEffect();

// Använd gärna happiness OCH randomMAgicNumberFactor för att jämföra ifall de är över winThreshold så att spelaren kan fortsätta påverka chansen att vinna genom att mata hunden ifall de inte lyckas första försöken efter att apport får först användas.
// ex:
// happiness + randomMagicNumberFactor > winThreshold -> win game.

public class Dog {


    //ArrayList<String> Food = new ArrayList<String>();
    int apportThresholdTry = 15; // Minimum för att försöka apport
    //Random rn = new Random(); //Genrera ett random nummer
    //int randomMagicNumberFactor = rn.nextInt(10) + 1;
    int happiness;
    int minApportThreshold = 5


    public void addFood(Treat treat) {
        happiness += treat.effect;
    }

    // TODO: Lägg till ett till threshold för att kolla så att happiness är MINST över ett visst värde INNAN vi får försöka att vinna.
    // flytta randomMagicNumberFactor till innanför funktionen.
    // if happiness > minimumThresholdforWin.

    //exempel:
    //      ge nytt random tal till randomMagicNumberFactor.
    //      if randomMagicNumberFactor + happiness > apportThresholdTry
    //          return true.
    //      else return false.
    //  else return false.


    public boolean winThreshold() {
        Random rn = new Random(); //Genrera ett random nummer
        int randomMagicNumberFactor = rn.nextInt(10) + 1;
        if ((randomMagicNumberFactor + happiness) > minApportThreshold) {
            if ((randomMagicNumberFactor + happiness) > apportThresholdTry) {
                return true;
            } else {
                return false;
            }
        } else return false;
    }


    // Ändra namn till searchForHiddenItems & searchForHiddenTreats
    // Ändra så att funktionerna returnerar en Arraylist<Item> med bara alla items som är hidden från den arraylist som passeras in i funktionen. Gör detsamma för Treats.

    //Search funktion för items
    public void searchHiddenItem(ArrayList<String> list, String isHidden, String Item) {
        ArrayList<String> items = new ArrayList<>();
        ArrayList<String> hiddenItems = new ArrayList<>();

        for (String item : list) {
            if (item.equals(isHidden)) {
                hiddenItems.add(item);
            }
            if (item.equals((Item))) {
                items.add(item);
            }

            ArrayList<String> itemResult = items(Map.getItems());
            ArrayList<String> hiddenResult = hiddenItems(Map.getItems(), isHidden);

                /*if (Map.items == null) {
                    System.out.println("Du hittar inget av intresse!");
                } else {
                    System.out.println("Du hittar följande saker:");
                    for (Item i : Map.items) ;
                    {
                        System.out.println("- " + Map.items + ": " + Map.description);
                    }
                }*/
        }
    }


    // Search funktion för treats
    public void searchHiddenTreat(ArrayList<String> list, String isHidden, String Treat) {
        ArrayList<String> treats = new ArrayList<>();
        ArrayList<String> hiddenTreats = new ArrayList<>();

        for (String treat : list) {
            if (treat.equals(isHidden)) {
                hiddenTreats.add(treat);
            }
            if (treat.equals((Treat))) {
                treats.add(treat);
            }

            ArrayList<String> treatResult = treats(Map.getTreats());
            ArrayList<String> hiddenTreatResult = hiddenTreats(Map.getTreats(), isHidden);
                    
                /*System.out.println("Du söker igenom rummet");

                if (Map.treats == null) {
                    System.out.println("Du hittar inget av intresse!");
                } else {
                    System.out.println("Du hittar följande saker:");
                    for (Treat treat : Map.treats) ;
                    {
                        System.out.println("- " + Map.treats + ": " + Map.description);
                    }*/
        }
    }
}
    }
//gör en liknande för treats me