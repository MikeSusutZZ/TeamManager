import java.util.Scanner;

public class Utility {

    public Utility(Team[] t) {
        Driver.teams = t;
    }

    public static int checkForNum(Scanner ask) {
        while (!ask.hasNextInt()) {

            String entry = ask.next();
            if (entry.toLowerCase().equals("back"))
                return Driver.BACK;
            else {
                System.out.println("Please enter a number or 'back'");
                inp();
                ask.nextLine();
            }

        }
        return ask.nextInt();
    }

    /**
     * Whenever you need to determine which team an action will be done on, this is
     * the method
     * 
     * @param ask
     * @return
     */
    @SuppressWarnings("resource")
    public static Team whichTeam() {
        Team ret = null;
        Scanner ask = new Scanner(System.in);
        while (true) {
            
            System.out.println("Give a team name or 'back'");
            inp();

            // read the user's input
            
            String entry = ask.nextLine();
            
//            System.out.println("*I have recieved " + entry);

            // check if the user entered a valid team name
            int index = teamNames(entry);
            if (index == Driver.BACK)
                break;

            if (index >= 0) {
                // return the matching team

                ret = Driver.teams[index];
                break;

            } else {
                System.out.println("not a valid entry");
            }
        }

        

        //ask.close();
        return ret;
    }

    public static void inp() {
        System.out.print("> ");
    }

    /**
     * Gets the correct team's index from it's name
     * 
     * @param input name
     * @return index of team
     */
    public static int teamNames(String input) {
//        System.out.println("* teamNames got " + input);
        int index = Driver.BACK;

        String[] teamNames = new String[Driver.teams.length];

        for (int i = 0; i < Driver.teams.length; i++) {
            teamNames[i] = Driver.teams[i].getTeamName();
        }
//        for (int i = 0; i < teamNames.length; i++) {
//            System.out.println("* " + teamNames[i]);
//        }

        for (int i = 0; i < teamNames.length; i++) {
            if (Driver.teams[i].getTeamName().equalsIgnoreCase(input)) {
                System.out.println(Driver.teams[i].getTeamName());
                index = i;

            }

        }

        return index;

    }

    public static String printTeam(Player[] team) {

        String str = "  ||  ";
        for (int i = 0; i < team.length; i++) {
            str += (i + ". #" + team[i].getNum() + "  ||  ");
        }
        return str;
    }
}
