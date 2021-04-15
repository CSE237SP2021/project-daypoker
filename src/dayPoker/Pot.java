package dayPoker;

public class Pot {
	
	
	//POT OBJECTS NEEDS AN AMOUNT IN THE POT, THE CURRENT OUTSTANDING BET, 
	//WHO TAKES THE LAST ACTION, AND THE NUMBER OF PLAYERS IN THE POT

	public int player_count = 2;
	public int inPot;
	public int outstanding_bet;
	public int last_action;
	
	Pot(){
		last_action = player_count + 1;
	};
	
	
	
	
	
	
}
