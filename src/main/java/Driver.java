
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

        while (runProgram) {
            System.out.println("Main Menu \nEnter the number of what you want" + " to do");
            System.out.println("1. Set all teams to random players");
            System.out.println("2. Set a team to a saved set of players (wip)");
            System.out.println("3. Play a single match between the teams");
            System.out.println("4. Swap the position of 2 players on a team");
            System.out.println("5. View the players on a team");
//            System.out.println("6. ");
//            System.out.println("7. Save a team's players");
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
                    System.out.println("this doesn't work yet");
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
                    System.out.println(teams[0].Roster()[0]);
                    System.out.println(teams[1].Roster()[0]);
                    break;
                case 7:
                    System.out.println("this doesn't work yet");
                    break;
                default:
                    System.out.println("Invalid input");
                    Utility.inp();
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
     * @return
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


}
