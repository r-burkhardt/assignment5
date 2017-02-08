/* ==================================================================
*
*   PROGRAM NAME:
*       Assignment5 - GUI Cards
*
*   Description:
*	Understand the Classes and Problem
*	
*	We wish to move our Card classes from the realm of console apps to 
*	that of GUI apps.  We'll do this in stages.
*	
*	>   Read and display Card pictures - Read .gif image files as Icons, 
*	and attach them to JLabels that we can display on a JFrame.
*	
*	>   Encapsulate the Card Icons in a class GUICard - Once we debug 
*	imagery for cards, above, we can move it into its own class, GUICard.
*	
*	>   Create a CardTable class - This JFrame class will embody the 
*	JPanels and Layout(s) needed for our application. This is where all 
*	the cards and controls will be placed. 
*	
*	>   Use a CardGameFramework class - Use an already created class to 
*	deal cards for display from an actual deck.
*	
*	>Create the game "High-Card"
*	
*	The first phase (item 1) will allow you to debug the problem of reading 
*	the .gif files and displaying them on a JFrame without any excess logic 
*	or class complexity.  The second phase (items 2 and 3) will let you 
*	turn what you did in the first phase into a multi-class project.  The 
*	final phase (items 4 and 5) will add the CardGameFramework class so 
*	that your card tools can be combined with your GUI tools to create a 
*	GUI program that has real computational power for a GUI card game, 
*	"High Card".
*
*   Classes:
*       Phase I - None
*       Phase II -
*           CardTable extends JFrame, GUICard, Card, Hand, Deck
*
*   Parameters:
*       1. none
*
*   Additional Files:
*
*   Created:
*       2017/02/01
*
*   Author/s:
*       Faiga Revah, Oswaldo Minez, Roderick Burkhardt
*
* ==================================================================*/


package assignment5;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Assignment5
{

    static int NUM_CARDS_PER_HAND = 7;
    static int NUM_PLAYERS = 2;
    static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
    static JButton[] humanButtons = new JButton[NUM_CARDS_PER_HAND];
    static JLabel[] playedCardLabels = new JLabel[NUM_PLAYERS];
    static JLabel[] playLabelText = new JLabel[NUM_PLAYERS];
    
    // Create CardGameFramework
    static int numPacksPerDeck = 1;
    static int numJokersPerPack = 0;
    static int numUnusedCardsPerPack = 0;
    static Card[] unusedCardsPerPack = null;

    static  CardGameFramework highCardGame = new CardGameFramework(numPacksPerDeck,
        numJokersPerPack, numUnusedCardsPerPack, unusedCardsPerPack, 
        NUM_PLAYERS, NUM_CARDS_PER_HAND);
    
    public static void main(String[] args)
    {
        int k;
        Icon tempIcon;
        
        Deck testDeck = new Deck();
        testDeck.shuffle();
        
//        int numPacksPerDeck = 1;
//        int numJokersPerPack = 0;
//        int numUnusedCardsPerPack = 0;
//        Card[] unusedCardsPerPack = null;
//        
//        CardGameFramework highCardGame = new CardGameFramework(numPacksPerDeck,
//            numJokersPerPack, numUnusedCardsPerPack, unusedCardsPerPack, 
//            NUM_PLAYERS, NUM_CARDS_PER_HAND);
        
        highCardGame.deal();
        
        CardTable myCardTable
                = new CardTable("CardTable", NUM_CARDS_PER_HAND, NUM_PLAYERS);
        myCardTable.setSize(800, 600);
        myCardTable.setLocationRelativeTo(null);
        myCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        myCardTable.setVisible(true);
        
        GUICard.loadCardIcons();
        
        CardActionListener cardListener = new CardActionListener();
        for (k = 0; k < NUM_CARDS_PER_HAND; k++)
        {
            computerLabels[k] = new JLabel( GUICard.getBackCardIcon());
            
            humanButtons[k] = new JButton(Integer.toString(k), GUICard.getIcon(highCardGame.getHand(1).inspectCard(k)));
            humanButtons[k].setBorderPainted(false);
            humanButtons[k].addActionListener(cardListener);     
        }

        for (k = 0; k < NUM_PLAYERS; k++)
        {
            //playedCardLabels[k] = new JLabel( GUICard.getIcon( () ),
            //        JLabel.CENTER );
            if ( k % NUM_PLAYERS == 0 )
            {
                playLabelText[k] = new JLabel( "Computer", JLabel.CENTER );
            }
            else
            {
                String temp = "Player " + k;
                playLabelText[k] = new JLabel( temp, JLabel.CENTER );
            }
        }

        // ADD LABELS TO PANELS -----------------------------------------
        for (k = 0; k < NUM_CARDS_PER_HAND; k++)
        {
            myCardTable.pnlComputerHand.add(computerLabels[k]);
            myCardTable.pnlHumanHand.add(humanButtons[k]);
        }
        
        for (k = 0; k < NUM_PLAYERS; k++)
            //System.out.println(k);
            //myCardTable.pnlPlayArea.add(playedCardLabels[k]);
        for (k = 0; k < NUM_PLAYERS; k++)
            myCardTable.pnlPlayArea.add(playLabelText[k]);
        
        // and two random cards in the play region (simulating a computer/hum ply)
        ///code goes here ...
        // show everything to the user
        myCardTable.setVisible(true);    
    }
    
    public static class CardActionListener implements ActionListener
    {

        public void actionPerformed(ActionEvent event)
        {
            //System.out.println(event.getActionCommand());
            switch (event.getActionCommand())
            {
                case "0":
                    highCardGame.playCard(0, 1);
                    
                    //System.out.println("Play Card 0");
                    break;
                case "1":
                    highCardGame.playCard(1, 1);
                    //System.out.println("Play Card 1");
                    break;
                case "2":
                    highCardGame.playCard(2, 1);
                    //System.out.println("Play Card 2");
                    break;
                case "3":
                    highCardGame.playCard(3, 1);
                    //System.out.println("Play Card 3");
                    break;
                case "4":
                    highCardGame.playCard(4, 1);
                    //System.out.println("Play Card 4");
                    break;
                case "5":
                    highCardGame.playCard(5, 1);
                    //System.out.println("Play Card 5");
                    break;
                case "6":
                    highCardGame.playCard(6, 1);
                    //System.out.println("Play Card 6");
                    break;
            }
        }
        
    }
    
    
}
