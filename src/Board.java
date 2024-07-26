import java.util.InputMismatchException;
import java.util.Scanner;

public class Board {
    private int sizeX;
    private int sizeY;
    private char[][] gameBoard;
    private char[] playerSigns;
    private int currentPlayerIndex;

    Board(int[] gameBoardSize){
        try {
            if (gameBoardSize.length == 1) {
                this.sizeX = gameBoardSize[0];
                this.sizeY = gameBoardSize[0];
            } else if (gameBoardSize.length == 2) {
                this.sizeX = gameBoardSize[0];
                this.sizeY = gameBoardSize[1];
            } else{
                this.sizeX = 0;
                this.sizeY = 0;
            }
        }catch(NullPointerException e){
            System.out.println("Error assigning board dimensions");
        }catch(Exception e){
            System.out.println("Error! " + e);
        }
        initializeBoard();
    }

    private void initializeBoard() {
        gameBoard = new char[sizeX][sizeY];
        for(int i = 0; i < sizeX; i++){
            for(int j = 0; j < sizeY; j++){
                gameBoard[i][j] = ' ';
            }
        }
        currentPlayerIndex = 0;
    }

    public void playersMove(){
        System.out.println("\u001B[97mPlayer '" + playerSigns[currentPlayerIndex] + "' turn!\u001B[0m \n" +
                "Please pick column number (less or equal to " + sizeX + ");");
        boolean wasInputTaken = false;
        Scanner s = new Scanner(System.in);
        try{
            while(!wasInputTaken){
                try{
                    int playerInputCol = s.nextInt();
                    System.out.println("Please pick row number (less or equal to " + sizeY + ")");
                    int playerInputRow = s.nextInt();
                    if(gameBoard[playerInputRow-1][playerInputCol-1] != ' '){
                        System.out.println("Field already taken! Please pick another one!");
                    }else{
                        gameBoard[playerInputRow-1][playerInputCol-1] = playerSigns[currentPlayerIndex];
                        System.out.println(this);
                        currentPlayerIndex = (currentPlayerIndex + 1) % playerSigns.length;
                        wasInputTaken = true;
                    }

                }catch(InputMismatchException e){
                    System.out.println("\u001B[91m> ERROR - " + e + " - Please input a number!\u001B[0m");
                    s.nextInt(); // Clear invalid input
                }catch(Exception e){
                    System.out.println("\u001B[91m> ERROR - " + e + "\u001B[0m");
                    System.out.println("Please pick column number (less or equal to " + sizeX + ")");
                }
            }
        }catch(Exception e){
            System.out.println("\u001B[91m> ERROR - " + e + "\u001B[0m");
        }
    }

    protected boolean checkForWinCondition(char playerSymbol) {
        // Check horizontal lines
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY - 2; j++) {
                if (gameBoard[i][j] == playerSymbol &&
                        gameBoard[i][j + 1] == playerSymbol &&
                        gameBoard[i][j + 2] == playerSymbol) {
                    return true;
                }
            }
        }
        // Check vertical lines
        for (int i = 0; i < sizeX - 2; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (gameBoard[i][j] == playerSymbol &&
                        gameBoard[i + 1][j] == playerSymbol &&
                        gameBoard[i + 2][j] == playerSymbol) {
                    return true;
                }
            }
        }
        // Check main diagonal lines
        for (int i = 0; i < sizeX - 2; i++) {
            for (int j = 0; j < sizeY - 2; j++) {
                if (gameBoard[i][j] == playerSymbol &&
                        gameBoard[i + 1][j + 1] == playerSymbol &&
                        gameBoard[i + 2][j + 2] == playerSymbol) {
                    return true;
                }
            }
        }
        // Check anti-diagonal lines
        for (int i = 0; i < sizeX - 2; i++) {
            for (int j = 2; j < sizeY; j++) {
                if (gameBoard[i][j] == playerSymbol &&
                        gameBoard[i + 1][j - 1] == playerSymbol &&
                        gameBoard[i + 2][j - 2] == playerSymbol) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (gameBoard[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public char getCurrentPlayerSign() {
        return playerSigns[(currentPlayerIndex + playerSigns.length - 1) % playerSigns.length];
    }

    public void setPlayerSigns(char[] playerSigns) {
        this.playerSigns = playerSigns;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        if(gameBoard != null){
            for(int i = 0; i < gameBoard.length; i++){
                for(int j = 0; j < gameBoard[i].length; j++){
                    if(j != gameBoard[i].length -1){
                        str.append(gameBoard[i][j]).append(" | ");
                    }else{
                        str.append(gameBoard[i][j]).append(" ");
                    }
                }
                str.append("\n");
                if(i != gameBoard.length -1){
                    for(int k = 0; k < gameBoard[0].length * 4 -3; k++){
                        if(k == 0){
                            str.append("-");
                        }else{
                            str.append("-");
                        }
                    }
                }
                str.append("\n");
            }
        }
        return str.toString();
    }
}
