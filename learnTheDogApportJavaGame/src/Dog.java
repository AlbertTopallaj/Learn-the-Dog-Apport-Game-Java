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
    Random rn = new Random(); //Genrera ett random nummer
    int randomMagicNumberFactor = rn.nextInt(10) + 1;
    int happiness;


    public void addFood(String String) {
        happiness += Treat.getEffect();
    }

    public boolean winThreshold() {
        if ((randomMagicNumberFactor + happiness) > apportThresholdTry) {
            System.out.println("Hunden lyckades med apport!");
            return true;
        } else {
            System.out.println("Tyvärr så lyckades du inte att få hunden att hämta pinnen");
            return false;
        }

    }


    //Search funktion för items
    public void searchItem(Map map) {
        System.out.println("Du söker igenom rummet");

        if (Map.items == null) {
            System.out.println("Du hittar inget av intresse!");
        } else {
            System.out.println("Du hittar följande saker:");
            for (Item i : Map.items) ;
            {
                System.out.println("- " + Map.items + ": " + Map.description);
            }
        }
    }


    // Search funktion för treats
    public void searchTreat(Map map) {
        System.out.println("Du söker igenom rummet");

        if (Map.treats == null) {
            System.out.println("Du hittar inget av intresse!");
        } else {
            System.out.println("Du hittar följande saker:");
            for (Treat treat : Map.treats) ;
            {
                System.out.println("- " + Map.treats + ": " + Map.description);
            }
        }
    }
}
//gör en liknande för treats me