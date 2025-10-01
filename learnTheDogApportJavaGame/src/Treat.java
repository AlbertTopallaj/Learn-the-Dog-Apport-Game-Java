public class Treat {
    public String name;
    public int effect;
    public String description;
    public boolean isHidden;

    public Treat(String name, int effect, String description, boolean isHidden) {
        this.name = name;
        this.effect = effect;
        this.description = description;
        this.isHidden = isHidden;
    }

    public String getName() {
        return name;
    }

    public int getEffect() {
        return effect;
    }

    public String getDescription() {
        return description;
    }

    public static Treat[] treatArray() {
        return new Treat[] {
                new Treat(
                        "Morot",
                        1,
                        "En knaprig men ointressant belöning.",
                        false
                ),
                new Treat(
                        "Biscuit",
                        3,
                        "Klassisk hundkex. Smakar okej, ger få poäng",
                        false
                ),
                new Treat(
                        "Hundfoder",
                        3,
                        "Same old, same old. Äts mest av rutin.. Ger få poäng.",
                        false
                ),
                new Treat(
                        "Ben",
                        5,
                        "Perfekt att tugga länge på. Ger mer poäng än vanliga treats.",
                        false
                ),
                new Treat(
                        "Köttbulle",
                        7,
                        "En saftig liten skatt – hög risk för dregel. Ger bra poäng!",
                        false
                ),
                new Treat(
                        "Ostbit",
                        7,
                        "Lyx på hög nivå – hundens version av finmiddag. Ger bra poäng!",
                        false
                ),
                new Treat(
                        "Lever",
                        10,
                        "Knaprigt, segt och helt enkelt himmelriket. Ger mycket poäng!",
                        false
                ),
                new Treat(
                        "Grisöra",
                        10,
                        "En sällsynt godsak. Ger mycket poäng!",
                        false
                ),
                new Treat(
                        "Special godis",
                        2,
                        "Hemlig belöning som bara dyker upp i speciella lägen. Dubblar  dina poäng och ditt ego!",
                        true
                ),
        };
    }

    public static Treat getTreat(String treatInput) {
        Treat[] treatArray = treatArray(); // metod för att skicka ut den plats som spelaren vill gå till
        for (Treat treat : treatArray) {
            if (treat.name.equals(treatInput)) { // söker efter platsnamn och skickar ut objektet som matchar från arrayen till spelet
                return treat;
            }
        }
        return null;
    }
}
