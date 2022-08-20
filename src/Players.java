import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * A players class that represents an arraylist of players
 * 
 * @Team MAGA
 * @Author Gajun Young - 16440714
 * @Author Royal Thomas - 16326926
 * @Author Richard  Otroshchenko - 16353416
 */
public class Players implements Iterable<Player>, Iterator<Player> {

	private static final int DEFAULT_CAPACITY = 6; //Maximum amount of players
	private int capacity; 						   //Number of players in the game
	private final ArrayList<Player> players;       
	private Iterator<Player> iterator;
	private BoardLayout board = new BoardLayout();        //Grid to set players on locations

	//Default constructor
	public Players() {
		players = new ArrayList<Player>(DEFAULT_CAPACITY);
	}
	
	//Constructor
	public Players(int capacity) {
		this.capacity = capacity;
		players = new ArrayList<Player>(capacity);
	}
	
	
	//Creating a list of players [Fixed starting location]
	public void createPlayers(String name, int choice) {
		players.add(new Player(name, choice));
	}
	
	//Create tokens for players
	public void createTokens(int curr) { // format [y][x]
		int choice = players.get(curr).getChoice();
		
		switch(choice) {
		case 1: //PLUM
			players.get(curr).setToken(new Token("Plum", Color.magenta, board.board[5][0]));
            players.get(curr).setImagePath("resources/Profiler/Plum.png");
			break;
		case 2: //WHITE
			players.get(curr).setToken(new Token("White", Color.white, board.board[24][14]));
            players.get(curr).setImagePath("resources/Profiler/White.png");
			break;
		case 3:	//SCARLET
			players.get(curr).setToken(new Token("Scarlet", Color.red, board.board[0][16]));
            players.get(curr).setImagePath("resources/Profiler/Scarlet.png");
			break;
		case 4: //GREEN
			players.get(curr).setToken(new Token("Green", Color.green, board.board[24][9]));
            players.get(curr).setImagePath("resources/Profiler/Green.png");
			break;
		case 5: //MUSTARD
			players.get(curr).setToken(new Token("Mustard", Color.yellow, board.board[7][23]));
            players.get(curr).setImagePath("resources/Profiler/Mustard.png");
			break;
		case 6: //PEACOCK
			players.get(curr).setToken(new Token("Peacock", Color.blue, board.board[18][0]));
            players.get(curr).setImagePath("resources/Profiler/Peacock.png");
			break;
		default:
			break;
		}
	}
	 
	//Check if array has the token name
	public boolean hasTokenName(String name) {
		for(Player Player: players) {
			if(Player.getToken().hasName(name)) {
				return true;
			}
		}
		
		return false;
	}
	
	//Iterates to check if two tokens are on the same tile
	public boolean getSameTile(Tile tile) {
		for(Player player: players) {
			if(player.hasTile(tile)) {
				return true;
			}
		}
		return false;
	}
	
	//Return player with the token
	public Player getPlayer(String token) {
		for(Player player: players) {
			if(player.getToken().hasName(token)) {
				return player;
			}
		}
		
		return null;
	}
	
	//Iterates to get a specific Player
	public Player getName(String name) {
		for (Player Player : players) {
			if (Player.hasName(name)) {
				return Player;
			}
		}
		return null;
	}
	
	//Gets the number of people in the array list
	public int getCapacity() {
		return capacity;
	}
	
	//Returns the player at the index
	public Player getPlayer(int index) {
		return players.get(index);
	}
	
	//Returns the Tile of the token/player
	public Tile getTile(int index) {
		return players.get(index).getToken().getPosition();
	}
	
	//Returns the token of the player.
	public String getTokenName(int index) {
		return players.get(index).getToken().getTokenName();
	}
	
	//Remove a player from the array
	public void remove(int index) {
		players.remove(index);
	}
	
	//Start of string containing the player token and the player's name
	public String currPlayer(int index) {
		return players.get(index).getName()  + " [" + players.get(index).getToken().getTokenName() + "] ";
	}
	
	//Add Player to the start of the array, move everyone else back in array
	public void addFirst(int index, Player temp) {
		players.remove(index);
		players.add(0, temp);
	}
	    
	@Override
	public Iterator<Player> iterator() {
		iterator = players.iterator();
		return iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Player next() {
		return iterator.next();
	}
}