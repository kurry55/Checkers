package project3;

//import java.util.List;

public class AIPlayer extends Player{
	/**
	 * private fields of the class
	 */
	
	private String name;
	private Player opponent;
	
	
	/**
	 * 
	 * @return opponent
	 */
	public Player getOpponent() {
		return opponent;
	}
	/*
	 * set opponent
	 */
	public void setOpponent(Player opponent) {
		this.opponent=opponent;
	}
	/**
	 * adding ai to the name and overriding toString
	 */
	public String toString() {
		return name + "" + "(AI)";
	}
	/**
	 * 
	 * @param name
	 * @param opponent
	 */
	public AIPlayer(String name, Player opponent) {
		this.name=name;
		this.opponent=opponent;
	}
	/**
	 * 
	 * @param arguements
	 * @param depth
	 * @return minimum value
	 */
	public double minValue(MiniCheckers arguements, int depth) {
		
		//System.out.println("Hello3");
		//base case to check if the opponent won
		if (arguements.checkWin(opponent)==true) {
			return 10.0;
			//base case to check if the opponent lost
		} else if (arguements.checkLose(opponent)==true) {
			return -10.0;
			//if it reaches the depth then we calculate the strength
		} else if (depth<1) {
			//assigning pieces based on the opponent color
			char smallPiece;
			char kingPiece;
			char oppsmallPiece;
			char oppkingPiece;
			//sets the opponent based on their color
			if (this==arguements.getRed()) {
				 smallPiece = 'r';
				 kingPiece = 'R';
				 oppsmallPiece = 'b';
				 oppkingPiece = 'B';
			} else {
				 smallPiece = 'b';
				 kingPiece = 'B';
				 oppsmallPiece = 'r';
				 oppkingPiece = 'R';
			}
			//calculating the number of checkers remaining 
			int numsmallPiece = arguements.countChecker(smallPiece);
			int numkingPiece = arguements.countChecker(kingPiece);
			int numoppsmallPiece = arguements.countChecker(oppsmallPiece);
			int numoppKingPiece = arguements.countChecker(oppkingPiece);
			//formula for calculating the value
			int retVal = (numsmallPiece+(3*numkingPiece))-(numoppsmallPiece+(3*numoppKingPiece));
			return retVal;
			//System.out.println(retVal);
		} else {
			// initializing the value
			double min_value = 0.0;
			// a counter to check if the desired number is found
	        boolean foundNum = false;
	        for (int i=0;i<arguements.possibleMoves(this).size(); i++) {
	        	//loops through all the possible moves and goes into the block only if the number is not found
	        	//if found it will return the minimum value
	            if (foundNum==false||maxValue(arguements.possibleMoves(this).get(i),depth- 1)< min_value) {
	            	//if found then it will assign the score which is the lowest and worst case based on the output
	                min_value= maxValue(arguements.possibleMoves(this).get(i),depth- 1); 
	                //sets the val to true to stop the condition
	                foundNum=true; 
	            }
	        }
	        return min_value;
	    }
	}
	/**
	 * 
	 * @param arguements
	 * @param depth
	 * @return maximum value
	 */
	public double maxValue(MiniCheckers arguements, int depth) {
		
		//base case to check if the opponent won
		if (arguements.checkWin(opponent)==true) {
			return 10.0;
			//base case to check if the opponent lost
		} else if (arguements.checkLose(opponent)==true) {
			return -10.0;
			//if it reaches the depth then we calculate the strength
		} else if (depth<1) {
			//assigning pieces based on the opponent color
			char smallPiece;
			char kingPiece;
			char oppsmallPiece;
			char oppkingPiece;
			//sets the opponent based on their color
			if (this==arguements.getRed()) {
				smallPiece = 'r';
				kingPiece = 'R';
				oppsmallPiece = 'b';
				oppkingPiece = 'B';
			} else {
				smallPiece = 'b';
				kingPiece = 'B';
				oppsmallPiece = 'r';
				oppkingPiece = 'R';
			}
			//calculating the number of checkers remaining 
			int numsmallPiece = arguements.countChecker(smallPiece);
			int numkingPiece = arguements.countChecker(kingPiece);
			int numoppsmallPiece = arguements.countChecker(oppsmallPiece);
			int numoppKingPiece = arguements.countChecker(oppkingPiece);
			//formula for calculating the value
			int retVal = (numsmallPiece+(3*numkingPiece))-(numoppsmallPiece+(3*numoppKingPiece));
			return retVal;
		} else {
			// initializing the value
			double max_value = 0.0;
			//counter to check if the number is found
	        boolean foundNum = false;
	        //looping through all the possible moves
	        for (int i =0; i< arguements.possibleMoves(this).size();i++) {
	        	//executes the block if the number is not found and decreases its depth whenever the method is called
	            if (foundNum==false||maxValue(arguements.possibleMoves(this).get(i),depth- 1)< max_value) {
	            	//it assigns the value which will get the best result for AI and get the best cases possible
	                max_value =maxValue(arguements.possibleMoves(this).get(i),depth- 1); 
	                foundNum= true; 
	            }
	        }
	        return max_value;
	    }
	}
	
	

	@Override
	/**
	 * overriding the choosemove method
	 */
	public MiniCheckers chooseMove(MiniCheckers arguements) {
		
	    int j = 0;
	    double highestNum = maxValue(arguements.possibleMoves(this).get(0), 1); 
	    boolean foundBestValue = false; 
	    //looping through all the possible moves and starting from one because there the first one is 
	    //done in the starting
	    for (int i=1; i<arguements.possibleMoves(this).size();i++) { 
	    	//checks if the best valur is found or if the current move gets a better value than before
	        if (foundBestValue==false||maxValue(arguements.possibleMoves(this).get(i), 10)>highestNum) {
	        	//if it is founf the highest number is updated
	            highestNum = maxValue(arguements.possibleMoves(this).get(i), 1);
	            //the next move is ready by assigning the value to i
	            j=i;
	            foundBestValue = true; 
	        }
	    }
	    //returns all the possible moves after evaluation
	    return arguements.possibleMoves(this).get(j);
	}




	@Override
	public double boardValue(MiniCheckers arguements) {
		//calls the method directly to assign depth and start the method.
		return maxValue(arguements, 10);
		

}
}
