import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.*;

/**
 * A panel that displays the Game Board
 * 
 * @Team MAGA
 * @Author Gajun Young - 16440714
 * @Author Royal Thomas - 16326926
 * @Author Richard  Otroshchenko - 16353416
 */
public class BoardPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private BufferedImage boardImage, die, die2; //Image of the board
	private Weapons weapons;
	private Players players;
	private Players dummies;
	
	//private Tokens tokens = new Tokens(); (test)
	
	public BoardPanel(Players players, Weapons weapons, Players dummies){	
		this.weapons = weapons;
		this.players = players;
		this.dummies = dummies;
		
		//Check if image input is available [Exceptions]
		 try {
	            boardImage = ImageIO.read(this.getClass().getResource("resources/Board.png"));
	     }catch (IOException ex) {
	            System.out.println("Couldn't find image...." + ex);
	     }
		 
		 //Set the size of the panel
		 setPreferredSize(new Dimension(boardImage.getHeight(), boardImage.getWidth()));
	}
	
	//Sets the board with different players & weapons
	public void set(Players players, Weapons weapons, Players dummies) {
		this.weapons = weapons;
		this.players = players;
		this.dummies = dummies;
	}
	
	//This takes the roll value as input, and draws the correct die image accordingly
	public void drawDice(int roll1, int roll2){
		try {
			if(roll1 == 0){ //Used to hide the die image
			    die = null;
		    }else {
				die = ImageIO.read(this.getClass().getResource("resources/dice" + roll1 + ".jpg"));
			}
			if(roll2 == 0){ //Used to hide the die image
				die2 = null;
			}else {
				die2 = ImageIO.read(this.getClass().getResource("resources/dice" + roll2 + ".jpg"));
			}
		}catch(IOException ex) {
			System.out.println("Couldn't find image...." + ex);
		}
	}

	@Override
	/**
	 * A paint component to draw tokens onto the board
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 =(Graphics2D) g;
        g2.drawImage(boardImage, 0, 0, boardImage.getHeight(), boardImage.getWidth(), this);
      /*  TileGrid grid = new TileGrid();
        grid.drawGrid(g); //(test)
       */ 
        for(Weapon weapon: weapons) {
        	weapon.drawWeapon(g2);
        }
        
        for(Player player: players) {
        	player.getToken().drawToken(g2);
        }
        
        //Draws dummies
        for(Player dummy: dummies) {
        	dummy.getToken().drawToken(g2);
        }
        
		if(die != null){ //This is used to help ensure the die is only on-screen when needed.
			g2.drawImage(die, 290, 255, die.getHeight()-90, die.getWidth()-90, this);
			g2.drawImage(die2, 290, 335, die.getHeight()-90, die.getWidth()-90, this);
		}
		
      /* (test)  
        for(Token tok: tokens) {
        	tok.drawToken(g2);
        }
       */
	}

}