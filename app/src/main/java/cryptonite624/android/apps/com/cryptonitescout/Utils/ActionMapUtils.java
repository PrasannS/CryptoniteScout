package cryptonite624.android.apps.com.cryptonitescout.Utils;

import java.util.ArrayList;
import java.util.List;
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

    public static int numScored(String [] basecodes, int [] matchStatus, boolean hatch, List<RobotAction> actions){
        int total = 0;

        for(String c : basecodes) {
            for(int m : matchStatus) {
                for (RobotAction i : actions) {
                    if (i.getActionCode().equals(c+i.getMatchStatus())) {
                        if(i.isHatch()==hatch)
                            total++;
                    }
                }
            }
        }
        return total;
    }

    public static int totalisHatch(boolean b, List<RobotAction> actions){
        int count =0;
        for(RobotAction r : actions){
            if(r.isHatch()==b){
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
            if(i.getActionCode().equals(code) && i.matchStatus == ms){
                acts.add(i);
            }
        }

        return new ActionMap(acts);
    }*/

    public static ActionMap parseActionMap(String s){
        ActionMap am = new ActionMap();
        StringTokenizer st = new StringTokenizer(s, ";");
        am.setEndclimb( Integer.parseInt(st.nextToken()));
        am.setMatchnum( Integer.parseInt(st.nextToken()));
        am.setPos( Integer.parseInt(st.nextToken()));

        StringTokenizer st1 = new StringTokenizer(st.nextToken(), ",");
        while(st1.hasMoreTokens()){
            am.getActions().add(new RobotAction(st1.nextToken()));
        }

        return am;
    }



}
