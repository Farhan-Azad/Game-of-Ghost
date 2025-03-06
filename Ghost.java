package GameofGhost;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ghost {

	public static void main(String[] args) {
		String[] words = new String[279496];
		
		//reading all words into the array
		try {
			Scanner wordInput = new Scanner(new File("words.txt"));
			for(int i = 0; i < words.length; i++) {
				words[i] = wordInput.next();
			}
		} 
		//reading all words into the array
		
		
		//in case the files isn't found
		catch (FileNotFoundException e) {
			System.out.println("File Not Found");
			return;
		}
		//in case the files isn't found
		
		
		//get number of players
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the number of players: ");
		int numPlayers = input.nextInt();
		
		ArrayList <Player> names = new ArrayList <>(); 
		System.out.println("Enter the names of the players: ");
		
		
		//Asking name individually and creating a player for each of them 
		for (int i = 0; i<numPlayers ; i++) {
			System.out.print("Name of Player " + (i+1) + " = " );
			String name = input.next();
            names.add(new Player(name));
		}
		//Asking name individually and creating a player for each of them
		 
		
		//setting variables
		int currentPlayer = 0;
		String letters = "";
		boolean finished = false;
		Player winner = null;
		//setting variables
		
		
	// main loop for the game
	while(!finished) {
			
			
			//determining current player
			currentPlayer++;
			if(currentPlayer > numPlayers) {
				currentPlayer = 1;
			}
			//determining current player
			
			
			//current player enters a letter or *
			System.out.println(  (names.get(currentPlayer-1).toString() ) + ", it's your turn. The letters are " + letters + ". Enter a letter or enter * to challenge.");
			String letter = input.next();
			
			
			//if * is entered
			if(letter.equals("*")) {
				boolean valid = false;
				for(String word : words) {
					if(word.length() >= 4 && word.length() > letters.length() && word.substring(0, letters.length()).equals(letters)) {
						
						
						//Telling the function that this player lost, and adding a letter in their name
						names.get(currentPlayer-1).loseRound();
						names.get(currentPlayer-1).toString();
						//Telling the function that this player lost, and adding a letter in their name
						
						
						System.out.println(word + " begins with those letters. " + (names.get(currentPlayer-1)) + " loses the round.");
						
					
						//Just spacing the main output
						System.out.println();
						System.out.println();
						//Just spacing the main output
						
						
						//Refreshing everything
						valid = true;
						currentPlayer = 0;
						letters = "";
						//Refreshing everything
						
						
						break;
					}
				}
				if(!valid) {
					
					//setting up the previous player
					int prevPlayer = currentPlayer - 1;
					if(prevPlayer == 0) {
						prevPlayer = numPlayers;
					}
					//setting up the previous player
					
					
					//Telling the function that this player lost, and adding a letter in their name
					names.get(prevPlayer-1).loseRound();
					names.get(prevPlayer-1).toString();
					//Telling the function that this player lost, and adding a letter in their name

					
					System.out.println("No word begins with those letters. Player " + (names.get(prevPlayer-1)) + " loses the round.");
					
					
					//Just spacing the main output
					System.out.println();
					System.out.println();
					//Just spacing the main output
					
					
					//Refreshing everything
					currentPlayer = 0;
					letters = "";
					//Refreshing everything
				}
				
			//if letter is entered
			}
			else {
				letters += letter;
				for(String word : words) {
					if(word.length() >= 4 && letters.equals(word)) {

						//Telling the function that this player lost, and adding a letter in their name
						names.get(currentPlayer-1).loseRound();
						names.get(currentPlayer-1).toString();
						//Telling the function that this player lost, and adding a letter in their name
						
						
						System.out.println(letters + " is a word. Player " + (names.get(currentPlayer-1)) + " loses the round.");			
						
						
						//Just spacing the main output
						System.out.println();
						System.out.println();
						//Just spacing the main output
						
						
						//Refreshing everything
						currentPlayer = 0;
						letters = "";
						//Refreshing everything
						
						
						break;
					}
				}
			}
		
		//end of each round
		
		
		//checking if someone got eliminated or not
		for (int i = 0; i < names.size(); i++) {
			if (names.get(i).isEliminated()) {
				System.out.println( "Unfortunately, " + ( names.get(i).toString() ) + " is eliminated!");
				System.out.println();
				names.remove(i);
				numPlayers--;
				//number of players -- because someone got eliminated, there will be one less player now
			}
		}
		//checking if someone got eliminated or not
		
		
		//if there is only one player left after eliminating everyone, that one player is the winner
		if (names.size()==1) {
			winner = names.get(0);
			finished = true;
		}
	}
	//end of the game 
	
	
	System.out.println( winner + " is victorious!");
	System.out.println("Congratulations to " + winner);
	input.close();
  }
}	