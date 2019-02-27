package cryptonite624.android.apps.com.cryptonitescout.Models;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;


@Entity(nameInDb = "actionmap")
public class ActionMap{

    @Property(nameInDb = "actions")
    String actions;
    @Property(nameInDb = "endclimb")
    int endclimb;
    @Property(nameInDb = "matchnum")
    int matchnum;
    @Property(nameInDb = "pos")
    int pos;
    @Property(nameInDb = "climbtime")
    int climbTime;


    public ActionMap(){
    }

    @Generated(hash = 1301682623)
    public ActionMap(String actions, int endclimb, int matchnum, int pos,
            int climbTime) {
        this.actions = actions;
        this.endclimb = endclimb;
        this.matchnum = matchnum;
        this.pos = pos;
        this.climbTime = climbTime;
    }

    public List<RobotAction> getActionsList() {
        Type listType = new TypeToken<ArrayList<RobotAction>>(){}.getType();
        List<RobotAction> arrayList = new Gson().fromJson(actions, listType);
        return arrayList;
    }

    public String getActions(){
        return actions;
    }

    public void setActions(ArrayList<RobotAction> actions) {
        String arrayString = new Gson().toJson(actions);
        this.actions = arrayString;
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

    public void setActions(String actions) {
        this.actions = actions;
    }
}
