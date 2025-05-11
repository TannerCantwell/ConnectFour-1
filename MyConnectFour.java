public class MyConnectFour 
{
    // variables
    private int[][] gameBoard = new int[6][7];
    private int lastMoveCol;
    private int lastMoveRow;

    // functions
    public MyConnectFour() {}

    public int[][] copyGameBoard()
    {
        int[][] copy = new int[6][7];

        for (int i = 0; i < this.gameBoard.length; i++)
        {
            copy[i] = gameBoard[i];
        }

        return copy;
    }

    public int determineWin()
    {
        if (determineWinVertical() || determineWinHorizontal() || determineWinPositive() || determineWinNegative())
        {
            return gameBoard[lastMoveRow][lastMoveCol];
        }

        return 0;
    }

    public boolean dropPiece(int player, int column)
    {
        if (column < 0 || column > 6)
        {
            return false;
        }

        if (gameBoard[0][column] != 0)
        {
            return false;
        }

        for (int i = 5; i >= 0; i--)
        {
            if (gameBoard[i][column] == 0)
            {
                gameBoard[i][column] = player;
                return true;
            }
        }

        return false;
    }

    public boolean isFull()
    {
        for (int i = 0; i < 7; i++)
        {
            if (gameBoard[0][i] == 0)
            {
                return false;
            }
        }

        return true;
    }

    public void printBoard()
    {
        for (int i = 0; i < 7; i++)
        {
            System.out.print("| ");
            for (int j = 0; j < 6; j++)
            {
                System.out.println(gameBoard[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
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
}