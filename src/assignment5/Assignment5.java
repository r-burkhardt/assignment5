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
*           CardTable extends JFrame
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

public class Assignment5
{

    static int NUM_CARDS_PER_HAND = 7;
    static int NUM_PLAYERS = 2;
    static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
    static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];
    static JLabel[] playedCardLabels = new JLabel[NUM_PLAYERS];
    static JLabel[] playLabelText = new JLabel[NUM_PLAYERS];

    public static void main(String[] args)
    {
        int k;
        Icon tempIcon;
        Deck testDeck = new Deck();

        // establish main frame in which program will run
        CardTable myCardTable
                = new CardTable("CardTable", NUM_CARDS_PER_HAND, NUM_PLAYERS);
        myCardTable.setSize(800, 600);
        myCardTable.setLocationRelativeTo(null);
        myCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // show everything to the user
        myCardTable.setVisible(true);

        
        
        /*// CREATE LABELS ----------------------------------------------------
      for(k = 0; k < NUM_CARDS_PER_HAND; k++)
         humanLabels[k] = new JLabel(GUICard.getIcon(generateRandomCard()));

      for(k = 0; k < NUM_CARDS_PER_HAND; k++)
         computerLabels[k] = new JLabel(GUICard.getBackCardIcon());


      // ADD LABELS TO PANELS -----------------------------------------
      for(k = 0; k < NUM_CARDS_PER_HAND; k++)
         myCardTable.pnlHumanHand.add(humanLabels[k]);

      for(k = 0; k < NUM_CARDS_PER_HAND; k++)
         myCardTable.pnlComputerHand.add(computerLabels[k]);

      // and two random cards in the play region (simulating a computer/hum ply)
      for(k = 0; k < NUM_PLAYERS; k++)
         playedCardLabels[k] = new JLabel(GUICard.getIcon(
               generateRandomCard()));

      playLabelText[0] = new JLabel("Computer", 0);
      playLabelText[1] = new JLabel("You", 0);

      //Adding cards to the play area panel.
      for(k = 0; k < NUM_PLAYERS; k++)
      {
         myCardTable.pnlPlayArea.add(playedCardLabels[k]);
         //myCardTable.pnlPlayArea.add(playLabelText[k]);
      }
      //Adding lables to the play area panel under the cards.
      myCardTable.pnlPlayArea.add(playLabelText[0]);
      myCardTable.pnlPlayArea.add(playLabelText[1]);*/
        
        
        
        // CREATE LABELS ----------------------------------------------------
        for (k = 0; k < NUM_CARDS_PER_HAND; k++)
        {
            computerLabels[k] = new JLabel(GUICard.getBackCardIcon(),
                    JLabel.CENTER );
            humanLabels[k] = new JLabel( GUICard.getIcon( generateRandomCard() ),
                    JLabel.CENTER );
        }

        for (k = 0; k < NUM_PLAYERS; k++)
        {
            playedCardLabels[k] = new JLabel( GUICard.getIcon( generateRandomCard() ),
                    JLabel.CENTER );
            if ( k % NUM_PLAYERS == 0 )
            {
                playedCardLabels[k] = new JLabel( "Computer", JLabel.CENTER );
            }
            else
            {
                String temp = "Player " + k;
                playedCardLabels[k] = new JLabel( temp, JLabel.CENTER );
            }
        }

        // ADD LABELS TO PANELS -----------------------------------------
        for (k = 0; k < NUM_CARDS_PER_HAND; k++)
        {
            myCardTable.pnlComputerHand.add(computerLabels[k]);
            myCardTable.pnlHumanHand.add(humanLabels[k]);
        }
        
        for (k = 0; k < NUM_PLAYERS; k++)
        {
            myCardTable.pnlPlayArea.add(playedCardLabels[k]);
        }
        
        myCardTable.getContentPane().add(myCardTable.pnlComputerHand);
        myCardTable.getContentPane().add(myCardTable.pnlPlayArea);
        myCardTable.getContentPane().add(myCardTable.pnlHumanHand);
        // and two random cards in the play region (simulating a computer/hum ply)
        ///code goes here ...
        // show everything to the user
        myCardTable.setVisible(true);
    }

    static Card generateRandomCard()
    {
        Card.Suit newSuit;
        char newValue;

        Random rand = new Random();
        int randNum = rand.nextInt(14);
        newValue = Card.valuRanks[randNum];
        switch (randNum % 4)
        {
            case 0:
                newSuit = Card.Suit.clubs;
                break;
            case 1:
                newSuit = Card.Suit.diamonds;
                break;
            case 2:
                newSuit = Card.Suit.hearts;
                break;
            default:
                newSuit = Card.Suit.spades;
        }

        return new Card(newValue, newSuit);

    }

}
