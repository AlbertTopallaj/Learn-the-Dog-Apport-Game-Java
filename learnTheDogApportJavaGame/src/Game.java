import java.util.ArrayList;
import java.util.Scanner;

    /*
    TODO:
        -
     */

public class Game {

    public boolean isWin = false;                       // will be change to private and make a set and get method.
    int isRunning = 1;


    // parameters and containers.
    private final int inventorySpace = 10;
    ArrayList<Item> inventory = new ArrayList<>(inventorySpace);          // All other items the player is carrying that are not edible.
    ArrayList<Treat> treats = new ArrayList<>();            // Dog treats that the player carries, will be displayed in inventory in game.
    ArrayList<Map> maps = new ArrayList<>();                // All maps of the game.
    Scanner scanner = new Scanner(System.in);
    String userInput = "";

    // objects actively in player use.
    Item heldItem;
    Map currentMap;
    Treat heldTreat;



    // text formatting and templates.
    String visibleItems = "";
    String oneLine = "\n";
    String twoLine = "\n\n";
    String threeLine = "\n\n\n";
    String indent = "    ";
    String indent2 = "  - ";
    String lineSeparator = "】============================================================================================【";

    String storyIntroduction = oneLine + "Du står med koppel i handen. Din hund är lat och ledsen." +
            "\nKan den där hunden ens några tricks alls? Men då får du idén..." +
            "\nApport!" +
            "\nKan du ens lära denna hund att göra apport?";
    String placeName="";
    String placeDescription ="";
    String commandOptions ="";
    String heldItemName ="";
    String heldItemDesc ="";

    String topHalf;
    String HUDLine = twoLine + lineSeparator + oneLine;
    String bottomHalf;
    String reactionToPickingUp = "Your inventory is empty...\n";
    String query = "What is your action?: ";
    String visibleHeader = twoLine + "[Visible Objects]:" + oneLine;
    String inventoryHeader = "[Inventory]:" + oneLine;
    String inspectingHeader = "[Inspecting]: ";
    String locationHeader = "Your Location: ";

    String commandsInfo =
            """
            [Help]:
                >Inspect [Object Name] - \nGives the player a list of object and items from the players vicinity that can be interacted and gathered.
               \s
                >Inventory - \nShows a list of items in the player's possession.
               \s
                >Move to [Location name] - \nChanges the player's current location to the location selected. \nThe location can be picked from available options mentioned in the current location's description,\noften marked by "[]" brackets.
               \s
                >Dog - \nA pet that allows the player to perform special abilities from the listed available options.
           \s""";


    //______________________________________________TEST AREA_________________________________

    ArrayList<Item> itemsTest = new ArrayList<>();
    ArrayList<Item> itemsTest2 = new ArrayList<>();



    ArrayList<Map> mapsTest = new ArrayList<Map>();

    //______________________________________________REMOVE LATER_____________________________


    //Will track the current state of the player in game.
    private enum playerStates {
        START,
        IDLE,
        HELP,
        INSPECT,
        INVENTORY,
        MOVE,
        DOG,
        EXIT
    }

    playerStates currentState = playerStates.START;

    public int runGame() // returns 0 = Game Quits
    {
        if(isRunning != 0) {
            newPage(); // this method clears the console.
            // Checks the players input and switches state when they're not moving.
            if(currentState != playerStates.MOVE){
                // Displays text on console for what state player is in.
                System.out.print(stateMachine(userInput));
                if(currentState != playerStates.START) {
                    userInput = scanner.nextLine();
                }
                checkUserInput(userInput,currentMap);
                }
            else {
                // this runs when the player is moving.
                currentState = playerStates.IDLE;
                updateTitles();
            }
            storyIntroduction ="";
        }
        return isRunning;
    }

    //Displays relative info and feedback to the player in the console.
    public String stateMachine(String outString) {
        // the string that gets returned and displayed in the console.
        String display = "";

        switch (currentState) {
            case playerStates.START:
                System.out.println("TEACH THE DOG APPORT the GAME!");

                //Maps here
                itemsTest.add(new Item("Key", "An old iron key covered in rust. It looks fragile but might open something important.", true, false));
                itemsTest.add(new Item("Tome", "A thick, dusty book filled with unreadable runes. It hums faintly with magic. IT CANNOT BE PICKED UP!", false, false));
                itemsTest.add(new Item("Sword", "The blade is snapped in half. Perhaps it could be repaired.", true, false));
                itemsTest2.add(new Item("GoldenChalice", "The blade is snapped in half. Perhaps it could be repaired.", true, false));

                maps.add(new Map("Home","A cozy and warm place, nothing out of the ordinary.\n Yet you wonder how it would be to go far far [Away].",itemsTest));
                maps.add(new Map("Away","A cold and scary place, nothing out ordinary at all here. \nYou start longing for [Home].",itemsTest2));

                currentMap = maps.get(0);
                currentState = playerStates.IDLE;
                updateTitles();

            case playerStates.IDLE:
                showAllItemInList(currentMap.getItems());
                commandOptions = """
                        [Type Commands]:
                        > Help
                        > Inspect [Object Name]
                        > Inventory
                        > Go to [Location Name]
                        > Dog""";
                topHalf = storyIntroduction + twoLine + locationHeader + placeName + placeDescription + visibleHeader + visibleItems + HUDLine;
                bottomHalf = threeLine + commandOptions + twoLine;

                //compiling UI
                display = topHalf + bottomHalf + query;
                break;

            case playerStates.HELP:
                commandOptions = """
                        [Type Commands]:
                        > Return""";

                bottomHalf = threeLine + commandOptions + threeLine + twoLine;
                //compiling UI
                display = commandsInfo + HUDLine + bottomHalf + query;
                break;

            case playerStates.INSPECT:

                if(heldItem.canPickUp() == true){
                    commandOptions = """
                        [Type Commands]:
                        Put in your inventory? 
                        Yes or No?""";
                } else {
                    commandOptions = """
                        [Type Commands]:
                        > Return""";
                    reactionToPickingUp = "";
                }

                //compiling UI
                display = inspectingHeader + heldItemName + oneLine + indent2 + heldItemDesc + twoLine + HUDLine + twoLine + commandOptions + threeLine + twoLine + query;

                break;

            case playerStates.INVENTORY:

                showAllItemInList(inventory);
                if(inventory.size()==0) {
                    commandOptions = """
                        [Type Commands]:
                        > Return""";
                }else {
                    commandOptions = """
                        [Type Commands]:
                        > Inspect [Object Name]
                        > Return""";
                }

                topHalf = visibleHeader + visibleItems + HUDLine + twoLine + inventoryHeader + HUDLine;
                bottomHalf = threeLine + reactionToPickingUp + commandOptions + twoLine;
                display = topHalf + bottomHalf;
                break;

            case playerStates.MOVE:
                currentState = playerStates.IDLE;
                break;

            case playerStates.DOG:
                break;

            case playerStates.EXIT:
                isRunning = 0;
                break;
        }
        // updates all the hud elements that need to update when player moves or interacts.
        updateTitles();
        return display;
    }

