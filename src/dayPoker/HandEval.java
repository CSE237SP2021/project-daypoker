package dayPoker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;


public class HandEval {
	
	//EVAL RETURN TYPES
	
	public static class Eval_Constants {
	    public static int royal_flush = 10;
	    public static int straight_flush = 9;
	    public static int quads = 8;
	    public static int full_house = 7;
	    public static int flush = 6;
	    public static int straight = 5;
	    public static int three_of_a_kind = 4;
	    public static int two_pair = 3;
	    public static int one_pair = 2;
	    public static int high_card = 1;
	    public static int failure = 0;
	    
	}
	
	
	private Card [] first_hand = new Card[7];
	private Card [] second_hand = new Card[7];
	
	//EVALUATION HELPER ARRAYLISTS
	
	private ArrayList<Integer> diamond_helper = new ArrayList<Integer>();
	private ArrayList<Integer> heart_helper = new ArrayList<Integer>();
	private ArrayList<Integer> spade_helper = new ArrayList<Integer>();
	private ArrayList<Integer> club_helper = new ArrayList<Integer>();
	private ArrayList<Integer> straight_helper = new ArrayList<Integer>();
	
	int distinguish_var = 0;
	int first_playervar = 0;
	int second_playervar = 0;
	
	// Constructor
    HandEval()
    {
    }

    public Card[] prep_hand(Card[] hand, Card[] community_cards) {
    	
    	//CREATE HAND BASED ON HAND AND COMMUNITY CARDS
    	
    	Card [] only_hand = new Card[7];
    	
    	for (int i = 0; i < 5; ++ i) {
    		only_hand[i] = community_cards[i];
    		only_hand[i] = community_cards[i];
    	}
    	
    	for (int i = 5; i < 7; ++ i) {
    		only_hand[i] = hand[i - 5];
    		only_hand[i] = hand[i - 5];
    	}
    	
    	return only_hand;
    	
    }

    public int compare_hands(Card[] hand_one, Card[] hand_two, Card[] community_cards) {
    	
    	
    	first_hand = prep_hand(hand_one, community_cards);
    	second_hand = prep_hand(hand_two, community_cards);
    	
    	Arrays.sort(first_hand);
    	Arrays.sort(second_hand);
    	
    	//PASS BOTH HANDS TO CHAIN OF EVAL AND GET RETURN VALUES
    	
    	int first_result = eval_chain(first_hand);
    	
    	first_playervar = distinguish_var;
    	
    	distinguish_var = 0; 
    	
    	int second_result = eval_chain(second_hand);
    	
    	second_playervar = distinguish_var;
    	
    	distinguish_var = 0; 
    	
    	if (first_result > second_result) return 1;
    	
    	else if (second_result > first_result) return 2;
    	
    	//GO TO OUR OTHER HEURISTIC TO DETERMINE WINNER
    	
    	else {
    		if (first_playervar > second_playervar) return 1;
    		else if (first_playervar < second_playervar) return 2;
    		else return 0;
    	}
	
    }
    
    private void reset_vars() {
    	
    	diamond_helper.clear();
    	heart_helper.clear();
    	spade_helper.clear();
    	club_helper.clear();
    	straight_helper.clear();
    	
    }
    
    public int eval_chain(Card[] player_hand) {
    	
    	//RUN DOWN ALL HAND VALUES FROM MOST VALUABLE TO LEAST VALUABLE
 
    	int result = 0;
    	
    	reset_vars();
    	
    	if (result == 0) result = royal_flush(player_hand);
    	
    	reset_vars();
    	
    	if (result == 0) result = straight_flush(player_hand);
    	
    	reset_vars();
    	
    	if (result == 0) result = quads(player_hand);
    	
    	if (result == 0) result = full_house(player_hand);
    	
    	if (result == 0) result = flush(player_hand);
    	
    	if (result == 0) result = straight(player_hand);
    	
    	if (result == 0) result = three_oak(player_hand);
    	
    	if (result == 0) result = pairs(player_hand);
    	
    	return result;
    	
    }
    
    private int royal_flush(Card[] player_hand) {
    	
    	ArrayList<Integer> list_name = new ArrayList<>();
    	
    	int flush_result = flush(player_hand);
    	
    	//MAKE SURE A FLUSH EXISTS
    	
    	if (flush_result == Eval_Constants.flush) {
    		if (heart_helper.size() >= 5) {
    			list_name = heart_helper;
    		}
    		else if (diamond_helper.size() >= 5) {
    			list_name = diamond_helper;
    		}
    		else if (spade_helper.size() >= 5) {
    			list_name = spade_helper;
    		}
    		else if (club_helper.size() >= 5) {
    			list_name = club_helper;
    		}
    		else return 0;
    	}
    	
    	//MAKE SURE EXISTING FLUSH IS ROYAL
    	
    	if (list_name.contains(10) && list_name.contains(11) 
    		&& list_name.contains(12) && list_name.contains(13) 
    		&& list_name.contains(14)) return Eval_Constants.royal_flush;
    	
    	return 0;
    	
    }
    
