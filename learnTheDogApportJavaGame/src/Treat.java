
public class Treat {
    public String name;
    public double effect;
    public String description;

    public treat(String name, double effect, String description) {
        this.name = name;
        this.effect = effect;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public double getEffect() {
        return effect;
    }

    public String getDescription() {
        return description;
    }
}

    // ArrayList<String> treat = new ArrayList<>(10); <---- ska vara någon annanstans

/*
Morot:
Effekt: 1 poäng.
Beskrivning: En knaprig men ointressant belöning.

Biscuit:
Effekt: 3 poäng.
Beskrivning: Klassisk hundkex. Smakar okej, ger få poäng

Hundfoder:
Effekt: 3 poäng.
Beskrivning: Vanlig vardagsmat. Ger få poäng.

Ben:
Effekt: 5 poäng.
Beskrivning: Perfekt att tugga länge på. Ger mer poäng än vanliga treats.

Köttbulle:
Effekt: 7 poäng.
Beskrivning:

Ostbit:
Effekt: 7 poäng.
Beskrivning: Supergott! Ger bra poäng.

Torkad lever:
Effekt: 10 poäng.
Beskrivning: Lyxig och god belöning. Ger mycket poäng.

Grisöra:
Effekt: 10 poäng.
Beskrivning: En sällsynt godsak. Ger mycket poäng


Specialgodis (gömd)
Effekt: Dubblar dina poäng.
Beskrivning: Hemlig belöning som bara dyker upp i speciella lägen.
(boolean?)
(isHidden?)
 */