public class PlayerRes {
  private int performance;
  private int num;
  private Team team;

  public PlayerRes(int performance, int num, Team team) {
    this.performance = performance;
    this.num = num;
    this.team = team;
  }

  public int getPerformance() {
    return performance;
  }

  public int getNum() {
    return num;
  }

  public Team getTeam() {
    return team;
  }
}

