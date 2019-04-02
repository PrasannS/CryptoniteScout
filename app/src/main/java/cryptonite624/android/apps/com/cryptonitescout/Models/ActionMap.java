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
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;


@Entity(nameInDb = "actionmap")
public class ActionMap{

    @Id(autoincrement = true)
    Long id;

    @Property(nameInDb = "actions")
    String actions;
    @Property(nameInDb = "endclimb")
    int endclimb;
    @Property(nameInDb = "matchnum")
    int matchnum;
    @Property(nameInDb = "teamnum")
    int teamnum;
    @Property(nameInDb = "pos")
    String pos;
    @Property(nameInDb = "climbtime")
    int climbTime;


    public ActionMap(){
    }

    @Generated(hash = 1592144978)
    public ActionMap(Long id, String actions, int endclimb, int matchnum,
            int teamnum, String pos, int climbTime) {
        this.id = id;
        this.actions = actions;
        this.endclimb = endclimb;
        this.matchnum = matchnum;
        this.teamnum = teamnum;
        this.pos = pos;
        this.climbTime = climbTime;
    }

    public List<RobotAction> getActionsList() {
        Type listType = new TypeToken<ArrayList<RobotAction>>(){}.getType();
        List<RobotAction> arrayList = new Gson().fromJson(actions, listType);
        if(actions.equals(""))
            return new ArrayList<RobotAction>();
        return arrayList;
    }

    public void removeLast(){
        Type listType = new TypeToken<ArrayList<RobotAction>>(){}.getType();
        List<RobotAction> arrayList = new Gson().fromJson(actions, listType);
        arrayList.remove(arrayList.size()-1);
        setActionsList((ArrayList<RobotAction>) arrayList);
    }

    public String getActions(){
        return actions;
    }

    public void setActionsList(ArrayList<RobotAction> actions) {
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

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTeamnum() {
        return this.teamnum;
    }

    public void setTeamnum(int teamnum) {
        this.teamnum = teamnum;
    }
}
