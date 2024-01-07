
package project3;

import java.util.Scanner;

/**
 * 
 * fields for userplayer
 *
 */
public class UserPlayer extends Player {
    private String name;
    private Scanner input;

    /**
     * 
     * @param input
     * @param name
     */
    public UserPlayer(Scanner input, String name) {
        this.input = input;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public MiniCheckers chooseMove(MiniCheckers arguements) {
        // prints out the board
        System.out.println("Current board:");
        System.out.println(arguements.toString());
        // gets all the possible moves by looping through the possible moves of player
        System.out.println("Possible moves:");
        for (int i = 0; i < arguements.possibleMoves(this).size(); i++) {
            System.out.println((i + 1) + ":\n" + arguements.possibleMoves(this).get(i));
        }
        // gives user to choose from the given number
        System.out.println("choose from 1 to " + arguements.possibleMoves(this).size());
        // scanner to take in the input
        int choosenval = input.nextInt();
        // to make sure that the code does not crash we make sure that it is in the
        // given range
        while (choosenval > arguements.possibleMoves(this).size()) {
            // prints when the number is out of bounds
            System.out.println("choose only from given number");
            if (input.hasNextInt()) {
                choosenval = input.nextInt();
            } else {
                input.next();
            }
        }
        // returns the output
        return arguements.possibleMoves(this).get(choosenval - 1);
    }
}