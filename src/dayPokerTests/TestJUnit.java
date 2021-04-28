package dayPokerTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import dayPoker.Card;
import dayPoker.Deck;
import dayPoker.Game;
import dayPoker.ShowFigures;

class TestJUnit {
	
//	boolean player_toact;
//	
//	boolean looped;
//	
//	boolean fold;
//
//	public Deck maindeck = new Deck();

//	
//	public Player player_one = new Player("Player 1");
//	
//	public Player player_two = new Player("Player 2");
//	
//	public Pot main_pot = new Pot();
//	
//	public CommunityCards table = new CommunityCards();
//	
//	 Scanner myObj = new Scanner(System.in);
//	 
//	 public ArrayList<Player> players_in = new ArrayList<Player>();
	 
	
	@Test
	void test_initial_fold() {
		
		// INPUTS: "fold"
		
		Game game = new Game();
		game.game_init();
		game.game_loop(1);
		
		// 101 because gains opponents ante
		
		assertEquals(101, game.player_two.chip_stack);
	}
	
	@Test
	void test_bet_raise_call() {
		
		// INPUTS: "bet 10, raise 30, call"
		
		Game game = new Game();
		game.game_init();
		game.preflop();
		
		// 62 because ante + 30 + 30
		
		assertEquals(62, game.main_pot.inPot);
	}
	
	
	@Test
	void test_bet_raise_fold() {
		
		// INPUTS: "bet 10, raise 30, fold"
		
		Game game = new Game();
		game.game_init();
		game.preflop();
		
		// 111 because ante + bet of 10 gained
		
		assertEquals(111, game.player_two.chip_stack);
	}
	
	@Test
	void test_check_check() {
		
		// INPUTS: "check, check "
		
		Game game = new Game();
		game.game_init();
		game.preflop();
		
		// 2 only money in pot is from ante
		
		assertEquals(2, game.main_pot.inPot);
	}

	@Test
	void test_onepair() {
		
		
		Game game = new Game();
		
		Card [] test_hand = new Card[7];
		
		Card card_one = new Card(10,3);
		Card card_two = new Card(10,4);
		Card card_three = new Card(12,1);
		Card card_four = new Card(14,2);
		Card card_five = new Card(2,4);
		Card card_six = new Card(4,2);
		Card card_seven = new Card(6,2);
		
		test_hand[0] = card_one;
		test_hand[1] = card_two;
		test_hand[2] = card_three;
		test_hand[3] = card_four;
		test_hand[4] = card_five;
		test_hand[5] = card_six;
		test_hand[6] = card_seven;
		
		//game.eval_factory.prep_hand(test_hand);
		
		int result = game.eval_factory.eval_chain(test_hand);
		

		assertEquals(2, result);
	}
	
	@Test
	void test_twopair() {
		
		
		Game game = new Game();
		
		Card [] test_hand = new Card[7];
		
		Card card_one = new Card(14,1);
		Card card_two = new Card(14,2);
		Card card_three = new Card(10,4);
		Card card_four = new Card(7,3);
		Card card_five = new Card(7,2);
		Card card_six = new Card(4,2);
		Card card_seven = new Card(6,4);
		
		test_hand[0] = card_one;
		test_hand[1] = card_two;
		test_hand[2] = card_three;
		test_hand[3] = card_four;
		test_hand[4] = card_five;
		test_hand[5] = card_six;
		test_hand[6] = card_seven;
		
	//	game.eval_factory.prep_hand(test_hand);
		
		int result = game.eval_factory.eval_chain(test_hand);

		assertEquals(3, result);
	}
	
	
	@Test
	void test_trips() {
		
		
		Game game = new Game();
		
		Card [] test_hand = new Card[7];
		
		Card card_one = new Card(3,1);
		Card card_two = new Card(3,2);
		Card card_three = new Card(3,3);
		Card card_four = new Card(8,4);
		Card card_five = new Card(11,1);
		Card card_six = new Card(14,2);
		Card card_seven = new Card(4,4);
		
		test_hand[0] = card_one;
		test_hand[1] = card_two;
		test_hand[2] = card_three;
		test_hand[3] = card_four;
		test_hand[4] = card_five;
		test_hand[5] = card_six;
		test_hand[6] = card_seven;
		
	//	game.eval_factory.prep_hand(test_hand);
		
		int result = game.eval_factory.eval_chain(test_hand);

		assertEquals(4, result);
	}
	
