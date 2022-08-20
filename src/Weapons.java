import java.util.Iterator;
import java.util.ArrayList;

/**
 * A weapons class which initializes all the possible types of weapons. It also allows for weapons to be iterable and searched 
 * through their names.
 * 
 * @Team MAGA
 * @Author Gajun Young - 16440714
 * @Author Royal Thomas - 16326926
 * @Author Richard  Otroshchenko - 16353416
 */
public class Weapons implements Iterable<Weapon>, Iterator<Weapon>{

    private final ArrayList<Weapon> weapons = new ArrayList<>();
    private Iterator<Weapon> iterator;
    private BoardLayout grid = new BoardLayout();
    
    public Weapons() {
    }
    
    //Creates a bunch of weapons for the game
    public void createWeapons() { // format [y][x]
        weapons.add(new Weapon("Candle Stick", grid.board[12][22])); 
        weapons.add(new Weapon("Dagger", grid.board[22][12])); 
        weapons.add(new Weapon("Lead Pipe", grid.board[22][22])); 
        weapons.add(new Weapon("Revolver", grid.board[1][22])); 
        weapons.add(new Weapon("Rope", grid.board[8][4])); 
        weapons.add(new Weapon("Spanner", grid.board[14][3])); 
    }
    
    //Iterates to get a specific weapon
    public Weapon get(String name) {
        for (Weapon weapon : weapons) {
            if (weapon.hasName(name)) {
                return weapon;
            }
        }
        return null;
    }
    
  //Iterates to check if two tokens are on the same tile
  	public boolean getSameTile(Tile tile) {
  		for(Weapon weapon: weapons) {
  			if(weapon.hasTile(tile)) {
  				return true;
  			}
  		}
  		return false;
  	}
    
	@Override
	public Iterator<Weapon> iterator() {
		iterator = weapons.iterator();
		return iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Weapon next() {
		 return iterator.next();
	}

}
