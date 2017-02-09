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

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

public class Assignment5
{
    static CardTable myCardTable;
    static int NUM_CARDS_PER_HAND = 7;
    static int NUM_PLAYERS = 2;
    static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
    static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];
    static JLabel[] playedCardLabels = new JLabel[NUM_PLAYERS];
    static JLabel[] playLabelText = new JLabel[NUM_PLAYERS];
    static CardGameFramework highCardGame;
    static boolean gameInPlay = false;
    
    public static void main(String[] args)
    {
        GUICard.loadCardIcons();
        
        // Create CardGameFramework
        int numPacksPerDeck = 1;
        int numJokersPerPack = 0;
        int numUnusedCardsPerPack = 0;
        Card[] unusedCardsPerPack = null;
        
        highCardGame = new CardGameFramework(numPacksPerDeck,
                numJokersPerPack, numUnusedCardsPerPack, unusedCardsPerPack, 
                NUM_PLAYERS, NUM_CARDS_PER_HAND);
        
        highCardGame.deal();
        
        myCardTable
                = new CardTable("CardTable", NUM_CARDS_PER_HAND, NUM_PLAYERS);
        myCardTable.setSize(800, 600);
        myCardTable.setLocationRelativeTo(null);
        myCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        myCardTable.setVisible(true);
        
        int playOrNot = JOptionPane.showConfirmDialog(null, "Ready to play War?", "", JOptionPane.YES_NO_OPTION);
        if (playOrNot == JOptionPane.YES_OPTION)
        {
            gameInPlay = true;
            
            buildPanels();

            while (gameInPlay)
            {            
                //buildPanels();
            }
            
        }
        System.out.println("thanks for playing");
        
        
        // show everything to the user
        //myCardTable.setVisible(true);    
    }
    
    public static void buildPanels()
    {
        int k;
        Icon tempIcon;
        
        //CardActionListener cardListener = new CardActionListener();
        CardClickListener clickListener = new CardClickListener();
        
        for (k = 0; k < NUM_CARDS_PER_HAND; k++)
        {
            computerLabels[k] = new JLabel( GUICard.getBackCardIcon());
            if (highCardGame.getHand(1).inspectCard(k) == null)
                tempIcon = GUICard.getBlankIcon();
            else
                tempIcon = GUICard.getIcon(highCardGame.getHand(1).inspectCard(k));
            humanLabels[k] = new JLabel( tempIcon );
            humanLabels[k].addMouseListener(clickListener);   
        }

        for (k = 0; k < NUM_PLAYERS; k++)
        {
            playedCardLabels[k] = new JLabel( GUICard.getBlankIcon(),
                    JLabel.CENTER );
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
            myCardTable.pnlHumanHand.add(humanLabels[k]);
        }
        
        for (k = 0; k < NUM_PLAYERS; k++)
            myCardTable.pnlPlayArea.add(playedCardLabels[k]);
        for (k = 0; k < NUM_PLAYERS; k++)
            myCardTable.pnlPlayArea.add(playLabelText[k]);
        myCardTable.setVisible(true);
        myCardTable.repaint();
        
    }
    
    public static void removeLabel( JLabel[] labels, int removed )
    {
        for (int i = removed; i < labels.length; i++)
        {
            if (i == labels.length-1)
            {
                labels[i] = new JLabel(GUICard.getBlankIcon());
            }
            else
            {
                labels[i] = labels[i+1];
            }
            labels[i].repaint();
            
        }
    }
    
    public static void updatePanels()
    {
        myCardTable.pnlComputerHand.removeAll();
        myCardTable.pnlComputerHand.repaint();
    }
    
    public static class CardClickListener implements MouseListener
    {
        public void mouseClicked(MouseEvent event)
        {
            if (event.getSource() == humanLabels[0])
            {
                highCardGame.playCard(0, 1);
                updatePanels();
                //removeLabel(humanLabels, 0);
                //myCardTable.revalidate();
                System.out.println(" Card 0 ");
                
            }
            if (event.getSource() == humanLabels[1])
            {
                highCardGame.playCard(1, 1);
                //removeLabel(humanLabels, 1);
                //buildPanels();
                //myCardTable.revalidate();
                System.out.println(" Card 1 ");
            }
            if (event.getSource() == humanLabels[2])
            {
                highCardGame.playCard(2, 1);
                //removeLabel(humanLabels, 2);
                //buildPanels();
                //myCardTable.revalidate();
                System.out.println(" Card 2 ");
            }
            if (event.getSource() == humanLabels[3])
            {
                highCardGame.playCard(3, 1);
                //removeLabel(humanLabels, 3);
                //buildPanels();
                //myCardTable.revalidate();
                System.out.println(" Card 3 ");
            }
            if (event.getSource() == humanLabels[4])
            {
                highCardGame.playCard(4, 1);
                //removeLabel(humanLabels, 4);
                //buildPanels();
                //myCardTable.revalidate();
                System.out.println(" Card 4 ");
            }
            if (event.getSource() == humanLabels[5])
            {
                highCardGame.playCard(5, 1);
                //removeLabel(humanLabels, 5);
                //
                //myCardTable.revalidate();
                System.out.println(" Card 5 ");
            }
            if (event.getSource() == humanLabels[6])
            {
                highCardGame.playCard(6, 1);
                //removeLabel(humanLabels, 6);
                //buildPanels();
                //myCardTable.revalidate();
                System.out.println(" Card 6 ");
            }
        }

        public void mousePressed(MouseEvent e){}

        public void mouseReleased(MouseEvent e){}
        
        public void mouseEntered(MouseEvent e){}
        
        public void mouseExited(MouseEvent e){}
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
                    buildPanels();
                    myCardTable.repaint();
                    //System.out.println("Play Card 0");
                    break;
                case "1":
                    highCardGame.playCard(1, 1);
                    myCardTable.repaint();
                    //System.out.println("Play Card 1");
                    break;
                case "2":
                    highCardGame.playCard(2, 1);
                    myCardTable.repaint();
                    //System.out.println("Play Card 2");
                    break;
                case "3":
                    highCardGame.playCard(3, 1);
                    myCardTable.repaint();
                    //System.out.println("Play Card 3");
                    break;
                case "4":
                    highCardGame.playCard(4, 1);
                    myCardTable.repaint();
                    //System.out.println("Play Card 4");
                    break;
                case "5":
                    highCardGame.playCard(5, 1);
                    myCardTable.repaint();
                    //System.out.println("Play Card 5");
                    break;
                case "6":
                    highCardGame.playCard(6, 1);
                    myCardTable.repaint();
                    //System.out.println("Play Card 6");
                    break;
            }
        }
        
    }
    
    
}
