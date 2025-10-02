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
                    "Du är hemma i ditt lilla hus med hunden. När du tittar in i köket ser du [hundfoder] \n" +
                            "och ett [biscuit] i ett halvöppet köksskåp, och på golvet ligger en [hundleksak]. På en \n" +
                            "anslagstavla i kork ovanför byrån i hallen hänger en lapp med texten [ledtråd_apport]. \n" +
                            "Utanför ett fönster ser du din [trädgård].",
                    new ArrayList<Item>(Arrays.asList(
                            Item.getItem("ledtråd_mata"),
                            Item.getItem("Hundleksak")
                    )),
                    new ArrayList<Treat>(Arrays.asList(
                            Treat.getTreat("Hundfoder"),
                            Treat.getTreat("Biscuit")
                    )),
                    new ArrayList<NPC>()
            ),

            new Map(
                    "trädgård",
                    "Hunden och du är i trädgården bakom ditt hus. På en trädgårdsmöbel ligger en lapp med \n" +
                            "texten [ledtråd_mata]. I trädgårdslandet sitter morfar [Gunnar] på knä och sköter om sina \n" +
                            "plantor. Bakom honom i en korg ligger en [morot] och lite andra grönsaker. I gräset \n" +
                            "längre bort ligger en [pinne]. I ett dunkelt hörn nära staketet ser du en grop i marken \n" +
                            "som din hund grävt. Ur gropen sticker det upp ett [ben]. En grind som leder mot skogen \n" +
                            "står öppen. Du vet att den vägen leder till [skogsgläntan]. Följer du vägen på husets \n" +
                            "framsida kommer du till parkens [parkeringsplats], eller så kan du gå in i ditt [Hem] igen.",
                    new ArrayList<Item>(Arrays.asList(
                            Item.getItem("ledtråd_mata"),
                            Item.getItem("Pinne")
                    )),
                    new ArrayList<Treat>(Arrays.asList(
                            Treat.getTreat("Morot"),
                            Treat.getTreat("Ben")
                    )),
                    new ArrayList<NPC>(Arrays.asList(
                            NPC.getNPC("Gunnar")
                    ))
            ),

            new Map(
                    "parkeringsplats",
                    "Du och hunden står på parkens parkeringsplats. [Kent], din jobbarkompis, står vid sin \n" +
                            "bil och ser förvirrad och stressad ut. På en bänk närmare parkmynningen ligger en \n" +
                            "bortglömd ostmacka. Ingen verkar sakna den och du kan nog ta en [ostbit]. Härifrån kan du \n" +
                            "antingen gå in i stadens [park], eller tillbaka till din [trädgård].",
                    new ArrayList<Item>(),
                    new ArrayList<Treat>(Arrays.asList(
                            Treat.getTreat("Ostbit"))),
                    new ArrayList<NPC>(Arrays.asList(
                            NPC.getNPC("Kent")
                    ))
            ),

            new Map(
                    "park",
                    "Nu är ni i parken. Parken har stora öppna gräsytor med träd sparsamt spridda i området. \n" +
                            "Det finns en fräsch och väl omhändertagen [kiosk] i närheten av parkens [parkeringsplats]. \n" +
                            "I ena änden av parken finns en [badplats], och i den andra änden en skog med en vältrampad \n" +
                            "[skogsstig]. På en picknickfilt under ett träd sitter [Agneta], din granne. I ett ihåligt \n" +
                            "träd lite längre bort skymtar du ett [grisöra] som du gömde i en lek till din hund i förra \n" +
                            "veckan. I gräset ligger också en utomordentligt fin [pinne].",
                    new ArrayList<Item>(Arrays.asList(
                            Item.getItem("Pinne")
                    )),
                    new ArrayList<Treat>(Arrays.asList(
                            Treat.getTreat("Grisöra")
                    )),
                    new ArrayList<NPC>(Arrays.asList(
                            NPC.getNPC("Agneta")
                    ))
            ),

            new Map("kiosk",
                    "Ni kliver fram till parkens [kiosk]. Här säljs massor av olika varor - tidningar och \n" +
                            "godis etc., men inget som är av direkt intresse för dig. På disken står en korg med \n" +
                            "köttbullemackor från dagen innan. \"Gratis\" står det på en liten skylt. En [köttbulle] \n" +
                            "från en sådan macka hade varit bra motivation för din hund. [Glenn] står bakom dig i kön \n" +
                            "och ler mot din hund. Utanför kiosken kan du se tillbaka ut över [park]en med sina öppna \n" +
                            "gräsytor och utspridda träd.",
                    new ArrayList<Item>(),
                    new ArrayList<Treat>(Arrays.asList(
                            Treat.getTreat("Köttbulle")
                    )),
                    new ArrayList<NPC>(Arrays.asList(
                            NPC.getNPC("Glenn")
                    ))
            ),

            new Map(
                    "badplats",
                    "Du lämnar [park]en bakom dig och går ner mot en [badplats]. Sanden är varm under solens \n" +
                            "ljus och vinden för med sig en frisk doft från vattnet. I sanden framför dig ligger en \n" +
                            "[pinne], halvt begravd men ändå tydligt synlig. Vid vattenbrynet står [Kurt] och tittar \n" +
                            "ut över sjön, stilla och eftertänksam. När du närmar dig vänder han sig om och sträcker \n" +
                            "fram en öppen hand. I den ligger en bit [lever] som han erbjuder dig att ta emot. Bakom \n" +
                            "honom glittrar vågorna och en mås ropar på avstånd.",
                    new ArrayList<Item>(Arrays.asList(
                            Item.getItem("Pinne")
                    )),
                    new ArrayList<Treat>(Arrays.asList(
                            Treat.getTreat("Lever")
                    )),
                    new ArrayList<NPC>(Arrays.asList(
                            NPC.getNPC("Kurt")
                    ))
            ),

            new Map(
                    "skogsstig",
                    "Skogsstigen är inte speciellt märkvärdig. Den är vältrampad och används ofta av \n" +
                            "personer som du som promenerar med husdjur. Solen skymtar in bland trädtopparna och lyser \n" +
                            "upp skogen. En [pinne] ligger vid stigens kant. Vanligtvis håller sig folk på stigen, men \n" +
                            "idag ser du färska fotspår leda iväg från huvudstigen. Du kan antingen vända tillbaka \n" +
                            "till [glänta]n du kom ifrån, eller följa stigen hela vägen till [park]en.",
                    new ArrayList<Item>(Arrays.asList(
                            Item.getItem("Pinne")
                    )),
                    new ArrayList<Treat>(Arrays.asList(
                            Treat.getTreat("Special godis")
                    )),
                    new ArrayList<NPC>()
            ),

            new Map(
                    "glänta",
                    "Du kliver igenom grinden och lämnar din [trädgård] bakom dig. Efter en kort promenad \n" +
                            "genom skogen når du en glänta. I det vildvuxna gräset ligger en gammal [hundleksak]. \n" +
                            "En rostig gammal cykel står lutad mot ett träd. När du tittar närmare på den ser du en \n" +
                            "lapp med texten [ledtråd_köttbullar]. Fortsätter du genom gläntan vet du att du kommer \n" +
                            "hitta en [skogsstig] som leder vidare mot parken.",
                    new ArrayList<Item>(Arrays.asList(
                            Item.getItem("ledtråd_köttbullar"),
                            Item.getItem("Hundleksak")
                    )),
                    new ArrayList<Treat>(),
                    new ArrayList<NPC>()
            )
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