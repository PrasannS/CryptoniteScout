package cryptonite624.android.apps.com.cryptonitescout.Models;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint("ParcelCreator")
public class ActionMap implements Parcelable{

    public ArrayList<RobotAction> actions = new ArrayList<>();
    public int endclimb;


    public ActionMap(){
        actions = new ArrayList<>();
    }

    public ActionMap(ArrayList<RobotAction> acts){
        actions = acts;
    }

    protected ActionMap(Parcel in) {
        actions = in.createTypedArrayList(RobotAction.CREATOR);
    }

    public ActionMap(String str){
        parseString(str);
    }

    public static final Creator<ActionMap> CREATOR = new Creator<ActionMap>() {
        @Override
        public ActionMap createFromParcel(Parcel in) {
            return new ActionMap(in);
        }

        @Override
        public ActionMap[] newArray(int size) {
            return new ActionMap[size];
        }
    };

    //action code + match status
    public int numScored(String [] basecodes, int [] matchStatus, boolean hatch){
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

    public int totalhatches(boolean b){
        int count =0;
        for(RobotAction r : actions){
            if(r.hatch==b){
                count++;
            }
        }
        return count;
    }


    public int totalPoints(){
        return actions.size();
    }

    /*
        actionCode, 0 = not on switch, 1 = jankredleft switch 1, 2 = jankblueleft switch1, 3 = jankblueleft scale, 4 = jankredleft scale, 5 = jankredleft switch2, 6 = jankblueleft switch, 7 = invalid click
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

    public boolean isFilled(String code){
        if(actions.contains(code)){
            return true;
        }
        else{
            return false;
        }
    }

    public String toString(){
        String temp = "";
        for(RobotAction r:actions){
            temp+=r+",";
        }
        return temp+endclimb;
    }

    public void parseString(String s){
        String [] parsed = s.split(" ");
        for(int i = 0; i < parsed.length; i++){
            RobotAction robotAction = new RobotAction(parsed[i]);

        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(actions);
    }
}
