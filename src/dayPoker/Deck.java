package dayPoker;

import java.util.Random;

public class Deck {
	
	
	//DECK OBJECTS NEED A DECK OF 52 CARDS AND A COUNTER FOR HOW MANY CARDS WE'VE USED

	public Card[] deck;
	public int next_card;
	public Card[] community_cards;
	
	
	public Deck() {
		
		next_card = 0;
		deck = new Card[52];
		community_cards = new Card[5];
		
		
		// FILLS THE DECK WITH CARDS REPRESENTING EACH CARD AND RANK
		
		int counter = 0;
		for (int cardsuit = 1; cardsuit <= 4; ++cardsuit) {
			for (int cardrank = 2; cardrank <= 14; ++cardrank) {
				deck[counter] = new Card(cardrank, cardsuit);
				++counter;
			}
		}
		
	}
	
	
	// SHUFFLES OUR DECK USING A RANDOM NUMBER GENERATOR
	
	public void shuffle_deck() {
		
		 Random rand = new Random();	
		
		for (int cardindex = 0; cardindex  < deck.length; ++cardindex) {
			
			int ran = cardindex + rand.nextInt(deck.length - cardindex);
			
			Card holder = deck[ran];
			deck[ran] = deck[cardindex];	
			deck[cardindex] = holder;
			
		}
	}
	
	
	//DEAL CARDS TO ANY DESIGNATED PLAYER
	
	public Card deal_cards() {
		
		Card new_card = deck[next_card];
		++next_card;
		return new_card;
		
	}
	
	
	//ITERATES OVER AND PRINTS THE CURRENT ORDER OF THE DECK (INCLUDES "USED" CARDS)
	
	public void print_deck() {
		for (int i = 0; i < 52; ++i) {
			System.out.print(deck[i].rank + " ");
			System.out.println(deck[i].suit);
			
		}
	
	}
	
	//PRINT COMMUNITY CARDS
	
	public void print_community(int cards) {
		
		for (int i = 0; i < cards; ++i ) {
			
			int rank = community_cards[i].rank;
			int suit = community_cards[i].suit;
			
			String rankstring = "";
			String suitstring = "";
			
			if (rank == 11) rankstring = "J"; 
			else if (rank == 12) rankstring = "Q"; 
			else if (rank == 13) rankstring = "K"; 
			else if (rank == 14) rankstring = "A";
			else rankstring = String.valueOf(rank);
			
			if (suit == 1) suitstring = "Diamond";
			if (suit == 2) suitstring = "Heart";
			if (suit == 3) suitstring = "Spade";
			if (suit == 4) suitstring = "Club";
			
			ShowFigures.GetFigures(suitstring, rankstring);
			
		}
		
	}
}
