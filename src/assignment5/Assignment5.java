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
import java.util.Arrays;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Assignment5
{
    // static for the 57 icons and their corresponding labels
    // normally we would not have a separate label for each card, but
    // if we want to display all at once using labels, we need to.

    static final int NUM_CARD_IMAGES = 57; // 52 + 4 jokers + 1 back-of-card image
    static Icon[] icon = new ImageIcon[NUM_CARD_IMAGES];

    /*static void loadCardIcons()
    {
        // build the file names ("AC.gif", "2C.gif", "3C.gif", "TC.gif", etc.)
        // in a SHORT loop.  For each file name, read it in and use it to
        // instantiate each of the 57 Icons in the icon[] array.
        int count = 0;
        for (int j = 0; j < 4; j++)
        {
            for (int i = 0; i < 14; i++)
            {
                String file = "images/" + turnIntIntoCardValue(i) +
                        turnIntIntoCardSuit(j) + ".gif";
                icon[count++] = new ImageIcon(file);
            }
        }
        icon[56] = new ImageIcon("images/BK.gif");
    }

    // turns 0 - 14 into "A", "2", "3", ... "Q", "K", "X"
    static String turnIntIntoCardValue(int k)
    {
        String[] cardValues = { "A", "2", "3", "4", "5", "6", "7", "8",
            "9", "T", "J", "Q", "K", "X" };
        if (k >=0 && k<= 13)
        {
            return cardValues[k];
        }
        return "";
    }

    // turns 0 - 3 into "C", "D", "H", "S"
    static String turnIntIntoCardSuit(int j)
    {
        String[] suites = { "C", "D", "H", "S" };
        if (j >=0 && j<= 3)
        {
            return suites[j];
        }
        return "";
    }*/

    // a simple main to throw all the JLabels out there for the world to see
    public static void main(String[] args)
    {
        Card card0 = new Card('A', Card.Suit.hearts);
        Card card1 = new Card('2', Card.Suit.hearts);
        Card card2 = new Card('3', Card.Suit.hearts);
        Card card3 = new Card('4', Card.Suit.hearts);
        Card card4 = new Card('5', Card.Suit.hearts);
        Card card5 = new Card('6', Card.Suit.hearts);
        Card card6 = new Card('7', Card.Suit.hearts);
        Card card7 = new Card('8', Card.Suit.hearts);
        Card card8 = new Card('9', Card.Suit.hearts);
        Card card9 = new Card('T', Card.Suit.hearts);
        Card card10 = new Card('J', Card.Suit.hearts);
        Card card11 = new Card('Q', Card.Suit.hearts);
        Card card12 = new Card('K', Card.Suit.hearts);
        Card card13 = new Card('X', Card.Suit.hearts);
        
        //System.out.println((card1.getSuit()).compareTo(card2.getSuit()));
        
        Deck test = new Deck(2);
        
        test.shuffle();
        test.sort();
        
        /*System.out.println(card0.getValue() + "  " + Arrays.binarySearch(Card.valuRanks, card0.getValue()));
        System.out.println(card1.getValue() + "  " + Arrays.binarySearch(Card.valuRanks, card1.getValue()));
        System.out.println(card2.getValue() + "  " + Arrays.binarySearch(Card.valuRanks, card2.getValue()));
        System.out.println(card3.getValue() + "  " + Arrays.binarySearch(Card.valuRanks, card3.getValue()));
        System.out.println(card4.getValue() + "  " + Arrays.binarySearch(Card.valuRanks, card4.getValue()));
        System.out.println(card5.getValue() + "  " + Arrays.binarySearch(Card.valuRanks, card5.getValue()));
        System.out.println(card6.getValue() + "  " + Arrays.binarySearch(Card.valuRanks, card6.getValue()));
        System.out.println(card7.getValue() + "  " + Arrays.binarySearch(Card.valuRanks, card7.getValue()));
        System.out.println(card8.getValue() + "  " + Arrays.binarySearch(Card.valuRanks, card8.getValue()));
        System.out.println(card9.getValue() + "  " + Arrays.binarySearch(Card.valuRanks, card9.getValue()));
        System.out.println(card10.getValue() + "  " + Arrays.binarySearch(Card.valuRanks, card10.getValue()));
        System.out.println(card11.getValue() + "  " + Arrays.binarySearch(Card.valuRanks, card11.getValue()));
        System.out.println(card12.getValue() + "  " + Arrays.binarySearch(Card.valuRanks, card12.getValue()));
        System.out.println(card13.getValue() + "  " + Arrays.binarySearch(Card.valuRanks, card13.getValue()));
        System.out.println(Card.valuRanks[9] == card9.getValue());*/
        
        
        System.out.println("number of cards in deck " + test.getNumCards());
        
        test.removeCard(card10);
        test.removeCard(card7);
        test.removeCard(card8);
        test.removeCard(card3);
        test.removeCard(card0);
        
        System.out.println("topcard after 5 removed " + test.getTopCard());
        System.out.println("number of cards in deck " + test.getNumCards());
        //test.sort();
        
        test.addCard(card8);
        test.addCard(card3);
        test.addCard(card0);
        
        System.out.println("topcard after 3 added " + test.getTopCard());        
        System.out.println("number of cards in deck " + test.getNumCards());
        
        while (test.getTopCard() >= 0)
        {            
            System.out.println(test.dealCard());
            //test.dealCard();
        }
        
        System.out.println("number of cards in deck " + test.getNumCards());
        
        /*int k;

        // prepare the image icon array
        loadCardIcons();

        // establish main frame in which program will run
        JFrame frmMyWindow = new JFrame("Card Room");
        frmMyWindow.setSize(1150, 650);
        frmMyWindow.setLocationRelativeTo(null);
        frmMyWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set up layout which will control placement of buttons, etc.
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 5, 20);
        frmMyWindow.setLayout(layout);

        // prepare the image label array
        JLabel[] labels = new JLabel[NUM_CARD_IMAGES];
        for (k = 0; k < NUM_CARD_IMAGES; k++)
        {
            labels[k] = new JLabel(icon[k]);
        }

        // place your 3 controls into frame
        for (k = 0; k < NUM_CARD_IMAGES; k++)
        {
            System.out.println(k);
            frmMyWindow.add(labels[k]);
        }

        // show everything to the user
        frmMyWindow.setVisible(true);*/
    }

}
