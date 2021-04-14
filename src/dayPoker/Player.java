package dayPoker;

public class Player {
	
	
	//PLAYERS NEED A HAND, CHIP STACK, PLAYER NAME, INDICATOR OF THEIR TURN, AND A TRACKER FOR THEIR MONEY IN THE POT

	public Card [] cards = new Card[2];
	public int  chip_stack;
	public boolean turn;
	public String name;
	public int money_in_pot;
	
	Player(String player_name) {
		chip_stack = 100;
		turn = true;
		name = player_name;
		money_in_pot = 0;
	};
	
	
	// PRINTS THE HAND OF THE GIVEN PLAYER
	
	public void print_hand() {
		
		for (int i = 0; i < cards.length; ++i ) {
			
			int rank = cards[i].rank;
			int suit = cards[i].suit;
			
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
