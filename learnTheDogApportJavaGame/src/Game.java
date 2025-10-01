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

    String oneLine = "\n";
    String twoLine = "\n\n";
    String threeLine = "\n\n\n";
    String indent = "    ";
    String indent2 = "  - ";
    String lineSeparator = "】============================================================================================【";

    String storyIntroduction = "-DETTA ÄR ETT OFÄRDIGT PROJECT MED TEMPORÄRA VARIABLAR PÅ PLATS OCH BLANDAT SPRÅK-" + oneLine + "\nDu står med koppel i handen. Din hund är lat och ledsen." +
            "\nKan den där hunden ens några tricks alls tänker du." +
            "\nMen då får du idén... Apport!" +
            "\nKan man ens lära denna hund att göra apport?";
    String placeName = "";
    String placeDescription = "";
    String commandOptions = "";
    String inventoryItems = "";
    String heldItemName = "";
    String heldItemDesc = "";
    String visibleItems = "";

    String topHalf;
    String HUDLine = twoLine + lineSeparator;
    String bottomHalf;
    String reactionToPickingUp = "Your inventory is empty...\n";
    String query = "What is your action?: ";
    String visibleHeader = twoLine + "[VISIBLE OBJECTS]:" + oneLine;
    String inventoryHeader = "[INVENTORY]:" + oneLine;
    String inspectingHeader = "[INSPECTING]: ";
    String locationHeader = "Your Location: ";

    String commandsInfo =
            """
                     [HELP]:
                     GOAL OF THE GAME:
                         Teach the dog fetch.
                         \s
                     ============================================================================================
                     COMMAND INFO:
                         \s
                         * Inspect [Object Name] - \nAllows the player to pick an object the scene and inspect it closer.
                     ____________________________________________________________________________________________
                         \s
                         * Inventory - \nShows a list of items in the player's possession.
                     ____________________________________________________________________________________________
                         \s
                         * Go to [Location name] - \nChanges the player's current location to the location selected. \nThe location can be picked from available options mentioned in the current location's description,\noften marked by "[]" brackets.
                     ____________________________________________________________________________________________
                         \s
                         * Dog - \nA pet that allows the player to perform special abilities from the listed available options.
                     ____________________________________________________________________________________________
                    \s""";


    //______________________________________________TEST AREA_________________________________

    ArrayList<Item> itemsTest = new ArrayList<>();
    ArrayList<Item> itemsTest2 = new ArrayList<>();

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
        if (isRunning != 0) {
            newPage(); // this method clears the console.
            // Checks the players input and switches state when they're not moving.
            if (currentState != playerStates.MOVE) {
                // Displays text on console for what state player is in.
                System.out.print(stateMachine(userInput));
                if (currentState != playerStates.START) {
                    userInput = scanner.nextLine();
                }
                checkUserInput();
            } else {
                // this runs when the player is moving.
                currentState = playerStates.IDLE;
                updateTitles();
            }
            storyIntroduction = "";
        }
        return isRunning;
    }

    // Displays relevant info and feedback to the player in the console.
    public String stateMachine(String outString) {
        // the string that gets returned and displayed in the console.
        String textToBeDisplayed = "";
        switch (currentState) {
            //INITIATES START VARIABLES HERE.
            case playerStates.START:
                System.out.println("TEACH THE DOG APPORT the GAME!");

                //Declare the map course here. TEMPORARY items and maps for now.
                itemsTest.add(new Item("Key", "An old iron key covered in rust. It looks fragile but might open something important.", true, false));
                itemsTest.add(new Item("Tome", "A thick, dusty book filled with unreadable runes. It hums faintly with magic. IT CANNOT BE PICKED UP!", false, false));
                itemsTest.add(new Item("Sword", "The blade is snapped in half. Perhaps it could be repaired.", true, false));
                itemsTest2.add(new Item("GoldenChalice", "The blade is snapped in half. Perhaps it could be repaired.", true, false));

                maps.add(new Map("Home", "A cozy and warm place, nothing out of the ordinary.\n Yet you wonder how it would be to go far far [Away].", itemsTest));
                maps.add(new Map("Away", "A cold and scary place, nothing out of the ordinary at all here. \nYou start longing for [Home].", itemsTest2));

                //maps = Map.locations;


                //sets the current player location.
                currentMap = maps.get(0);
                //Updates a bunch of String variables that prepares context to be displayed.
                updateTitles();
                //showAllItemInCurrentMap();
                //Enters IDLE state.
                currentState = playerStates.IDLE;
            case playerStates.IDLE:

                showAllItemInCurrentMap();
                commandOptions = """
                        [TYPE COMMAND]:
                        > Help
                        > Inspect [Object Name]
                        > Inventory
                        > Go to [Location Name]
                        > Dog""";
                topHalf = storyIntroduction + twoLine + locationHeader + placeName + placeDescription + visibleHeader + visibleItems + HUDLine;
                bottomHalf = threeLine + commandOptions + twoLine;

                //compiling UI
                textToBeDisplayed = topHalf + bottomHalf + query;
                break;

            case playerStates.HELP:
                commandOptions = """
                        [TYPE COMMANDS]:
                        > Return""";

                bottomHalf = threeLine + commandOptions + twoLine + twoLine;
                //compiling UI
                textToBeDisplayed = commandsInfo + HUDLine + bottomHalf + query;
                break;

            case playerStates.INSPECT:

                if (heldItem.canPickUp()) {
                    query = "What is your decision: ";
                    commandOptions = """
                            [TYPE COMMANDS]:
                            Put in your inventory?
                            Yes or No?""";
                } else {
                    query = "What is your action?: ";
                    commandOptions = """
                            [TYPE COMMANDS]:
                            > Return""";
                    reactionToPickingUp = "";
                }

                //compiling UI
                textToBeDisplayed = inspectingHeader + heldItemName + oneLine + indent2 + heldItemDesc + twoLine + HUDLine + twoLine + commandOptions + threeLine + twoLine + query;

                break;

            case playerStates.INVENTORY:

                showAllItemInInventory();
                if (inventory.isEmpty()) {
                    commandOptions = """
                            [TYPE COMMANDS]:
                            > Return""";
                } else {
                    commandOptions = """
                            [TYPE COMMANDS]:
                            > Inspect [Object Name]
                            > Return""";
                }

                topHalf = visibleHeader + visibleItems + HUDLine + twoLine + reactionToPickingUp + inventoryHeader + inventoryItems + HUDLine;
                bottomHalf = threeLine + commandOptions + twoLine + query;
                textToBeDisplayed = topHalf + bottomHalf;
                break;

            case playerStates.MOVE:
                currentState = playerStates.IDLE;
                break;

            case playerStates.DOG:
                commandOptions = """
                        [TYPE COMMANDS]:
                        > Return""";
                textToBeDisplayed = "NOT FINISHED, WORK IN PROGRESS \n\n\n\n\n" + commandOptions + "\n\n" + query;
                break;

            case playerStates.EXIT:
                isRunning = 0;
                break;
        }
        // updates all the hud elements that need to update when player moves or interacts.
        //showAllItemInList(currentMap.getItems());
        updateTitles();
        return textToBeDisplayed;
    }

    // Handels the players string input.
    private void checkUserInput() {
        if (userInput.length() > 25) {
            return;
        }
        //Debug print.
        //System.out.println("Player typed: " + userIn + " | Player current map: " + curMap.getName());
        if (userInput != "") {
            switch (currentState) {
                case playerStates.IDLE:
                    if (userInput.equalsIgnoreCase("help")) {
                        //System.out.println("Played asked for help");
                        currentState = playerStates.HELP;
                        break;
                    } else if (userInput.toLowerCase().contains("inspect ")) {
                        //if what comes after "inspect " matches any of the items on the map.
                        if (searchAndInspectItem(userInput.substring(8), currentMap.getItems()) != null) {
                            heldItem = searchAndInspectItem(userInput.substring(8), currentMap.getItems());
                            currentState = playerStates.INSPECT;
                        }
                        break;
                    } else if (userInput.equalsIgnoreCase("inventory")) {
                        currentState = playerStates.INVENTORY;
                        break;
                    } else if (userInput.toLowerCase().contains("go to ")) {
                        //
                        if (searchMapinMapList(userInput.substring(6)) != null) {
                            walkingAnimation();
                            currentMap = searchMapinMapList(userInput.substring(6));
                            currentState = playerStates.MOVE;
                        }
                        break;
                    } else if (userInput.toLowerCase().contains("move to ")) {
                        //
                        if (searchMapinMapList(userInput.substring(8)) != null) {
                            walkingAnimation();
                            currentMap = searchMapinMapList(userInput.substring(8));
                            currentState = playerStates.MOVE;
                        }
                        break;
                    } else if (userInput.equalsIgnoreCase("dog")) {
                        currentState = playerStates.DOG;
                        break;
                    }
                    break;
                case playerStates.HELP:
                    if (userInput.equalsIgnoreCase("return")) {
                        currentState = playerStates.IDLE;
                    }
                    break;
                case playerStates.INSPECT:
                    if (heldItem.canPickUp()) {
                        if (userInput.toLowerCase().contains("no")) {
                            currentState = playerStates.IDLE;
                        } else if (userInput.toLowerCase().contains("yes")) {
                            pickUpItemFromMap();
                            currentState = playerStates.INVENTORY;
                        }
                        break;
                    }
                    if (userInput.equalsIgnoreCase("return")) {
                        currentState = playerStates.IDLE;
                    }
                    ;
                case playerStates.INVENTORY:
                    if (userInput.equalsIgnoreCase("return")) {
                        reactionToPickingUp = "";
                        currentState = playerStates.IDLE;
                    } else if (userInput.toLowerCase().contains("inspect ")) {
                        //if what comes after "inspect " matches any of the items on the map.
                        if (searchAndInspectItem(userInput.substring(8), currentMap.getItems()) != null) {
                            heldItem = searchAndInspectItem(userInput.substring(8), currentMap.getItems());
                            currentState = playerStates.INSPECT;
                        }
                        break;
                    }
                    break;
                case playerStates.MOVE:
                    break;
                case playerStates.DOG:
                    if (userInput.equalsIgnoreCase("return")) {
                        currentState = playerStates.IDLE;
                    }
                    ;
                    break;
            }
        }
    }

    private void updateTitles() {
        placeName = "[" + currentMap.getName() + "]";
        placeDescription = twoLine + indent2 + currentMap.getDescription();
    }

    private void pickUpItemFromMap() {
        System.out.println("Item was picked up DEBUG");
        for (int j = 0; j <= maps.size() - 1; j++) {
            if (currentMap.getName().equalsIgnoreCase(maps.get(j).getName().toLowerCase())) {
                for (int i = 0; i <= currentMap.getItems().size() - 1; i++) {
                    if (heldItem.getItem_name().equalsIgnoreCase(currentMap.getItems().get(i).getItem_name())) {
                        reactionToPickingUp = "\nYou picked up the " + heldItemName + "!\n\n";
                        inventory.add(heldItem);
                        currentMap.getItems().remove(i);
                        maps.set(j, currentMap);
                        showAllItemInCurrentMap();
                        showAllItemInInventory();
                        updateTitles();
                    }
                }
            }
        }
    }

    private Map searchMapinMapList(String searchTerm) {
        for (int i = 0; i <= maps.size() - 1; i++) {
            if (searchTerm.equalsIgnoreCase(maps.get(i).getName())) {
                currentMap = maps.get(i);
                return maps.get(i);
            }
        }
        return null;
    }

    private void walkingAnimation() {
        String walkin = "Walking ";
        try {
            for (int j = 0; j <= 2; j++) {
                Thread.sleep(100);
                for (int i = 0; i <= 3; i++) {
                    Thread.sleep(400);
                    walkin += ".";
                    newPage();
                    System.out.println(walkin);
                }
                walkin = "Walking ";
                newPage();
                System.out.println(walkin);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Item searchAndInspectItem(String searchTerm, ArrayList<Item> listOfItems) {
        for (int i = 0; i <= listOfItems.size() - 1; i++) {
            if (searchTerm.equalsIgnoreCase(listOfItems.get(i).getItem_name())) {
                //heldItem = listOfItems.get(i);
                heldItemName = heldItem.getItem_name();
                heldItemDesc = heldItem.getDescription();
                return listOfItems.get(i);
            }
        }
        System.out.println("! Type in the correct item.");
        return null;
    }

    private void showAllItemInInventory() {
        inventoryItems = "";
        for (int i = 0; i <= inventory.size() - 1; i++) {
            inventoryItems += indent2 + inventory.get(i).getItem_name() + ",\n";
        }
    }

    private void showAllItemInCurrentMap() {
        visibleItems = "";
        for (int i = 0; i <= currentMap.getItems().size() - 1; i++) {
            visibleItems += indent2 + currentMap.getItems().get(i).getItem_name() + ",\n";
        }
    }

    private void newPage() {
        for (int i = 0; i < 16; i++) {
            System.out.println("\n");
        }
    }
}
