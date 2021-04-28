package dayPoker;
import java.util.Scanner;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {
	
	
	//------------RETURN TYPE CONSTANTS------------------//
	
	public static class Constants {
	    public static int success = 0;
	    public static int fold_success = 1;
	    public static int bet_success = 2;
	    public static int bet_failure = 3;
	    public static int raise_success = 4;
	    public static int raise_failure = 5;
	}
	
	//------------CLASS VARIABLES------------------//
	
	boolean player_toact;
	
	boolean looped;
	
	boolean fold;
	
	boolean turn_order = true;
	
	Scanner myObj = new Scanner(System.in);
	
	public ArrayList<Player> players_in = new ArrayList<Player>();
	
	int games_to_play = 0;
	
	
	
	// ----------INSTATIATE OTHER CLASS OBJECTS FOR MAIN GAMEPLAY-------------- //

	public Deck maindeck = new Deck();
	
	public Player player_one = new Player("Player 1");
	
	public Player player_two = new Player("Player 2");
	
	public Pot main_pot = new Pot();
	
	public ShowFigures figure_printer = new ShowFigures();
	
	public HandEval eval_factory = new HandEval(maindeck);
	
	
	
	public static void main(String[] args) {
	
		
	   Game game = new Game();
	    
	   game.print_intro();
	    
	    game.game_init();
	    
	    game.game_loop(game.games_to_play);
	   
	    
	}
	
	public void print_intro() {
		
		System.out.println();
		System.out.println();
		
		int width = 150;
		int height = 24;
		
		BufferedImage bufferedImage = new BufferedImage(
				  width, height, 
				  BufferedImage.TYPE_INT_RGB);
		
		Graphics graphics = bufferedImage.getGraphics();
		
		 graphics.setFont(new Font("SansSerif", Font.BOLD, 24));
		
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
		  RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		graphics2D.drawString("DAYPOKER", 10, 20);
		
		for (int y = 0; y < height; y++) {
		    StringBuilder stringBuilder = new StringBuilder();

		    for (int x = 0; x < width; x++) {
		        stringBuilder.append(bufferedImage.getRGB(x, y) == -16777216 ? "*" : " ");
		    }

		    if (stringBuilder.toString().trim().isEmpty()) {
		        continue;
		    }

		    System.out.println(stringBuilder);
		}
		
		System.out.println();
		System.out.println();
		
		System.out.println("Welcome to dayPoker. How many hands of Texas Hold'em would you like to play?");
		
		String action = myObj.nextLine();
		
		games_to_play = Integer.parseInt(action.toString());
			
	}
	
	
	public void hand_test() {
		
		maindeck.shuffle_deck();
		
		
		for (int i = 0; i < 2; ++i) {
			player_one.cards[i] = maindeck.deal_cards();
			System.out.println(player_one.cards[i].rank);
			player_two.cards[i] = maindeck.deal_cards();
		}
	
		
		for (int i = 0; i < 5; ++i) {
			maindeck.community_cards[i] = maindeck.deal_cards();
			System.out.println(maindeck.community_cards[i].rank);
		}
		
		Card [] first_hand = new Card[7];
		
		for (int i = 0; i < 5; ++ i) {
    		first_hand[i] = maindeck.community_cards[i];
    	}
    	
    	for (int i = 5; i < 7; ++ i) {
    		first_hand[i] = player_one.cards[i - 5];
    	}
    	
    	Arrays.sort(first_hand);
    	
		eval_factory.eval_chain(first_hand);
		
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
		player_one.money_in_pot = 0;
		player_two.money_in_pot = 0;
		main_pot.last_action = 1;
	}
	
	public void game_loop(int times) {
		
		int counter = 0;
		
		while (player_one.chip_stack != 0 && player_two.chip_stack != 0 && counter < times) {
			
			++counter;
			
			turn_order = !turn_order;
			
			if (players_in.size() == 1 && players_in.get(0).equals(player_one)) players_in.add(player_two);
			if (players_in.size() == 1 && players_in.get(0).equals(player_two)) players_in.add(player_one);
			fold = false;
		
			System.out.println("");
			System.out.println("A new hand will now begin:");
			
			if (!fold) preflop();
		
			if (!fold) flop();

			if (!fold) turn();
			
			if (!fold) river();
		 
		}
		
	}
	
	
	// ----------PREFLOP BETTING ROUND-------------- //
	
	public void preflop() {
		
		street_init();
		
		// EACH PLAYERS PUTS UP A 1 CHIP ANTE
		
		for (int i  = 0; i < players_in .size(); ++i) {
			players_in.get(i).chip_stack -= 1;
			main_pot.inPot += 1;
		}
		
		
		// GIVE PLAYERS THEIR HOLE CARDS
		
		for (int i = 0; i < 2; ++i) {
			player_one.cards[i] = maindeck.deal_cards();
			player_two.cards[i] = maindeck.deal_cards();
		}
		
		int result = action();
		
		if (result == Constants.fold_success) fold = true;

	}
	
	
	// ----------FLOP BETTING ROUND-------------- //
	
	public void flop() {
		
		street_init();
		
		System.out.println("Here is the flop.");
		for (int i = 0; i < 3; ++i) {
			maindeck.community_cards[i] = maindeck.deal_cards();
		}
		
		maindeck.print_community(3);
		
		System.out.println("");
		
		int result = action();
		
		if (result == Constants.fold_success) fold = true;
		
		
	}
	
	
	// ----------TURN BETTING ROUND-------------- //
	
	public void turn() {
		
		street_init();
		
		System.out.println("Here is the turn.");
		maindeck.community_cards[3] = maindeck.deal_cards();

		// SHOW THE TURN CARD
		maindeck.print_community(4);
		
		System.out.println("");
		
		int result = action();
		
		if (result == Constants.fold_success) fold = true;
		
	}
	
	
	// ----------RIVER BETTING ROUND-------------- //
	
	public void river() {
		
		street_init();
		
		System.out.println("Here is the river.");
		maindeck.community_cards[4] = maindeck.deal_cards();
		
		maindeck.print_community(5);
		
		System.out.println("");
		
		int result = action();
		
		if (result == Constants.fold_success) fold = true;
		
		manage_win();
		
		if (turn_order) players_in.remove(player_two);
		
		else players_in.remove(player_one);
		
	}
	
	public void manage_win() {
		
		System.out.println();
		
		System.out.println("Player One's hand: ");
		
		player_one.print_hand();
		
		System.out.println();
		
		System.out.println("Player Two's hand: ");
		
		player_two.print_hand();
		
		
		int winner = eval_factory.compare_hands(player_one.cards, player_two.cards, maindeck.community_cards);
		
		if (winner == 1) {
			System.out.println("Player One, you had the better hand! The pot of " + main_pot.inPot + " chips has been pushed your way.");
			player_one.chip_stack += main_pot.inPot;
			main_pot.inPot = 0;
		}
		
		if (winner == 2) {
			System.out.println("Player Two, you had the better hand! The pot of " + main_pot.inPot + " chips has been pushed your way.");
			player_two.chip_stack += main_pot.inPot;
			main_pot.inPot = 0;
		}
		
		if (winner == 0) {
			System.out.println("Looks like a chopped pot! You both will get half of what's in the middle (" + main_pot.inPot + ")");
			player_one.chip_stack += main_pot.inPot/2;
			player_two.chip_stack += main_pot.inPot/2;
			main_pot.inPot = 0;
		}
		
		
		
	}
	
	
	//-----------DESIGNATION FUNCTION FOR ALL ACITIONS A PLAYER MAY TAKE--------------//
	
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
		 
					if (action.equals("fold")) return fold(i);
		 
					
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
						int bet_return = bet(i, action);
						if (bet_return == Constants.bet_success) break;
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
						int raise_return = raise(i,action);
						if (raise_return == Constants.raise_success) break;
					}
		 
			   }
		}
		
		
	}
	
		return Constants.success;
		
	}
	
	
	//-----------ACTION "FOLD" DECLARED---------------//
	
	public int fold (int player_index) {
		
		 players_in.remove(player_index);
		 
		 if (players_in.size() == 1 && player_index == 0) {
			 System.out.println("Player 2" + ", you have won! " + main_pot.inPot + " chips have been added to your stack.");
			 Player winner = player_two;
			 winner.chip_stack += main_pot.inPot;
		 }
		 if (players_in.size() == 1 && player_index == 1) {
			 System.out.println("Player 1" + ", you have won! " + main_pot.inPot + " chips have been added to your stack.");
			 Player winner = player_one;
			 winner.chip_stack += main_pot.inPot;
		 }
		 
		 return Constants.fold_success;
		
	}
	
	
	//-----------ACTION "BET X" DECLARED---------------//
	
	public int bet (int player_index, String action) {
		
		String bet_size_s = action.split(" ")[1];
		
		int bet_size = Integer.parseInt(bet_size_s);
			 
			 System.out.println(players_in.get(player_index).chip_stack);
			 
			 if (bet_size <= players_in.get(player_index).chip_stack) {
				 
				 main_pot.inPot += bet_size;
				 
				 players_in.get(player_index).chip_stack -= bet_size;
				 players_in.get(player_index).money_in_pot = bet_size;
				 
				 main_pot.outstanding_bet = bet_size;
				 main_pot.last_action = player_index;
				 
				 System.out.println("You bet " + bet_size + " chips.");
				 System.out.println("There are " + main_pot.inPot + " chips in the pot.");

				 return Constants.bet_success;
				 
			 }
			
			 else System.out.println("You do not have enough chips to bet that amount.");
			 
		return Constants.bet_failure;
		
	}
	
	
	//-----------ACTION "RAISE X" DECLARED---------------//
	
	public int raise (int player_index, String action) {
			
		String raise_size_s = action.split(" ")[1];
			 
		int raise_size = Integer.parseInt(raise_size_s);
			 
		if (raise_size <= players_in.get(player_index).chip_stack) {
				 
			if (main_pot.outstanding_bet > 0 && raise_size >= main_pot.outstanding_bet * 2) {
				 
				main_pot.inPot += raise_size;
				players_in.get(player_index).chip_stack -= raise_size;
					 
				main_pot.outstanding_bet = raise_size;
				main_pot.last_action = player_index;
					 
				System.out.println("You raised to " + raise_size + " chips.");
				System.out.println("There are " + main_pot.inPot + " chips in the pot.");
					 
				return Constants.raise_success;
				
			}
		
			else {
				System.out.println("You cannot raise in this instance.");
				return Constants.raise_failure;
			}
				 
		}
			 
		else {
			System.out.println("You do not have enough chips to raise this amount.");
			return Constants.raise_failure;
		}
					
	}
	
}
