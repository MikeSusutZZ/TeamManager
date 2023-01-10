import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Tournament {
    static int[][] schedule;
    static int [] record;
    static int [] difference;

    public static void beginTour(Scanner ask) {
        Random rand = new Random();
        System.out.println("How many rounds of regular season?");
        int rounds = Utility.checkForNum(ask);
        final int amt = Driver.teams.length;
        schedule = new int[amt][2];
        record = new int[amt];
        difference = new int[amt];

        for(int i = 0; i < amt; i++){
            for (int j = i + 1; j < amt; j++){
                schedule[i][j] = j;
            }
            Collections.shuffle(Arrays.asList(schedule[i]), rand);
        }
        Collections.shuffle(Arrays.asList(schedule), rand);
        for (int i = 0; i < amt; i++) {
            Collections.shuffle(Arrays.asList(schedule[i]), rand);
        }
    }

    public void playMatch(int teamA, int teamB) {
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

    }
}
