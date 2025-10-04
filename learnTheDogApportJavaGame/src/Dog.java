import java.util.ArrayList;
import java.util.Random;

public class Dog {

    public int apportThresholdTry = 40;
    public int happiness = 0;
    public int minApportThreshold = 15; // Minimum för att försöka apport

    public int getHappniess(){
        return happiness;
    }

    public void addHappiness(Treat treat) {
        happiness += treat.effect;
    }

    public void removeHappiness(int subtractionAmount) {
        if(happiness - subtractionAmount < 0){
            return;
        }
        happiness -= subtractionAmount;
    }

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

    //Search funktion för items
    public ArrayList<Item> searchForHiddenItems(ArrayList<Item> list) {
        ArrayList<Item> hiddenItems = new ArrayList<>();
        for (Item item : list) {
            if (item.isHidden()) {
                hiddenItems.add(item);
            }
        }
        return hiddenItems;
    }

    //Search funktion för treats
    public ArrayList<Treat> searchForHiddenTreats(ArrayList<Treat> list) {
        ArrayList<Treat> hiddenTreats = new ArrayList<>();
        for (Treat treat : list) {
            if (treat.getIsHidden()) {
                hiddenTreats.add(treat);
            }
        }
        return hiddenTreats;
    }
}