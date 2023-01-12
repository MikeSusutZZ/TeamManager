import java.io.*;
import java.util.Scanner;

public class Save {

    public static void menu (Scanner ask) {
        System.out.print("What would you like to do? \n1. Save\n2. Load\n> ");
        int input = Utility.checkForNum(ask);
        if (input == 1) {
            System.out.print("Name the save file\n> ");
            save(Driver.teams, ask.next());
        }
        else if (input == 2) {
            System.out.print("Enter save file\n> ");
            Driver.teams = load(ask.next());
        }
        else {
            System.out.println("Not a valid input");
        }
    }
    public static void save(Team[] teams, String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(teams);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in " + filename + ".ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static Team[] load(String fileName) {
        Team[] yourArray = null;
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            yourArray = (Team[]) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Object class not found");
            c.printStackTrace();
            return null;
        }
        return yourArray;
    }

}
