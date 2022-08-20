import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.*;



public class StartScreen extends JLabel implements ActionListener
{
    JLabel screen;
    JFrame frame;
    JButton play,quit;
    
    
    boolean letsPlay = false; //Becomes true when the play button is pressed

    public void gameStart()
    {
        setLayout(new BorderLayout());

        InputStream stream = getClass().getResourceAsStream("/resources/ClueStart.jpg");
        ImageIcon icon = null;
        try {
            icon = new ImageIcon(ImageIO.read(stream));
        } catch (IOException e) {
            System.out.println("Couldn't find image...." + e);
        }
        
        screen = new JLabel(icon);
        screen.setVisible(true);
        screen.setBackground(Color.BLACK);
        frame = new JFrame("Clue");
        play=new JButton("PLAY");
        quit= new JButton("QUIT");
        JPanel grid = new JPanel(new GridLayout(1,2)); // 1 row, 2 cols
        grid.add(play);
        grid.add(quit);
        frame.add(grid, BorderLayout.PAGE_END);
        frame.add(screen, BorderLayout.PAGE_START);

        play.addActionListener(this);
        quit.addActionListener(this);

        frame.setSize(1024,768);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.BLACK);

    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==play){ //Closes the menu and starts the game up
            letsPlay = true;
            frame.dispose();
        }

        if(e.getSource()==quit) //Ends program if player chooses quit
        {
            System.exit(0);
        }


    }

}