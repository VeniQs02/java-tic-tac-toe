import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {

        int size = 3;
        int winCondition = 0;
        String player = "X";

        int[] coordinates = new int[2];
        String board[][] = new String[size][size];
        boardInitializer(board);

        while(winCondition == 0)
        {
            boardPrinter(board);                                           // board printing
            System.out.println("Player " + player +  "'s turn!");         //
            coordinates = input(board);                                  // collecting input
            board[coordinates[0]][coordinates[1]] = player;             //
            winCondition = winChecker(board);                          // checking win condition
            player = playerChanger(player);                           // changing player at the end
        }

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
            if(board[xy[0]][xy[1]] == " ") break;
            else System.out.println("Already taken!");
        }
        return xy;
    }
    static String playerChanger(String player) {
        if(player == "X") return "Y";
        else if(player == "Y") return "X";
        else return "E";
    }
    static int winChecker(String[][] board){
        return 0;
    }

}