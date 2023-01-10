import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Game {
    
    final static int SYN_PEN = 5;
    public static void go(Team a, Team b) {
        if (a.getBot()) a.findBestTeam();
        if (b.getBot()) b.findBestTeam();
              
        PlayerRes[] res1 = TeamResult(a.ActTeam());
        PlayerRes[] res2 = TeamResult(b.ActTeam());
        announce(matchScore(res1, res2), a.getTeamName(), b.getTeamName());
        printPerformanceList(performance(res1, res2));
    }

    public static PlayerRes[] TeamResult(Player[] players) {
        // Create an array of PlayerRes objects with the same size as the players array
        PlayerRes[] playerResults = new PlayerRes[players.length];

        // Loop through the players array
        for (int i = 0; i < players.length; i++) {
            // Create a new PlayerRes object for each player, using the play and getName
            // methods
            playerResults[i] = new PlayerRes(players[i].play(synPlayer(i, players)), players[i].getNum());
        }

        // Return the playerResults array
        return playerResults;
    }

    public static boolean checkSyn(Player player1, Player player2) {
        // Get the play style of each player using the getPlayStyle method
        int playStyle1 = player1.getPlayStyle();
        int playStyle2 = player2.getPlayStyle();

        // Return true if the play styles are the same, and false otherwise
        return playStyle1 == playStyle2;
    }

    public static int synPlayer(int position, Player[] team) {
        int syn = 0;
        switch (position) {

        case 0:
            for (int run = 1; run < team.length; run++) {
                if (checkSyn(team[0], team[run]))
                    syn += SYN_PEN;
            }
            break;

        case 1:
            if (checkSyn(team[position], team[2]))
                syn += SYN_PEN;
            if (checkSyn(team[position], team[0]))
                syn += SYN_PEN;
            break;

        case 2:
            if (checkSyn(team[position], team[0]))
                syn += SYN_PEN;
            if (checkSyn(team[position], team[1]))
                syn += SYN_PEN;
            break;

        case 3:
            if (checkSyn(team[position], team[4]))
                syn += SYN_PEN;
            if (checkSyn(team[position], team[0]))
                syn += SYN_PEN;
            break;

        case 4:
            if (checkSyn(team[position], team[3]))
                syn += SYN_PEN;
            if (checkSyn(team[position], team[0]))
                syn += SYN_PEN;
        }

        return syn;
    }

    public static int[] matchScore(PlayerRes[] array1, PlayerRes[] array2) {
        // Initialize the total variables to 0
        int total1 = 0;
        int total2 = 0;

        // Loop through array1 and add each PlayerRes's performance to total1
        for (int i = 0; i < 5; i++) {
            int line = switch (i) {
                case 0 -> 3;
                case 1, 2 -> 2;
                default -> 1;
            };
            total1 += array1[i].getPerformance() * line;
        }

        // Loop through array2 and add each PlayerRes's performance to total2
        for (int i = 0; i < 5; i++) {
            int line = switch (i) {
                case 0 -> 3;
                case 1, 2 -> 2;
                default -> 1;
            };
            total2 += array2[i].getPerformance() * line;
        }

        return new int[]{total1, total2};

    }

    public static void announce(int[] scores, String aName, String bName) {
        // Print which array has the higher total performance
        System.out.println("* " + scores[0]);
        System.out.println("* " + scores[1]);
        if (scores[0] > scores[1]) {
            System.out.println(aName + " Wins");
        } else {
            System.out.println(bName + " Wins");
        }
    }

    public static PlayerRes[] performance(PlayerRes[] array1, PlayerRes[] array2) {
        // Create a list to store the combined PlayerRes objects
        List<PlayerRes> combined = new ArrayList<>();

        // Add all the PlayerRes objects from array1 to the combined list
        combined.addAll(Arrays.asList(array1));

        // Add all the PlayerRes objects from array2 to the combined list
        combined.addAll(Arrays.asList(array2));

        // Sort the combined list in order of performance, using a comparator
        combined.sort(new Comparator<PlayerRes>() {
            @Override
            public int compare(PlayerRes player1, PlayerRes player2) {
                return player2.getPerformance() - player1.getPerformance();
            }
        });

        // Create a new array to store the sorted PlayerRes objects, with a size of 10
        PlayerRes[] sorted = new PlayerRes[10];

        // Copy the first 10 PlayerRes objects from the combined list to the sorted
        // array
        for (int i = 0; i < 10; i++) {
            sorted[i] = combined.get(i);
        }

        // Return the sorted array
        return sorted;
    }

    /**
     * Gets the overall performance of a matchScore from all players
     * 
     * @param players
     */
    public static void printPerformanceList(PlayerRes[] players) {
        for (PlayerRes player : players) {
            System.out.print("#" + player.getNum() + ", ");
        }
        System.out.println("");
    }

    public static int[] tourGame(Team a, Team b) {
        if (a.getBot()) a.findBestTeam();
        if (b.getBot()) b.findBestTeam();

        PlayerRes[] res1 = TeamResult(a.ActTeam());
        PlayerRes[] res2 = TeamResult(b.ActTeam());
        printPerformanceList(performance(res1, res2));
        return matchScore(res1, res2);
    }

    
}
