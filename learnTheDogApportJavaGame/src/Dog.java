import java.util.ArrayList;
import java.util.Random;

public class Dog {

    public int apportThresholdWin = 40;
    public int happiness = 0;
    public int minApportThresholdTry = 15; // Minimum för att försöka apport
    Random rn = new Random(); //Genrera ett random nummer
    int randomMagicNumberFactor = rn.nextInt(10) + 1;

    public int getHappiness(){
        return happiness;
    }

    public int getHappinessPercentage(){
        randomMargin();
        double happinessPercentage = ((double)(happiness + randomMagicNumberFactor) / (double) apportThresholdWin * 100);
        return (int) happinessPercentage;
    }

    public void randomMargin(){
        randomMagicNumberFactor = rn.nextInt(10) + 1;
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
        randomMargin();
        if ((randomMagicNumberFactor + happiness) > minApportThresholdTry) {
            if ((randomMagicNumberFactor + happiness) > apportThresholdWin) {
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