	@Test
	void test_Alow_straight() {
		
		
		Game game = new Game();
		
		Card [] test_hand = new Card[7];
		
		Card card_one = new Card(14,1);
		Card card_two = new Card(11,2);
		Card card_three = new Card(2,3);
		Card card_four = new Card(10,4);
		Card card_five = new Card(3,1);
		Card card_six = new Card(4,2);
		Card card_seven = new Card(5,4);
		
		test_hand[0] = card_one;
		test_hand[1] = card_two;
		test_hand[2] = card_three;
		test_hand[3] = card_four;
		test_hand[4] = card_five;
		test_hand[5] = card_six;
		test_hand[6] = card_seven;
		
		//game.eval_factory.prep_hand(test_hand);
		
		int result = game.eval_factory.eval_chain(test_hand);

		assertEquals(5, result);
	}
	
	@Test
	void test_Ahigh_straight() {
		
		
		Game game = new Game();
		
		Card [] test_hand = new Card[7];
		
		Card card_one = new Card(10,1);
		Card card_two = new Card(11,2);
		Card card_three = new Card(12,3);
		Card card_four = new Card(13,4);
		Card card_five = new Card(2,1);
		Card card_six = new Card(14,2);
		Card card_seven = new Card(7,4);
		
		test_hand[0] = card_one;
		test_hand[1] = card_two;
		test_hand[2] = card_three;
		test_hand[3] = card_four;
		test_hand[4] = card_five;
		test_hand[5] = card_six;
		test_hand[6] = card_seven;
		
		//game.eval_factory.prep_hand(test_hand);
		
		int result = game.eval_factory.eval_chain(test_hand);

		assertEquals(5, result);
	}
	
	@Test
	void test_general_straight() {
		
		
		Game game = new Game();
		
		Card [] test_hand = new Card[7];
		
		Card card_one = new Card(5,1);
		Card card_two = new Card(7,2);
		Card card_three = new Card(9,3);
		Card card_four = new Card(6,4);
		Card card_five = new Card(8,1);
		Card card_six = new Card(14,2);
		Card card_seven = new Card(7,4);
		
		test_hand[0] = card_one;
		test_hand[1] = card_two;
		test_hand[2] = card_three;
		test_hand[3] = card_four;
		test_hand[4] = card_five;
		test_hand[5] = card_six;
		test_hand[6] = card_seven;
		
		Arrays.sort(test_hand);
		
	//	game.eval_factory.prep_hand(test_hand);
		
		int result = game.eval_factory.eval_chain(test_hand);

		assertEquals(5, result);
	}
	
	@Test
	void test_flush() {
		
		
		Game game = new Game();
		
		Card [] test_hand = new Card[7];
		
		Card card_one = new Card(5,1);
		Card card_two = new Card(7,1);
		Card card_three = new Card(9,1);
		Card card_four = new Card(11,1);
		Card card_five = new Card(13,1);
		Card card_six = new Card(14,2);
		Card card_seven = new Card(7,4);
		
		test_hand[0] = card_one;
		test_hand[1] = card_two;
		test_hand[2] = card_three;
		test_hand[3] = card_four;
		test_hand[4] = card_five;
		test_hand[5] = card_six;
		test_hand[6] = card_seven;
		
		Arrays.sort(test_hand);
		
	//	game.eval_factory.prep_hand(test_hand);
		
		int result = game.eval_factory.eval_chain(test_hand);

		assertEquals(6, result);
	}
	
	
	@Test
	void test_full_house() {
		
		Game game = new Game();
		
		Card [] test_hand = new Card[7];
		
		Card card_one = new Card(6,1);
		Card card_two = new Card(10,1);
		Card card_three = new Card(6,2);
		Card card_four = new Card(10,2);
		Card card_five = new Card(13,1);
		Card card_six = new Card(6,3);
		Card card_seven = new Card(7,4);
		
		test_hand[0] = card_one;
		test_hand[1] = card_two;
		test_hand[2] = card_three;
		test_hand[3] = card_four;
		test_hand[4] = card_five;
		test_hand[5] = card_six;
		test_hand[6] = card_seven;
		
		Arrays.sort(test_hand);
		
		//game.eval_factory.prep_hand(test_hand);
		
		int result = game.eval_factory.eval_chain(test_hand);

		assertEquals(7, result);
	}
	
