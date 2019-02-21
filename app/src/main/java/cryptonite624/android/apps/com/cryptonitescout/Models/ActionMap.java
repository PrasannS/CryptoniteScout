package cryptonite624.android.apps.com.cryptonitescout.Models;

import java.util.ArrayList;
import java.util.StringTokenizer;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;


public class ActionMap extends SugarRecord<ActionMap>{

    ArrayList<RobotAction> actions;
    int endclimb;
    int matchnum;
    int pos;
    int climbTime;


    public ActionMap(){
        actions = new ArrayList<>();
    }



    public String toString(){
        String temp = "";
        temp += endclimb + ";" + matchnum + ";" + pos + ";";
        for(RobotAction r:actions){
            temp+=r+",";
        }
        return temp;
    }

    public ArrayList<RobotAction> getActions() {
        return actions;
    }

    public void setActions(ArrayList<RobotAction> actions) {
        this.actions = actions;
    }

    public int getEndclimb() {
        return endclimb;
    }

    public void setEndclimb(int endclimb) {
        this.endclimb = endclimb;
    }

    public int getMatchnum() {
        return matchnum;
    }

    public void setMatchnum(int matchnum) {
        this.matchnum = matchnum;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getClimbTime() {
        return climbTime;
    }

    public void setClimbTime(int climbTime) {
        this.climbTime = climbTime;
    }
}
