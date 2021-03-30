package dayPoker;
import java.util.Scanner; 
import java.util.ArrayList;

public class Game {
	
	boolean player_toact;
	
	boolean looped;
	
	boolean fold;

	public Deck maindeck = new Deck();
	
	public Player player_one = new Player("Player 1");
	
	public Player player_two = new Player("Player 2");
	
	public Pot main_pot = new Pot();
	
	public CommunityCards table = new CommunityCards();
	
	 Scanner myObj = new Scanner(System.in);
	 
	 public ArrayList<Player> players_in = new ArrayList<Player>();
	 
	
	public static void main(String[] args) {
	
	    
	    Game game = new Game();
	    
	    game.game_init();
	    
	    game.game_loop(100);
	   
	    
	}
	
	public void game_init() {
		
		players_in.add(player_one);
		players_in.add(player_two);
		
		main_pot.inPot = 0;
		
		player_toact = true;
		looped = false;
		fold = false;
		
		maindeck.shuffle_deck();
		
	}
	
	public void street_init() {
		player_toact = true;
		looped = false;
		main_pot.inPot = 0;
		player_one.money_in_pot = 0;
		player_two.money_in_pot = 0;
		main_pot.last_action = 1;
	}
	
	public void game_loop(int times) {
		
		int counter = 0;
		
		while (player_one.chip_stack != 0 && player_two.chip_stack != 0 && counter < times) {
			
			++counter;
			
		if (players_in.size() == 1 && players_in.get(0).equals(player_one)) players_in.add(player_two);
		if (players_in.size() == 1 && players_in.get(0).equals(player_two)) players_in.add(player_one);
		fold = false;
		
		System.out.println("");
		System.out.println("A new hand will now begin:");
		if (!fold) {
		preflop();
		}
		street_init();
		if (!fold) {
			System.out.println("");
			flop();
		}
		street_init();
		if (!fold) {
			System.out.println("");
			turn();
		}
		street_init();
		if (!fold) {
		System.out.println("");
		river();
		}
		
		}
		
	}
	
	public void preflop() {
		
		// ante	
		for (int i  = 0; i < players_in .size(); ++i) {
			players_in.get(i).chip_stack -= 1;
			main_pot.inPot += 1;
		}
		
		
		// give players their hole cards
		for (int i = 0; i < 2; ++i) {
			player_one.cards[i] = maindeck.deal_cards();
			player_two.cards[i] = maindeck.deal_cards();
		}
		
		int result = action();
		
		if (result == 3) fold = true;

	}
	
	public void flop() {
		
		System.out.println("Here is the flop.");
		for (int i = 0; i < 3; ++i) {
			table.table_cards[i] = maindeck.deal_cards();
		}
		
		table.print_community(3);
		
		System.out.println("");
		
		int result = action();
		
		if (result == 3) fold = true;
		
		
	}
	
	public void turn() {
		
		System.out.println("Here is the turn.");
		table.table_cards[3] = maindeck.deal_cards();

		
		table.print_community(4);
		
		System.out.println("");
		
		int result = action();
		
		if (result == 3) fold = true;
		
	}
		
	
	public void river() {
		
		System.out.println("Here is the river.");
		table.table_cards[4] = maindeck.deal_cards();
		
		table.print_community(5);
		
		System.out.println("");
		
		int result = action();
		
		if (result == 3) fold = true;
		
		player_one.chip_stack += main_pot.inPot;
		
		System.out.println("Player One, you have won the hand! The pot of " + main_pot.inPot + " chips has been pushed your way.");
	}
	
	public int action() {
		
		while (player_toact) {
		
	for (int i  = 0; i < players_in.size(); ++i) {
		
			
			if (i == main_pot.last_action && looped) {
				player_toact = false;
				return 0;
			}
			
			if (i == players_in.size() - 1) looped = true;
			
			 System.out.println("Hello " + players_in.get(i).name + ", what is your action? (bet x, raise x, call, check, fold, see hand)");
			 
		while (true) {
			
			 String action = myObj.nextLine();
		
		 // FOLD
		 
		 if (action.equals("fold")) {
			 players_in.remove(i);
			 if (players_in.size() == 1 && i == 0) {
				 System.out.println("Player 2" + ", you have won! " + main_pot.inPot + " chips have been added to your stack.");
				 Player winner = player_two;
				 winner.chip_stack += main_pot.inPot;
				 return 3;
			 }
			 if (players_in.size() == 1 && i == 1) {
				 System.out.println("Player 1" + ", you have won! " + main_pot.inPot + " chips have been added to your stack.");
				 Player winner = player_one;
				 winner.chip_stack += main_pot.inPot;
				 return 3;
			 }
			 break;
		 }
		 
		 // SEE HAND
		 
		 else if (action.equals("see hand")) {
			 players_in.get(i).print_hand();
		 }
		 
		 // CHECK 
		 else if (action.equals("check")) {
			if (main_pot.outstanding_bet == 0 && i == 1) return 0;
			break;
		 }
		 
		 
		 // BET
		 
		 else if (action.toLowerCase().indexOf("bet") != -1) {
			 String bet_size_s = action.split(" ")[1];
			 int bet_size = Integer.parseInt(bet_size_s);
			 
			 System.out.println(players_in.get(i).chip_stack);
			 
			 if (bet_size <= players_in.get(i).chip_stack) {
				 main_pot.inPot += bet_size;
				 players_in.get(i).chip_stack -= bet_size;
				 players_in.get(i).money_in_pot = bet_size;
				 main_pot.outstanding_bet = bet_size;
				 main_pot.last_action = i;
				 System.out.println("You bet " + bet_size + " chips.");
				 System.out.println("There are " + main_pot.inPot + " chips in the pot.");
				 break;
			 }
			
			 else System.out.println("You do not have enough chips to bet that amount.");
			 
		 }
		 
		 // CALL
		 
		 else if (action.equals("call")) {
			 if (main_pot.outstanding_bet == 0) System.out.println("There is no outstanding bet to call.");
			 main_pot.inPot += main_pot.outstanding_bet - players_in.get(i).money_in_pot;
			 players_in.get(i).chip_stack -= main_pot.outstanding_bet - players_in.get(i).money_in_pot;
			 System.out.println("There are " + main_pot.inPot + " chips in the pot.");
			 break;
		 }
		 
		 // RAISE
		 
		 else if (action.toLowerCase().indexOf("raise") != -1) {
			 String raise_size_s = action.split(" ")[1];
			 int raise_size = Integer.parseInt(raise_size_s);
			 if (raise_size <= players_in.get(i).chip_stack) {
				 if (main_pot.outstanding_bet > 0 && raise_size >= main_pot.outstanding_bet * 2) {
					 main_pot.inPot += raise_size;
					 players_in.get(i).chip_stack -= raise_size;
					 main_pot.outstanding_bet = raise_size;
					 main_pot.last_action = i;
					 System.out.println("You raised to " + raise_size + " chips.");
					 System.out.println("There are " + main_pot.inPot + " chips in the pot.");
					 break;
				 }
				 else System.out.println("You cannot raise in this instance.");
				 
			 }
			 else System.out.println("You do not have enough chips to raise this amount.");
			 
			 
		 }
		 
	
		
		 
		}
	}
	}

		return 0;
		
	}
	
}
