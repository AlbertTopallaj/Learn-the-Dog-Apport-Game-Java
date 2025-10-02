import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

    /*
    TODO:
        -
     */

public class Game {
    Boolean isRunning = true;


    // parameters and containers.
    private final int inventorySpace = 10;
    ArrayList<Item> itemInventory = new ArrayList<>(inventorySpace);          // All other items the player is carrying that are not edible.
    ArrayList<Treat> treatInventory = new ArrayList<>();            // Dog treats that the player carries, will be displayed in inventory in game.
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

    String inventoryTreats = "";
    String heldTreatName = "";
    String heldTreatDesc = "";
    String visibleTreats = "";

    String topHalf;
    String bottomHalf;
    String HUDLine = twoLine + lineSeparator;
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

    public Boolean isGameRunning() {
        return isRunning;
    }

    public void runGame() // returns 0 = Game Quits
    {
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
    }

    // Displays relevant info and feedback to the player in the console.
    public String stateMachine(String outString) {
        // the string that gets returned and displayed in the console.
        String textToBeDisplayed = "";
        switch (currentState) {
            //INITIATES START VARIABLES HERE.
            case playerStates.START:
                System.out.println("TEACH THE DOG APPORT the GAME!");

                maps = new ArrayList<>(Arrays.asList(Map.locations));
                //sets the current player location.
                currentMap = maps.get(0);
                //Updates a bunch of String variables that prepares context to be displayed.
                updateTitles();
                showAllItemInCurrentMap();
                showAllTreatsInCurrentMap();
                //Enters IDLE state.
                storyIntroduction = "";
                currentState = playerStates.IDLE;
            case playerStates.IDLE:

                showAllItemInCurrentMap();
                showAllTreatsInCurrentMap();
                commandOptions = """
                        [TYPE COMMAND]:
                        > Help
                        > Inspect [Object Name]
                        > Inventory
                        > Go to [Location Name]
                        > Dog""";
                query = "What is your action?: ";
                topHalf = storyIntroduction + twoLine + locationHeader + placeName + placeDescription + visibleHeader + visibleItems + visibleTreats + HUDLine;
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

                if (heldItem != null) {
                    if (heldItem.canPickUp()) {
                        commandOptions = """
                                [TYPE COMMANDS]:
                                Put in your inventory?
                                Yes or No?""";
                        query = "What is your decision: ";
                    } else {
                        commandOptions = """
                                [TYPE COMMANDS]:
                                > Return""";
                        reactionToPickingUp = "";
                        query = "What is your action?: ";
                    }
                } else if (heldTreat != null) {
                    commandOptions = """
                            [TYPE COMMANDS]:
                            Put in your inventory?
                            Yes or No?""";
                    query = "What is your decision: ";
                }

                //compiling UI
                textToBeDisplayed = inspectingHeader + heldItemName + oneLine + indent2 + heldItemDesc + heldTreatName + oneLine + indent2 + heldTreatDesc + twoLine + HUDLine + twoLine + commandOptions + threeLine + twoLine + query;
                break;

            case playerStates.INVENTORY:

                showAllItemsInInventory();
                showAllTreatsInCurrentMap();

                // changes command options displayed depending on which inventory is empty.
                if (itemInventory.isEmpty() && (treatInventory.isEmpty())) // if both are filled.
                {
                    commandOptions = """
                            [TYPE COMMANDS]:
                            > Return""";
                } else if (itemInventory.isEmpty()) {   // if only treatInventory.
                    commandOptions = """
                            [TYPE COMMANDS]:
                            > Inspect [Object Name]
                            > Feed dog [Object Name]
                            > Return""";
                } else {
                    commandOptions = """
                            [TYPE COMMANDS]:
                            > Inspect [Object Name]
                            > Return""";
                }

                query = "What is your action?: ";
                topHalf = visibleHeader + visibleItems + visibleTreats + HUDLine + twoLine + reactionToPickingUp + inventoryHeader + inventoryItems + inventoryTreats + HUDLine;
                bottomHalf = threeLine + commandOptions + twoLine + query;
                textToBeDisplayed = topHalf + bottomHalf;
                break;

            case playerStates.MOVE:
                currentState = playerStates.IDLE;
                break;

            case playerStates.DOG:
                commandOptions = """
                        [TYPE COMMANDS]:
                        > Feed dog [Object Name]
                        > Try fetch
                        > Return""";
                topHalf = "[DOG]: ";
                bottomHalf = threeLine + commandOptions + twoLine + query;
                textToBeDisplayed = topHalf + threeLine + "Dog -Woof Woof! \n\n\n\n\n" + bottomHalf;
                break;

            case playerStates.EXIT:
                isRunning = false;
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
        if (userInput.isEmpty()) {
            return;
        }

        switch (currentState) {
            case playerStates.IDLE:
                if (userInput.equalsIgnoreCase("help")) {
                    //System.out.println("Played asked for help");
                    currentState = playerStates.HELP;
                    break;
                } else if (userInput.equalsIgnoreCase("inventory")) {
                    currentState = playerStates.INVENTORY;
                    break;
                } else if (userInput.toLowerCase().contains("inspect ")) {
                    //if what comes after "inspect " matches any of the items on the map.
                    Item itemSearched = searchAndHoldItem(userInput.substring(8), currentMap.getItems());
                    Treat treatSearched = searchAndHoldTreat(userInput.substring(8), currentMap.getTreats());
                    if (itemSearched == null && treatSearched == null) {
                        heldItem = null;
                        heldTreat = null;
                        break;
                    }
                    if (itemSearched != null) {
                        heldItem = itemSearched;
                        currentState = playerStates.INSPECT;
                    } else {
                        heldTreat = treatSearched;
                        currentState = playerStates.INSPECT;
                    }
                    break;
                } else if (userInput.toLowerCase().contains("go to ")) {
                    //
                    Map mapSearched = searchMapInMapList(userInput.substring(6));
                    if (mapSearched != null) {
                        walkingAnimation();
                        //currentMap = mapSearched;
                        currentState = playerStates.MOVE;
                    }
                    break;
                } else if (userInput.toLowerCase().contains("move to ")) {
                    //
                    Map mapSearched = searchMapInMapList(userInput.substring(8));
                    if (mapSearched != null) {
                        walkingAnimation();
                        //currentMap = mapSearched;
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

                if (heldItem != null) {
                    if (!(heldItem.canPickUp())) {
                        if (userInput.equalsIgnoreCase("return")) {
                            currentState = playerStates.IDLE;
                        }
                        break;
                    }
                    if (userInput.toLowerCase().contains("no")) {
                        currentState = playerStates.IDLE;
                        break;
                    }
                    if (userInput.toLowerCase().contains("yes")) {
                        pickUpItemFromMap();
                        currentState = playerStates.INVENTORY;
                        break;
                    }
                } else {
                    if (userInput.toLowerCase().contains("no")) {
                        currentState = playerStates.IDLE;
                        break;
                    }
                    if (userInput.toLowerCase().contains("yes")) {
                        pickUpTreatFromMap();
                        currentState = playerStates.INVENTORY;
                        break;
                    }
                }
                break;

            case playerStates.INVENTORY:
                if (userInput.equalsIgnoreCase("return")) {
                    reactionToPickingUp = "";
                    currentState = playerStates.IDLE;
                } else if (userInput.toLowerCase().contains("inspect ")) {
                    //if what comes after "inspect " matches any of the items on the map.
                    Item itemSearched = searchAndHoldItem(userInput.substring(8), currentMap.getItems());
                    Treat treatSearched = searchAndHoldTreat(userInput.substring(8), currentMap.getTreats());
                    if (itemSearched == null && treatSearched == null) {
                        break;
                    }
                    if (itemSearched != null) {
                        heldItem = itemSearched;
                        currentState = playerStates.INSPECT;
                    } else {
                        heldTreat = treatSearched;
                        currentState = playerStates.INSPECT;
                    }
                    break;
                } else if (userInput.toLowerCase().contains("feed dog ")) {
                    Item itemSearched = searchAndHoldItem(userInput.substring(9), itemInventory);
                    Treat treatSearched = searchAndHoldTreat(userInput.substring(9), treatInventory);
                    if (itemSearched == null && treatSearched == null) {
                        heldItem = null;
                        heldTreat = null;
                        break;
                    }
                    if (itemSearched != null) {
                        heldItem = null;
                        // CANNOT FEED THE DOG THAT ITEM
                        System.out.println("YOU CANNOT FEED THE DOG THAT ITEM!\n");
                        currentState = playerStates.INVENTORY;
                    } else {
                        heldTreat = treatSearched;
                        currentState = playerStates.DOG;
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

    private void updateTitles() {
        placeName = "[" + currentMap.getName() + "]";
        placeDescription = twoLine + indent2 + currentMap.getDescription();
    }

    private void pickUpItemFromMap() {
        System.out.println("Item was picked up DEBUG");
        for (int j = 0; j < maps.size(); j++) {
            if (!currentMap.getName().equalsIgnoreCase(maps.get(j).getName().toLowerCase())) {
                continue;
            }
            for (int i = 0; i < currentMap.getItems().size(); i++) {
                if (!heldItem.getName().equalsIgnoreCase(currentMap.getItems().get(i).getName())) {
                    continue;
                }
                reactionToPickingUp = "\nYou picked up the " + heldItemName + "!\n\n";
                itemInventory.add(heldItem);
                currentMap.getItems().remove(i);
                maps.set(j, currentMap);
                showAllItemInCurrentMap();
                showAllItemsInInventory();
                updateTitles();
            }
        }
    }

    private void pickUpTreatFromMap() {
        System.out.println("treat was picked up DEBUG");
        for (int j = 0; j < maps.size(); j++) {
            if (!currentMap.getName().equalsIgnoreCase(maps.get(j).getName().toLowerCase())) {
                continue;
            }
            for (int i = 0; i < currentMap.getTreats().size(); i++) {
                if (!heldTreat.getName().equalsIgnoreCase(currentMap.getTreats().get(i).getName())) {
                    continue;
                }
                reactionToPickingUp = "\nYou picked up the " + heldTreatName + "!\n\n";
                treatInventory.add(heldTreat);
                currentMap.getTreats().remove(i);
                maps.set(j, currentMap);
                showAllTreatsInCurrentMap();
                showAllTreatsInInventory();
                updateTitles();
            }
        }
    }

    private Map searchMapInMapList(String searchTerm) {
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

    private Item searchAndHoldItem(String searchTerm, ArrayList<Item> listOfItems) {
        for (Item listOfItem : listOfItems) {
            if (searchTerm.equalsIgnoreCase(listOfItem.getName())) {
                heldItem = listOfItem;
                heldItemName = heldItem.getName();
                heldItemDesc = heldItem.getDescription();
                return listOfItem;
            }
        }
        System.out.println("! Type in the correct item.");
        return null;
    }

    private Treat searchAndHoldTreat(String searchTerm, ArrayList<Treat> listOfTreats) {
        for (Treat listOfTreat : listOfTreats) {
            if (searchTerm.equalsIgnoreCase(listOfTreat.getName())) {
                heldTreat = listOfTreat;
                heldTreatName = heldTreat.getName();
                heldTreatDesc = heldTreat.getDescription();
                return listOfTreat;
            }
        }
        System.out.println("! Type in the correct item.");
        return null;
    }

    private void showAllItemsInInventory() {
        inventoryItems = "";
        for (int i = 0; i <= itemInventory.size() - 1; i++) {
            inventoryItems += indent2 + itemInventory.get(i).getName() + ",\n";
        }
    }

    private void showAllTreatsInInventory() {
        inventoryTreats = "";
        for (int i = 0; i <= treatInventory.size() - 1; i++) {
            inventoryTreats += indent2 + treatInventory.get(i).getName() + ",\n";
        }
    }

    private void showAllItemInCurrentMap() {
        visibleItems = "";
        for (int i = 0; i <= currentMap.getItems().size() - 1; i++) {
            visibleItems += indent2 + currentMap.getItems().get(i).getName() + ",\n";
        }
    }

    private void showAllTreatsInCurrentMap() {
        visibleTreats = "";
        for (int i = 0; i <= currentMap.getTreats().size() - 1; i++) {
            visibleTreats += indent2 + currentMap.getTreats().get(i).getName() + ",\n";
        }
    }

    private void newPage() {
        for (int i = 0; i < 16; i++) {
            System.out.println("\n");
        }
    }
}
