package cryptonite624.android.apps.com.cryptonitescout.Models;

public class RobotAction {
    //matchstatus, 0 = pregame, 1 = auton, 2 = teleop, 3 = endgame
    //actionCode 0 = not on switch, 1 = red switch 1, 2 = blue switch1, 3 = blue scale, 4 = red scale, 5 = red switch2, 6 = blue switch, 7 = invalid click
    public int x, y, actionCode, matchStatus;

    public RobotAction(){

    }

    public RobotAction(int xCoor, int yCoor, int ac, int ms){
        x = xCoor;
        y = yCoor;
        actionCode = ac;
        matchStatus = ms;
    }




}
