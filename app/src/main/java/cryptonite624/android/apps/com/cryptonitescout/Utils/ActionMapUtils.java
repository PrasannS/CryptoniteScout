package cryptonite624.android.apps.com.cryptonitescout.Utils;

import java.util.ArrayList;
import java.util.StringTokenizer;

import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;
import cryptonite624.android.apps.com.cryptonitescout.Models.RobotAction;

public class ActionMapUtils {


    /*public static ActionMap parseString(String s){
        String [] parsed = s.split(" ");
        ArrayList<RobotAction> robotActions = new ArrayList<RobotAction>();
        for(int i = 0; i < parsed.length; i++){
            RobotAction robotAction = new RobotAction(parsed[i]);
        }
        ActionMap tempMap = new ActionMap(parsed);
    }*/

    public static boolean isFilled(String code, ActionMap actionMap, ArrayList<RobotAction> actions){
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


    public static int totalPoints(ArrayList<RobotAction> actions){
        return actions.size();
    }
    /*
    public ActionMap getMiniMap(int code, int ms, ArrayList<RobotAction> actions){
        ArrayList<RobotAction> acts = new ArrayList<>();
        for(RobotAction i : actions){
            if(i.actionCode.equals(code) && i.matchStatus == ms){
                acts.add(i);
            }
        }

        return new ActionMap(acts);
    }*/

    public static ActionMap parseActionMap(String s){
        ActionMap am = new ActionMap();
        StringTokenizer st = new StringTokenizer(s, ";");
        am.endclimb = Integer.parseInt(st.nextToken());
        am.matchnum = Integer.parseInt(st.nextToken());
        am.pos = Integer.parseInt(st.nextToken());

        StringTokenizer st1 = new StringTokenizer(st.nextToken(), ",");
        while(st1.hasMoreTokens()){
            am.actions.add(new RobotAction(st1.nextToken()));
        }

        return am;
    }



}
