import java.util.*;

public class Team {
    // Define an array of Player objects
    private Player[] players = new Player[7];

    // Define an ArrayList of strings to store the team's record
    private ArrayList<String> record;

    // Define a String to store the team's name
    private final String teamName;

    private final boolean botOrNot;

    private int dif;

    // Define a constructor that takes the team's name as a parameter
    public Team(String teamName, boolean bot) {
        this.teamName = teamName;
        botOrNot = bot;
    }

    public Team(String teamName, boolean bot, int diff) {
        this.teamName = teamName;
        botOrNot = bot;
        dif = diff;
    }

    // Define a method to set the team's players
    public void setPlayers(Player[] players) {
        this.players = players;
    }

    // Define a method to add a record to the team's record
    public void addRecord(String record) {
        this.record.add(record);
    }

    // Define a method to get the team's name
    public String getTeamName() {
        return this.teamName;
    }

    // Define a method to get the team's players
    public Player[] Roster() {
        return this.players;
    }

    public Player[] ActTeam() {
        return Arrays.copyOfRange(players, 0, 5);
    }

    // Define a method to get the team's record
    public ArrayList<String> getRecord() {
        return this.record;
    }

    public boolean getBot() {
        return botOrNot;
    }

    public void findBestTeam() {
        long bestScore = 0;
        Player[] bestLine = this.players;
        Random rand = new Random();

        for (int i = 0; i < dif; i++) {
            PlayerRes[] res1 = Game.TeamResult(Arrays.copyOfRange(bestLine, 0, 5));

            for (PlayerRes player : res1) {
                bestScore += player.getPerformance();
            }
        }
        //System.out.println(bestScore / dif);
        
        for (int i = 0; i < dif; i++) {
            //set score to 0
            long score = 0;
            //current line is set to the list of players
            Player[] curLine = players;
            //The line of curLine is randomized
            Collections.shuffle(Arrays.asList(curLine), rand);

            //gets the score for the current line
            for (int j = 0; j < dif; j++) {

                PlayerRes[] res1 = Game.TeamResult(Arrays.copyOfRange(curLine, 0, 5));

                for (PlayerRes player : res1) {

                    score += player.getPerformance();

                }
            }
            //System.out.println("*team 84*" + (score / dif));
            //System.out.println(Utility.printTeam(bestLine));
            //System.out.println(Utility.printTeam(curLine));
            //System.out.println(score + " " + bestScore);
            if (score > bestScore) {
                bestScore = score;
                bestLine = Arrays.copyOf(curLine, 7);
                //System.out.println("*team 88* the score was better than current best, lines changed");
                
            }
            //System.out.println(Utility.printTeam(bestLine));
        }
        setPlayers(bestLine);
    }

    public void replace(Player player, Scanner ask){
        while (true) {
            System.out.println("Which player do you want to remove from your team?\n> ");
            System.out.println(Utility.printTeam(players));
            int p = Utility.checkForNum(ask);
            if (p == Driver.BACK) break;
            if (p < 0 || p > 6) {
                System.out.println("Not a valid input");
            }
            else {
                players[p] = player;
                break;
            }
        }
    }

}