    private int straight_flush(Card[] player_hand) {
    	
    	ArrayList<Integer> list_name = new ArrayList<>();
    	
    	int flush_result = flush(player_hand);
    	
    	//MAKE SURE A FLUSH EXISTS
    	
    	if (flush_result == Eval_Constants.flush) {
    		if (heart_helper.size() >= 5) {
    			list_name = heart_helper;
    		}
    		else if (diamond_helper.size() >= 5) {
    			list_name = diamond_helper;
    		}
    		else if (spade_helper.size() >= 5) {
    			list_name = spade_helper;
    		}
    		else if (club_helper.size() >= 5) {
    			list_name = club_helper;
    		}
    		else return 0;
    	}
    	else return 0;

    	//CONVERT ARRAY LIST BACK TO A HAND AND CHECK FOR STRAIGHT
    	
    	Card [] straight_hand = new Card[list_name.size()];
    	
    	for (int card = 0; card < list_name.size(); ++card) {
    		Card new_card = new Card (list_name.get(card), 1);
    		straight_hand[card] = new_card;
    	}
    	
    	int straight_result = straight(straight_hand);
    	
    	if (straight_result == Eval_Constants.straight) {
    		return Eval_Constants.straight_flush;
    	}
    	
    	return 0;
    	
    	
    }
    
    private int quads(Card[] player_hand) {
    	
    	int quad_counter = 0;
    	
    	//CHECK ALL RANKS FOR WHAT RANK OF QUADS WE MAY HAVE
    	
    	for (int test_rank = 0; test_rank <= 14; ++ test_rank) {
    		
    		quad_counter = 0;

    		for (int card = 0; card < player_hand.length; ++card) {
    		
    			if (test_rank == player_hand[card].rank) {
    			
    			++quad_counter;
    			
    			if (quad_counter == 4) {
    				distinguish_var = test_rank;
    				return Eval_Constants.quads;
    			}
    			
    			}
    		}
    		
    	}
    	
    	return Eval_Constants.failure;
	
    }
    
    private int full_house(Card[] player_hand) {
    	
    	int fh_counter = 0;
    	int fh_two = 0;
    	int fh_three = 0;

    	
    	for (int test_rank = 0; test_rank <= 14; ++ test_rank) {
    		
    		fh_counter = 0;
    		
    		for (int card = 0; card < player_hand.length; ++card) {

    			if (test_rank == player_hand[card].rank) {
    				
    				++fh_counter;
    				
    				//MAKE SURE WE HAVE ENOUGH FOR 3 OF A KIND
    				
    				if (fh_counter == 3) {
    					++fh_three;
    					distinguish_var = test_rank;
    				}
    				
    				//MAKE SURE WE HHAVE ENOUGH FOR 2 OF A KIND
    				
    				if (fh_counter == 2) {
    					++fh_two;
    				}
    			}
        	}
    	}
    	
    	if (fh_two >= 2 && fh_three >= 1) {
    		return Eval_Constants.full_house;
    	}
    	
    	return Eval_Constants.failure;
    	
    }
    
