import java.util.ArrayList;
import java.util.Random;

//Start av kod

public class Dog {
    public static boolean main(String[] args) {

        ArrayList<String> Food = new ArrayList<String>();

        public void addFood(String String item;) {
            Food.add(item);
            System.out.println(item + " har ätits upp.");
        }

        public static boolean apportTry(ArrayList<String> Food) {
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
        }
}
