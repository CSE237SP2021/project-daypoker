package dayPoker;

public class Card {

	
	 // VARIABLES TO REPRESENT CARD SUITS
	
	 public final static int DIAMONDS = 1;  
	 public final static int HEARTS = 2;
	 public final static int SPADES = 3;
	 public final static int CLUBS = 4;


	// VARIABLES TO REPRESENT CARD RANKS
	 
	 public final static int JACK = 11;     
	 public final static int QUEEN = 12;  
	 public final static int KING = 13;
	 public final static int ACE = 14;
	    
	 
	 // CARDS HAVE A RANK AND SUIT
	 
	 public int rank;
	 public int suit;
	 
	
	 // CONSTRUCTOR
	 
	 public Card(int cardrank, int cardsuit) {
	 this.rank = cardrank;
	 this.suit = cardsuit;
	 
	 }
}
