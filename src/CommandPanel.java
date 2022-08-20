
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

/**
 * A panel that allows users to type information which interacts with the board
 *
 * @Team MAGA
 * @Author Gajun Young - 16440714
 * @Author Royal Thomas - 16326926
 * @Author Richard Otroshchenko - 16353416
 */
public class CommandPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int SIZE = 30;
    private static final int FONT_SIZE = 14;

    private final LinkedList<String> commandBuffer = new LinkedList<>();
    private static JLabel movesRemaining = new JLabel("");
    private static JButton button1;

    public CommandPanel() {

        JPanel buttonPanel = new JPanel();
        JPanel menuPanel = new JPanel();
        JPanel containerPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(600, 250));
        buttonPanel.setPreferredSize(new Dimension(300, 250));
        containerPanel.setPreferredSize(new Dimension(1000, 500));
        containerPanel.add(menuPanel);
        containerPanel.add(buttonPanel);
        buttonPanel.setBackground(Color.BLACK);
        menuPanel.setBackground(Color.BLACK);
        containerPanel.setBackground(Color.BLACK);
        //add(inputPanel);
        add(containerPanel);
        //Beautification
        setPreferredSize(new Dimension(2000, 400));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        int x = 2;
        int y = 5;
        menuPanel.setLayout(new GridLayout(1, 7));
        buttonPanel.setLayout(new GridLayout(3, 3));
        JButton buttonQuit = new JButton("Quit");
        JButton buttonRoll = new JButton("Roll");
        JButton buttonLog = new JButton("Log");
        JButton buttonHelp = new JButton("Help");
        JButton buttonNotes = new JButton("Notes");
        JButton buttonQuestion = new JButton("Question");
        JButton buttonAccuse = new JButton("Accuse");
        JButton buttonEndTurn = new JButton("End Turn");
        JButton button5 = new JButton("5");
        JButton button6 = new JButton("6");
        JButton buttonBlank = new JButton("");
        JButton buttonBlank2 = new JButton("");
        JButton buttonBlank3 = new JButton("");
        JButton buttonBlank4 = new JButton("");
        JButton buttonBlank5 = new JButton("");
        JButton buttonUp = new JButton("Up");
        JButton buttonDown = new JButton("Down");
        JButton buttonLeft = new JButton("Left");
        JButton buttonRight = new JButton("Right");
        menuPanel.add(buttonQuit);
        menuPanel.add(buttonHelp);
        menuPanel.add(buttonLog);
        menuPanel.add(buttonNotes);
        menuPanel.add(buttonQuestion);
        menuPanel.add(buttonAccuse);
        menuPanel.add(buttonRoll);
        buttonPanel.add(buttonBlank);
        buttonPanel.add(buttonUp);
        buttonPanel.add(buttonBlank4);
        buttonPanel.add(buttonLeft);
        buttonPanel.add(buttonEndTurn);
        buttonPanel.add(buttonRight);
        buttonPanel.add(buttonBlank2);
        buttonPanel.add(buttonDown);
        buttonPanel.add(buttonBlank5);

        buttonEndTurn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                synchronized (commandBuffer) {
                    commandBuffer.add(buttonEndTurn.getText());

                    commandBuffer.notify();
                }

            }
        });

        buttonNotes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                synchronized (commandBuffer) {
                    commandBuffer.add(buttonNotes.getText());

                    commandBuffer.notify();
                }

            }
        });

        buttonAccuse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                synchronized (commandBuffer) {
                    commandBuffer.add(buttonAccuse.getText());

                    commandBuffer.notify();
                }

            }
        });

        buttonQuestion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                synchronized (commandBuffer) {
                    commandBuffer.add(buttonQuestion.getText());

                    commandBuffer.notify();
                }

            }
        });

        buttonLog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                synchronized (commandBuffer) {
                    commandBuffer.add(buttonLog.getText());

                    commandBuffer.notify();
                }

            }
        });

        buttonHelp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                synchronized (commandBuffer) {
                    commandBuffer.add(buttonHelp.getText());

                    commandBuffer.notify();
                }

            }
        });

        buttonQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                synchronized (commandBuffer) {
                    commandBuffer.add(buttonQuit.getText());

                    commandBuffer.notify();
                }

            }
        });

        buttonRoll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                synchronized (commandBuffer) {
                    commandBuffer.add(buttonRoll.getText());

                    commandBuffer.notify();
                }

            }
        });

        buttonUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                synchronized (commandBuffer) {
                    commandBuffer.add(buttonUp.getText());

                    commandBuffer.notify();
                }

            }
        });

        buttonDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                synchronized (commandBuffer) {
                    commandBuffer.add(buttonDown.getText());

                    commandBuffer.notify();
                }

            }
        });

        buttonLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                synchronized (commandBuffer) {
                    commandBuffer.add(buttonLeft.getText());

                    commandBuffer.notify();
                }

            }
        });

        buttonRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                synchronized (commandBuffer) {
                    commandBuffer.add(buttonRight.getText());

                    commandBuffer.notify();
                }

            }
        });
        

        //An actionListener to listen for user inputs and respond
        class AddActionListener implements ActionListener {

            public void actionPerformed(ActionEvent event) {

                synchronized (commandBuffer) {
                    commandBuffer.add(button1.getText());

                    commandBuffer.notify();
                }
            }

        }

        //border style

    }

    //Used to clear the text box.
    public static void updateCommands() {
        //availableInputs.setText("quit");
    }

    //Method to update label that shows how many moves the player has left
    public static void updateMovesReamining(int x) {
        if (x == 0) {
            movesRemaining.setText("You've run out of moves! Enter 'done' to move to next player.");
        } else if (x == -1) {
            movesRemaining.setText("");
        } else if (x == -2) {
            movesRemaining.setText("Choose an exit from the available set in information panel.");
        } else if (x == -3) {
            movesRemaining.setText("Answering question! Enter 'done' to move to next player.");
        } else {
            movesRemaining.setText("Moves remaining for current player:" + x);
        }
    }

    /**
     * A method that takes in a string of information
     *
     * @return A string that user typed
     */
    public String getCommand() {
        String command;
        synchronized (commandBuffer) {
            while (commandBuffer.isEmpty()) {
                try {
                    commandBuffer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            command = commandBuffer.pop();
        }

        return command;
    }

    /*
	 * Updates current user image on the command panel
	 * @param path
     */
}
