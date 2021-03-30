package dayPoker;

public class Card {

	
	 // FOR CARDS
	 public final static int DIAMONDS = 1;  
	 public final static int HEARTS = 2;
	 public final static int SPADES = 3;
	 public final static int CLUBS = 4;


	 public final static int JACK = 11;     
	 public final static int QUEEN = 12;  
	 public final static int KING = 13;
	 public final static int ACE = 14;
	    
	 
	 // ASPECTS OF CARD
	 public int rank;
	 public int suit;
	 
	 
	 public Card(int cardrank, int cardsuit) {
	 this.rank = cardrank;
	 this.suit = cardsuit;
	 
	 }
}