	@Test
	void test_straight_flush() {
		
		Game game = new Game();
		
		Card [] test_hand = new Card[7];
		
		Card card_one = new Card(4,1);
		Card card_two = new Card(5,1);
		Card card_three = new Card(6,1);
		Card card_four = new Card(7,1);
		Card card_five = new Card(8,1);
		Card card_six = new Card(10,3);
		Card card_seven = new Card(11,4);
		
		test_hand[0] = card_one;
		test_hand[1] = card_two;
		test_hand[2] = card_three;
		test_hand[3] = card_four;
		test_hand[4] = card_five;
		test_hand[5] = card_six;
		test_hand[6] = card_seven;
		
		Arrays.sort(test_hand);
		
	//	game.eval_factory.prep_hand(test_hand);
		
		int result = game.eval_factory.eval_chain(test_hand);

		assertEquals(9, result);
	}
	
	@Test
	void test_royal_flush() {
		
		Game game = new Game();
		
		Card [] test_hand = new Card[7];
		
		Card card_one = new Card(10,3);
		Card card_two = new Card(11,3);
		Card card_three = new Card(12,3);
		Card card_four = new Card(13,3);
		Card card_five = new Card(14,3);
		Card card_six = new Card(3,1);
		Card card_seven = new Card(5,2);
		
		test_hand[0] = card_one;
		test_hand[1] = card_two;
		test_hand[2] = card_three;
		test_hand[3] = card_four;
		test_hand[4] = card_five;
		test_hand[5] = card_six;
		test_hand[6] = card_seven;
		
		Arrays.sort(test_hand);
		
		//game.eval_factory.prep_hand(test_hand);
		
		int result = game.eval_factory.eval_chain(test_hand);

		assertEquals(10, result);
	}
	
	@Test
	void test_better_pair() {
		
		Game game = new Game();
		
		Card [] hand_one = new Card[2];
		Card [] hand_two = new Card[2];
		Card [] comm_cards = new Card[5];
		
		Card card_one = new Card(4,1);
		Card card_two = new Card(4,2);
		
		hand_one[0] = card_one;
		hand_one[1] = card_two;

		card_one = new Card(6,1);
		card_two = new Card(6,2);
		
		hand_two[0] = card_one;
		hand_two[1] = card_two;
		
		Card card_three = new Card(7,3);
		Card card_four = new Card(8,4);
		Card card_five = new Card(10,1);
		Card card_six = new Card(12,2);
		Card card_seven = new Card(14,3);
		
		comm_cards[0] = card_three;
		comm_cards[1] = card_four;
		comm_cards[2] = card_five;
		comm_cards[3] = card_six;
		comm_cards[4] = card_seven;
		
		
		int result = game.eval_factory.compare_hands(hand_one, hand_two, comm_cards);

		assertEquals(2, result);
	}
	
	@Test
	void test_better_twopair() {
		
		Game game = new Game();
		
		Card [] hand_one = new Card[2];
		Card [] hand_two = new Card[2];
		Card [] comm_cards = new Card[5];
		
		Card card_one = new Card(4,1);
		Card card_two = new Card(4,2);
		
		hand_one[0] = card_one;
		hand_one[1] = card_two;

		card_one = new Card(8,1);
		card_two = new Card(8,2);
		
		hand_two[0] = card_one;
		hand_two[1] = card_two;
		
		Card card_three = new Card(6,3);
		Card card_four = new Card(6,4);
		Card card_five = new Card(10,1);
		Card card_six = new Card(12,2);
		Card card_seven = new Card(14,3);
		
		comm_cards[0] = card_three;
		comm_cards[1] = card_four;
		comm_cards[2] = card_five;
		comm_cards[3] = card_six;
		comm_cards[4] = card_seven;
		
		
		int result = game.eval_factory.compare_hands(hand_one, hand_two, comm_cards);

		assertEquals(2, result);
	}
	
	@Test
	void test_better_trips() {
		
		Game game = new Game();
		
		Card [] hand_one = new Card[2];
		Card [] hand_two = new Card[2];
		Card [] comm_cards = new Card[5];
		
		Card card_one = new Card(4,1);
		Card card_two = new Card(4,2);
		
		hand_one[0] = card_one;
		hand_one[1] = card_two;

		card_one = new Card(8,1);
		card_two = new Card(8,2);
		
		hand_two[0] = card_one;
		hand_two[1] = card_two;
		
		Card card_three = new Card(4,3);
		Card card_four = new Card(8,3);
		Card card_five = new Card(10,1);
		Card card_six = new Card(12,2);
		Card card_seven = new Card(14,3);
		
		comm_cards[0] = card_three;
		comm_cards[1] = card_four;
		comm_cards[2] = card_five;
		comm_cards[3] = card_six;
		comm_cards[4] = card_seven;
		
		
		int result = game.eval_factory.compare_hands(hand_one, hand_two, comm_cards);

		assertEquals(2, result);
	}
}
