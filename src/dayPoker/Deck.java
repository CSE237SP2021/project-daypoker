package dayPoker;

import java.util.Random;

public class Deck {

	public Card[] deck;
	public int next_card;
	
	
	
	public Deck() {
		
		next_card = 0;
		
		deck = new Card[52];
		
		int counter = 0;
		
		for (int cardsuit = 1; cardsuit <= 4; ++cardsuit) {
			for (int cardrank = 2; cardrank <= 14; ++cardrank) {
				deck[counter] = new Card(cardrank, cardsuit);
				++counter;
			}
			
		}
		
	}
	
	public void shuffle_deck() {
		
		 Random rand = new Random();	
		
		for (int cardindex = 0; cardindex  < deck.length; ++cardindex) {
			
			int ran = cardindex + rand.nextInt(deck.length - cardindex);
			
			Card holder = deck[ran];
			deck[ran] = deck[cardindex];	
			deck[cardindex] = holder;
			
			
			
		}
	}
	
	public Card deal_cards() {
		
		Card new_card = deck[next_card];
		++next_card;
		return new_card;
		
	}
	
		
	public void print_deck() {
		for (int i = 0; i < 52; ++i) {
			System.out.print(deck[i].rank + " ");
			System.out.println(deck[i].suit);
			
	}
	
	}
}