    private void checkUserInput(String userIn,Map curMap)
    {
        System.out.println("Player typed: " + userIn + " | Player current map: " + curMap.getName());
        if(userIn != "")
        {
            switch (currentState)
            {
                case playerStates.IDLE:
                    if(userIn.equalsIgnoreCase("help")) {
                        System.out.println("Played asked for help");
                        currentState = playerStates.HELP;
                    }
                    else if(userIn.toLowerCase().contains("inspect ")) {
                        if(searchItemInItemList(userIn.substring(8),curMap.getItems()) != null) {
                            heldItem = searchItemInItemList(userIn.substring(8),curMap.getItems());
                            currentState = playerStates.INSPECT;
                            System.out.println("Item found : " + heldItem.getItem_name());
                        }
                        break;
                    }
                    else if(userIn.equalsIgnoreCase("inventory")) {
                        currentState = playerStates.INVENTORY;
                        break;
                    }
                    else if(userIn.toLowerCase().contains("go to ")) {
                        if(searchMapinMapList(userIn.substring(6),maps) != null) {
                            walkingAnimation();
                            currentMap = searchMapinMapList(userIn.substring(6),maps);
                            currentState = playerStates.MOVE;
                        }
                        break;
                    }
                    else if(userIn.equalsIgnoreCase("dog")) {
                    }
                    break;
                case playerStates.HELP:
                    if(userIn.equalsIgnoreCase("return")) {
                        currentState = playerStates.IDLE;
                    }
                    break;
                case playerStates.INSPECT:
                    if(heldItem.canPickUp()) {
                        if(userIn.toLowerCase().contains("no")) {
                            currentState = playerStates.IDLE;
                        }
                        else if(userIn.toLowerCase().contains("yes")){
                            reactionToPickingUp = "You picked up the " + heldItemName + "!\n\n";
                            inventory.add(heldItem);
                            currentState = playerStates.INVENTORY;
                        }
                    }
                    break;
                case playerStates.INVENTORY:
                    break;
                case playerStates.MOVE:
                    break;
                case playerStates.DOG:
                    break;
            }
        }
    }

    private void updateTitles() {
        placeName = "[" + currentMap.getName() + "]";
        placeDescription = twoLine + indent2 + currentMap.getDescription();
    }

    private Map searchMapinMapList(String searchTerm, ArrayList<Map> listOfMaps) {
        for (int i = 0; i <= listOfMaps.size() -1; i++) {
            if (searchTerm.equalsIgnoreCase(listOfMaps.get(i).getName())) {
                currentMap = listOfMaps.get(i);
                return listOfMaps.get(i);
            }
        }
        return null;
    }

    private void walkingAnimation() {
        String walkin ="Walking ";
        try{
            for (int j = 0; j <= 2; j++){
                Thread.sleep(100);
                for (int i = 0; i <= 3; i++){
                    Thread.sleep(400);
                    walkin += ".";
                    newPage();
                    System.out.println(walkin);
                }
                walkin ="Walking ";
                newPage();
                System.out.println(walkin);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Item searchItemInItemList(String searchTerm, ArrayList<Item> listOfItems) {
        for (int i = 0; i <= listOfItems.size() -1; i++) {
            if (searchTerm.equalsIgnoreCase(listOfItems.get(i).getItem_name())) {
                heldItem = listOfItems.get(i);
                heldItemName = heldItem.getItem_name();
                heldItemDesc = heldItem.getDescription();
                return listOfItems.get(i);
            }
        }
        System.out.println("! Type in the correct item.");
        return null;
    }

    private void showAllItemInList(ArrayList<Item> listOfItems) {
        visibleItems = "";
        for (int i = 0; i <= listOfItems.size() -1; i++) {
            visibleItems += indent2 + listOfItems.get(i).getItem_name() + ", \n";
        }
    }
    private void newPage() {
        for (int i = 0; i < 16; i++) {
            System.out.println("\n");
        }
    }
}
