
package project3;

import java.util.ArrayList;
/**
 * all the fields for minicheckers
 *
 */
public class MiniCheckers {
    private char[][] board;
    private Player red;
    private Player black;
    /**
     * 
     * @param red
     * @param black
     * making a 5*5 board
     */

    public MiniCheckers(Player red, Player black) {
    	setRed(red);
    	setBlack(black);
    	//initializing the value and making the board
    	this.board=new char[5][5];
    	this.board[0][0]='r';
    	this.board[0][1]='.';
    	this.board[0][2]='r';
    	this.board[0][3]='.';
    	this.board[0][4]='r';
    	this.board[1][0]='.';
    	this.board[1][1]='_';
    	this.board[1][2]='.';
    	this.board[1][3]='_';
    	this.board[1][4]='.';
    	this.board[2][0]='_';
    	this.board[2][1]='.';
    	this.board[2][2]='_';
    	this.board[2][3]='.';
    	this.board[2][4]='_';
    	this.board[3][0]='.';
    	this.board[3][1]='_';
    	this.board[3][2]='.';
    	this.board[3][3]='_';
    	this.board[3][4]='.';
    	this.board[4][0]='b';
    	this.board[4][1]='.';
    	this.board[4][2]='b';
    	this.board[4][3]='.';
    	this.board[4][4]='b';
    }
/**
 * 
 * @return board
 */
    public char[][] getBoard() {
		return board;
        
    }
/**
 * 
 * @param board
 */
    public void setBoard(char[][] board) {
    	this.board=board;
    }
/**
 * 
 * @return red
 */
    public Player getRed() {
		return red;
    }
/**
 * 
 * @param red
 */
    public void setRed(Player red) {
    	this.red=red;
    }
/**
 * 
 * @return black
 */
    public Player getBlack() {
		return black;
    }
/**
 * 
 * @param black
 */
    public void setBlack(Player black) {
    	this.black=black;
    }
/**
 * 
 * @param color
 * @return total num
 */
    public int countChecker(char color) {
    	//initializing the total number to count
       	int num=0;
       	//looping through the rows and columns
           for (int i = 0; i < this.board.length; i++) {
           	for (int j = 0; j < this.board[i].length; j++) {
           		//if the color if found
           		if (this.board[i][j]==color) {
           			//add the number to variable
           			num++;
           		}
           	}
           }
           //returns the number
           return num;
    }
/**
 * 
 * @param player
 */
    public boolean checkWin(Player player) {
    	//assigning a checker king and normal checker
        char oppcheck;
        char oppcheckking;
        //if the opponent is red then we set black
        if (player == this.red) {
            oppcheck = 'b';
            oppcheckking = 'B';
        } else {
        	//or else it it takes red and the opponent
            oppcheck = 'r';
            oppcheckking = 'R';
        }
        //to check the number of pieces of all the checkers
        int totaloppcheck = countChecker(oppcheck); 
        int totaloppcheckking = countChecker(oppcheckking);
        //if there are no checkers left then the player wins and returns true
        if (totaloppcheck+totaloppcheckking==0) {
        	return true;
        } else {
        	return false;
        }
    }
/**
 * 
 * @param player
 * @return true/false
 */
    public boolean checkLose(Player player) {
    	//a player type which is named opposite to check who is going to be the opponent
       	Player opposite = null;
       	//case for if it is black
           if (player==this.black) {
           	 opposite =this.red;
           	 //case for red
           }else if (player==this.red) {
           	 opposite = this.black;
           }
           //calls the checkwin method and directly tells who lost
           return checkWin(opposite);
       }

