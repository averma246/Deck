/******* @author Ana Verma averma20@amherst.edu*********/
import java.util.Scanner;

public class Deck {

    public static Scanner keyboard = new Scanner (System.in);

    /******************************************************************************

    A Deck object represents a deck of cards.

    ******************************************************************************/

    private Card[] cards;             // The cards
    private int cardsLeft;           // The number of cards not yet drawn.
                                     // Make sure that the undrawn cards are in
                                     // positions 0 through cardsLeft-1.
    /************************ constructors ***************************************/

    public Deck() {                  // This constructor creates a standard deck

	cards = new Card[52];
	
	// add code here to create 52 distinct Card objects, and put them in the deck
	int k = 0;

	for(int i = 0; i<=3;i++)
	    for(int j = 1; j<=13;j++)
		{
		    cards[k] = new Card(j,i);
		    k++;
		}
	for(int i = 0; i<cards.length;i++)
	    System.out.println(cards[i]);


	shuffle();
	cardsLeft = 52;
    }

    /***************************************************************************
    drawCard(): This method returns a Card from the deck.  It should
         return null if the deck is empty.
    ****************************************************************************/

    public Card drawCard() {
	// To draw a card, it isn't necessary to change the contents of the array.
	// But you do want to make sure that each card can be drawn only once.
	// Do you see how to do this?  (Hint: it's easiest to draw cards from the
	// high end of the array.)
       

	cardsLeft = cardsLeft -1;

	return cards[cardsLeft];
    }


    /***************************************************************************
    getCardsRemaining(): This method tells how many cards remain in the deck.
    ****************************************************************************/

    public int getCardsRemaining() {

	return cardsLeft;

    }

    /***************************************************************************
    shuffle(): This method shuffles the deck.
    ****************************************************************************/
    
    public void shuffle() {
	
	cardsLeft = cards.length;
	for (int i=cards.length-1; i>=0; --i) {
	    
	    int r = (int)(Math.random()*(i+1));  // pick a random pos <= i

	    Card t = cards[i];
	    cards[i] = cards[r];
	    cards[r] = t;

	}
    }

    //Following three methods sort the hand in ascending order to make it easier to check whether a player has cards in consequtive order 
    public static void selectionSort(Card[] hand){
    
	for(int i = 0; i<hand.length;i++)
	    {
		int j = findMinPos(hand,i);
		swap(hand,i,j);
	
	    }
    }

    public static int findMinPos(Card[] hand,int lo){
	int pos = lo;
	int min = hand[lo].getValue();
	for(int i = lo+1; i<hand.length;i++)
	    if(hand[i].getValue() < min)
		{
		    min = hand[i].getValue();
		    pos = i;
		}
	return pos;
    }

    public static void swap (Card[] hand, int i, int j){
	Card temporary1 = hand[i];
	Card temporary2 = hand[j];
	hand[i] = temporary2;
	hand[j] = temporary1; 

    }

    //The following methods take in the array of Cards (hand) of each player -- varible "a" used for simplicity 

    public static boolean straight(Card[] a){

	
	if(a[0].getValue()+1==a[1].getValue() && a[1].getValue()+1==a[2].getValue() && a[2].getValue()+1==a[3].getValue() && a[3].getValue()+1==a[4].getValue())
	    return true;
	else if(a[0].getValue()== 1 && a[1].getValue()+1==a[2].getValue() && a[2].getValue()+1==a[3].getValue() && a[3].getValue()+1==a[4].getValue() && a[4].getValue() == 13 && a[1].getValue() != 1)
	    return true;
    
    
    return false;
    }


    public static boolean threeOfAKind(Card[]a){
	int [] suitWeights = new int[4];

	for(int i = 0; i<a.length;i++)
	    suitWeights[a[i].getSuit()]++;
	for(int i = 0; i<4;i++)
	    if(suitWeights[i] == 3)
		return true;

	return false;    
    }

    public static boolean fullHouse(Card[] a){
	int[] suitWeights = new int[4];

	boolean threeOfaKind = false;
	for(int i = 0; i<a.length;i++)
	    suitWeights[a[i].getSuit()]++;

	for(int i = 0;i<4;i++)
	    if(suitWeights[i]==3)
		threeOfaKind = true;

	Card pair1 = null;
	Card pair2 = null;

	if(threeOfaKind)
	    for(int i = 0; i<4;i++)
		{
		    if(suitWeights[i] == 1 && pair1 == null)
			pair1 = a[i];
		    else if(suitWeights[i] == 1 && pair2 == null)
			pair2 = a[i];
		}
	if(pair1 != null && pair2!=null && pair1.getValue() == pair2.getValue())
	    return true;
 
	return false;
    }

