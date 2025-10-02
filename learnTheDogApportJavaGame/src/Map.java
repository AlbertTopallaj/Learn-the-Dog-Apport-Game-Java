import java.util.ArrayList;
import java.util.Arrays;

public class Map {
    public String name;
    public String description;
    public ArrayList<Item> items;
    public ArrayList<Treat> treats;
    public ArrayList<NPC> NPCs;

    Map(String name, String description, ArrayList<Item> items) {
        this.name = name;
        this.description = description;
        this.items = items;
    }

    Map(String name, String description, ArrayList<Item> items, ArrayList<Treat> treats, ArrayList<NPC> NPCs) {
        this.name = name;
        this.description = description;
        this.items = items;
        this.treats = treats;
        this.NPCs = NPCs;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Treat> getTreats() {
        return treats;
    }

    public ArrayList<NPC> getNPCs() {
        return NPCs;
    }

    public static Map[] locations = { // array med varje plats tillgänglig i spelet
            new Map(
                    "hemma",
                    "Du är hemma i ditt hus. Här finns alla möjliga saker till hunden.",
                    new ArrayList<Item>(),
                    new ArrayList<Treat>(Arrays.asList(
                            Treat.getTreat("Grisöra"),
                            Treat.getTreat("Lever"),
                            Treat.getTreat("Hundfoder")
                    )),
                    new ArrayList<NPC>(Arrays.asList(NPC.getNPC("Kent")))
            ),

            new Map(
                    "trädgård",
                    "Din inhägnade lilla trädgård utanför ditt hus. Fina blommor och buskar pryder trädgården.",
                    new ArrayList<Item>(Arrays.asList(Item.getItem("Hundleksak"))),
                    new ArrayList<Treat>(Arrays.asList(Treat.getTreat("Morot"))),
                    new ArrayList<NPC>()
            ),

            new Map(
                    "park",
                    "Du ser en park med stora, öppna grönytor som hade passat perfekt till att öva apport med hunden. Åt vänster om dig står en [kiosk] med en man bakom kassan. ",
                    new ArrayList<Item>(Arrays.asList(Item.getItem("Pinne"))),
                    new ArrayList<Treat>(Arrays.asList(Treat.getTreat("Ben"))),
                    new ArrayList<NPC>(Arrays.asList(NPC.getNPC("Agneta")))
            ),

            new Map("kiosk",
                    "Du står vid en välskött kiosk. Den är öppen och en man står bakom disken och ser uttråkad ut.",
                    new ArrayList<Item>(),
                    new ArrayList<Treat>(Arrays.asList(
                            Treat.getTreat("Köttbulle"),
                            Treat.getTreat("Ostbit")
                    )),
                    new ArrayList<NPC>()
            ),

            new Map(
                    "parkeringsplats",
                    "En asfalterad parkeringsplats intill parken med ett par parkerade bilar.",
                    new ArrayList<Item>(),
                    new ArrayList<Treat>(Arrays.asList(Treat.getTreat("Biscuit"))),
                    new ArrayList<NPC>(Arrays.asList(NPC.getNPC("Gunnar")))
            ),

            new Map(
                    "badplats",
                    "En liten strand vid en sjö ligger vid parkens bortre ände.",
                    new ArrayList<Item>(),
                    new ArrayList<Treat>(Arrays.asList(Treat.getTreat("Ben"))),
                    new ArrayList<NPC>(Arrays.asList(NPC.getNPC("Kurt")))
            ),

            new Map(
                    "skogsstig",
                    "En smal stig slingrar sig in i en skog jämte parken. Här kanske man kan hitta en bra pinne.",
                    new ArrayList<Item>(Arrays.asList(Item.getItem("Pinne"))),
                    new ArrayList<Treat>(Arrays.asList(
                            Treat.getTreat("Biscuit"),
                            Treat.getTreat("Ostbit")
                    )),
                    new ArrayList<NPC>(Arrays.asList(NPC.getNPC("Glenn")))
            ),
    };

    public static Map getLocation(String locationInput) { // metod för att skicka ut den plats som spelaren vill gå till
        for (Map location : locations) {
            if (location.name.equalsIgnoreCase(locationInput.toLowerCase())) { // söker efter platsnamn och skickar ut objektet som matchar från arrayen till spelet
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