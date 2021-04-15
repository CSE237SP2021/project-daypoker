package dayPoker;

import java.io.*;


public class ShowFigures {
	

	public static void GetFigures(String cardsuit, String cardindex) {
		String FilePath = ".\\card_figures\\Figure_" + cardsuit + "_" + cardindex + ".txt";
		

		try (BufferedReader Figure = new BufferedReader(new FileReader(FilePath))) {
			String line;
			 while ((line = Figure.readLine()) != null) {
			   System.out.println(line);
			 }
		}
		catch (IOException ie) {
			ie.printStackTrace();
		}
		 	
		System.out.print("");
	}
		
/*	example
 * public static void main(String[] args) throws Exception
	  {
	    String cardsuit = "Heart";
	    String cardindex = "10";
	    GetFigures(cardsuit, cardindex);
	  } 
	  
	  *output be like:
* _________ 
 |10       |
 |  __ __  |
 | (  v  ) |
 |  \   /  |
 |    v    |
 |_________|
	  */

}
