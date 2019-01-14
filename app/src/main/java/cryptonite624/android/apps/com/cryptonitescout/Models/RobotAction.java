package cryptonite624.android.apps.com.cryptonitescout.Models;

public class RobotAction {
    //matchstatus, 0 = pregame, 1 = auton, 2 = teleop, 3 = endgame
    //actionCode 0 = not on switch, 1 = red switch 1, 2 = blue switch1, 3 = blue scale, 4 = red scale, 5 = red switch2, 6 = blue switch, 7 = invalid click
    public int matchStatus;
    public String actionCode;
    public boolean hatch;

    public RobotAction(){

    }

    public RobotAction(String ac, int ms){
        actionCode = ac;
        matchStatus = ms;
    }




}
