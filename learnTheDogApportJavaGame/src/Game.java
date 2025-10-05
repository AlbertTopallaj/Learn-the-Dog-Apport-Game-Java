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
    ArrayList<Item> itemInventory = new ArrayList<>();          // All other items the player is carrying that are not edible.
    ArrayList<Treat> treatInventory = new ArrayList<>();        // Dog treats that the player carries, will be displayed in inventory in game.
    ArrayList<Map> maps = new ArrayList<>();                    // All maps of the game.
    Dog dog = new Dog();
    Scanner scanner = new Scanner(System.in);
    String userInput = "";
    int textSpeed = 60;
    int walkingSpeed = 700;
    int textSpeedMultiplier = 2;

    // objects actively in player use.
    Map currentMap;
    Item heldItem;
    Treat heldTreat;
    NPC currentNPC;
    Boolean hiddenObjectsAreVisible = false;


    // text formatting and templates.
    String oneLine = "\n";
    String twoLine = "\n\n";
    String threeLine = "\n\n\n";
    String indent2 = "  - ";
    String lineSeparator = "】============================================================================================【";

    String storyIntroduction = "-DETTA PROJEKT HAR UTRYMME FÖR FÖRBÄTTRINGAR FRAMÖVER.-" + twoLine + "Introduktion:" + twoLine +
            "Du står med koppel i handen. Din hund är lat och ledsen." +
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
    String hiddenItem = "";

    String inventoryTreats = "";
    String heldTreatName = "";
    String heldTreatDesc = "";
    String visibleTreats = "";
    String hiddenTreats = "";

    String visibleNPCs = "";
    String NPCName = "";
    String NPCMessage = "";

    String topHalf;
    String bottomHalf;
    String HUDLine = twoLine + lineSeparator;
    String reactionToPickingUp = "Din väska är tom...\n";
    String query = "Vad vill du göra?: ";
    String visibleHeader = twoLine + "[SYNLIGA OBJEKT]:" + oneLine;
    String inventoryHeader = "[VÄSKA]:" + oneLine;
    String inspectingHeader = "[UPP PLOCKAT]: ";
    String locationHeader = "Din Nuvarande Plats: ";
    String errorMessage = "";

    String commandsInfo =
            """
                     [HELP/HJÄLP]:
                     HUVUDMÅLET MED SPELET:
                         Lär din hund att göra apport.
                         \s
                     ============================================================================================
                     COMMAND INFO:
                         \s
                         * Plocka Upp [Objekt Namn] - \nLåter spelaren se närmare på ett objekt i scenen och inspektera det. \nFör att senare använda det så måste först använda "Hund" kommandot. \nDet går inte att plocka upp grejer från din väska, de går bara att använda.
                     ____________________________________________________________________________________________
                         \s
                         * Väska - \nVisar en lista av saker som spelaren bär på.
                     ____________________________________________________________________________________________
                         \s
                         * Gå Till [Plats Namn] - \nÄndrar din nuvarande position till det som skrivs in. \nPlatser som man kan besöka går att se i diskriptionerna av den plats man befinner sig på redan.\nDe är markerade med "[]" markörer.\n\n Hunden förlorar happiness när man går.
                     ____________________________________________________________________________________________
                         \s
                         * Tala Med [NPC Namn] - \nLåter en NPC att prata med dig, De kanske hjälper dig att lyssna på vad de har att säga.
                     ____________________________________________________________________________________________
                         \s
                         * Hund - \nDitt husdjur tillåter dig att utföra speciella skickligheter som listas efter du kallar på den.
                     ____________________________________________________________________________________________
                    \s""";


    //Will track the current state of the player in game.
    private enum playerStates {
        START, // Runs initiation of project variables.
        IDLE, // Default state of game.
        HELP, // Help commands access to settings.
        SETTING, // Settings for text display speed.
        INSPECT, // State for when player is inspecting an item or object.
        INVENTORY, // Displays all the items and treats the player has picked up.
        MOVE, // Transitional state that reduces happiness for the dog.
        TALK, // NPC's message is being displayed to the player here.
        DOG, // Interaction menu for the dog. Feed, Search and Fetch.
        SEARCH, // Searches the current location for hidden items.
        FEED_DOG, // Displays stats for when the dog has eaten a treat.
        FETCH,
        EXIT // last step before exiting the game.
    }

    playerStates currentState = playerStates.START;
    playerStates previousState;

    public Boolean isGameRunning() {
        return isRunning;
    }

    public void runGame() // returns 0 = Game Quits
    {
        newPage(); // this method clears the console.
        // Checks the players input and switches state when they're not moving.
        if (currentState != playerStates.MOVE) {
            // Displays text on console for what state player is in.
            //animateText(stateMachine());
            System.out.print(stateMachine());
            if (currentState != playerStates.START) {
                userInput = scanner.nextLine();
            }
            checkUserInput();
        } else {
            // this runs when the player is moving.
            currentState = playerStates.IDLE;
        }
    }


    // Displays relevant info and feedback to the player in the console.
    public String stateMachine() {
        // the string that gets returned and displayed in the console.
        String textToBeDisplayed = "";
        switch (currentState) {
            //INITIATES START VARIABLES HERE.
            case playerStates.START:
                System.out.println("TEACH THE DOG FETCH THE GAME!\n\n");

                maps = new ArrayList<>(Arrays.asList(Map.locations));

                //sets the current player location.
                currentMap = maps.getFirst();
                //Updates a bunch of String variables that prepares context to be displayed.
                showAllItemsInCurrentMap(hiddenObjectsAreVisible);
                showAllTreatsInCurrentMap(hiddenObjectsAreVisible);
                showAllNPCsInCurrentMap();
                //Enters IDLE state.
                animateText(storyIntroduction);
                storyIntroduction = "";
                currentState = playerStates.IDLE;

                //break;
            case playerStates.IDLE:
                showAllItemsInCurrentMap(hiddenObjectsAreVisible);
                showAllTreatsInCurrentMap(hiddenObjectsAreVisible);
                showAllNPCsInCurrentMap();
                showAllItemsInInventory();
                showAllTreatsInInventory();
                updateTitles();

                commandOptions = """
                        [SKRIV KOMMANDON]:
                        > Help
                        > Plocka upp [Objekt Namn]
                        > Väska
                        > Gå till [Plats Namn]
                        > Tala Med [NPC Namn]
                        > Hund""";
                query = "Vad vill du göra?: ";
                topHalf =  twoLine + locationHeader + placeName + placeDescription + visibleHeader + visibleItems + hiddenItem + visibleTreats + hiddenTreats + visibleNPCs + HUDLine;
                bottomHalf = threeLine + commandOptions + twoLine;

                //compiling UI
                textToBeDisplayed = topHalf + bottomHalf + errorMessage + query;
                break;

            case playerStates.HELP:
                commandOptions = """
                        [SKRIV KOMMANDON]:
                        > Inställningar
                        > Tillbaka""";

                bottomHalf = threeLine + commandOptions + twoLine + twoLine;
                //compiling UI
                textToBeDisplayed = commandsInfo + HUDLine + bottomHalf + errorMessage + query;
                break;

            case playerStates.SETTING:
                System.out.println("INSTÄLLNINGAR:");
                textSpeed = askForTextSpeed();
                walkingSpeed = askForWalkSpeed();
                currentState = playerStates.IDLE;
                break;

            case playerStates.INSPECT:
                if (heldItem != null) {
                    if (heldItem.canPickUp()) {
                        commandOptions = """
                                [SKRIV KOMMANDON]:
                                Lägg i din väska?
                                Ja eller Nej?""";
                        query = "Gör ditt val: ";
                    } else {
                        commandOptions = """
                                [SKRIV KOMMANDON]:
                                > Tillbaka""";
                        reactionToPickingUp = "";
                        query = "Vad vill du göra?: ";
                    }
                } else if (heldTreat != null) {
                    commandOptions = """
                            [SKRIV KOMMANDON]:
                            Lägg i ditt väska?
                            Ja eller Nej?""";
                    query = "Gör ditt val: ";
                }
                //compiling UI
                textToBeDisplayed = inspectingHeader + heldItemName + heldTreatName + oneLine + indent2 + heldItemDesc + heldTreatDesc + twoLine + HUDLine + twoLine + commandOptions + threeLine + twoLine + errorMessage + query;
                break;

            case playerStates.INVENTORY:
                showAllItemsInCurrentMap(hiddenObjectsAreVisible);
                showAllTreatsInCurrentMap(hiddenObjectsAreVisible);
                showAllItemsInInventory();
                showAllTreatsInInventory();
                // changes command options displayed depending on which inventory is empty.
                if (itemInventory.isEmpty() && (treatInventory.isEmpty())) // if both are filled.
                {
                    commandOptions = """
                            [SKRIV KOMMANDON]:
                            > Plocka Upp [Objekt Namn]
                            > Tillbaka""";
                } else if (!treatInventory.isEmpty()) {   // if only treatInventory.
                    commandOptions = """
                            [SKRIV KOMMANDON]:
                            > Plocka Upp [Objekt Namn]
                            > Mata Hund [Objekt Namn]
                            > Tillbaka""";
                } else{
                    commandOptions = """
                            [SKRIV KOMMANDON]:
                            > Plocka Upp [Objekt Namn]
                            > Tillbaka""";
                }

                query = "Vad vill du göra?: ";
                topHalf = visibleHeader + visibleItems + visibleTreats + HUDLine + twoLine + reactionToPickingUp + inventoryHeader + inventoryItems + inventoryTreats + HUDLine;
                bottomHalf = threeLine + commandOptions + twoLine + errorMessage + query;
                textToBeDisplayed = topHalf + bottomHalf;
                break;
            case playerStates.TALK:
                NPCTalking();
                commandOptions = """
                        [SKRIV KOMMANDON]:
                        > Tillbaka""";
                query = "Vad vill du göra?: ";
                bottomHalf = threeLine + commandOptions + twoLine + errorMessage + query;
                textToBeDisplayed = bottomHalf;
                currentState = playerStates.IDLE;
                break;

            case playerStates.DOG:
                showAllItemsInCurrentMap(hiddenObjectsAreVisible);
                showAllTreatsInCurrentMap(hiddenObjectsAreVisible);
                commandOptions = """
                        [SKRIV KOMMANDON]:
                        > Mata Hund [Objekt Namn]
                        > Sök
                        > Apport
                        > Tillbaka""";
                topHalf = "[HUND]: " + "Glädje Nivå: " + dog.getHappinessPercentage() + "%";
                bottomHalf = threeLine + commandOptions + twoLine + errorMessage + query;
                textToBeDisplayed = topHalf + threeLine + "Hund -Baoww bawW!" + twoLine + "[TIDIGARE GÖMDA FYND]: " + twoLine + hiddenItem + hiddenTreats + HUDLine + twoLine + inventoryHeader + inventoryItems + inventoryTreats + HUDLine + bottomHalf;
                break;
            case playerStates.SEARCH:
                commandOptions = """
                        [SKRIV KOMMANDON]:
                        > Tillbaka""";
                animateText("[HUND LETAR]: " + threeLine + "Din hund undersöker området och letar efter gömda items och treats..." + threeLine);
                waitForSeconds(1200);
                bottomHalf = threeLine + commandOptions + twoLine + errorMessage + query;
                textToBeDisplayed = bottomHalf;
                break;
            case playerStates.FEED_DOG:
                commandOptions = """
                        [SKRIV KOMMANDON]:
                        > Tillbaka""";

                animateText("Chow.. Chow..." + twoLine + twoLine);
                animateText("[HUND]: " + "Glädje Nivå: " + dog.getHappinessPercentage() + "%" + threeLine);
                animateText("Hunden åt " + heldTreat.getName()+" och blev " + heldTreat.getEffect() + " mer gladare!" + twoLine);
                waitForSeconds(800);
                bottomHalf = threeLine + commandOptions + twoLine + errorMessage + query;
                textToBeDisplayed = bottomHalf;
                break;
            case playerStates.FETCH:
                animateText("Du kallar på kommandot apport!" + twoLine + twoLine);
                //animateText("Chow.. Chow..." + twoLine + twoLine);
                bottomHalf = threeLine + commandOptions + twoLine + errorMessage + query;
                textToBeDisplayed = bottomHalf;
                break;
            case playerStates.EXIT:
                animateText("Din hund lärde sig apport" + twoLine + twoLine);
                waitForSeconds(800);
                animateText("Spelet är över. \n Bra jobbat!" + twoLine + twoLine);
                waitForSeconds(2000);
                isRunning = false;
                break;
        }
        // updates all the hud elements that need to update when player moves or interacts.
        showAllItemsInInventory();
        showAllTreatsInCurrentMap(hiddenObjectsAreVisible);
        showAllNPCsInCurrentMap();
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
                previousState = currentState;
                if (userInput.equalsIgnoreCase("hjälp") || userInput.equalsIgnoreCase("help") ) {
                    //System.out.println("Played asked for help");
                    currentState = playerStates.HELP;
                    errorMessage = "";
                    break;
                } else if (userInput.equalsIgnoreCase("väska")) {
                    currentState = playerStates.INVENTORY;
                    errorMessage = "";
                    break;
                } else if (userInput.toLowerCase().contains("plocka upp ")) {
                    inspectItem();
                    break;
                } else if (userInput.toLowerCase().contains("gå till ")) {
                    moveToNewMap();
                    break;
                } else if (userInput.toLowerCase().contains("tala med ")) {
                    receiveMessagePack();
                    errorMessage = "";
                    break;

                } else if (userInput.equalsIgnoreCase("hund")) {
                    currentState = playerStates.DOG;
                    errorMessage = "";
                    break;
                }
                errorMessage = "\n!Skriv in rätt kommando som visas av alternativen.\n";
                break;

            case playerStates.HELP:
                if (userInput.equalsIgnoreCase("inställningar")) {
                    currentState = playerStates.SETTING;
                    errorMessage = "";
                    break;
                } else if (userInput.equalsIgnoreCase("återvänd") ||
                        userInput.equalsIgnoreCase("returnera") ||
                        userInput.equalsIgnoreCase("bak") ||
                        userInput.equalsIgnoreCase("tillbaka")) {
                    currentState = previousState;
                    errorMessage = "";
                    break;
                }
                errorMessage = "\n!Skriv in rätt kommando som visas av alternativen.\n";
                break;

            case playerStates.INSPECT:
                if (heldItem == null && heldTreat == null) {
                    //Should not execute
                    System.out.println("ERROR: No item is being held...");
                    break;
                }
                pickFromItemOrTreat(previousState);
                break;

            case playerStates.INVENTORY:
                previousState = currentState;
                if (userInput.equalsIgnoreCase("återvänd") ||
                        userInput.equalsIgnoreCase("returnera") ||
                        userInput.equalsIgnoreCase("bak") ||
                        userInput.equalsIgnoreCase("tillbaka")) {
                    reactionToPickingUp = "";
                    currentState = playerStates.IDLE;
                    errorMessage = "";
                    break;
                } else if (userInput.toLowerCase().contains("plocka upp ")) {
                    //if what comes after "inspect " matches any of the items on the map.
                    inspectItem();
                    break;
                } else if (userInput.toLowerCase().contains("mata hund ")) {
                    feedDog();
                    errorMessage = "";
                    break;
                }
                errorMessage = "\n!Skriv in rätt kommando som visas av alternativen.\n";
            case playerStates.MOVE:
                errorMessage = "";
                break;

            case playerStates.SETTING:
                errorMessage = "";
                break;

            case playerStates.DOG:
                previousState = currentState;
                if (userInput.equalsIgnoreCase("återvänd") ||
                        userInput.equalsIgnoreCase("returnera") ||
                        userInput.equalsIgnoreCase("bak") ||
                        userInput.equalsIgnoreCase("tillbaka")) {
                    currentState = playerStates.IDLE;
                    errorMessage = "";
                    break;
                } else if (userInput.toLowerCase().contains("mata hund ")) {
                    feedDog();
                    currentState = previousState;
                    errorMessage = "";
                    break;
                } else if (userInput.toLowerCase().contains("apport")) {
                    currentState = playerStates.FETCH;
                    errorMessage = "";
                    break;
                } else if (userInput.toLowerCase().contains("sök")) {
                    dogSearch();
                    errorMessage = "";
                    break;
                }
                errorMessage = "\n!Skriv in rätt kommando som visas av alternativen.\n";
                break;

            case playerStates.SEARCH, playerStates.FEED_DOG:
                if (userInput.equalsIgnoreCase("återvänd") ||
                        userInput.equalsIgnoreCase("returnera") ||
                        userInput.equalsIgnoreCase("bak") ||
                        userInput.equalsIgnoreCase("tillbaka")) {
                    currentState = previousState;
                    errorMessage = "";
                    break;
                }
                errorMessage = "\n!Skriv in rätt kommando som visas av alternativen.\n";
                break;

            case playerStates.FETCH:
                Item pinneCheck = searchAndHoldItem("pinne",itemInventory);
                if(pinneCheck != null){
                    if(dog.winThreshold()){
                        currentState = playerStates.EXIT;
                        errorMessage = "";
                        break;
                    }
                }

                if (userInput.equalsIgnoreCase("återvänd") ||
                        userInput.equalsIgnoreCase("returnera") ||
                        userInput.equalsIgnoreCase("bak") ||
                        userInput.equalsIgnoreCase("tillbaka")) {
                    currentState = previousState;
                    errorMessage = "";
                    break;
                }
                errorMessage = "\n!Skriv in rätt kommando som visas av alternativen\n";
        }
    }

    private void dogSearch(){
        ArrayList<Item> itemsSearched;
        ArrayList<Treat> treatsSearched;

        itemsSearched = dog.searchForHiddenItems(currentMap.getItems());
        treatsSearched = dog.searchForHiddenTreats(currentMap.getTreats());
        if ((itemsSearched == null) && (treatsSearched == null)) {
            //NO ITEMS OR TREATS WERE FOUND.
            System.out.println("\n\nNO HIDDEN ITEMS WERE FOUND!\n\n");
            hiddenObjectsAreVisible = false;
            currentState = playerStates.DOG;
        } else {
            hiddenObjectsAreVisible = true;
            currentState = playerStates.SEARCH;
        }
    }

    private void feedDog() {
        Item itemSearched = searchAndHoldItem(userInput.substring(10), itemInventory);
        Treat treatSearched = searchAndHoldTreat(userInput.substring(10), treatInventory);
        if (itemSearched == null && treatSearched == null) {
            heldItem = null;
            heldTreat = null;
            errorMessage = "\nDet där verkar inte finnas\n";
            return;
        }
        if (itemSearched != null) {
            heldItem = null;
            newPage();
            errorMessage = "\nDu kan tyvärr inte mata hunden det där.\n";
        } else {
            heldTreat = treatSearched;
            treatInventory.remove(treatSearched);
            dog.addHappiness(heldTreat);
            currentState = playerStates.FEED_DOG;
            errorMessage = "";
        }
    }

    private void pickFromItemOrTreat(playerStates previousState) {
        if (heldItem != null) { // IF AN ITEM IS INSPECTED
            if (heldItem.canPickUp()) {
                if (userInput.toLowerCase().contains("nej")) {
                    currentState = previousState;
                    errorMessage = "";
                    return;
                }
                if (userInput.toLowerCase().contains("ja")) {
                    pickUpItemFromMap();
                    currentState = playerStates.INVENTORY;
                    errorMessage = "";
                    return;
                }
            } else {
                if (userInput.equalsIgnoreCase("återvänd") ||
                        userInput.equalsIgnoreCase("returnera") ||
                        userInput.equalsIgnoreCase("bak") ||
                        userInput.equalsIgnoreCase("tillbaka")) {
                    currentState = previousState;
                    heldItem = null; //resets held item.
                    errorMessage = "";
                    return;
                }
            }
        } else { // IF A TREAT IS BEING INSPECTED.
            if (userInput.toLowerCase().contains("nej")) {
                currentState = previousState;
                errorMessage = "";
                return;
            }
            if (userInput.toLowerCase().contains("ja")) {
                pickUpTreatFromMap();
                currentState = playerStates.INVENTORY;
                errorMessage = "";
                return;
            }
        }
        errorMessage = "\nDu måste välja från alternativet Ja eller Nej..\n";
    }

    private void receiveMessagePack() {
        NPC npcSearched = searchAndTalkToNPC(userInput.substring(9), currentMap.getNPCs());
        if (npcSearched == null) {
            currentNPC = null;
        } else {
            currentNPC = npcSearched;
            currentState = playerStates.TALK;
        }
    }

    private void moveToNewMap() {
        Map mapSearched = searchMapInMapList(userInput.substring(8));
        if (mapSearched != null) {
            hiddenObjectsAreVisible = false;
            walkingAnimation();
            currentState = playerStates.MOVE;
            errorMessage = "";
            return;
        }
        errorMessage = "\nhmm.. den plats ni skrev in finns inte tillgängligt.\n";
    }

    private void inspectItem() {
        //if what comes after "inspect " matches any of the items on the map.
        Item itemSearched = searchAndHoldItem(userInput.substring(11), currentMap.getItems());
        Treat treatSearched = searchAndHoldTreat(userInput.substring(11), currentMap.getTreats());
        if (itemSearched == null && treatSearched == null) {
            heldItem = null;
            heldTreat = null;
            errorMessage = "\nDet du söker efter finns inte..\nSe till att det du försöker att plocka upp finns på din nuvarande plats.\n";
            return;
        }
        if (itemSearched != null) {
            heldItem = itemSearched;
            currentState = playerStates.INSPECT;
            errorMessage = "";
        } else if (treatSearched != null){
            heldTreat = treatSearched;
            currentState = playerStates.INSPECT;
            errorMessage = "";
        }
        else{
            heldItem = null;
            heldTreat = null;
            errorMessage = "\nDet du söker efter finns inte..\nSe till att det du försöker att plocka upp finns på din nuvarande plats.\n";
        }
    }

    private void updateTitles() {
        placeName = "[" + currentMap.getName() + "]";
        placeDescription = twoLine + indent2 + currentMap.getDescription();
    }

    private void pickUpItemFromMap() {
        for (int j = 0; j < maps.size(); j++) {
            if (!currentMap.getName().equalsIgnoreCase(maps.get(j).getName().toLowerCase())) {
                continue;
            }
            for (int i = 0; i < currentMap.getItems().size(); i++) {
                if (!heldItem.getName().equalsIgnoreCase(currentMap.getItems().get(i).getName())) {
                    continue;
                }
                reactionToPickingUp = "\nDu plockade upp " + heldItemName + "!\n\n";
                itemInventory.add(heldItem);
                heldItem = null; //resets held item.
                currentMap.getItems().remove(i);
                maps.set(j, currentMap);
                showAllItemsInCurrentMap(hiddenObjectsAreVisible);
                showAllItemsInInventory();
                updateTitles();
            }
        }
    }

    private void pickUpTreatFromMap() {
        for (int j = 0; j < maps.size(); j++) {
            if (!currentMap.getName().equalsIgnoreCase(maps.get(j).getName().toLowerCase())) {
                continue;
            }
            for (int i = 0; i < currentMap.getTreats().size(); i++) {
                if (!heldTreat.getName().equalsIgnoreCase(currentMap.getTreats().get(i).getName())) {
                    continue;
                }
                reactionToPickingUp = "\nDu plockade upp " + heldTreatName + "!\n\n";
                treatInventory.add(heldTreat);
                heldTreat = null; //resets held item.
                currentMap.getTreats().remove(i);
                maps.set(j, currentMap);
                showAllTreatsInCurrentMap(hiddenObjectsAreVisible);
                showAllTreatsInInventory();
                updateTitles();
            }
        }
    }

    private Map searchMapInMapList(String searchTerm) {
        for (Map map : maps) {
            if (searchTerm.equalsIgnoreCase(map.getName())) {
                currentMap = map;
                showAllItemsInCurrentMap(hiddenObjectsAreVisible);
                showAllTreatsInCurrentMap(hiddenObjectsAreVisible);
                showAllNPCsInCurrentMap();
                return map;
            }
        }
        return null;
    }

    private Item searchAndHoldItem(String searchTerm, ArrayList<Item> listOfItems) {
        for (Item item : listOfItems) {
            if (searchTerm.equalsIgnoreCase(item.getName())) {
                //heldItem = item;
                heldItemName = item.getName();
                heldItemDesc = item.getDescription();
                heldTreatName = "";
                heldTreatDesc = "";
                return item;
            }
        }
        return null;
    }

    private Treat searchAndHoldTreat(String searchTerm, ArrayList<Treat> listOfTreats) {
        for (Treat treat : listOfTreats) {
            if (searchTerm.equalsIgnoreCase(treat.getName())) {
                //heldTreat = treat;
                heldTreatName = treat.getName();
                heldTreatDesc = treat.getDescription() +" [Effekt: "+ treat.getEffect() + "]";
                heldItemName = "";
                heldItemDesc = "";
                return treat;
            }
        }
        return null;
    }

    private NPC searchAndTalkToNPC(String searchTerm, ArrayList<NPC> listOfNPCs) {
        for (NPC npc : listOfNPCs) {
            if (searchTerm.equalsIgnoreCase(npc.getName())) {
                //currentNPC = npc;
                NPCName = npc.getName();
                NPCMessage = npc.getMessage();
                heldItemName = "";
                heldItemDesc = "";
                heldItemName = "";
                heldItemDesc = "";
                return npc;
            }
        }
        return null;
    }

    private void showAllItemsInInventory() {
        inventoryItems = "";
        for (int i = 0; i < itemInventory.size(); i++) {
            inventoryItems += "[SAK]" + indent2 + itemInventory.get(i).getName() + ",\n";
        }
    }

    private void showAllTreatsInInventory() {
        inventoryTreats = "";
        for (int i = 0; i < treatInventory.size(); i++) {
            inventoryTreats += "[MAT]" + indent2 + treatInventory.get(i).getName() + ",\n";
        }
    }

    private void showAllItemsInCurrentMap(boolean showHidden) {
        visibleItems = "";
        hiddenItem = "";
        if (!showHidden) {
            for (int i = 0; i < currentMap.getItems().size(); i++) {
                if (currentMap.getItems().get(i).isHidden()) {
                    hiddenItem += "[ETT GÖMT FÖREMÅL]" + ",\n";
                } else {
                    visibleItems += "[SAK]" + indent2 + currentMap.getItems().get(i).getName() + ",\n";
                }
            }
        } else {
            for (int i = 0; i < currentMap.getItems().size(); i++) {
                if (currentMap.getItems().get(i).isHidden()) {
                    hiddenItem += "[FUNNEN SAK]" + indent2 + currentMap.getItems().get(i).getName() + ",\n";
                } else {
                    visibleItems += "[SAK]" + indent2 + currentMap.getItems().get(i).getName() + ",\n";
                }
            }
        }
    }

    private void showAllTreatsInCurrentMap(boolean showHidden) {
        visibleTreats = "";
        hiddenTreats = "";
        if (!showHidden) {
            for (int i = 0; i < currentMap.getTreats().size(); i++) {
                if (currentMap.getTreats().get(i).getIsHidden()) {
                    hiddenTreats += "[ETT GÖMT FÖREMÅL]" + ",\n";
                } else {
                    visibleTreats += "[MAT]" + indent2 + currentMap.getTreats().get(i).getName() + ",\n";
                }
            }
        } else {
            for (int i = 0; i < currentMap.getTreats().size(); i++) {
                if (currentMap.getTreats().get(i).isHidden) {
                    hiddenTreats += "[FUNNEN SAK]" + indent2 + currentMap.getTreats().get(i).getName() + ",\n";
                } else {
                    visibleTreats += "[MAT]" + indent2 + currentMap.getTreats().get(i).getName() + ",\n";
                }
            }
        }
    }

    private void showAllNPCsInCurrentMap() {
        visibleNPCs = "";
        if (currentMap.getNPCs().isEmpty()) {
            return;
        }
        for (int i = 0; i < currentMap.getNPCs().size(); i++) {
            visibleNPCs += "[NPC]" + indent2 + currentMap.getNPCs().get(i).getName() + ",\n";
        }
    }

    private void walkingAnimation() {
        String walkin = "Går till plats ";
        dog.removeHappiness(1);
        for (int j = 0; j <= 2; j++) {
            for (int i = 0; i <= 3; i++) {
                waitForSeconds(walkingSpeed);
                walkin += ".";
                newPage();
                System.out.println(walkin);
            }
            walkin = "Går till plats ";
            newPage();
            System.out.println(walkin);
        }
    }

    private void NPCTalking() {
        char letter;
        String message = "    - " + NPCMessage;
        System.out.println(NPCName + "\n\n");
        for (int i = 0; i < message.length(); i++) {
            if (i <= 6) {
                letter = message.charAt(i);
                System.out.print(letter);
            } else {
                letter = message.charAt(i);
                if (letter == '.') {
                    waitForSeconds(700);
                } else {
                    waitForSeconds(textSpeed);
                }
                System.out.print(letter);
            }
        }
    }

    private void animateText(String text) {
        char letter;
        for (int i = 0; i < text.length(); i++) {
            letter = text.charAt(i);
            if (i % textSpeedMultiplier == 0) {
                if (letter == '.') {
                    waitForSeconds(textSpeed + 500);
                } else if (letter == ',') {
                    waitForSeconds(textSpeed + 300);
                } else {
                    waitForSeconds(textSpeed);
                }
            }
            System.out.print(letter);
        }
    }

    private void waitForSeconds(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void newPage() {
        for (int i = 0; i < 16; i++) {
            System.out.println("\n");
        }
    }

    private int askForTextSpeed() {
        int speed;
        System.out.print("Välj text hastighet(20 - 100), vi rekommenderar 45 :");
        while (!scanner.hasNextInt()) {
            System.out.println("!!! Var snäll och välj från alternativet ovan.");
            scanner.nextLine();
        }
        speed = scanner.nextInt();
        scanner.nextLine();
        if (speed > 100) {
            System.out.println("!!! För långsamt! \nVälj en annan hastighet.");
            askForTextSpeed();
        } else if (speed < 20) {
            System.out.println("!!! Sakta ner lite! \nVälj en annan hastighet.");
            askForTextSpeed();
        }
        return speed;
    }

    private int askForWalkSpeed() {
        int speed;
        System.out.print("\nVälj din gång hastighet(50 - 800), vi rekommenderar 400 :");
        while (!scanner.hasNextInt()) {
            System.out.println("!!! Var snäll och välj från alternativet ovan.");
            scanner.nextLine();
        }
        speed = scanner.nextInt();
        if (speed > 800) {
            System.out.println("!!! Det där kommer att ta för lång tid \nVälj en annan hastighet.");
            askForWalkSpeed();
        } else if (speed < 50) {
            askForWalkSpeed();
        }
        return speed;
    }
}
