/**
 * @Team MAGA
 * @Author Gajun Young - 16440714
 * @Author Royal Thomas - 16326926
 * @Author Richard  Otroshchenko
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class Board extends JPanel {

    private BufferedImage boardImage;

    //Constructor
    public Board() {
        /**
         * Catch any errors if the image doesn't exist
         */
        try {
            boardImage = ImageIO.read(CluedoUI.class.getResourceAsStream("Board.png"));
        } catch (IOException ex) {
            System.out.println("Couldn't find image...." + ex);
        }

    }

    //Draw the image
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(boardImage, 0, 0, boardImage.getWidth(), boardImage.getHeight(), null);
    }

}
