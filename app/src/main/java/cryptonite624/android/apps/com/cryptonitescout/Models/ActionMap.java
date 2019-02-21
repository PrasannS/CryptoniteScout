package cryptonite624.android.apps.com.cryptonitescout.Models;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint("ParcelCreator")
public class ActionMap{

    public ArrayList<RobotAction> actions = new ArrayList<>();
    public int endclimb;


    public ActionMap(){
        actions = new ArrayList<>();
    }

    public ActionMap(ArrayList<RobotAction> acts){
        actions = acts;
    }

    /*protected ActionMap(Parcel in) {
        actions = in.createTypedArrayList(RobotAction.CREATOR);
    }*/

    /*public ActionMap(String str){
        parseString(str);
    }*/

    /*
    public static final Creator<ActionMap> CREATOR = new Creator<ActionMap>() {
        @Override
        public ActionMap createFromParcel(Parcel in) {
            return new ActionMap(in);
        }

        @Override
        public ActionMap[] newArray(int size) {
            return new ActionMap[size];
        }
    };*/

    //action code + match status




    /*
        actionCode, 0 = not on switch, 1 = jankredleft switch 1, 2 = jankblueleft switch1, 3 = jankblueleft scale, 4 = jankredleft scale, 5 = jankredleft switch2, 6 = jankblueleft switch, 7 = invalid click
        matchstatus, 0 = pregame, 1 = auton, 2 = teleop, 3 = endgame
     */




    public String toString(){
        String temp = "";
        for(RobotAction r:actions){
            temp+=r+",";
        }
        return temp+endclimb;
    }



/*
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(actions);
    }*/
}
