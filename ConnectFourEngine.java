import java.util.ArrayList;

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

        ArrayList<Integer> possibleMoves = new ArrayList<Integer>(); // list to hold moves that don't give opponent a chance

        for (int i = 0; i < 7; i++) // check what moves don't give opponent a chance and are in bounds
        {
            if (!moveGivesChance(gameBoard, engineNumber, opponentNumber, i) && gameBoard[0][i] == 0)
            {
                possibleMoves.add(i);
            }
        }

        for (int i = 0; i < possibleMoves.size(); i++) // check if any possible move gives engine a chance
        {
            if (moveGivesChance(gameBoard, engineNumber, engineNumber, possibleMoves.get(i)))
            {
                return possibleMoves.get(i);
            }
        }

        if (possibleMoves.size() > 0)
        {
            return possibleMoves.get((int)(Math.random() * possibleMoves.size())); // return random move from possibleMoves
        }

        return (int)(Math.random() * 6) + 1; // return random move if every move gives opponent a chance
    }

    private static int checkPossibleWin(int[][] gameBoard, int player)
    {
        int horizontal = CPWHorizontal(gameBoard, player);
        int vertical = CPWVertical(gameBoard, player);
        int positive = CPWPositive(gameBoard, player);
        int negative = CPWNegative(gameBoard, player);

        int horizontalDouble = CPWHorizontalDouble(gameBoard, player);
        int positiveDouble = CPWPositiveDouble(gameBoard, player);
        int negativeDouble = CPWNegativeDouble(gameBoard, player);
        
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

        if (horizontalDouble != -1)
        {
            return horizontalDouble;
        }

        if (positiveDouble != -1)
        {
            return positiveDouble;
        }

        if (negativeDouble != -1)
        {
            return negativeDouble;
        }

        return -1;
    }

    private static int[][] copyGameBoard(int[][] gameBoard)
    {
        int[][] result = new int[6][7];

        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 7; j++)
            {
                result[i][j] = gameBoard[i][j];
            }
        }

        return result;
    }

    private static boolean dropPiece(int[][] gameBoard, int player, int column)
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

    private static boolean moveGivesChance(int[][] gameBoard, int engine, int player, int col)
    {
        int[][] gameBoardCopy = copyGameBoard(gameBoard);
            
        if (dropPiece(gameBoardCopy, engine, col))
        {
            if (checkPossibleWin(gameBoardCopy, player) == -1)
            {
                return false;
            }

            System.out.println(col);
            return true;
        }

        return false;
    }

    // check possible win functions
    
    private static int CPWHorizontal(int[][] gameBoard, int player)
    {
        int count = 0;
        int emptySpace = -1;

        for (int i = 5; i >= 0; i--)
        {
            for (int j = 0; j < 4; j++)
            {
                for (int k = j; k < j + 4; k++)
                {
                    if (gameBoard[i][k] == player)
                    {
                        count++;
                    }

                    if (gameBoard[i][k] == 0)
                    {
                        emptySpace = k;
                    }
                }

                if (count == 3 && emptySpace >= 0)
                {
                    if (checkWinSpot(gameBoard, i, emptySpace))
                    {
                        return emptySpace;
                    }
                }

                count = 0;
                emptySpace = -1;
            }
        }

        return -1;
    }

    private static int CPWVertical(int[][] gameBoard, int player)
    {
        int count = 0;

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
        int emptySpaceRow = -1;
        int emptySpaceCol = -1;

        for (int i = 5; i >= 0; i--)
        {
            for (int j = 0; j < 7; j++)
            {
                if (gameBoard[i][j] == player)
                {
                    if (i - 3 >= 0 && j + 3 < 7)
                    {
                        for (int k = 0; k < 4; k++)
                        {
                            if (gameBoard[i - k][j + k] == player)
                            {
                                count++;
                            }

                            if (gameBoard[i - k][j + k] == 0)
                            {
                                emptySpaceRow = i - k;
                                emptySpaceCol = j + k;
                            }
                        }

                        if (count == 3 && emptySpaceRow >= 0)
                        {
                            if (checkWinSpot(gameBoard, emptySpaceRow, emptySpaceCol))
                            {
                                return emptySpaceCol;
                            }
                        }

                        count = 0;
                        emptySpaceRow = -1;
                        emptySpaceCol = -1;
                    }
                }
            }
        }

        return -1;
    }

    private static int CPWNegative(int[][] gameBoard, int player)
    {
        int count = 0;
        int emptySpaceRow = -1;
        int emptySpaceCol = -1;

        for (int i = 5; i >= 0; i--)
        {
            for (int j = 6; j >= 0; j--)
            {
                if (gameBoard[i][j] == player)
                {
                    if (i - 3 >= 0 && j - 3 >= 0)
                    {
                        for (int k = 0; k < 4; k++)
                        {
                            if (gameBoard[i - k][j - k] == player)
                            {
                                count++;
                            }

                            if (gameBoard[i - k][j - k] == 0)
                            {
                            emptySpaceRow = i - k;
                            emptySpaceCol = j - k;
                            }
                        }

                        if (count == 3 && emptySpaceRow >= 0)
                        {
                            if (checkWinSpot(gameBoard, emptySpaceRow, emptySpaceCol))
                            {
                                return emptySpaceCol;
                            }
                        }

                        count = 0;
                        emptySpaceRow = -1;
                        emptySpaceCol = -1;
                    }
                }
            }
        }

        return -1;
    }

    private static int CPWHorizontalDouble(int[][] gameBoard, int player)
    {
        int count = 0;
        int emptySpace1 = -1;
        int emptySpace2 = -1;

        for (int i = 5; i >= 0; i--)
        {
            for (int j = 0; j < 4; j++)
            {
                for (int k = j; k < j + 4; k++)
                {
                    if (gameBoard[i][k] == player)
                    {
                        count++;
                    }

                    if (gameBoard[i][k] == 0)
                    {
                        if (emptySpace1 == -1)
                        {
                            emptySpace1 = k;
                        } else {
                            emptySpace2 = k;
                        }
                    }
                }

                if (count == 2 && Math.abs(emptySpace1 - emptySpace2) == 3)
                {
                    if (checkWinSpot(gameBoard, i, emptySpace1) && checkWinSpot(gameBoard, i, emptySpace2))
                    {
                        return emptySpace1;
                    }
                }

                count = 0;
                emptySpace1 = -1;
                emptySpace2 = -1;
            }
        }

        return -1;
    }

    private static int CPWPositiveDouble(int[][] gameBoard, int player)
    {
        int count = 0;
        int emptySpace1Row = -1;
        int emptySpace1Col = -1;
        int emptySpace2Row = -1;
        int emptySpace2Col = -1;

        for (int i = 5; i >= 0; i--)
        {
            for (int j = 0; j < 7; j++)
            {
                if (gameBoard[i][j] == player)
                {
                    if (i - 3 >= 0 && j + 3 < 7)
                    {
                        for (int k = 0; k < 4; k++)
                        {
                            if (gameBoard[i - k][j + k] == player)
                            {
                                count++;
                            }

                            if (gameBoard[i - k][j + k] == 0)
                            {
                                if (emptySpace1Row == -1)
                                {
                                    emptySpace1Row = i - k;
                                    emptySpace1Col = j + k;
                                } else {
                                    emptySpace2Row = i - k;
                                    emptySpace2Col = j + k;
                                }
                            }
                        }

                        if (count == 2 && Math.abs(emptySpace1Row - emptySpace2Row) == 3)
                        {
                            if (checkWinSpot(gameBoard, emptySpace1Row, emptySpace1Col) && checkWinSpot(gameBoard, emptySpace2Row, emptySpace2Col))
                            {
                                return emptySpace1Col;
                            }
                        }

                        count = 0;
                        emptySpace1Row = -1;
                        emptySpace1Col = -1;
                        emptySpace2Row = -1;
                        emptySpace2Col = -1;
                    }
                }
            }
        }

        return -1;
    }

    private static int CPWNegativeDouble(int[][] gameBoard, int player)
    {
        int count = 0;
        int emptySpace1Row = -1;
        int emptySpace1Col = -1;
        int emptySpace2Row = -1;
        int emptySpace2Col = -1;

        for (int i = 5; i >= 0; i--)
        {
            for (int j = 6; j >= 0; j--)
            {
                if (gameBoard[i][j] == player)
                {
                    if (i - 3 >= 0 && j - 3 >= 0)
                    {
                        for (int k = 0; k < 4; k++)
                        {
                            if (gameBoard[i - k][j - k] == player)
                            {
                                count++;
                            }

                            if (gameBoard[i - k][j - k] == 0)
                            {
                                if (emptySpace1Row == -1)
                                {
                                    emptySpace1Row = i - k;
                                    emptySpace1Col = j - k;
                                } else {
                                    emptySpace2Row = i - k;
                                    emptySpace2Col = j - k;
                                }
                            }
                        }

                        if (count == 2 && Math.abs(emptySpace1Row - emptySpace2Row) == 3)
                        {
                            if (checkWinSpot(gameBoard, emptySpace1Row, emptySpace1Col) && checkWinSpot(gameBoard, emptySpace2Row, emptySpace2Col))
                            {
                                return emptySpace1Col;
                            }
                        }

                        count = 0;
                        emptySpace1Row = -1;
                        emptySpace1Col = -1;
                        emptySpace2Row = -1;
                        emptySpace2Col = -1;
                    }
                }
            }
        }

        return -1;
    }

    private static boolean checkWinSpot(int[][] gameBoard, int row, int col)
    {
        if (row >= 0 && row < 6 && col >= 0 && col < 7)
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
        }

        return false;
    }
}