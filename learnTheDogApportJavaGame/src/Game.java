import java.util.ArrayList;

    /*
    TODO:
        -
     */

public class Game {
    //___PUBLIC___
    public boolean isWin = false;                       // will be change to private and make a set and get method.
    int isRunning = 1;

    //___PRIVATE___
    private final int inventorySpace = 10;
    Item[] inventory = new Item[inventorySpace];          // All other items the player is carrying that are not edible.
    ArrayList<Treat> treats = new ArrayList<>();            // Dog treats that the player carries, will be displayed in inventory in game.
    ArrayList<Map> maps = new ArrayList<>();                // All maps of the game.

    //Will track the current state of the player in game.
    private enum playerStates {
        MAIN,
        HELP,
        PICK_UP,
        INVENTORY,
        MOVE,
        EXIT
    }

    playerStates currentState = playerStates.MAIN;

    public int runGame()
    {
        System.out.println("Game started");
        // stuff happened and game finish isRunning = 0;
        return isRunning;
    }

    //Displays relative info and feedback to the player in the console.
    public String displayText(String outString) {
        String display = "";


        while(currentState.equals(playerStates.MAIN))       // Uses Enum states to determine what game logic to run
        {
            System.out.println("test test :O");
            display = "test test :)";
            currentState = playerStates.EXIT;               // NOTE: this is temporary
            break;                                          // will immediately exit while loop.
        }
        while(currentState.equals(playerStates.HELP))       // .
        {

        }
        while(currentState.equals(playerStates.PICK_UP))       // .
        {

        }
        while(currentState.equals(playerStates.INVENTORY))       // .
        {

        }
        while(currentState.equals(playerStates.MOVE))       // .
        {

        }
        while(currentState.equals(playerStates.EXIT))       // .
        {

        }

        return display;
    }
}
