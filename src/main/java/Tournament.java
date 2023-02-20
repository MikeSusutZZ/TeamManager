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
    static int[][] record;
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
        day = 0;

        // Prompt user for number of rounds
        System.out.println("How many rounds of regular season?");
        System.out.print("> ");
        int rounds = Utility.checkForNum(ask);

        // Initialize schedule, record, and difference arrays with size based on number of teams
        final int amt = Driver.teams.length;
        schedule = new int[(int)(amt * (amt / 2 -0.5) ) * rounds][2];
        record = new int[amt][2];
        difference = new int[amt];

        // Loop through rounds to generate schedule
        for (int g = 0; g < rounds; g++) {
            int arrayIndex = 0 + g*(int)(amt * (amt / 2 -0.5));
            for (int i = 0; i < amt - 1; i++) {

                for (int j = i + 1; j < amt; j++) {
                    schedule[arrayIndex][0] = i;
                    schedule[arrayIndex][1] = j;
                    arrayIndex++;

                }
            }
        }
        System.out.println(" ------");
//        for (int i = 0; i < schedule.length; i++) {
//            System.out.println(i);
//            System.out.println(Driver.teams[schedule[i][0]].getTeamName() + " vs " + Driver.teams[schedule[i][1]].getTeamName());
//        }
        // shuffle schedule array
        Collections.shuffle(Arrays.asList(schedule));

        // Print shuffled schedule
        for (int i = 0; i < schedule.length; i++) {
            System.out.println(Driver.teams[schedule[i][0]].getTeamName() + " vs " + Driver.teams[schedule[i][1]].getTeamName());
        }
    }



    public static void playMatch(int teamA, int teamB) {
        System.out.println("Match between " + Driver.teams[teamA].getTeamName() + "(" + record[teamA][0] + "/" + record[teamA][1] + ") vs "
        + Driver.teams[teamB].getTeamName() + "(" + record[teamB][0] + "/" + record[teamB][1] + ")");
        int [] res = Game.tourGame(Driver.teams[teamA], Driver.teams[teamB]);
        System.out.println(Driver.teams[teamA].getTeamName() + " " + res[0]/30 + " : " + res[1]/30 + " "
                + Driver.teams[teamB].getTeamName());
        int dif = res[0] - res[1];
        record[teamA][1] += 1;
        record[teamB][1] += 1;
        if (dif > 0) {
            record[teamA][0] += 1;
            System.out.println(Driver.teams[teamA].getTeamName() + " Wins!");
        }
        else {
            record[teamB][0] += 1;
            System.out.println(Driver.teams[teamB].getTeamName() + " Wins!");
        }
        difference[teamA] += dif;
        difference[teamB] += (dif * -1);
    }

    public static void nextMatch() {
        if (day < schedule.length) {
            playMatch(schedule[day][0], schedule[day][1]);
            day++;
        } else {
            System.out.println("Finished!!\n" + recordString());
            for (Team team : Driver.teams) {
                for (Player player : team.Roster()){
                    //System.out.println(player.getNum() + " " + player.getSkill());
                    player.age();
                    //System.out.println(player.getSkill());
                }
            }
            Driver.tournamentOn = false;
        }
    }

    public static String recordString(){
        String s = "";
        for (int i = 0; i < record.length; i++){
            s += Driver.teams[i].getTeamName() + " " + record[i][0] + "/" + record[i][1] + " Wins,  ";
        }
        return s;
    }
}