    public String toString() {
        String res = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                res += board[i][j];
            }
            res += "\n";
        }
        return res;
    }

    private MiniCheckers makeClone() {
        /* provided code. You may call this method, but you should NOT modify it */
        MiniCheckers newMiniCheckers = new MiniCheckers(this.red, this.black);
        char[][] newBoard = newMiniCheckers.getBoard();
        char[][] curBoard = this.getBoard();
        for (int i = 0; i < curBoard.length; i++) {
            for (int j = 0; j < curBoard[i].length; j++) {
                newBoard[i][j] = curBoard[i][j];
            }
        }
        newMiniCheckers.setBoard(newBoard);
        return newMiniCheckers;
    }

    private MiniCheckers movePiece(int startRow, int startCol, int endRow, int endCol){
        /* provided code. You may call this method, but you should NOT modify it */
        MiniCheckers move = this.makeClone();
        char[][] newBoard = move.getBoard();
        char tmpPiece = newBoard[startRow][startCol];
        newBoard[startRow][startCol] = '_';
        newBoard[endRow][endCol] = tmpPiece;
        if((tmpPiece=='r' && endRow==newBoard.length-1)||(tmpPiece=='b'&&endRow==0)){
            newBoard[endRow][endCol] = Character.toUpperCase(newBoard[endRow][endCol]);
        }
        return move;
    }

    private static void removePiece(char[][] board, int i, int j){
        /* provided code. You may call this method, but you should NOT modify it */
        board[i][j] = '_';
    }

    private static boolean validIndex(char[][] board, int i, int j){
        /* provided code. You may call this method, but you should NOT modify it */
        if(i<0 || j<0 || i>=board.length || j>=board[0].length) return false;
        return true;
    }
