public class ConnectFour{
    private int[][] gameBoard = new int[6][7];
    private int lastMoveCol;
    private int lastMoveRow;

    public boolean drop(int player, int column){
        if(column>6||column<0){
            return false;
        }
        if(gameBoard[0][column]!=0){
            return false;
        }
        for(int i = 5; i>-1; i--){
            if(gameBoard[i][column]==0){
                gameBoard[i][column] = player;
                lastMoveCol = column;
                lastMoveRow = i;
                return true;
            }
        }
        return false;
    }

    public int determineWin()
    {
        if (determineWinVertical() || determineWinHorizontal() || determineWinPositive() || determineWinNegative())
        {
            return gameBoard[lastMoveRow][lastMoveCol];
        }

        return 0;
    }

// determine win functions

    public boolean determineWinHorizontal()
    {
        int count = 1;
        int player = gameBoard[lastMoveRow][lastMoveCol];

        for(int i = lastMoveCol - 1; i >= 0; i--)
        {
            if (gameBoard[lastMoveRow][i] == player)
            {
                count++;
            } else {
                break;
            }
        }

        for(int i = lastMoveCol + 1; i < 7; i++)
        {
            if (gameBoard[lastMoveRow][i] == player)
            {
                count++;
            } else {
                break;
            }
        }

        return count >= 4;
    }

    public boolean determineWinVertical()
    {
        int count = 1;
        int player = gameBoard[lastMoveRow][lastMoveCol];

        for(int i = lastMoveRow + 1; i < 6; i++)
        {
            if (gameBoard[i][lastMoveCol] == player)
            {
                count++;
            } else {
                break;
            }
        }

        return count >= 4;
    }

    public boolean determineWinPositive()
    {
        int count = 0;
        int player = gameBoard[lastMoveRow][lastMoveCol];

        for(int i = 5; i >= 0; i--)
        {
            if (lastMoveCol + (lastMoveRow - i) < 7 && lastMoveCol + (lastMoveRow - i) >= 0)
            {
                if (gameBoard[i][lastMoveCol + (lastMoveRow - i)] == player)
                {
                    count++;
                } else {
                    count = 0;
                }
            }

            if (count >= 4)
            {
                return true;
            }
        }

        return false;
    }

    public boolean determineWinNegative()
    {
        int count = 0;
        int player = gameBoard[lastMoveRow][lastMoveCol];

        for(int i = 5; i >= 0; i--)
        {
            if (lastMoveCol - (lastMoveRow - i) < 7 && lastMoveCol - (lastMoveRow - i) >= 0)
            {
                if (gameBoard[i][lastMoveCol - (lastMoveRow - i)] == player)
                {
                    count++;
                } else {
                    count = 0;
                }
            }

            if (count >= 4)
            {
                return true;
            }
        }

        return false;
    }

    public void print(){
        for(int[] x:gameBoard){
            for(int y:x){
                if(y==1){
                    System.out.print("[X]");
                }
                if(y==2){
                    System.out.print("[O]");
                }
                if(y==0){
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
    }

    public boolean isFull(){
        int count = 0;
        for(int i=0; i<6; i++){
            for(int j=0; j<7; j++){
                if(gameBoard[i][j]==0){
                    count++;
                }
            }
        }
      return count==0;
    }



    public int[][] getGameBoard(){
      int[][] copy = new int[6][7];
      for(int i=0;i<6;i++){
        for(int j=0;j<7;j++){
            copy[i][j]=gameBoard[i][j];
        }
      }
      return copy;
    }
}