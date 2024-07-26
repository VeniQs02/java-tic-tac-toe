import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TicTacToe {

    private char[] playerSigns;

        TicTacToe(boolean defaultPreset){
            int[] gameBoardDimensions;
            if(defaultPreset){
                gameBoardDimensions = new int[] {3};
                playerSigns = new char[]{'X', 'O'};
            }else{
                gameBoardDimensions = acquireUserInputForSize();
            }

            if (gameBoardDimensions != null) {
                Board gameBoard = new Board(gameBoardDimensions);
                System.out.println(gameBoard.getSizeX() + " x " + gameBoard.getSizeY() + " sized board chosen! \n" + gameBoard);
                if(!defaultPreset){
                    playerSigns = acquireUserInputForSymbols();
                }
                System.out.println(userSymbolsMessage());

                gameBoard.setPlayerSigns(playerSigns);
                boolean isWinConditionMet = false;
                boolean isDraw = false;

                while(!isWinConditionMet && !isDraw){
                    gameBoard.playersMove();
                    isWinConditionMet = gameBoard.checkForWinCondition(gameBoard.getCurrentPlayerSign());
                    if (isWinConditionMet) {
                        System.out.println("Player " + gameBoard.getCurrentPlayerSign() + " wins!");
                    } else if (gameBoard.isBoardFull()) {
                        isDraw = true;
                        System.out.println("The game is a draw!");
                    }
                }
            } else {
                System.out.println("\u001B[91m> ERROR - Failed to initialize the board.\u001B[0m");
            }
        }

    private int[] acquireUserInputForSize() {
        System.out.println("""
                x   - for a square board
                x y - for a rectangular board shape
                (eg. input '2 3' in the console for a 2x3 rectangular board)
                \u001B[97mInput board size:\u001B[0m""");

        int[] finalBoardSizeArray = null;
        Scanner s = new Scanner(System.in);
        try {
            boolean wasInputTaken = false;
            while (!wasInputTaken) {
                try {
                    String boardSizeInput = s.nextLine();
                    String[] boardSizeStringArray = boardSizeInput.split(" ");
                    if (boardSizeStringArray.length > 2) {
                        System.out.println("\u001B[93m> WARNING - More size parameters than 2 will be ignored!\u001B[0m");
                        boardSizeStringArray = Arrays.copyOfRange(boardSizeStringArray, 0, 2);
                    }

                    int[] boardSizeIntArray = new int[boardSizeStringArray.length];
                    for (int i = 0; i < boardSizeStringArray.length && i < 2; i++) {
                        boardSizeIntArray[i] = Integer.parseInt(boardSizeStringArray[i]);
                    }

                    if(boardSizeIntArray[0] < 3 || boardSizeIntArray[1] < 3){
                        System.out.println("\u001B[93m> WARNING - Boards with size lesser than 3, might not be suitable for meeting the win condition!\u001B[0m");
                    }

                    finalBoardSizeArray = boardSizeIntArray;
                    wasInputTaken = true;
                } catch (NumberFormatException e) {
                    System.out.println("Please input a number 'X' or a set of two numbers 'X' and 'Y'");
                } catch (Exception e) {
                    System.out.println("\u001B[91m> ERROR - " + e + "\u001B[0m");
                }
            }
        } catch (Exception e) {
            System.out.println("\u001B[91m> ERROR - " + e + "\u001B[0m");
        }
        return finalBoardSizeArray;
    }

    private char[] acquireUserInputForSymbols() {
        System.out.println("""
                            If the symbol contains multiple letters, only the first one will be chosen
                            (e.g. x y)
                            \u001B[97mPlease input two or more characters/symbols you would like to play with\u001B[0m \n""");

        char[] finalUserCharArray = null;
        Scanner s;
        try {
            s = new Scanner(System.in);
            boolean wasInputTaken = false;
            while (!wasInputTaken) {
                try {
                    String userSymbols = s.nextLine().trim();
                    if (userSymbols.isEmpty()) {
                        System.out.println("Input cannot be empty. Please input at least 2 unique symbols.");
                        continue;
                    }

                    String[] userSymbolsArray = userSymbols.split("\\s+");
                    Set<Character> uniqueChars = new HashSet<>();
                    for (String symbol : userSymbolsArray) {
                        if (!symbol.isEmpty()) {
                            uniqueChars.add(symbol.charAt(0));
                        }
                    }

                    char[] userCharArray = new char[uniqueChars.size()];
                    int i = 0;
                    for (char c : uniqueChars) {
                        userCharArray[i++] = c;
                    }
                    if (userCharArray.length < 2) {
                        System.out.println("Please input at least 2 unique symbols.");
                    } else {
                        wasInputTaken = true;
                        finalUserCharArray = userCharArray;
                    }
                } catch (Exception e) {
                    System.out.println("\u001B[91m> ERROR - " + e + "\u001B[0m");
                }
            }
        } catch (Exception e) {
            System.out.println("\u001B[91m> ERROR - " + e + "\u001B[0m");
        }
        return finalUserCharArray;
    }

    private String userSymbolsMessage(){
        StringBuilder symbolsMessage = new StringBuilder();
        for(int i = 0; i< playerSigns.length; i++){
            if(i!= playerSigns.length-1){
                symbolsMessage.append(playerSigns[i]).append(", ");
            }else{
                symbolsMessage.append(playerSigns[i]).append(" ");
            }
        }
        return symbolsMessage.append("symbols chosen! \n").toString();
    }
}
