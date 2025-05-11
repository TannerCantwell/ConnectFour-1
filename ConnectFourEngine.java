public class ConnectFourEngine {

    public static int move(int[][] gameBoard, int turn)
    {
        int engineNumber = turn;
        int opponentNumber = turn % 2 + 1;

        int engineWin = checkPossibleWin(gameBoard, engineNumber);
        int opponentWin = checkPossibleWin(gameBoard, opponentNumber);

        if (engineWin != -1) // check if engine can win on this move 
        {
            return engineWin;
        }

        if (opponentWin != -1) // check if opponent can win on their next move (block if so)
        {
            return opponentWin;
        }

        return (int)(Math.random() * 6) + 1; // for testing (random move)
    }

    private static int checkPossibleWin(int[][] gameBoard, int player)
    {
        int horizontal = CPWHorizontal(gameBoard, player);
        int vertical = CPWVertical(gameBoard, player);
        int positive = CPWPositive(gameBoard, player);
        int negative = CPWNegative(gameBoard, player);
        
        if (horizontal != -1)
        {
            return horizontal;
        }

        if (vertical != -1)
        {
            return vertical;
        }

        if (positive != -1)
        {
            return positive;
        }

        if (negative != -1)
        {
            return negative;
        }

        return -1;
    }

    // check possible win functions
    
    private static int CPWHorizontal(int[][] gameBoard, int player)
    {
        int count = 1;

        for (int i = 5; i >= 0; i--)
        {
            for (int j = 0; j < 7; j++)
            {
                if (gameBoard[i][j] == player)
                {
                    count++;
                } else {
                    count = 0;
                }

                if (count == 3)
                {
                    if (checkWinSpot(gameBoard, i, j - 3))
                    {
                        return j - 3;
                    }

                    if (checkWinSpot(gameBoard, i, j + 1))
                    {
                        return j + 1;
                    }
                }
            }
        }

        return -1;
    }

    private static int CPWVertical(int[][] gameBoard, int player)
    {
        int count = 1;

        for (int i = 0; i < 7; i++)
        {
            for (int j = 5; j >= 0; j--)
            {
                if (gameBoard[j][i] == player)
                {
                    count++;
                } else {
                    count = 0;
                }

                if (count == 3)
                {
                    if (checkWinSpot(gameBoard, j - 1, i))
                    {
                        return i;
                    }
                }
            }
        }

        return -1;
    }

    private static int CPWPositive(int[][] gameBoard, int player)
    {
        int count = 0;
        
    }

    private static int CPWNegative(int[][] gameBoard, int player)
    {
        int count = 0;
        
    }

    private static boolean checkWinSpot(int[][] gameBoard, int row, int col)
    {
        if (gameBoard[row][col] == 0)
        {
            if (row == 5)
            {
                return true;
            } else {
                if (gameBoard[row + 1][col] != 0)
                {
                    return true;
                }
            }
        }

        return false;
    }
}