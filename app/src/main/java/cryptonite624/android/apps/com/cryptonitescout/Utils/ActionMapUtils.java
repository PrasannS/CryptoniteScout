package cryptonite624.android.apps.com.cryptonitescout.Utils;

import java.util.ArrayList;

import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;
import cryptonite624.android.apps.com.cryptonitescout.Models.RobotAction;

public class ActionMapUtils {

    public void parseString(String s){
        String [] parsed = s.split(" ");
        for(int i = 0; i < parsed.length; i++){
            RobotAction robotAction = new RobotAction(parsed[i]);

        }
    }

    public boolean isFilled(String code, ActionMap actionMap, ArrayList<RobotAction> actions){
        if(actions.contains(code)){
            return true;
        }
        else{
            return false;
        }
    }

    public static int numScored(String [] basecodes, int [] matchStatus, boolean hatch, ArrayList<RobotAction> actions){
        int total = 0;

        for(String c : basecodes) {
            for(int m : matchStatus) {
                for (RobotAction i : actions) {
                    if (i.actionCode.equals(c+i.matchStatus)) {
                        if(i.hatch==hatch)
                            total++;
                    }
                }
            }
        }
        return total;
    }

    public static int totalhatches(boolean b, ArrayList<RobotAction> actions){
        int count =0;
        for(RobotAction r : actions){
            if(r.hatch==b){
                count++;
            }
        }
        return count;
    }


    public int totalPoints(ArrayList<RobotAction> actions){
        return actions.size();
    }

    public ActionMap getMiniMap(int code, int ms, ArrayList<RobotAction> actions){
        ArrayList<RobotAction> acts = new ArrayList<>();
        for(RobotAction i : actions){
            if(i.actionCode.equals(code) && i.matchStatus == ms){
                acts.add(i);
            }
        }

        return new ActionMap(acts);
    }

}
