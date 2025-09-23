public class Treat {
        public String name;
        public int effect;
        public String description;

        public Treat(String name, int effect, String description) {
            this.name = name;
            this.effect = effect;
            this.description = description;
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
        return new Treat[0];
    }
}
    // ArrayList<String> treat = new ArrayList<>(10); <---- ska vara någon annanstans

/*
Biscuit
Effekt: 3 poäng.
Beskrivning: Klassisk hundkex. Smakar okej, ger få poäng

Hundfoder
Effekt: 3 poäng.
Beskrivning: Vanlig vardagsmat. Ger få poäng.

Ben
Effekt: 5 poäng.
Beskrivning: Perfekt att tugga länge på. Ger mer poäng än vanliga treats.

Köttbulle
Effekt: 7 poäng.
Beskrivning: En favorit! Ger bra poäng.

Ostbit
Effekt: 7 poäng.
Beskrivning: Supergott! Ger bra poäng.

Torkad lever
Effekt: 10 poäng.
Beskrivning: Lyxig och god belöning. Ger mycket poäng.

Grisöra
Effekt: 10 poäng.
Beskrivning: En sällsynt godsak. Ger mycket poäng


Specialgodis (gömd)
Effekt: Dubblar dina poäng.
Beskrivning: Hemlig belöning som bara dyker upp i speciella lägen.
(boolean?)
(isHidden?)
 */