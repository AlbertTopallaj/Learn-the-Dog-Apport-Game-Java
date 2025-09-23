public class Treat {
        public String name;
        public int effect;
        public String description;
        // public boolean isHidden;

        public Treat(String name, int effect, String description) {
            this.name = name;
            this.effect = effect;
            this.description = description;
            // this.isHidden = isHidden;
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
        new Treat(
                "Morot",
                1,
                "En knaprig men ointressant belöning."
        );
        return new Treat[0]; // blir detta rätt sätt att returnera på?

        new Treat(
                "Biscuit",
                3,
                "Klassisk hundkex. Smakar okej, ger få poäng"
        );
        return new Treat[1];

        new Treat(
                "Hundfoder",
                3,
                "Vanlig vardagsmat. Ger få poäng."
        );
        return new Treat[2];

        new Treat(
                "Ben",
                5,
                "Perfekt att tugga länge på. Ger mer poäng än vanliga treats."
        );
        return new Treat[3];

        new Treat(
                "Köttbulle",
                7,
                "En favorit! Ger bra poäng."
        );
        return new Treat[4];

        new Treat(
                "Ostbit",
                7,
                "Supergott! Ger bra poäng."
        );
        return new Treat[5];

        new Treat(
                "Lever",
                10,
                "MUMS! Ger mycket poäng!"
        );
        return new Treat[6];

        new Treat(
                "Grisöra",
                10,
                "En sällsynt godsak. Ger mycket poäng!"
        );
        return new Treat[7];

        new Treat(
                "Special godis",
                "Dubblar dina poäng!", // hur skriver jag detta som int?
                "Hemlig belöning som bara dyker upp i speciella lägen."
        );
        return new Treat[8];
    }
}