/**
 * 
 * @param board
 * @param startRow
 * @param startCol
 * @param endRow
 * @param endCol
 * @return true/false
 */
    private static boolean redCanMoveHere(char[][] board, int startRow, int startCol, int endRow, int endCol) {
    	//checks for all the moves and makes sure they are in the required boundry
        if (startRow<0) {
            return false;
        } else if (startRow>4) {
            return false;
        } else if (startCol<0) {
            return false;
        } else if (startCol>4) {
            return false;
        } else if (endRow<0) {
            return false;
        } else if (endRow>4) {
            return false;
        } else if (endCol<0) {
            return false;
        } else if (endCol>4) {
            return false;
        }
        //making sure the piece is moving diagonally by one
        if (endRow ==startRow+ 1) {
            if (endCol !=startCol +1&& endCol!= startCol- 1) {
                return false;
            }
        } else if (endRow== startRow- 1) {
            if (endCol!=startCol+ 1&& endCol!=startCol-1) {
                return false;
            }
        } else {
            return false;
        }
        //makes sure it does not jump on to the '_'
        if (board[endRow][endCol]!= '_') {
            return false;
        }
        //if it is a normal piece it only moves in the regular direction
        if (board[startRow][startCol]=='r') {
            if (endRow >startRow) {
                return true;
            }
            //if it is the king piece it can more in any direction
        } else if (board[startRow][startCol]=='R') {
            return true;
        }
        
        return false;
    }

    
    
    private static boolean redCanJumpHere(char[][] board, int startRow, int startCol, int endRow, int endCol){
        /* provided code. You may call this method, but you should NOT modify it */
        if(!validIndex(board, startRow, startCol) || !validIndex(board,endRow,endCol)) return false;
        if(Math.abs(startRow-endRow)!=2 || Math.abs(startCol-endCol)!=2) return false;
        if(board[startRow][startCol] == 'r'){
            if(endRow != startRow+2) return false;
            if(board[endRow][endCol] != '_') return false;
            if( (endCol == startCol+2 && (board[startRow+1][startCol+1] == 'b' || board[startRow+1][startCol+1] == 'B')) ||
                (endCol == startCol-2 && (board[startRow+1][startCol-1] == 'b' || board[startRow+1][startCol-1] == 'B'))){
                return true;
            } else {
                return false;
            }
        } else if(board[startRow][startCol] == 'R'){
            if(board[endRow][endCol] != '_') return false;
            if(endRow > startRow && endCol > startCol){
                //down-right
                if(board[startRow+1][startCol+1]=='b' || board[startRow+1][startCol+1]=='B') return true;
                else return false;
            } else if(endRow < startRow && endCol > startCol){
                //up-right
                if(board[startRow-1][startCol+1]=='b' || board[startRow-1][startCol+1]=='B') return true;
                else return false;
            } else if(endRow > startRow && endCol < startCol){
                //down-left
                if(board[startRow+1][startCol-1]=='b' || board[startRow+1][startCol-1]=='B') return true;
                else return false;
            } else if(endRow < startRow && endCol < startCol){
                //up-left
                if(board[startRow-1][startCol-1]=='b' || board[startRow-1][startCol-1]=='B') return true;
                else return false;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    /**
     * 
     * @param board
     * @param startRow
     * @param startCol
     * @param endRow
     * @param endCol
     * @return true/false
     */
    private static boolean blackCanMoveHere(char[][] board, int startRow, int startCol, int endRow, int endCol) {
    	//checks for all the moves and makes sures they are in the required boundry
        if (startRow<0) {
            return false;
        } else if (startRow>4) {
            return false;
        } else if (startCol<0) {
            return false;
        } else if (startCol>4) {
            return false;
        } else if (endRow<0) {
            return false;
        } else if (endRow>4) {
            return false;
        } else if (endCol<0) {
            return false;
        } else if (endCol>4) {
            return false;
        }
        //making sure the piece is moving diagonally by one
        if (endRow ==startRow+ 1) {
            if (endCol!= startCol+ 1&& endCol!=startCol- 1) {
                return false;
            }
        } else if (endRow ==startRow- 1) {
            if (endCol!=startCol+1&&endCol!= startCol- 1) {
                return false;
            }
        } else {
            return false;
        }
        //makes sure it does not jump on to the '_'
        if (board[endRow][endCol]!='_') {
            return false;
        }
        //if it is a normal piece it only moves in the regular direction
        if (board[startRow][startCol]=='b') {
            if (endRow<startRow) {
                return true;
            }
            //if it is a king piece it can move in any direction
        } else if (board[startRow][startCol]=='B') {
            return true;
        }
        return false;
    }

    
    private static boolean blackCanJumpHere(char[][] board, int startRow, int startCol, int endRow, int endCol){
        /* provided code. You may call this method, but you should NOT modify it */
        if(!validIndex(board, startRow, startCol) || !validIndex(board,endRow,endCol)) return false;
        if(Math.abs(startRow-endRow)!=2 || Math.abs(startCol-endCol)!=2) return false;
        if(board[startRow][startCol] == 'b'){
            if(endRow != startRow-2) return false;
            if(board[endRow][endCol] != '_') return false;
            if( (endCol == startCol+2 && (board[startRow-1][startCol+1] == 'r' || board[startRow-1][startCol+1] == 'R')) ||
                (endCol == startCol-2 && (board[startRow-1][startCol-1] == 'r' || board[startRow-1][startCol-1] == 'R'))){
                return true;
            } else {
                return false;
            }
        } else if(board[startRow][startCol] == 'B'){
            if(board[endRow][endCol] != '_') return false;
            if(endRow > startRow && endCol > startCol){
                //down-right
                if(board[startRow+1][startCol+1]=='r' || board[startRow+1][startCol+1]=='R') return true;
                else return false;
            } else if(endRow < startRow && endCol > startCol){
                //up-right
                if(board[startRow-1][startCol+1]=='r' || board[startRow-1][startCol+1]=='R') return true;
                else return false;
            } else if(endRow > startRow && endCol < startCol){
                //down-left
                if(board[startRow+1][startCol-1]=='r' || board[startRow+1][startCol-1]=='R') return true;
                else return false;
            } else if(endRow < startRow && endCol < startCol){
                //up-left
                if(board[startRow-1][startCol-1]=='r' || board[startRow-1][startCol-1]=='R') return true;
                else return false;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public ArrayList<MiniCheckers> possibleMoves(Player player) {
        /* provided code. You may call this method, but you should NOT modify it */
        char[][] curBoard = this.getBoard();
        ArrayList<MiniCheckers> rv = new ArrayList<MiniCheckers>();
        if(player == this.red){
            boolean couldJump = false;
            for(int i=0;i<curBoard.length;i++){
                for(int j=0;j<curBoard[i].length;j++){
                    if(board[i][j]=='r' || board[i][j] =='R'){
                        if(redCanJumpHere(board,i,j,i-2,j-2)){
                            MiniCheckers newBoard = movePiece(i,j,i-2,j-2);
                            removePiece(newBoard.board,i-1,j-1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                        if(redCanJumpHere(board,i,j,i-2,j+2)){
                            MiniCheckers newBoard = movePiece(i,j,i-2,j+2);
                            removePiece(newBoard.board,i-1,j+1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                        if(redCanJumpHere(board,i,j,i+2,j-2)){
                            MiniCheckers newBoard = movePiece(i,j,i+2,j-2);
                            removePiece(newBoard.board,i+1,j-1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                        if(redCanJumpHere(board,i,j,i+2,j+2)){
                            MiniCheckers newBoard = movePiece(i,j,i+2,j+2);
                            removePiece(newBoard.board,i+1,j+1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                    }
                }
            }
            if(!couldJump){
                for(int i=0;i<curBoard.length;i++){
                    for(int j=0;j<curBoard[i].length;j++){
                        if(board[i][j]=='r' || board[i][j]=='R'){                            
                            if(redCanMoveHere(board,i,j,i-1,j-1)){
                                rv.add(movePiece(i,j,i-1,j-1));
                            }
                            if(redCanMoveHere(board,i,j,i-1,j+1)){
                                rv.add(movePiece(i,j,i-1,j+1));
                            }
                            if(redCanMoveHere(board,i,j,i+1,j-1)){
                                rv.add(movePiece(i,j,i+1,j-1));
                            }
                            if(redCanMoveHere(board,i,j,i+1,j+1)){
                                rv.add(movePiece(i,j,i+1,j+1));
                            }
                        }
                    }
                }
            }
        } else if(player==this.black){
            boolean couldJump = false;
            //check for jumps first
            for(int i=0;i<curBoard.length;i++){
                for(int j=0;j<curBoard[i].length;j++){
                    if(board[i][j]=='b' || board[i][j] =='B'){
                        if(blackCanJumpHere(board,i,j,i-2,j-2)){
                            MiniCheckers newBoard = movePiece(i,j,i-2,j-2);
                            removePiece(newBoard.board,i-1,j-1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                        if(blackCanJumpHere(board,i,j,i-2,j+2)){
                            MiniCheckers newBoard = movePiece(i,j,i-2,j+2);
                            removePiece(newBoard.board,i-1,j+1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                        if(blackCanJumpHere(board,i,j,i+2,j-2)){
                            MiniCheckers newBoard = movePiece(i,j,i+2,j-2);
                            removePiece(newBoard.board,i+1,j-1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                        if(blackCanJumpHere(board,i,j,i+2,j+2)){
                            MiniCheckers newBoard = movePiece(i,j,i+2,j+2);
                            removePiece(newBoard.board,i+1,j+1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                    }
                }
            }
            if(!couldJump){
                for(int i=0;i<curBoard.length;i++){
                    for(int j=0;j<curBoard[i].length;j++){
                        if(board[i][j]=='b' || board[i][j]=='B'){
                            if(blackCanMoveHere(board,i,j,i-1,j-1)){
                                rv.add(movePiece(i,j,i-1,j-1));
                            }
                            if(blackCanMoveHere(board,i,j,i-1,j+1)){
                                rv.add(movePiece(i,j,i-1,j+1));
                            }
                            if(blackCanMoveHere(board,i,j,i+1,j-1)){
                                rv.add(movePiece(i,j,i+1,j-1));
                            }
                            if(blackCanMoveHere(board,i,j,i+1,j+1)){
                                rv.add(movePiece(i,j,i+1,j+1));
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println("Unrecognized player?!");
        }
        return rv;
    }
}