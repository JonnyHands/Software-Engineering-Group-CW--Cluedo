import java.util.ArrayList;
import java.util.List;

import javax.swing.*;


public class Turn {

    private CluedoUI cluedoUI;
    private BoardLayout grid = new BoardLayout();
    private Questions questions = new Questions();
    private Weapons items;
    private Players players;
    private Players dummies;
    private int numofPlayers;
    ArrayList<Card> murderEnvelope = new ArrayList<>(); //Holds the murder envelope contents

    public Turn(CluedoUI ui, Players players, Weapons weapons, Players dummies) {
        this.cluedoUI = ui;
        this.players = players;
        this.items = weapons;
        this.dummies = dummies;
    }

    //Mutator
    public void setTurn(Players players, Weapons weapons, Players dummies) {
        this.players = players;
        this.items = weapons;
        this.dummies = dummies;
        this.numofPlayers = players.getCapacity();
    }

    /**
     * Each player takes turns
     */
    public void turns() {

        String command;               //Text that is entered
        boolean valid;                //Check if action is valid
        Dice dice = new Dice();

        do {
            for (int i = 0; i < players.getCapacity(); i++) {
            	int selectedOption = -1;
            	cluedoUI.displayString("ROTATING PLAYER.......");
        		//Have window be still for 2 seconds
        		try { 
        	          Thread.sleep(2000);
        	    } catch (InterruptedException e) {
        	    	  e.printStackTrace();
        	    }
        		cluedoUI.clearContent(); //Clears info panel for next player
        		
        		do {
      			  //Pops up a JOptionPane and ensures turn is passed
      			  selectedOption = JOptionPane.showConfirmDialog(null,
      					  "Turn handed over to " + players.currPlayer(i) + "?", "TURN", JOptionPane.YES_NO_OPTION); 
      	
      			}while(selectedOption != JOptionPane.YES_NO_OPTION);
      			
        		//Checks if player is alive
                if (!players.getPlayer(i).isAlive()) {
                    continue;
                }

                if (numofPlayers == 1) {
                    finishGame(players.getPlayer(i));
                }

                valid = false;

                //First prompt for first board action
                cluedoUI.displayString("===" + players.currPlayer(i) + "'s TURN===");
                cluedoUI.displayString("Type a command");



                CommandPanel.updateMovesReamining(-1);

                do {
                    command = cluedoUI.getCommand();
                    cluedoUI.displayString(players.currPlayer(i) + ": " + command);

                    if (command.equalsIgnoreCase("roll")) {

                        //Rolls the dice and displays the result on-screen
                        dice.rollDice();
                        CommandPanel.updateCommands();
                        cluedoUI.drawDice(dice.getRoll1(), dice.getRoll2());
                        cluedoUI.displayString(players.currPlayer(i) + " rolled " + (dice.getRoll1()
                                + dice.getRoll2()));
                        cluedoUI.display();
                        try { //The resulting dice roll is on-screen for 2 seconds
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        cluedoUI.drawDice(0, 0); //This hides the dice
                        cluedoUI.display();
                        valid = true;
                    } else if (command.equalsIgnoreCase("notes")) {
                        //Displays all cards apart from murder envelope ones, indicating cards
                        // they own or cards everyone can see
                        players.getPlayer(i).displayNote();
                    } else if (command.equalsIgnoreCase("cheat")) {
                        //Displays the murder envelope cards
                        cheat();
                    } else if (command.equalsIgnoreCase("log")){
                        log();
                    }
                        else if (command.equalsIgnoreCase("help")) {
                        //Displays various commands and an explanation on what they do
                        help();
                    } else {
                        cluedoUI.displayString(
                                "Whoops! Wrong command.\nType 'help' if you're unsure what to do.");
                    }
                } while (!valid); //The first part of their turn ends only when they make a roll

                //After rolling, the player decides where and how to move
                movement(dice.getRoll1() + dice.getRoll2(), i);

                //After all actions
                cluedoUI.displayString(players.currPlayer(i) + "Out of moves! Type a command.");
                
                if (players.getTile(i).getTileType() == 5) {
                    String[] extra;
                    if (players.getTile(i).getRoom() != 10) {
                        extra = Commands.questionCommands;
                    } else {
                        extra = Commands.accuseCommands;
                    }
                   // CommandPanel.updateCommands(extra);
                } else {
                    //CommandPanel.updateCommands(Commands.endCommands);
                }

                boolean questionBarrier = false; //This turns to true if the user has asked a question this turn
                do {
                    valid = false;

                    command = cluedoUI.getCommand();
                    cluedoUI.displayString(players.currPlayer(i) + ": " + command);

                    if (command.equalsIgnoreCase("end turn") && players.getTile(i).getRoom() != 10) {
                        valid = true;
                    } else if (command.equalsIgnoreCase("notes")) {
                        players.getPlayer(i).displayNote();
                    } else if (command.equalsIgnoreCase("cheat")) {
                        cheat();
                    } else if(command.equalsIgnoreCase("log")){
                       log();
                    }  else if (command.equalsIgnoreCase("help")) {
                        help();
                    } else if (players.getTile(i).getTileType() == 5 && players.getTile(i).getRoom()
                            != 10 && !questionBarrier) {
                        if (command.equalsIgnoreCase("question")) {
                           questionBarrier = questions.question(players, i, cluedoUI, this);
                        }
                    } else if (players.getTile(i).getRoom() == 10) {
                        if (command.split(" ")[0].equalsIgnoreCase("accuse")) {
                            Boolean done = accuse(command, players.getPlayer(i));
                            if (done) {
                                valid = true;
                            }
                        }
                    } else {
                        cluedoUI.displayString(
                                "Whoops! Wrong command.\nType 'help' if you're unsure what to do.");
                    }
                    
                    if(!valid) {
                    	cluedoUI.displayString("Type another command");
                    }
                    
                    if(players.getTile(i).getRoom() == 10 && !valid) {
                    	//CommandPanel.updateCommands(Commands.accuseCommands);
                    }else if(players.getTile(i).getRoom() != 10 && !valid && !questionBarrier) {
                    	//CommandPanel.updateCommands(Commands.questionCommands);
                    }else {
                    	//CommandPanel.updateCommands(Commands.endCommands);
                    }
                } while (!valid); //Their turn ends after they type the 'done' command
            }
        } while (true);
    }

    //The following method iterates through the contents of the murder envelope and displays it
    // to the player
    private void cheat() {
        cluedoUI.displayString("======CHEATS======");
        for (Card x : murderEnvelope) {
            cluedoUI.displayString(x.toString());
        }
    }

    //The following method displays info about each command to the player
    private void help() {
        cluedoUI.displayString("======HELP======");
        cluedoUI.displayString("'roll' - to roll the dice and begin your turn."
                + "\nA roll ranges from 1 to 6 and you can move that many spaces on the board."
                + "\n\n'notes' - Type this to inspect your notes."
                + "\nThis lists all players, weapons and rooms,\n'X' mark for cards"
                + "\n'A' mark for cards everybody sees."
                + "\n'V' for cards that were 'seen' "
                + "\n\n'cheat' - Allows you to inspect the-\nmurder envelope."
                + "\n\n'u,r,d,l' - Type one of these to move up, right,-\ndown or left respectively."
                + "\n\n'Passage' - Type to move from one corner-\nof the board using a room to room"
                + " passageway"
                + "\n\n'log' - To show a list of questions asked-\nand people that answered"
                + "\n\n'End turn' - Finishes up your turn or a requirement"
                + "\n\n'question' - To question players about -\nyour suggestion"
                + "\n\n'accuse' - To accuse the murder, with the -\nweapon and place they killed the victum"
                + "\n\n'quit' - This ends the game immediately.");
    }

    //This method provides the user with a log of all questions asked, including who answered them.
    private void log(){
        List<String> log = questions.getLog();
        if(log.size() == 0){
            cluedoUI.displayString("No questions have been asked yet.");
        }else {
            for (int i = 0; i < log.size(); i++) {
                cluedoUI.displayString(i + 1 + ". " + log.get(i));
            }
        }
    }

    //Method used in main to tell the turn class the contents of the murder envelope
    public void setMurderEnvelope(ArrayList<Card> array) {
        murderEnvelope = array;
    }

    /**
     * Allows players to move depending on their dice roll
     */
    public void movement(int dice, int currPlayer) {
        String
                direction;                                    //Contains direction of where the
        // user wants to go
        boolean validDirection;                                //If direction is valid
        int sentinel = 0;                                    //Ensures right warning is displayed
        Tile currTile = players.getTile(currPlayer);


        cluedoUI.displayString(players.currPlayer(currPlayer) + "make your move!");

        //Set of commands a player could possibly use
        //CommandPanel.updateCommands(Commands.movementCommands);
        CommandPanel.updateMovesReamining(dice);

        do {
            validDirection = false; //Reset
            sentinel = 0;  //Reset

            if (players.getTile(currPlayer).getTileType() == 5 || players.getTile(currPlayer).getTileType()
                    == 3 || players.getTile(currPlayer).getTileType() == 4) {
                if (players.getTile(currPlayer).getTileType() == 3) {
                    cluedoUI.displayString("Choose another exit");
                }
                Boolean tookSecretPath = exitRoom(currPlayer,
                        players.getTile(currPlayer).getRoom());
                cluedoUI.display();
                if (!tookSecretPath) {
                    cluedoUI.displayString(players.currPlayer(currPlayer) + " now choose a direction");
                    CommandPanel.updateMovesReamining(dice);
                } else {
                    dice = 0;
                    break;
                }
            }

            direction = cluedoUI.getCommand();
            cluedoUI.displayString(players.currPlayer(currPlayer) + ": " + direction);
            //Catch if array is out of bounds
            //Move character to another tile depending on direction choosen
            try {

                if (direction.equalsIgnoreCase("up")) {
                    currTile = grid.board[players.getTile(currPlayer).getRowPosition() - 1][players.getTile(
                            currPlayer).getColumnPosition()];
                    validDirection = true;

                } else if (direction.equalsIgnoreCase("down")) {
                    currTile = grid.board[players.getTile(currPlayer).getRowPosition() + 1][players.getTile(
                            currPlayer).getColumnPosition()];
                    validDirection = true;

                } else if (direction.equalsIgnoreCase("left")) {
                    currTile = grid.board[players.getTile(currPlayer).getRowPosition()][players.getTile(
                            currPlayer).getColumnPosition() - 1];
                    validDirection = true;
                } else if (direction.equalsIgnoreCase("right")) {
                    currTile = grid.board[players.getTile(currPlayer).getRowPosition()][players.getTile(
                            currPlayer).getColumnPosition() + 1];
                    validDirection = true;
                } else {
                    cluedoUI.displayString("'" + direction + "'" + " is not a valid direction");
                    sentinel = -1;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                cluedoUI.displayString("Invalid direction...[Off the board]");
                sentinel = -1;
            }

            //Ensures no two players are on the same slot
            for (int i = 0; i < players.getCapacity(); i++) {
                if (currTile == players.getTile(i) && validDirection && players.getPlayer(i)
                        != players.getPlayer(currPlayer)) {
                    validDirection = false;
                    cluedoUI.displayString(
                            players.currPlayer(currPlayer) + " cannot move onto an occupied tile");
                    sentinel = -1;
                    break;
                }

            }

            //If the moved tile is a path & the move is valid
            if ((currTile.getTileType() == 1 || currTile.getTileType() == 7) && validDirection) {

                if (players.getTile(currPlayer).getTileType() == 3 && currTile.getTileType() == 7) {
                    cluedoUI.displayString("There's a wall in your way...");
                } else {
                    players.getPlayer(currPlayer).getToken().moveBy(currTile);
                    cluedoUI.display();
                    dice--;
                    CommandPanel.updateMovesReamining(dice);
                }
            } else if ((currTile.getTileType() == 3 && players.getTile(currPlayer).getTileType() != 7)
                    && validDirection) {
                //Enter a room
                players.getPlayer(currPlayer).getToken().moveBy(currTile);
            
                //Move into center of room and no more movement
                roomCenter(currPlayer, players.getTile(currPlayer).getRoom());
                cluedoUI.display();
                dice = 0;
                CommandPanel.updateMovesReamining(dice);
            } 
            else if ((currTile.getTileType() == 8)){
                
            }
            else if (sentinel == 0 && (currTile.getTileType() != 3 || (currTile.getTileType() == 3
                    && players.getTile(currPlayer).getTileType() == 7)
                    || currTile.getTileType() == 7 && players.getTile(currPlayer).getTileType() == 3)) {
                cluedoUI.displayString("There's a wall in your way...");
            }

        } while (dice > 0);

    }

    /**
     * Reposition players onto the centre of the room
     */
    public void roomCenter(int currPlayer, int room) {
        Tile currTile = players.getTile(currPlayer);
        boolean invalidRoom = true;


        //Looks for the centre in which a character is positioned
        for (int i = 0; i < grid.board.length; i++) {
            //Quick escape from nested for loop if the value is found early.
            if (!invalidRoom) {
                break;
            }
            for (int j = 0; j < grid.board[i].length; j++) {
                if (grid.board[i][j].getTileType() == 5 && grid.board[i][j].getRoom() == room
                        && invalidRoom) {
                    currTile = grid.board[i][j];
                    invalidRoom = players.getSameTile(currTile);

                    //Might be in dummies array
                    if (!invalidRoom) {
                        invalidRoom = dummies.getSameTile(currTile);
                    }
                }
            }
        }

        players.getPlayer(currPlayer).getToken().moveBy(currTile);
    }

    /**
     * Gives a list of exits to players if there are exits
     */
    public Boolean exitRoom(int currPlayer, int room) {
        ArrayList<Tile> exits = new ArrayList<Tile>();

        //Searches for possible exits
        for (int i = 0; i < grid.board.length; i++) {
            for (int j = 0; j < grid.board[i].length; j++) {
                if (room == grid.board[i][j].getRoom() && grid.board[i][j].getTileType() == 3) {
                    exits.add(grid.board[i][j]);
                }
            }
        }

        //Displays an array of exits for players
        int numExits = 0;
        String exitChoice;
        cluedoUI.displayString("Available exits for " + players.currPlayer(currPlayer));
        for (Tile t : exits) {
            cluedoUI.displayString(++numExits + ". Exit location" + " " + t.roomTiles());
        }
        if (room == 9 || room == 7 || room == 1 || room == 3) {
            cluedoUI.displayString("Enter 'passage' to take the secret passage!");
        }
        CommandPanel.updateMovesReamining(-2);

        //Player chooses an exit that isn't blocked
        do {
            do {
                exitChoice = cluedoUI.getCommand();
                cluedoUI.displayString(players.currPlayer(currPlayer) + ": " + exitChoice);
                if (exitChoice.equalsIgnoreCase("passage") && (room == 9 || room == 7 || room == 1
                        || room == 3)) {
                    break;
                }
                if (!StartUp.isNum(exitChoice)) {
                    cluedoUI.displayString("'" + exitChoice + "'" + " is not a valid choice");
                }
            } while (!StartUp.isNum(exitChoice));

            if (exitChoice.equalsIgnoreCase("passage") && (room == 9 || room == 7 || room == 1
                    || room == 3)) {
                break;
            }
            if (Integer.parseInt(exitChoice) < 1 || Integer.parseInt(exitChoice) > exits.size()) {
                cluedoUI.displayString(exitChoice + " is not a valid exit choice");
            }
        } while (Integer.parseInt(exitChoice) < 1 || Integer.parseInt(exitChoice) > exits.size());

        if (exitChoice.equalsIgnoreCase("passage") && ((room == 9 || room == 7 || room == 1
                || room == 3))) {
            switch (room) {
                case 9:
                    roomCenter(currPlayer, 1);
                    break;
                case 3:
                    roomCenter(currPlayer, 7);
                    break;
                case 7:
                    roomCenter(currPlayer, 3);
                    break;
                case 1:
                    roomCenter(currPlayer, 9);
                default:
                    break;
            }
            return true;
        } else {
            players.getPlayer(currPlayer).getToken().moveBy(
                    exits.get(Integer.parseInt(exitChoice) - 1));
            return false;
        }

    }

    /**
     * Teleports weapon token, based on player's suggestion
     */
    public void weaponTeleport(String name, int room) {
        Tile currTile = items.get(name).getPosition();        //Tile of current weapon
        boolean invalidRoom = true;

        //Ensures that two weapons don't land onto same tile
        if (currTile.getRoom() != room) {

            //Looks for the position in which a weapon is positioned
            for (int i = 0; i < grid.board.length; i++) {
                //Quick escape from nested for loop if the value is found early.
                if (!invalidRoom) {
                    break;
                }
                for (int j = 0; j < grid.board[i].length; j++) {
                    if (grid.board[i][j].getTileType() == 6 && grid.board[i][j].getRoom() == room
                            && invalidRoom) {
                        currTile = grid.board[i][j];
                        invalidRoom = items.getSameTile(currTile);
                    }
                }
            }
        }
        items.get(name).moveBy(currTile); //Move weapon to new position
    }


    /**
     * Searches given string array for the given string(findMe)
     * @param stringArray an array of strings to search through
     * @param findMe string to find within the array of strings
     * @return true if string is found
     */
    public Boolean findInStringArray(String[] stringArray, String findMe) {
        for (String currentString : stringArray) {
            if (currentString.equalsIgnoreCase(findMe) || currentString.replaceAll("\\s+",
                    "").equalsIgnoreCase(findMe)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Used to end the game by accusing a token of committing a crime using a specific weapon
     * inside a given room
     * Will end the game if the accusation is correct but will remove the player from play if
     * he/she is wrong.
     *
     * @param accuseString user's input string eg : "accuse white dagger hall"
     * @param player       the player that is accusing.
     * @return A_ boolean value of true or false, false if the input is in an invalid format and
 true if the user is either right or wrong.
     */

    public Boolean accuse(String accuseString, Player player) {
        // Divide the input into multiple words by splitting them by ' ' (spaces)
        String[] collection = accuseString.split(" ");

        try {
            // Divides the string into name, weapon and room and handles when the inputs have
            // spaces in them.
            int i = 1;
            String name = collection[i++];
            String weapon, room;
            if (collection[i + 1].equalsIgnoreCase("stick") || collection[i + 1].equalsIgnoreCase(
                    "pipe")) {
                weapon = collection[i++] + collection[i++];
            } else {
                weapon = collection[i++];
            }
            if (collection.length == i + 2) {
                room = collection[i] + collection[i + 1];
            } else {
                room = collection[i];
            }

            // See if the inputs are valid
            if (findInStringArray(Card.suspects, name)) {
                if (findInStringArray(Card.weapons, weapon)) {
                    if (findInStringArray(Card.rooms, room)) {
                        // See if the user's accusation was right
                        if (murderEnvelope.get(0).toString().replaceAll("\\s+",
                                "").equalsIgnoreCase(name)
                                && murderEnvelope.get(1).toString().replaceAll("\\s+",
                                "").equalsIgnoreCase(weapon)
                                && murderEnvelope.get(2).toString().replaceAll("\\s+",
                                "").equalsIgnoreCase(room)) {

                            // Inputs are valid and the user's accusation is correct.
                            finishGame(player);

                        } else {

                            // User got it wrong, end his turns and remove him from the game.
                            player.killPlayer();
                            numofPlayers--;
                            JOptionPane.showMessageDialog(null,
                                    "Oops, that was a wrong suggestion, you're now out of the "
                                            + "game, you are now only allowed to answer "
                                            + "questions!");
                            return true;
                        }
                    } else {
                        cluedoUI.displayString("Could not find the room:" + room
                                + ", are you sure you spelled it correctly?");
                    }
                } else {
                    cluedoUI.displayString("Could not find the weapon:" + weapon
                            + ", are you sure you spelled it correctly?");
                }
            } else {
                cluedoUI.displayString("Could not find the token:" + name
                        + ", are you sure you spelled it correctly?");
            }


        } catch (Exception ex)

        {
            // Invalid format for input
            cluedoUI.displayString(
                    "Unknown input format, make sure you it is in the following format: \n'accuse "
                            + "(name) (weapon) (room) without()!");
        }

        return false;


    }

    public void playerTeleport(String token, int room) {
        int side = 1; //1 = player's array, 2 = dummy's array
        Tile currTile = null;
        boolean invalidRoom = true;

        //Check which array the token is in
        if (players.hasTokenName(token)) {
            side = 1;
            currTile = players.getPlayer(token).getToken().getPosition();
        } else if (dummies.hasTokenName(token)) {
            side = 2;
            currTile = dummies.getPlayer(token).getToken().getPosition();
        }

        //Ensures that two tokens are on the same tile
        if (currTile.getRoom() != room) {

            //Looks for the position in which a weapon is positioned
            for (int i = 0; i < grid.board.length; i++) {
                //Quick escape from nested for loop if the value is found early.
                if (!invalidRoom) {
                    break;
                }
                for (int j = 0; j < grid.board[i].length; j++) {
                    if (grid.board[i][j].getTileType() == 5 && grid.board[i][j].getRoom() == room
                            && invalidRoom) {
                        currTile = grid.board[i][j];
                        invalidRoom = players.getSameTile(currTile);

                        if (!invalidRoom) {
                            invalidRoom = dummies.getSameTile(currTile);
                        }
                    }
                }
            }
        }

        //Move according to what array token is in
        if (side == 1) {
            players.getPlayer(token).getToken().moveBy(currTile);
        } else {
            dummies.getPlayer(token).getToken().moveBy(currTile);
        }
    }

    /**
     * Method that calls the class that handles the frame that is shown when the game has ended.
     * Shows messages signifying the importance of the person's victory and prevents inputs other
     * than quit
     */

    public void finishGame(Player player) {
        // Clear information panel
        cluedoUI.clearContent();

        // Add celebratory contents to it
        cluedoUI.displayString("YOU'VE WON!\nCongrats " + player.getName()+"[" +player.getToken().getTokenName() + "]" + "!\nYOU'VE WON!\nCongrats "
                + player.getName()+"[" +player.getToken().getTokenName() + "]" + "!\nYOU"
                + "'VE WON!\nCongrats " + player.getName()+"[" +player.getToken().getTokenName() + "]" + "!\nYOU'VE WON!\nCongrats "
                + player.getName() + "!\nYOU'VE "
                + "WON!\nCongrats!\n");

        // Call Class that handles the celebration frame.
        new Congrats(player.getName() +"[" +player.getToken().getTokenName() + "]");
        CommandPanel.updateCommands();
        CommandPanel.updateMovesReamining(-1);

        // Ensure user can only exit from here on.
        String input = cluedoUI.getCommand();
        while (!input.equalsIgnoreCase("quit")) {
            input = cluedoUI.getCommand();
        }
        System.exit(0);
    }
}