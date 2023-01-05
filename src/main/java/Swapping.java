import java.util.Scanner;

public class Swapping {
    
    final static int BACK = 2345678;
    public static void go(Player[] team, Scanner ask) {
        System.out.println(Utility.printTeam(team));
        boolean go = true;
        while (go) {
            while (true) {
                System.out.println("Swap the player in spot...");
                Utility.inp();
                int first = checkForNum(ask);
                if (first == BACK) {
                    go = false;
                    break;
                }
                if (first > 6 || first < 0) {
                    System.out.println("Invalid input");
                    break;
                }
                System.out.println("With the player in spot...");
                Utility.inp();
                int sec = checkForNum(ask);
                if (sec == BACK) {

                    break;
                }
                if (sec > 6 || sec < 0) {
                    System.out.println("Invalid input");
                    break;
                }
                swapPlayers(team, first, sec);
                System.out.println("Swap more players or type back");
                System.out.println(Utility.printTeam(team));
            }
        }
    }

    public static void swapPlayers(Player[] players, int firstPlayerIndex, int secondPlayerIndex) {
        if (firstPlayerIndex < 0 || firstPlayerIndex > players.length - 1 || secondPlayerIndex < 0
                || secondPlayerIndex > players.length) {
            System.out.println("Invalid player index. Please enter a valid index.");
            return;
        }
        Player temp = players[firstPlayerIndex];
        players[firstPlayerIndex] = players[secondPlayerIndex];
        players[secondPlayerIndex] = temp;
    }
    
    /**
     * Checks that the number a user inputs is actually a number.
     * 
     * @param ask The scanner
     * @return a number
     */

    public static int checkForNum(Scanner ask) {
        while (!ask.hasNextInt()) {

            String entry = ask.next();
            if (entry.toLowerCase().equals("back"))
                return BACK;
            else {
                System.out.println("Please enter a number or 'back'");
                Utility.inp();
                ask.nextLine();
            }

        }
        return ask.nextInt();
    }
}
