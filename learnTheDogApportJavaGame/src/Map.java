public class Map { // även lägga till Items/Treat/NPC-objektarrayer för att spara varje sak till tillhörande plats?
    public String name;
    public String description;

    Map(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static Map[] locations = { // array med varje plats tillgänglig i spelet
            new Map(
                    "park",
                    "Du ser en park med stora, öppna grönytor som hade passat perfekt till att öva apport med hunden."
            ),

            new Map("kiosk",
                    "Du står vid en välskött kiosk. Den är öppen och en man står bakom disken och ser uttråkad ut."
            )
    };

    public static Map getLocation(String locationInput) { // metod för att skicka ut den plats som spelaren vill gå till
        for (Map location : locations) {
            if (location.name.equals(locationInput.toLowerCase())) { // söker efter platsnamn och skickar ut objektet som matchar från arrayen till spelet
                return location;
            }
        }
        return null;
    }
}