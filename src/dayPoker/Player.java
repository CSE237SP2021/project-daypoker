package dayPoker;

public class Player {

	public Card [] cards = new Card[2];
	public int  chip_stack;
	public boolean turn;
	public String name;
	int money_in_pot;
	
	Player(String player_name) {
		chip_stack = 100;
		turn = true;
		name = player_name;
		money_in_pot = 0;
	};
	
	public void print_hand() {
		
		for (int i = 0; i < cards.length; ++i ) {
			
			int rank = cards[i].rank;
			int suit = cards[i].suit;
			
			String rankstring = "";
			String suitstring = "";
			
			if (rank == 11) rankstring = "Jack"; 
			else if (rank == 12) rankstring = "Queen"; 
			else if (rank == 13) rankstring = "King"; 
			else if (rank == 14) rankstring = "Ace";
			else rankstring = String.valueOf(rank);
			
			if (suit == 1) suitstring = "Diamonds";
			if (suit == 2) suitstring = "Hearts";
			if (suit == 3) suitstring = "Spades";
			if (suit == 4) suitstring = "Clubs";
			
			System.out.println("You have the " + rankstring + " of " + suitstring + ".");
			
		}
		
		
		
	}
}