    public static boolean flush(Card[]a){
	if(a[0].getSuit() == a[1].getSuit() && a[1].getSuit() == a[2].getSuit() && a[2].getSuit() == a[3].getSuit() && a[3].getSuit() == a[4].getSuit())
	    return true;
	return false;
    }
   

    public static boolean fourOfAKind(Card[]a){
	int[] valueWeights = new int[13];

	for(int i = 0; i<a.length;i++)
	    valueWeights[a[i].getValue()-1]++;

	for(int i = 0; i<13;i++)
	    if(valueWeights[i]==4)
		return true;
	return false;
    }

    public static boolean twoPair(Card[]a){
	int[] valueWeights = new int[13];

	for(int i = 0; i<a.length;i++)
	    valueWeights[a[i].getValue()-1]++;

	int counter = 0;

	for(int i = 0; i<13;i++)
	    if(valueWeights[i]==2)
		counter++;
	if(counter == 2)
	    return true;
	return false;
    }

    public static boolean onePair(Card[] a){
	int[] valueWeights = new int[13];

	for(int i = 0;i<a.length;i++)
	    valueWeights[a[i].getValue()-1]++;
	
	int pairCounter = 0;
	int distinct = 0;

	for(int i = 0; i<13;i++)
	    {
		if(valueWeights[i]==2)
		    pairCounter++;
		if(valueWeights[i]==1)
		    distinct++;
	    }
	if(pairCounter==1 && distinct==3)
	    return true;

	return false;
    }

    public static Card[] highCard(Card[] a, Card[] b){
	for(int i = a.length-1;i>=0;i--)
	    if(a[i].getValue()>b[i].getValue())
		return a;
	    else if(b[i].getValue() > a[i].getValue())
		return b;
	return null;
    }

    public static Card[] handRanks(Card[] a, Card[] b){

	//Straight Flush
	if (straight(a) && flush(a))
	    {
		System.out.println("Won by STRAIGHT FLUSH!");
		return a;
	    }
	else if(straight(b) && flush(b))
	    {
		System.out.println("Won by STRAIGHT FLUSH!");
		return b;
	    }

	//4 of a kind
	if(fourOfAKind(a))
	    {
		System.out.println("Won by 4 OF A KIND!");
		return a;
	    }
	else if(fourOfAKind(b))
	    {
		System.out.println("Won by a 4 OF A KIND!");
		return b;
	    }

	//Full House 
	if(fullHouse(a))
	    {
		System.out.println("Won by a FULL HOUSE!");
		return a;
	    }
	else if(fullHouse(b))
	    {
		System.out.println("Won by a FULL HOUSE!");		
		return b;
	    }

	//Flush
	if(flush(a))
	    {
		System.out.println("Won by a FLUSH!");
		return a;
	    }
	if(flush(b))
	    { 
		System.out.println("Won by a FLUSH!");
		return b;
	    }

	//Straight

	if(straight(a))
	    {
		System.out.println("Won by a STRAIGHT!");
		return a;
	    }
	if (straight(b))
	    {
		System.out.println("Won by a STRAIGHT!");
		return b;
	    }
	
	//3 of a Kind
	if(threeOfAKind(a))
	    {
		System.out.println("Won by a 3 OF A KIND!");
		return a;
	    }
	if (threeOfAKind(b))
	    {
		System.out.println("Won by a 3 OF A KIND!");
		return b;
	    }

	//2 pair 

	if(twoPair(a))
	    {
		System.out.println("Won by a 2 PAIR!");
		return a;
	    }
	if(twoPair(b))
	    {
		System.out.println("Won by a 2 PAIR!");
		return b;
	    }
	
	//1 pair
	if(onePair(a))
	    {
		System.out.println("Won by 1 PAIR!");
		return a;
	    }
	if(onePair(b))
	    {
		System.out.println("Won by 1 PAIR!");
		return b;
	    }

	System.out.println("Won by HIGHCARD!");
	return highCard(a,b);
	
    }
    
