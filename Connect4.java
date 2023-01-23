
import java.util.Scanner;

public class Connect4 {
    public static void printArray (String[][] board)
    {
        for(int row = 0; row < board.length; row++)
        {
            System.out.print("|");
            for (int position = 0; position < board[row].length; position++){
                System.out.print(board[row][position] + "|");
            }
            System.out.println();
        }
    }
    public static boolean isBoardFull (String[][] board)
    {
        boolean isFull = true;
        for(int row = 0; row < board.length; row++)
        {
            for (int position = 0; position < board[row].length; position++)
            {
                if (board[row][position].equals(" ")) {
                    isFull = false;
                    break;
                }
            }
        }
        return isFull;
    }
    public static boolean winVertical(String[][] board){
        boolean winCondition = false;
        //first loop goes through first row
        for(int column = 0; column <= board[0].length-1; column++)
        {
            //checks if any columns are full by checking the top row
            if (!board[0][column].equals(" "))
            {
                //if column is full, check rows beneath it for equal values
                for (int row = 1; row < board.length-1; row++)
                {
                    if (board[row][column].equals(board[0][column])){
                        winCondition = true;
                    }
                    else {
                        winCondition = false;
                        break;
                    }
                }
                if (winCondition) break;
            }
        }
        return winCondition;
    }
    public static boolean winHorizontal(String[][] board){
        boolean winCondition = false;
        for(int row = 0; row < board.length; row++)
        {
            if (!board[row][0].equals(" "))
            {
                for (int column = 0; column < board[row].length; column++)
                {
                    if (board[row][column].equals(board[row][0])) {
                        winCondition = true;
                    }
                    else {
                        winCondition = false;
                        break;
                    }
                }
                if (winCondition) break;
            }
        }
        return winCondition;
    }
    public static int addDisc(String[][] board, int turn, int column){
        int turnSwap = turn;
        boolean wasPlacementSuccessful = false;

        for(int row = board.length-1; row >= 0; row--)
        {
            if (board[row][column].equals(" "))
            {

                if (turn == 1) board[row][column] = "X";
                else if (turn == 2) board[row][column] = "O";
                wasPlacementSuccessful = true;

                //Switches whose turn it is if the placement was successful
                if (turnSwap == 1) turnSwap = 2;
                else if (turnSwap == 2) turnSwap = 1;

                break;
            }
        }
        if (!wasPlacementSuccessful) System.out.println("Invalid placement! Please try again player " + turn + ".\n");
        //will return the same turn if the placement is unsuccessful. otherwise turn is swapped.
        return turnSwap;
    }
    public static void main (String[] args)
    {
        Scanner myScan = new Scanner(System.in);

        String[][] gameBoard = {
                {" ", " ", " ", " "},
                {" ", " ", " ", " "},
                {" ", " ", " ", " "},
                {" ", " ", " ", " "}
        };

        int whoseTurn = 1;

        System.out.println("[Connect Four]");
        printArray(gameBoard);

        while (!isBoardFull(gameBoard) && !winVertical(gameBoard) && !winHorizontal(gameBoard))
        {
            System.out.print("\nPlayer " + whoseTurn + ", enter a column: ");
            int columnChoice = myScan.nextInt() - 1;
            System.out.println();

            int takeTurn = addDisc(gameBoard, whoseTurn, columnChoice);

            whoseTurn = takeTurn;

            printArray(gameBoard);
        }

        if (whoseTurn == 1) whoseTurn = 2;
        else if (whoseTurn == 2) whoseTurn = 1;

        if (isBoardFull(gameBoard)) System.out.println("\nNo one wins!");
        else if (winVertical(gameBoard) || winHorizontal(gameBoard)) System.out.println("\nPlayer " + whoseTurn + " wins!");
    }
}
