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
*       None
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

import java.util.Random;

public class Deck
{

    public final int MAX_CARDS = 312; // max 6 decks of 52 cards

    private static Card[] masterPack;
    private Card[] cards;

    int topCard;
    int numPacks;

    // A constructor that populates the arrays and assigns initial values to
    // members with the assistance of init()
    // Defaults to one pack of cards in the deck.
    public Deck()
    {
        init(1);
    }

    // A constructor that populates the arrays and assigns initial values to
    // members with the assistance of init(). This constructor is an overload
    // of the default constructor allowing for an parameter being set with the 
    // number of packs the deck will contain.
    // Takes one parameter, an int numPacks, that is used to to create a deck
    // of cards that is a combination of more than one pack
    public Deck(int numPacks)
    {
        init(numPacks);
    }

    // This public method initializes a deck of card according to the parameter
    // numPacks which is passed to it. This method calls on private method
    // allocateMasterPack() which sets the static array with 52 cards used to 
    // create each pack that is added to the Deck.
    // Here the private members numPacks and topCard are allso set accordingly.
    // The parameter int numPacks tells the method how many packs of cards are
    // to be added to the Deck from the masterPack.
    public void init(int numPacks)
    {
        allocateMasterPack();
        this.numPacks = numPacks;
        this.topCard = (52 * this.numPacks) - 1;

        cards = new Card[52 * numPacks];

        for (int pack = 0; pack < numPacks; pack++)
        {
            for (int card = 0; card < masterPack.length; card++)
            {
                cards[(52 * pack) + card] = masterPack[card];
            }
        }
    }

    // This public method is used to randomly shuffle the cards within a deck
    // so that all card are out of sequence.
    public void shuffle()
    {
        int split = cards.length / 2;
        Random rand = new Random();
        int shufCount = 5 * (52 * numPacks);

        do
        {
            int cardA = rand.nextInt(split);
            int cardB = rand.nextInt(split) + split;

            Card temp = cards[cardA];
            cards[cardA] = cards[cardB];
            cards[cardB] = temp;

            shufCount--;
        } while (shufCount != 0);

    }

    // A public method that returns the card on the top of the deck replacing
    // its position in the array with a null, and moving the topCard private
    // member to the next card in the deck.
    public Card dealCard()
    {
        Card retVal = cards[topCard];
        cards[topCard] = null;
        if (topCard > 0)
        {
            topCard--;
        }
        return retVal;
    }

    // This is an accessor method used to retreive the position of the current
    // top card in the deck array.
    public int getTopCard()
    {
        return topCard;
    }
    
    // Accessor for an individual card. 
    //Returns a card with errorFlag = true if k is bad
    public Card inspectCard(int k)
    {
        if (cards[k].getErrorFlag())
            return cards[k];
        else
            return new Card();
        
    }
    
    // This private method is used to fill the masterPack array with 52 unique
    // cards, which can than be used to populate a deck as needed. This method
    // will only run when the first deck in the program is initialized. If the
    // masterPack array is not null the method simply end makeing no changes to
    // the masterPack array.
    private static void allocateMasterPack()
    {
        if (masterPack == null)
        {
            masterPack = new Card[52];

            for (Card.Suit s : Card.Suit.values())
            {
                for (int x = 1; x < 14; x++)
                {
                    Card newCard;
                    int value = x % 14;
                    switch (value)
                    {
                        case 1:
                            newCard = new Card('A', s);
                            break;
                        case 10:
                            newCard = new Card('T', s);
                            break;
                        case 11:
                            newCard = new Card('J', s);
                            break;
                        case 12:
                            newCard = new Card('Q', s);
                            break;
                        case 13:
                            newCard = new Card('K', s);
                            break;
                        default:
                            newCard = new Card(Integer.toString(value).charAt(0), s);
                    }
                    if (s == Card.Suit.clubs)
                    {
                        masterPack[x - 1] = newCard;
                    }
                    else if (s == Card.Suit.diamonds)
                    {
                        masterPack[(x - 1) + 13]
                                = newCard;
                    }
                    else if (s == Card.Suit.hearts)
                    {
                        masterPack[(x - 1) + 26]
                                = newCard;
                    }
                    else
                    {
                        masterPack[(x - 1) + 39] = newCard;
                    }
                }
            }
        }
    }
}