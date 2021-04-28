package dayPokerTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

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
	void initial_fold_test() {
		
		// INPUTS: "fold"
		
		Game game = new Game();
		game.game_init();
		game.game_loop(1);
		
		// 101 because gains opponents ante
		
		assertEquals(101, game.player_two.chip_stack);
	}
	
	@Test
	void bet_raise_call() {
		
		// INPUTS: "bet 10, raise 30, call"
		
		Game game = new Game();
		game.game_init();
		game.preflop();
		
		// 62 because ante + 30 + 30
		
		assertEquals(62, game.main_pot.inPot);
	}
	
	
	@Test
	void bet_raise_fold() {
		
		// INPUTS: "bet 10, raise 30, fold"
		
		Game game = new Game();
		game.game_init();
		game.preflop();
		
		// 111 because ante + bet of 10 gained
		
		assertEquals(111, game.player_two.chip_stack);
	}
	
	@Test
	void check_check() {
		
		// INPUTS: "check, check "
		
		Game game = new Game();
		game.game_init();
		game.preflop();
		
		// 2 only money in pot is from ante
		
		assertEquals(2, game.main_pot.inPot);
	}

	@Test
    void nonfacecard_print() {

        // INPUTS: "10 , Hearts"

        ShowFigures.GetFigures("Heart", "10");

        // Asserting no error is thrown/visual confirmation

        assertEquals(true,true);
    }

    @Test
    void facecard_print() {

        // INPUTS: "Jack , Spades"

        ShowFigures.GetFigures("Spade", "J");

        // Asserting no error is thrown/visual confirmation

        assertEquals(true,true);
    }
}
