import java.util.*;

public class Team {
    // Define an array of Player objects
    private Player[] players = new Player[7];

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

    public boolean getBot() {
        return botOrNot;
    }

    public void findBestTeam() {
        // Add a new player to the end of the players array
        players[6] = Driver.createPlayer(this);

        // Initialize the best score and best team with the current players array
        long bestScore = 0;
        Player[] bestLine = this.players;
        // Create a random number generator to shuffle the players array
        Random rand = new Random();

        // Calculate the initial best score based on the current players array
        for (int i = 0; i < dif; i++) {
            // Calculate the performance scores for each player in the current team
            Player[] res1 = Game.TeamResult(Arrays.copyOfRange(bestLine, 0, 5));
            // Add the performance scores to the best score
            for (Player player : res1) {
                bestScore += player.getPerformance();
            }
        }

        // Iterate through all possible combinations of players
        for (int i = 0; i < dif; i++) {
            // Initialize the score for the current combination of players
            long score = 0;
            // Shuffle the players array
            Player[] curLine = players;
            Collections.shuffle(Arrays.asList(curLine), rand);

            // Calculate the score for the current combination of players
            for (int j = 0; j < dif; j++) {
                // Calculate the performance scores for each player in the current team
                Player[] res1 = Game.TeamResult(Arrays.copyOfRange(curLine, 0, 5));
                // Add the performance scores to the current score
                for (Player player : res1) {
                    score += player.getPerformance();
                }
            }
            // If the current score is better than the best score, update the best score and best team
            if (score > bestScore) {
                bestScore = score;
                bestLine = Arrays.copyOf(curLine, 7);
            }
        }
        // Set the players array to the best team of players
        setPlayers(bestLine);
    }


    public void replace(Player player, Scanner ask){
        while (true) {
            System.out.println("Which player do you want to remove from your team?");
            System.out.println(Utility.printTeam(players));
            System.out.print("> ");
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


    public int getDif() {
        return dif;
    }
}
