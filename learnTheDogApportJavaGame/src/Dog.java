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
    public static boolean dog(String[] args) {
    }

        //ArrayList<String> Food = new ArrayList<String>();
        static int apportThresholdTry = 15; // Minimum för att försöka apport
        static Random rn = new Random(); //Genrera ett random nummer
        static int randomMagicNumberFactor = rn.nextInt(10) + 1;
        static int happiness;


        public static void addFood(String String) {
            //Food.add(item);
            //System.out.println(item + " har ätits upp.");
            happiness += Treat.getEffect();
        }

        public static boolean winThreshold() {
            if ((randomMagicNumberFactor + happiness) > apportThresholdTry){
                System.out.println("Hunden lyckades med apport!");
                return true;
            } else {
                System.out.println("Tyvärr så lyckades du inte att få hunden att hämta pinnen");
                return false;
            }

        }
}

        /*public static boolean apportTry(ArrayList<String> Food) {
            // Om listan är tom, går det inte att lyckas
            if (Food.isEmpty()) {
                System.out.println("För hungrig för att lyckas!");
                return false;
            }

            double successChance = (double) Food.size() / 10.0;

            if (successChance > 1.0) {
                successChance = 1.0;
            }

            Random random = new Random();
            double randomValue = random.nextDouble();

            if (randomValue < successChance) {
                System.out.println("Du lyckades!");
                return true;
            } else {
                System.out.println("Du misslyckades.");
                return false;
            }
        */
            //Search funktion
            public void search(search room) {
                System.out.println("Du söker igenom rummet: " + room.getName());

                if (room.getItems().isEmpty()) {
                    System.out.println("Du hittar inget av intresse!");
                } else {
                    System.out.println("Du hittar följande saker:");
                    for (Item item : room.getItems()) {
                        System.out.println("- " + item.getName() + ": " + item.getDescription());
                    }
                }
        }
}
