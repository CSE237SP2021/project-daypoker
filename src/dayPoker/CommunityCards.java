package dayPoker;

public class CommunityCards {
	
	public Card [] table_cards = new Card[5];
	
	CommunityCards() {};
	
	public void print_community(int cards) {
		
		for (int i = 0; i < cards; ++i ) {
			
			int rank = table_cards[i].rank;
			int suit = table_cards[i].suit;
			
			String rankstring = "";
			String suitstring = "";
			
			if (rank == 11) rankstring = "J"; 
			else if (rank == 12) rankstring = "Q"; 
			else if (rank == 13) rankstring = "K"; 
			else if (rank == 14) rankstring = "A";
			else rankstring = String.valueOf(rank);
			
			if (suit == 1) suitstring = "d";
			if (suit == 2) suitstring = "h";
			if (suit == 3) suitstring = "s";
			if (suit == 4) suitstring = "c";
			
			System.out.print(rankstring + suitstring + " ");
			
		}
		
		
		
		
	}

}