    private int flush(Card[] player_hand) {
    	
    	//ADD ALL CARDS TO RESPECTIVE HOLDER AND DETERMINE FLUSH BASED ON SIZE
    	
    	for (int card = 0; card < player_hand.length; ++ card) {
    		if (player_hand[card].suit == 1) diamond_helper.add(player_hand[card].rank);
    		if (player_hand[card].suit == 2) heart_helper.add(player_hand[card].rank);
    		if (player_hand[card].suit == 3) spade_helper.add(player_hand[card].rank);
    		if (player_hand[card].suit == 4) club_helper.add(player_hand[card].rank);
    		straight_helper.add(player_hand[card].rank);
    	}
    	
    	if (diamond_helper.size() >= 5) {
    		distinguish_var = diamond_helper.get(diamond_helper.size() - 1);
    		return Eval_Constants.flush;
    	}
    	if (heart_helper.size() >= 5) {
    		distinguish_var = heart_helper.get(heart_helper.size() - 1);
    		return Eval_Constants.flush;
    	}
    	if (spade_helper.size() >= 5) {
    		distinguish_var = spade_helper.get(spade_helper.size() - 1);
    		return Eval_Constants.flush;
    	}
    	if (club_helper.size() >= 5) {
    		distinguish_var = club_helper.get(club_helper.size() - 1);
    		return Eval_Constants.flush;
    	}
    	
    	return Eval_Constants.failure;
    	
    }
    
    
    private int straight(Card[] player_hand) {
    	
    	LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(straight_helper);
    	
    	 ArrayList<Integer> cards_nodupes = new ArrayList<>(hashSet);
    	
    	// A STRAIGHTS
    	
    	if (cards_nodupes.contains(14) && cards_nodupes.contains(2) && cards_nodupes.contains(3) && cards_nodupes.contains(4) &&  cards_nodupes.contains(5)) {
    		distinguish_var = 5;
    		return Eval_Constants.straight;
    	}
    	
    	//LOW STRAIGHT
    	
    	if (cards_nodupes.contains(14) && cards_nodupes.contains(10) && cards_nodupes.contains(11) && cards_nodupes.contains(12) &&  cards_nodupes.contains(13)) {
    		distinguish_var = 14;
    		return Eval_Constants.straight;
    	}
    	
    	//GENERAL STRAIGHTS FROM INDEX 2 - 7
    	
    	if (cards_nodupes.size() == 7 ) {
    		
    		int test_rank = cards_nodupes.get(2) + 1;
    		
    		int straight_counter = 0;
    		
    		for (int i = 3; i < 7; ++i) {
    			
    			if(cards_nodupes.get(i) == test_rank) ++straight_counter;
    			
    			if (straight_counter == 4) {
    				distinguish_var = cards_nodupes.get(6);
    				return Eval_Constants.straight;
    			}
    			
    			++test_rank;
    		}
    		
    	}
    	
    	//GENERAL STRAIGHTS FROM INDEX 1 - 6
    	
    	if (cards_nodupes.size() >= 6 ) {
    		
    		int test_rank = cards_nodupes.get(1) + 1;
    		
    		int straight_counter = 0;
    		
    		for (int i = 2; i < 6; ++i) {
    			
    			if(cards_nodupes.get(i) == test_rank) ++straight_counter;
    			
    			if (straight_counter == 4) {
    				distinguish_var = cards_nodupes.get(5);
    				return Eval_Constants.straight;
    			}
    			
    			++test_rank;
    		}
    		
    	}
    	
    	//GENERAL STRAIGHTS FROM INDEX 0 - 5
    	
    	if (cards_nodupes.size() >= 5) {
    		
    		int test_rank = cards_nodupes.get(0) + 1;
    		
    		int straight_counter = 0;
    		
    		for (int i = 1; i < 5; ++i) {
    			
    			if(cards_nodupes.get(i) == test_rank) ++straight_counter;
    			
    			if (straight_counter == 4) {
    				distinguish_var = cards_nodupes.get(4);
    				return Eval_Constants.straight;
    			}
    			
    			++test_rank;
    		}
    		
    		
    	}
    	
    	return Eval_Constants.failure;
 	
    }
    
    private int three_oak(Card[] player_hand) {
    	
    	int trips_counter = 0;
    	
    	for (int test_rank = 0; test_rank <= 14; ++ test_rank) {
    		
    		trips_counter = 0;
    		
    		// MAKE SURE ENOUGH WE HAVE ENOUGH FOR THREE OF A KIND

    		for (int card = 0; card < player_hand.length; ++card) {
    		
    			if (test_rank == player_hand[card].rank) {
    			
    			++trips_counter;
    			
    			if (trips_counter== 3) {
    				distinguish_var = test_rank;
    				return Eval_Constants.three_of_a_kind;
    			}
    			
    			}
    		}
    		
    	}
    	
    	return 0;
     	
    }
    
    private int pairs(Card[] player_hand) {
     	
    	int pair_counter = 0;
    	int two_p = 0;
    	
    	for (int test_rank = 2; test_rank <= 14; ++ test_rank) {
    		
    		pair_counter = 0;

    		for (int card = 0; card < player_hand.length; ++card) {
    		
    			if (test_rank == player_hand[card].rank) {
    			
    			++pair_counter;
    			
    			if (pair_counter== 2) {
    				distinguish_var = test_rank;
    				++two_p;
    			}
    			
    			}
    		}
    		
    	}
    	
    	//WE HAVE TWO PAIR
    	
    	if (two_p > 1) return Eval_Constants.two_pair;
    	
    	//WE HAVE ONE PAIR
    	
    	if (two_p == 1) return Eval_Constants.one_pair;
    	
    	//WE'VE HIT NOTHING SO FAR, SO WE HAVE HIGH CARD
    	
    	return Eval_Constants.high_card;
 
}
    
}