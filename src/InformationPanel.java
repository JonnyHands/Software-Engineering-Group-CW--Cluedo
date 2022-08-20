import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * An information panel that displays information to users
 *
 * @Team MAGA
 * @Author Gajun Young - 16440714
 * @Author Royal Thomas - 16326926
 * @Author Richard  Otroshchenko - 16353416
 */
public class InformationPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int HEIGHT = 30;
    private static final int WIDTH = 24;

    private JTextArea infoArea = new JTextArea("", HEIGHT, WIDTH);



    //Constructor
    public InformationPanel() {
        JScrollPane scroll = new JScrollPane(infoArea);
        setPreferredSize(new Dimension(300, 600));


        //Ensures text area doesn't expand to the right, but pushes text downwards
        infoArea.setLineWrap(true);
        infoArea.setEditable(false); //Non editable, so players can't text here
        infoArea.setMaximumSize(infoArea.getPreferredSize());
        add(scroll);


    }

    /**
     * A method that updates the content on the information panel that is displayed to everyone
     *
     * @param value - The string of text to be displayed
     */
    public void updateContent(String value) {
        // Add string below current value.
        infoArea.append(value + "\n");

        // Auto scroll down to current position.
        infoArea.setCaretPosition(infoArea.getDocument().getLength());
    }

    /**
     * Method to clear information area
     */

    public void clearContent(){
        infoArea.setText("");
    }



    /**
     * Updates the UI to show the cards that are not dealt to the players
     *
     * @param cards An ArrayList of cards that are not dealt to the players
     * @return Nothing
     */

}