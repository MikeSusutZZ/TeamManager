import java.util.Random;

public class Player {
    int skill;
    int consistency;
    int playStyle;
    int potential;
    int number;
    Team team;

    public Player(int skill, int consistency, int playStyle, int potential, int num, Team thisTeam) {
        this.skill = skill;
        this.consistency = consistency;
        this.playStyle = playStyle;
        this.potential = potential;
        number = num;
        team = thisTeam;
    }

    public int play(int syn) {
        // Generate a random number from 0 to consistency
        int random = new Random().nextInt(consistency + 1);

        // Return skill minus the random number
        return skill - random - syn;
    }

    public int getPlayStyle() {
        return playStyle;
    }

    public void age() {
        skill += potential;
        potential -= new Random().nextInt(4) + 1;
    }

    public int getConsistency() {
        return consistency;
    }

    public void setConsistency(int consistency) {
        this.consistency = consistency;
    }

    public int getSkill() {
        return skill;
    }

    public int getPotential() {
        return potential;
    }


    public void setPlayStyle(int playStyle) {
        this.playStyle = playStyle;
    }

    public int getNum() {
        return number;
    }

    public void setNum(int num) {
        number = num;
    }

    public Team getTeam() {
        return team;
    }
}
