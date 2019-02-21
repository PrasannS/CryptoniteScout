package cryptonite624.android.apps.com.cryptonitescout.Models;

import java.util.ArrayList;
import java.util.StringTokenizer;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;


public class ActionMap extends SugarRecord<ActionMap>{

    public ArrayList<RobotAction> actions;
    public int endclimb;
    public int matchnum;
    public int pos;
    public int climbTime;


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


}
