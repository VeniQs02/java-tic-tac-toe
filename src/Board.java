public class Board {
    private int sizeX;
    private int sizeY;
    private char[][] gameBoard;
    public char currentPlayerSign;

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
    }

    protected boolean checkForWinCondition(int pos1, int pos2){
//        if(game[0][0] == board[1][1] && board[1][1] == board[2][2] ) return 1;
//        else if(board[0][2] == board[1][1] && board[1][1] == board[2][0]) return 1;
//        else if(board[0][0] == board[0][1] && board[0][1] == board[0][2]) return 1;
//        else if(board[1][0] == board[1][1] && board[1][1] == board[1][2]) return 1;
//        else if(board[2][0] == board[2][1] && board[2][1] == board[2][2]) return 1;
//        else if(board[0][0] == board[1][0] && board[1][0] == board[2][0]) return 1;
//        else if(board[0][1] == board[1][1] && board[1][1] == board[2][1]) return 1;
//        else if(board[0][2] == board[1][2] && board[1][2] == board[2][2]) return 1;
        return false;
    }


    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
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
