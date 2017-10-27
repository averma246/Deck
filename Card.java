import java.util.Scanner;

public class Card {

    /******************************************************************************

    A Card object represents a playing card.  You can construct a card by writing

        Card c = new Card(value, suit);

    The value and suit are each given as an int, using the encodings shown below.

    There are three non-static methods, getValue(), getSuit(), and
    toString().  In another class, you could write 

            c.getValue() 

    to get the value of card c, and so on.

    There are three static methods, getSuitString(), getValueString(),
    and main().  In another class you could write

            Card.getSuitString(3);

    to get the String, "Clubs", for the suit with index 3.

    Your task in this class is to fill in methods getValueString() and toString().
    Once you've done this, you can compile, and then you can test your program by running

             java Card

    The main method has an infinite loop, so you'll need to hit CTRL-C to quit.

    ******************************************************************************/

    private int value;         // 1=ace, ..., 11=jack, 12=queen, 13=king
    private int suit;          // 0=spades, 1=hearts, 2=diamonds, 3=clubs

    public Card (int v, int s) {
	value = v;
	suit = s;
    }

    /********************** non-static methods **********************************/

    public int getValue() {
	return value;
    }

    public int getSuit() {
	return suit;
    }

    public String toString() { // returns a string for the card, e.g., King of Spades

	return ""+ getValueString(value) + " of " + getSuitString(suit);           // you should replace this with something else
	                     // hint: fix the getValueString() method first
    }

    /********************** static variable and methods *************************/

    private static final String[] SUITS = {"Spades", "Hearts", "Diamonds", "Clubs" };

    public static String getSuitString(int i) {
	
	if (i >= 0 && i <= 3)
	    return SUITS[i];
	else
	    throw new RuntimeException 
		("Tried to use getSuitString on an incorrect value");
    }

    public static String getValueString(int i) {

	// This method should return a string for the given value, e.g., 2, 3, 4, 
	// ..., 10, Jack, Queen, King
	String[] VALUES = {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
	if(i>0 && i<14)
	    return VALUES[i-1];
	else
	    throw new RuntimeException
		("Tried to use getValueString on an invalid value.");

    }

    /********************** main, for testing purposes only ********************/

    public static void main (String[] args) {

	Scanner keyboard = new Scanner(System.in);

	while (true) {

	    System.out.print ("Enter suit number: ");
	    int s = keyboard.nextInt();
	    
	    System.out.print ("Enter value number: ");
	    int v = keyboard.nextInt();
	    
	    System.out.println ("The suit is " + getSuitString(s));
	    System.out.println ("The value is " + getValueString(v));
	    
	    Card c = new Card(v, s);
	    System.out.println ("This card is the " + c);
	}
    }
}
