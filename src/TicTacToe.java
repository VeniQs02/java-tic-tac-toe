import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TicTacToe {

    private int[] gameBoardDimensions;
    private char[] gameSymbols;

    TicTacToe(boolean defaultPreset){
        if(defaultPreset){
            gameBoardDimensions = new int[] {3};
            gameSymbols = new char[]{'X', 'O'};
        }else{
            gameBoardDimensions = acquireUserInputForSize();
        }

        if (gameBoardDimensions != null) {
            Board gameBoard = new Board(gameBoardDimensions);
            System.out.println(gameBoard.getSizeX() + " x " + gameBoard.getSizeY() + " sized board chosen! \n" + gameBoard);
            if(!defaultPreset){
                gameSymbols = acquireUserInputForSymbols();
            }
            System.out.println(userSymbolsMessage());

//            boolean isWinConditionMet = false;
//            while(!isWinConditionMet){
//
//                isWinConditionMet = gameBoard.checkForWinCondition(1, 2);
//            }

        } else {
            System.out.println("Failed to initialize the board.");
        }
    }

    private int[] acquireUserInputForSize() {
        System.out.println("""
                x   - for a square board
                x y - for a rectangular board shape
                eg. input '2 3' in the console for a 2x3 rectangular board
                Input board size:""");

        int[] finalBoardSizeArray = null;
        Scanner s = new Scanner(System.in);
        try {
            boolean wasInputTaken = false;
            while (!wasInputTaken) {
                try {
                    String boardSizeInput = s.nextLine();                                                               // getting user input
                    String[] boardSizeStringArray = boardSizeInput.split(" ");
                    if (boardSizeStringArray.length > 2) {                                                              // checking if input length is less or equal to 2
                        System.out.println("More size parameters than 2 will be ignored!");
                        boardSizeStringArray = Arrays.copyOfRange(boardSizeStringArray, 0, 2);                  // trimming if necessary
                    }

                    int[] boardSizeIntArray = new int[boardSizeStringArray.length];                                     // converting to int
                    for (int i = 0; i < boardSizeStringArray.length && i < 2; i++) {
                        boardSizeIntArray[i] = Integer.parseInt(boardSizeStringArray[i]);
                    }

                    finalBoardSizeArray = boardSizeIntArray;                                                            // if performed successfully, then step out of the loop
                    wasInputTaken = true;
                } catch (NumberFormatException e) {
                    System.out.println("Please input a number 'X' or a set of two numbers 'X' and 'Y'");                // in case user inputs nothing or string values
                } catch (Exception e) {
                    System.out.println("Error! " + e);
                }
            }
        } catch (Exception e) {
            System.out.println("Error! " + e);
        }
        return finalBoardSizeArray;
    }

    private char[] acquireUserInputForSymbols(){
        System.out.println("\n\n Please input two or more characters/symbols you would like to play with \n (eg. x y)");
        char[] finalUserCharArray = null;
        Scanner s;
        try {
            s = new Scanner(System.in);
            boolean wasInputTaken = false;
            while (!wasInputTaken) {
                try {
                    String userSymbols = s.nextLine();
                    String[] userSymbolsArray = userSymbols.toUpperCase().split(" ");
                    Set<Character> uniqueChars = new HashSet<>();
                    for (String symbol : userSymbolsArray) {
                        uniqueChars.add(symbol.charAt(0));
                    }
                    char[] userCharArray = new char[uniqueChars.size()];
                    int i = 0;
                    for (char c : uniqueChars) {
                        userCharArray[i++] = c;
                    }
                    wasInputTaken = true;
                    finalUserCharArray = userCharArray;
                } catch (Exception e) {
                    System.out.println("Error! " + e);
                }
            }
        } catch (Exception e) {
            System.out.println("Error! " + e);
        }
        return finalUserCharArray;
    }

    private String userSymbolsMessage(){
        StringBuilder symbolsMessage = new StringBuilder();
        for(int i = 0; i<gameSymbols.length; i++){
            if(i!=gameSymbols.length-1){
                symbolsMessage.append(gameSymbols[i]).append(", ");
            }else{
                symbolsMessage.append(gameSymbols[i]).append(" ");
            }
        }
        return symbolsMessage.append("symbols chosen! \n").toString();
    }
}
