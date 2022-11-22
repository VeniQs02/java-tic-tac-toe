import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {

        int size = 3;
        int winCondition = 0;
        String player = "Y";

        int[] coordinates = new int[2];
        String board[][] = new String[size][size];
        boardInitializer(board);

        while(winCondition == 0)
        {
            player = playerChanger(player);                                 // changing player at the end
            boardPrinter(board);                                           // board printing
            System.out.println("Player " + player +  "'s turn!");         //
            coordinates = input(board);                                  // collecting input
            board[coordinates[0]][coordinates[1]] = player;             //
            winCondition = statusChecker(board);                       // checking win condition

        }
        boardPrinter(board);
        winnerChickenDinner(winCondition, player);

    }
    static void boardInitializer(String[][] board) {

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                board[i][j] = " ";}
        }
        System.out.println("Please input numbers in a range 1-3 when you're asked to!");
    }
    static void boardPrinter(String[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                System.out.print("[" + board[i][j] + "]");}
            System.out.println();
        }
    }
    static int[] input(String[][] board) {
        Scanner sc = new Scanner(System.in);
        int xy[] = new int[2];
        while(true)
        {
            System.out.print("Row: ");
            xy[0] = sc.nextInt()-1;
            System.out.print("Column: ");
            xy[1] = sc.nextInt()-1;
            if(xy[0] >= 0 && xy[0] <= 2 && xy[1] >= 0 && xy[1] <= 2){
                if(board[xy[0]][xy[1]] == " ") break;
                else{
                    System.out.println("Already taken!");
                }
            }
            else System.out.println("Input numbers in range 1-3!");


        }
        return xy;
    }
    static String playerChanger(String player) {
        if(player == "X") return "Y";
        else if(player == "Y") return "X";
        else return "E";
    }
    static int statusChecker(String[][] board){
        int check = 0;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] != " ") check += 1;
            }
        }
        if(check == 9) return 2;
        else if(check > 5)
        {
            return winCondition(board);
        }
        else return 0;
    }

    static int winCondition(String[][] board){
        if(board[0][0] == board[1][1] && board[1][1] == board[2][2] ) return 1;
        else if(board[0][2] == board[1][1] && board[1][1] == board[2][0]) return 1;
        else if(board[0][0] == board[0][1] && board[0][1] == board[0][2]) return 1;
        else if(board[1][0] == board[1][1] && board[1][1] == board[1][2]) return 1;
        else if(board[2][0] == board[2][1] && board[2][1] == board[2][2]) return 1;
        else if(board[0][0] == board[1][0] && board[1][0] == board[2][0]) return 1;
        else if(board[0][1] == board[1][1] && board[1][1] == board[2][1]) return 1;
        else if(board[0][2] == board[1][2] && board[1][2] == board[2][2]) return 1;
        else return 0;

    }

    static void winnerChickenDinner(int winCondition, String player){
        if(winCondition == 1) System.out.println("Player " + player + " wins!");
        else if (winCondition == 2) System.out.println("Draw!");
        else System.out.println("Something went wrong!");
    }

}