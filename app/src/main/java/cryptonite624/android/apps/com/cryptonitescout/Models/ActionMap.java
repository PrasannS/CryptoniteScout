package cryptonite624.android.apps.com.cryptonitescout.Models;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint("ParcelCreator")
public class ActionMap implements Parcelable{

    public ArrayList<RobotAction> actions;


    public ActionMap(){
        actions = new ArrayList<>();
    }

    public ActionMap(ArrayList<RobotAction> acts){
        actions = acts;
    }

    protected ActionMap(Parcel in) {
        actions = in.createTypedArrayList(RobotAction.CREATOR);
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

    public int totalhatches(boolean b){
        int count = 0;
        for(RobotAction r: actions){
            if(b==r.hatch){
                count++;
            }
        }
        return count;
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

    public boolean isFilled(String code){
        if(actions.contains(code)){
            return true;
        }
        else{
            return false;
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
