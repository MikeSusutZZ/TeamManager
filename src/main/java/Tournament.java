import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 * Class that generates and prints a schedule for a given number of rounds
 */
public class Tournament {
    /** schedule array to hold the schedule of games */
    static int[][] schedule;
    /** record array to hold records of each team */
    static int[] record;
    /** difference array to hold the +/- of the scores */
    static int[] difference;
    /** variable to hold the current round/day of the schedule */
    static int day = 0;

    /**
     * Generates and prints the schedule for the specified number of rounds
     *
     * @param ask the scanner object for user input
     */
    public static void beginTour(Scanner ask) {
        // Initialize random number generator
        Random rand = new Random();

        // Prompt user for number of rounds
        System.out.println("How many rounds of regular season?");
        int rounds = Utility.checkForNum(ask);

        // Initialize schedule, record, and difference arrays with size based on number of teams
        final int amt = Driver.teams.length;
        schedule = new int[(int)(amt * (amt / 2 -0.5) ) * rounds][2];
        record = new int[amt];
        difference = new int[amt];

        // Loop through rounds to generate schedule
        //for (int g = 0; g < rounds; g++) {
            for (int i = 0; i < amt - 1; i++) {
                for (int j = i + 1; j < amt; j++) {
                    schedule[i][0] = i;
                    schedule[i][1] = j;
                    System.out.println(i);
                    System.out.println(Driver.teams[schedule[i][0]].getTeamName() + " vs " + Driver.teams[schedule[i][1]].getTeamName());
                }
            }
        //}
        System.out.println(" ------");
        for (int i = 0; i < schedule.length; i++) {
            System.out.println(i);
            System.out.println(Driver.teams[schedule[i][0]].getTeamName() + " vs " + Driver.teams[schedule[i][1]].getTeamName());
        }
        // shuffle schedule array
        Collections.shuffle(Arrays.asList(schedule));

        // Print shuffled schedule
        for (int i = 0; i < schedule.length; i++) {
            System.out.println(Driver.teams[schedule[i][0]].getTeamName() + " vs " + Driver.teams[schedule[i][1]].getTeamName());
        }
    }



    public static void playMatch(int teamA, int teamB) {
        System.out.println("Match between " + Driver.teams[teamA].getTeamName() + "(" + record[teamA] + ") vs "
        + Driver.teams[teamB].getTeamName() + "(" + record[teamB] + ")");
        int [] res = Game.tourGame(Driver.teams[teamA], Driver.teams[teamB]);
        int dif = res[0] - res[1];
        if (dif > 0) {
            record[teamA] += 1;
            System.out.println(Driver.teams[teamA].getTeamName() + "Wins!");
        }
        else {
            record[teamB] += 1;
            System.out.println(Driver.teams[teamB].getTeamName() + "Wins!");
        }
        difference[teamA] += dif;
        difference[teamB] += (dif * -1);
    }

    public static void nextMatch() {
        do {
            playMatch(schedule[day][0], schedule[day][1]);
            day++;
        } while ((!(Driver.teams[schedule[day][0]].getBot() || Driver.teams[schedule[day][1]].getBot())));
    }

    public static String recordString(){
        String s = "";
        for (int i = 0; i < record.length; i++){
            s += Driver.teams[i].getTeamName() + ": " + record[i] + " Wins\n";
        }
        return s;
    }
}
