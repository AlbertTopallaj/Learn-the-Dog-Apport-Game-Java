import java.util.ArrayList;

    /*
    TODO:
        - Change name of Items to "Item".
        - Treat Class needs getMethods to display Treat information to the player ("Name, Description, Effects")
        - Map Class should be a container class
            e.g: Map Constructor should hold ArrayLists of the type
            Item, Treat & NPC as it's private variables.
        - Dog Class needs to figure out together with Treat Class how
            they want to calculate the eat method and the calculation for it.
            Specifically how "successChance" should be affected by treats with different values.
            Also so that it becomes more reliable.
        - Items Class should be a container class(?) Depends on where we want to store the arraylists.
            In the Game Class?
     */

public class Game {
    //___PUBLIC___
    public boolean isWin = false;                       // will be change to private and make a set and get method.

    //___PRIVATE___
    private final int inventorySpace = 10;
    Items[] inventory = new Items[inventorySpace];          // All other items the player is carrying that are not edible.
    ArrayList<Treat> treats = new ArrayList<>();            // Dog treats that the player carries, will be displayed in inventory in game.
    ArrayList<Map> maps = new ArrayList<>();                // All maps of the game.

    //Will track the current state of the player in game.
    private enum PlayerStates {
        MAIN,
        HELP,
        PICK_UP,
        INVENTORY,
        MOVE,
        EXIT
    }

    PlayerStates currentState = PlayerStates.MAIN;

    //Displays relative info and feedback to the player in the console.
    public String DisplayText(String outString) {
        String display = "";

        while(currentState.equals(PlayerStates.MAIN))       // Uses Enum states to determine what game logic to run
        {
            System.out.println("test test :O");
            display = "test test :)";
            currentState = PlayerStates.EXIT;               // NOTE: this is temporary
            break;                                          // will immediately exit while loop.
        }
        return display;
    }
}
