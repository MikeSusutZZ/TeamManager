
import java.util.Random;
import java.util.Scanner;

public class Driver {

    public static int playerNumber = 1;

    public final static int BACK = 2345678;

    public static Team[] teams;

    public static void main(String[] args) {
        Scanner ask = new Scanner(System.in);
        System.out.println("Initializing");
        System.out.println("How many teams will you be playing with?");
        Utility.inp();
        teams = new Team[Utility.checkForNum(ask)];
        for (int i = 0; i < teams.length; i++) {
            ask.nextLine();
            System.out.println("Name team " + (i + 1));
            Utility.inp();
            String name = ask.nextLine();
            System.out.println("Is this team bot controlled? (y/n)");
            System.out.print("> ");
            String bot = ask.next();
            boolean bott = false;
            if (bot.equalsIgnoreCase("y")) {
                System.out.println("What difficulty?");
                Utility.inp();
                int difficulty = Utility.checkForNum(ask);

                teams[i] = new Team(name, true, difficulty);

            }

            else
                teams[i] = new Team(name, bott);
        }
        for (int i = 0; i < teams.length; i++) {
            teams[i].setPlayers(initTeam());
        }

//        for (int i = 0; i < teams.length; i++) {
//            System.out.println(teams[i].getTeamName());
//
//        }

        boolean runProgram = true;
        boolean tournamentOn = false;
        while (runProgram) {
            if (!tournamentOn) {
                System.out.println("Main Menu \nEnter the number of what you want" + " to do");
                System.out.println("1. Set all teams to random players");
                System.out.println("2. Save / Load");
                System.out.println("3. Play a single match between the teams");
                System.out.println("4. Swap the position of 2 players on a team");
                System.out.println("5. View the players on a team");
                System.out.println("6. Replace a player on a team");
                System.out.println("7. Introduction");
                System.out.print("> ");

            int input = Utility.checkForNum(ask);
            //this is the one that is breaking

            switch (input) {

                case BACK:
                    System.out.println("You're at the main menu, this is as far back as it goes");
                    break;
                case 1:
                    for (int i = 0; i < teams.length; i++) {
                        teams[i].setPlayers(initTeam());
                    }

                    break;
                case 2:
                    // setTeamToSavedPlayers();
                    Save.menu(ask);
                    break;
                case 3:
                    Team a = Utility.whichTeam();
                    Team b = Utility.whichTeam();
                    if (a != null && b != null)
                        Game.go(a, b);
                    else System.out.println("Not a valid team name");
                    // game(whichTeam(), whichTeam());
                    break;
                case 4:
                    Team c = Utility.whichTeam();
                    if (c != null)
                        Swapping.go(c.Roster(), ask);
                    else System.out.println("Not a valid team name");
                    // swapPlayersMenu(whichTeam().Roster(), ask);
                    break;
                case 5:
                    Team d = Utility.whichTeam();
                    if (d != null)
                        System.out.println(Utility.printTeam(d.Roster()));
                    else System.out.println("Not a valid team name");
                    break;
                case 6:
                    Utility.whichTeam().replace(createPlayer(), ask);
                    break;
                case 7:
                    intro();
                    break;
                case 8:
                    Tournament.beginTour(ask);
                    tournamentOn = true;
                    break;
                default:
                    System.out.println("Invalid input");
                    Utility.inp();
            }
            }
            else {
                System.out.println("1. Play next match");
                System.out.println("2. Swap players on a team");
                System.out.println("3. Remove a player from a team");
                System.out.println("4. View the players on a team");
                System.out.println("5. View Records");

                int input = Utility.checkForNum(ask);

                switch (input) {
                    case 1:
                         Tournament.nextMatch();
                    case 2:
                        Team c = Utility.whichTeam();
                        if (c != null)
                            Swapping.go(c.Roster(), ask);
                        else System.out.println("Not a valid team name");
                        // swapPlayersMenu(whichTeam().Roster(), ask);
                        break;
                    case 3:
                        Utility.whichTeam().replace(createPlayer(), ask);
                    case 4:
                        Team d = Utility.whichTeam();
                        if (d != null)
                            System.out.println(Utility.printTeam(d.Roster()));
                        else System.out.println("Not a valid team name");
                        break;
                }

            }
            System.out.println("**************************************");
        }

    }


    //
    //
    // Making players and teams
    //
    //
    //
    /**
     * makes a random player
     *
     * @return a new player
     */
    public static Player createPlayer() {

        // Create a random number generator
        Random rand = new Random();

        // Loop through the players array

        // Create a new Player object with random values for the fields
        Player player = new Player(rand.nextInt(51) + 50, // skill (random from 50 to 100)
                rand.nextInt(20) + 1, // consistency (random from 1 to 20)
                rand.nextInt(4) + 1, // play style (random from 1 to 4)
                rand.nextInt(31) - 15, // potential (random from -15 to 15)
                playerNumber); // sets the player's number
        playerNumber++; // increase the count of players

        // Return the players array
        return player;
    }

    public static Player[] initTeam() {
        Player[] team = new Player[7];
        for (int i = 0; i < 7; i++) {
            team[i] = createPlayer();
        }
        return (team);
    }

    public static void intro() {
        System.out.println("\nYou are the coach of a 'Codee' sport team! Your job is to change the lines of your players\n" +
                "to find the best possible lines and defeat you opponent! \n\n" +
                "The way a game of Codee works is as such:\n" +
                "Teams are comprised of 7 players, but only the first 5 play in a game\n" +
                "A team has 2 'lines' of 3 players. The first line plays twice as much as the second line.\n" +
                " The Captain in spot 0 on your team and plays on both line 1 and 2, while spots 1-2 \nare only on line 1 and 3-4 are on line 2, \n" +
                "and lastly spots 5-6 are on the bench and don't play\n\n" +
                "Your players have 3 stats, and can be identified by their player number.\n" +
                "These stats are:\n" +
                "* Their skill, which contributes directly to their teams success\n" +
                "* a consistency factor that varies their skill each game\n" +
                "* and a play style. There are 4 different play styles, and players will receive \na skill reduction if " +
                "they are on a line with another player with the same play style\n");
    }

}
