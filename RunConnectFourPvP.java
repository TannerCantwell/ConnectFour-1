import java.util.Scanner;

public class RunConnectFourPvP{
    public static void main(String[] args){
        ConnectFour game = new ConnectFour();
        Scanner input = new Scanner(System.in);
        boolean win = false;
        int count = -1;
        int move = -1;
        int winner = -1;

        while(!win && !game.isFull()){
            count++;
            System.out.println("What is your move?");
            move = input.nextInt();
            while(!game.drop(count%2+1,move)){
                System.out.println("Please try again.");
                move = input.nextInt();
            }
            game.print();
            winner = game.determineWin();
            win = winner != 0;
        }
        if(win){
            System.out.println("Player " + winner + " is the winner.");
        }else{
            System.out.println("Tie game :(");
        }
    }
}