
package project3;

public abstract class Player {
	//to provide implementation later in AIPlayer
	public abstract MiniCheckers chooseMove(MiniCheckers arguements);
	/**
	 * 
	 * @param current state of the game
	 */
	public double boardValue(MiniCheckers arguements) {
		//checks for win/loss situation
		if (arguements.checkWin(this)==true) {
			return 1.0;
		} else if (arguements.checkLose(this)==true) {
			return -1.0;
		} else {
			//for draw
			return 0.0;
		}
		
	}
}
