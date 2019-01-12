package cryptonite624.android.apps.com.cryptonitescout.Models;

import java.util.ArrayList;

public class ActionMap {

    public ArrayList<RobotAction> actions;


    public ActionMap(){
        actions = new ArrayList<>();
    }

    public ActionMap(ArrayList<RobotAction> acts){
        actions = acts;
    }

    //action code + match status
    public int numScored(int [] codes, int [] matchStatus){
        int total = 0;

        for(int c : codes) {
            for(int m : matchStatus) {
                for (RobotAction i : actions) {
                    if (i.actionCode.equals(c) && i.matchStatus == m) {
                        total++;
                    }
                }
            }
        }

        return total;
    }

    public int totalPoints(){
        return actions.size();
    }

    /*
        actionCode, 0 = not on switch, 1 = red switch 1, 2 = blue switch1, 3 = blue scale, 4 = red scale, 5 = red switch2, 6 = blue switch, 7 = invalid click
        matchstatus, 0 = pregame, 1 = auton, 2 = teleop, 3 = endgame
     */
    public ActionMap getMiniMap(int code, int ms){
        ArrayList<RobotAction> acts = new ArrayList<>();
        for(RobotAction i : actions){
            if(i.actionCode.equals(code) && i.matchStatus == ms){
                acts.add(i);
            }
        }

        return new ActionMap(acts);
    }




}
