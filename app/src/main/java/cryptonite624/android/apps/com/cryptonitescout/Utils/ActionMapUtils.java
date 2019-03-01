package cryptonite624.android.apps.com.cryptonitescout.Utils;

import java.lang.reflect.Array;
import java.util.List;
import java.util.List;
import java.util.StringTokenizer;

import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;
import cryptonite624.android.apps.com.cryptonitescout.Models.RobotAction;

public class ActionMapUtils {


    /*public static ActionMap parseString(String s){
        String [] parsed = s.split(" ");
        List<RobotAction> robotActions = new List<RobotAction>();
        for(int i = 0; i < parsed.length; i++){
            RobotAction robotAction = new RobotAction(parsed[i]);
        }
        ActionMap tempMap = new ActionMap(parsed);
    }*/

    public static boolean isFilled(String code, ActionMap actionMap, List<RobotAction> actions){
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

    public static int totalhatches(boolean b, List<RobotAction> actions){
        int count =0;
        for(RobotAction r : actions){
            if(r.isHatch()==b){
                count++;
            }
        }
        return count;
    }


    public static int totalPoints(List<RobotAction> actions){
        return actions.size();
    }
    /*
    public ActionMap getMiniMap(int code, int ms, List<RobotAction> actions){
        List<RobotAction> acts = new List<>();
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
        am.setPos(st.nextToken());

        StringTokenizer st1 = new StringTokenizer(st.nextToken(), ",");
        while(st1.hasMoreTokens()){
            am.getActionsList().add(new RobotAction(st1.nextToken()));
        }

        return am;
    }

    public static int tournamentTotalHatches(List<ActionMap> maps){
        int count = 0;
        for(ActionMap m : maps){
            count += totalhatches(true, m.getActionsList());
        }
        return count;
    }

    public static int tournamentTotalCargos(List<ActionMap> maps){
        int count = 0;
        for(ActionMap m : maps){
            count += totalhatches(false, m.getActionsList());
        }
        return count;
    }

    public static double tournamentAverageHatch(List<ActionMap> maps){
        double average = tournamentTotalHatches(maps) / maps.size();
        return average;
    }

    public static double tournamentAverageCargo(List<ActionMap> maps){
        double average = tournamentTotalCargos(maps) / maps.size();
        return average;
    }

    public static int tournamentHighestClimbLv(List<ActionMap> maps){
        int lv = 0;
        for(ActionMap m : maps){
            if(m.getEndclimb() > lv){
                lv = m.getEndclimb();
            }
        }
        return lv;
    }

    public static String toString(ActionMap m){
        String temp = "";
        temp += m.getEndclimb() + ";" + m.getMatchnum() + ";" + m.getPos() + ";";
        for(RobotAction r:m.getActionsList()){
            temp+=r+",";
        }
        return temp;
    }





}
