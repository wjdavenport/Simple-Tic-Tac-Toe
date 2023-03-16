package tictactoe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Get user input of the board setup
        // var pattern = scanner.next();

        // The starting pattern
        var pattern = "         "; // 9 spaces
        printBoard(pattern);
        // System.out.println(analyzeBoard(pattern));

        // xOrO: 0 = X, 1 = O
        int xOrO = 0;
        int turnCounter = 0;

        while (analyzeBoard(pattern) == "Game not finished") {
            pattern = makeMove(scanner, pattern, turnCounter%2);
            turnCounter++;
        }
        System.out.println(analyzeBoard(pattern));
    }

    private static String makeMove(Scanner scanner, String pattern, int xOrO) {
        String[] token = new String[2];
        token[0] = "X";
        token[1] = "O";
        // Get the user input of a move.
        do {
            var testMoveRow = 0;
            try {
                testMoveRow = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
            }
            var testMoveCol = 0;
            try {
                testMoveCol = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
            }

            // Map of pattern index to row/col coordinates
            // 0 -> 1 1     1 -> 1 2     2 -> 1 3
            // 3 -> 2 1     4 -> 2 2     5 -> 2 3
            // 6 -> 3 1     7 -> 3 2     8 -> 3 3

            // Check that user input contains only integers


            // Check validity of integer coordinates
            if (testMoveRow < 1 || testMoveRow > 3 ||
                    testMoveCol < 1 || testMoveCol > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else {
                var testPlacement = (testMoveRow - 1) * 3 + (testMoveCol - 1);
                String newPattern = null;
                if (pattern.charAt(testPlacement) != ' ') {
                    System.out.println("This cell is occupied!  Choose another one!");
                } else {
                    newPattern = pattern.substring(0, testPlacement) +
                            token[xOrO] + pattern.substring(testPlacement + 1);
                    printBoard(newPattern);
                    // Return new pattern
                    return newPattern;
                }
            }
        } while (true);
    }

    /**
     * The method printBoard prints out a board
     * given the String input representing that board
     * @ param String pattern, nine characters in a string
     * containing only O, X or _
     */
    private static void printBoard(String pattern) {
        // Create top/bottom and sides of board
        var topBoard = "---------";
        var sideBoard = "|";
        // Print top of the board
        System.out.println(topBoard);
        // Begin tic-tac-toe items
        for (int row = 0; row < 3; row++) {
            System.out.print(sideBoard + " ");
            for (int col = 0; col < 3; col++) {
                System.out.print(pattern.substring(row * 3 + col, row * 3 + col + 1));
                System.out.print(" ");
            }
            System.out.println(sideBoard);
        }
        System.out.println(topBoard);
    }

    /**
     * The method analyzeBoard takes in a board pattern
     * and returns one of 4 results depending on the state
     * of the board:
     * Game not finished
     * Draw
     * X wins
     * O wins
     *
     * @param pattern - a String-expressed state of the board
     * @return analysis[n] - a String one of 4 possible states,
     * 0 <= n <= 3
     */
    private static String analyzeBoard(String pattern) {
        // Possible outcomes
        String[] analysis = new String[5];
        analysis[0] = "Game not finished";
        analysis[1] = "Draw";
        analysis[2] = "X wins";
        analysis[3] = "O wins";
        analysis[4] = "Impossible";
        int result = -1;
        boolean solved = false;
        
        do {
            boolean xWins = false;
            boolean oWins = false;
            
        // Check for X wins
            // 8 ways for X to win
            // horizontals
            if  (pattern.charAt(0) == 'X' &&
                 pattern.charAt(1) == 'X' &&
                 pattern.charAt(2) == 'X') { xWins = true;}
            else
            if (pattern.charAt(3) == 'X' &&
                pattern.charAt(4) == 'X' &&
                pattern.charAt(5) == 'X') { xWins = true;}
            else
            if (pattern.charAt(6) == 'X' &&
                pattern.charAt(7) == 'X' && 
                pattern.charAt(8) == 'X') { xWins = true;}
            // verticals
            else 
            if (pattern.charAt(0) == 'X' &&
                pattern.charAt(3) == 'X' &&
                pattern.charAt(6) == 'X') { xWins = true;}
            else
            if (pattern.charAt(1) == 'X' &&
                pattern.charAt(4) == 'X' &&
                pattern.charAt(7) == 'X') { xWins = true;}
            else
            if (pattern.charAt(2) == 'X' &&
                pattern.charAt(5) == 'X' &&
                pattern.charAt(8) == 'X') { xWins = true;}
            
            // diagonals
            if (pattern.charAt(0) == 'X' &&
                pattern.charAt(4) == 'X' &&
                pattern.charAt(8) == 'X') { xWins = true;}
            else
            if (pattern.charAt(2) == 'X' &&
                pattern.charAt(4) == 'X' &&
                pattern.charAt(6) == 'X') { xWins = true;}

            // Check for O wins
            // 8 ways for O to win
            // horizontals
            if (pattern.charAt(0) == 'O' &&
                pattern.charAt(1) == 'O' &&
                pattern.charAt(2) == 'O') { oWins = true;}
            else
            if (pattern.charAt(3) == 'O' &&
                pattern.charAt(4) == 'O' &&
                pattern.charAt(5) == 'O') { oWins = true;}
            else
            if (pattern.charAt(6) == 'O' &&
                pattern.charAt(7) == 'O' &&
                pattern.charAt(8) == 'O') { oWins = true;}
            // verticals
            else
            if (pattern.charAt(0) == 'O' &&
                pattern.charAt(3) == 'O' &&
                pattern.charAt(6) == 'O') { oWins = true;}
            else
            if (pattern.charAt(1) == 'O' &&
                pattern.charAt(4) == 'O' &&
                pattern.charAt(7) == 'O') { oWins = true;}
            else
            if (pattern.charAt(2) == 'O' &&
                pattern.charAt(5) == 'O' &&
                pattern.charAt(8) == 'O') { oWins = true;}

            // diagonals
            else
            if (pattern.charAt(0) == 'O' &&
                pattern.charAt(4) == 'O' &&
                pattern.charAt(8) == 'O') { oWins = true;}
            else
            if (pattern.charAt(2) == 'O' &&
                pattern.charAt(4) == 'O' &&
                pattern.charAt(6) == 'O') { oWins = true;}
            // Determine result of winner
            if (xWins) {result = 2; solved = true;}
            if (oWins) {result = 3; solved = true;}

            // Check for draw
            if (!(xWins || oWins)) {
                result = 1;
            }

            // Check for game not finished
            if (!solved) {
                for (int i = 0; i < pattern.length(); i++) {
                    if (pattern.charAt(i) == ' ') {
                        result = 0;
                        solved = true;
                        break;
                    }
                }
            }

            // Check for impossibility
            // Check to see if X and O win (impossible)
            if (xWins && oWins) {
                result = 4;
                solved = true;
            }
            
        // Count Xs and Os
        int xCount = 0;
        int oCount = 0;
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == 'X') {
                xCount++;
            } else if (pattern.charAt(i) == 'O') {
                oCount++;
            }
        }
        if (Math.abs(xCount - oCount) > 1) {
            result = 4;
            solved = true;
        }
        } while (!solved && result != 1);



        // Return the appropriate outcome
        return analysis[result];
    }
}