    public static void poker(Deck d){
	System.out.println("\n\n\n"+"**********\n"+"Welcome to a game of Poker!" + "\n\n" + "Here are the basic rules: When you enter the game, you will be dealt 5 random cards. You will have the opportunity to discard three cards and draw 3 new ones, if you think that that would help you get a higher hand. BUT you can only change hands ONCE, and if you chose to do so, you MUST discard three distinct cards and get three RANDOM new cards. After that, the computer will reveal its cards, and a winner will be declared!" + "\n\n" + "Here are the possible hands:" + "\n" + "Straight Flush--5 cards in a row of the same suit" + "\n"+"Four of a kind--get 4 cards of the same rank"+"\n"+"Full House--3 of a kind and a pair"+"\n"+"Flush--5 cards of the same suit"+"\n"+"Straight--5 cards in a row"+"\n"+"3 of a kind--3 cards of the same suit"+"\n"+"2 Pair--Two distinct pairs and a 5th card"+"\n"+"Pair--One pair with 3 distinct cards"+"\n"+"High Card--If neither player has the other hands, then the player with the highest card wins."+"\n\n"+ "Enter y to enter game, and n to exit.");


	if((keyboard.nextLine()).equals("y"))
	    {
		Card[] computerHand = new Card[5];
		Card[] playerHand = new Card[5];	
		
		for(int i = 0; i<5;i++)
		    computerHand[i] = d.drawCard();
		for(int i = 0;i<5;i++)
		    playerHand[i] = d.drawCard();


		System.out.println("\nHere is your hand:\n");

		selectionSort(playerHand);
		selectionSort(computerHand);

		for(int i = 0; i<5;i++)
			System.out.println("("+ (i+1) + ") "+ playerHand[i]);


		

		System.out.println("\nWould you like to change your hand? Press y to change, and n to keep.");
	       
		String enterGame = keyboard.nextLine();
		while(!enterGame.equals("y") && !enterGame.equals("n"))
		    {
			System.out.println("Please enter y or n.");
			enterGame = keyboard.nextLine();
		    }

		if(enterGame.equals("y"))
		    {
			System.out.println("\nThe number of the card is listed directly to the left of the list of card names. \nEnter the number of the first card you would like to discard (1-5)");

			int change1 = keyboard.nextInt();

			while(change1>5 || change1<1)
			    {
				System.out.println("Please enter a number 1-5:");
				change1 = keyboard.nextInt();
			    }
			playerHand[change1-1] = d.drawCard();

			System.out.println("\nEnter the number of the second card you would like to discard:");

			int change2 = keyboard.nextInt();
			while(change2>5 || change2<1 || change2==change1)
			    {
				System.out.println("Please enter a different number 1-5:");
				change2 = keyboard.nextInt();
			    }
			playerHand[change2-1] = d.drawCard();

			System.out.println("\nEnter the number of the last card you would like to discard:");
			int change3 = keyboard.nextInt();
			while(change3>5 || change3<1 || change3==change2|| change3==change1)
			    {
				System.out.println("Please enter a different number 1-5:");
				change3 = keyboard.nextInt();
			    }
			playerHand[change3-1] = d.drawCard();
			
			System.out.println("\nHere is your new hand:");
			for(int i = 0; i<5;i++)
			    System.out.println("("+(i+1)+") " + playerHand[i]);

			System.out.println();
		    }

		System.out.println("\nHere is the computer's hand:");
		for(int i = 0; i<5;i++)
		    System.out.println(computerHand[i]);

		System.out.println("\n");
		

		if(handRanks(playerHand, computerHand)==playerHand)
		    System.out.println("THE WINNER IS....YOU!!\n");
		else
		    System.out.println("THE WINNER IS....COMPUTER. Sorry...you lost. Better luck next time.");
	    }
    }
	      
    /***************************************************************************
    main(): This method is used for testing the Deck class
    ****************************************************************************/

    public static void main (String[] args) {
	
	Deck d = new Deck();
	
	System.out.println("*****************************" + "\n");
	

	// Add code that draws cards, one by one, from the deck and prints them.
	while(d.getCardsRemaining() != 0)
	    System.out.println(d.drawCard());

	//Run poker game 
	Deck game = new Deck();
	poker(game);

    }
}

	    
