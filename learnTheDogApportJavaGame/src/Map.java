import java.util.ArrayList;
import java.util.Arrays;

public class Map { // även lägga till Items/Treat/NPC-objektarrayer för att spara varje sak till tillhörande plats?
    public final String name;
    public static final String description;
    public static ArrayList<Item> items;
    public static ArrayList<Treat> treats;
    public ArrayList<NPC> NPCs;

    Map(String name, String description, ArrayList<Item> items, ArrayList<Treat> treats, ArrayList<NPC> NPCs) {
        this.name = name;
        this.description = description;
        this.items = items;
        this.treats = treats;
        this.NPCs = NPCs;
    }

    public static Map[] locations = { // array med varje plats tillgänglig i spelet
            new Map(
                    "hemma",
                    "Du är hemma i ditt hus. Här finns alla möjliga saker till hunden.",
                    new ArrayList<Item>(),
                    new ArrayList<Treat>(Arrays.asList(
                            new Treat("Grisöra", 10, "En sällsynt godsak. Ger mycket poäng.")
                    )),
                    new ArrayList<NPC>()
            ),

            new Map(
                    "trädgård",
                    "Din inhägnade lilla trädgård utanför ditt hus. Fina blommor och buskar pryder trädgården.",
                    new ArrayList<Item>(Arrays.asList(
                            new Item("Pinne", "En helt vanlig pinne", true)
                    )),
                    new ArrayList<Treat>(),
                    new ArrayList<NPC>()
            ),

            new Map(
                    "park",
                    "Du ser en park med stora, öppna grönytor som hade passat perfekt till att öva apport med hunden.",
                    new ArrayList<Item>(),
                    new ArrayList<Treat>(),
                    new ArrayList<NPC>()
            ),

            new Map("kiosk",
                    "Du står vid en välskött kiosk. Den är öppen och en man står bakom disken och ser uttråkad ut.",
                    new ArrayList<Item>(),
                    new ArrayList<Treat>(),
                    new ArrayList<NPC>()
            ),

            new Map(
                    "parkeringsplats",
                    "En asfalterad parkeringsplats intill parken med ett par parkerade bilar.",
                    new ArrayList<Item>(),
                    new ArrayList<Treat>(),
                    new ArrayList<NPC>()
            ),

            new Map(
                    "badplats",
                    "En liten strand vid en sjö ligger vid parkens bortre ände.",
                    new ArrayList<Item>(),
                    new ArrayList<Treat>(),
                    new ArrayList<NPC>()
            ),

            new Map(
                    "skogsstig",
                    "En smal stig slingrar sig in i en skog jämte parken. Här kanske man kan hitta en bra pinne.",
                    new ArrayList<Item>(),
                    new ArrayList<Treat>(),
                    new ArrayList<NPC>()
            ),
    };

    public static Map getLocation(String locationInput) { // metod för att skicka ut den plats som spelaren vill gå till
        for (Map location : locations) {
            if (location.name.equals(locationInput.toLowerCase())) { // söker efter platsnamn och skickar ut objektet som matchar från arrayen till spelet
                return location;
            }
        }
        return null;
    }

    /*
     * spånar lite på hur man tar bort ett item från arrayen ovan när det plockas upp.
     *
    public static void removeItemFromLocation(Map currentLocation, Item item) {
        currentLocation.items.remove(item);
    }
    public static void removeTreatFromLocation(Map currentLocation, Treat treat) {
        currentLocation.treats.remove(treat);
    }
    */
}