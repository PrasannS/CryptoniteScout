package cryptonite624.android.apps.com.cryptonitescout.Models;
import android.os.Parcel;
import android.os.Parcelable;



import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.StringTokenizer;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "robotaction")
public class RobotAction {
    //matchstatus, 0 = pregame, 1 = auton, 2 = teleop, 3 = endgame
    //actionCode 0 = not on switch, 1 = jankredleft switch 1, 2 = jankblueleft switch1, 3 = jankblueleft scale, 4 = jankredleft scale, 5 = jankredleft switch2, 6 = jankblueleft switch, 7 = invalid click
    @Id(autoincrement = true)
    Long id;
    @Property(nameInDb = "matchstatus")
    int matchStatus;
    @Property(nameInDb = "actioncode")
    String actionCode;
    @Property(nameInDb = "hatch")
    boolean hatch;
    @Property(nameInDb = "hablevel")
    int habLevel;
    @Property(nameInDb = "time")
    int time;

    public int getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(int matchStatus) {
        this.matchStatus = matchStatus;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public boolean isHatch() {
        return hatch;
    }

    public void setHatch(boolean hatch) {
        this.hatch = hatch;
    }

    public int getHabLevel() {
        return habLevel;
    }

    public void setHabLevel(int habLevel) {
        this.habLevel = habLevel;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public RobotAction(){
    }

    public RobotAction(String s){

    }

    @Generated(hash = 621957603)
    public RobotAction(Long id, int matchStatus, String actionCode, boolean hatch, int habLevel, int time) {
        this.id = id;
        this.matchStatus = matchStatus;
        this.actionCode = actionCode;
        this.hatch = hatch;
        this.habLevel = habLevel;
        this.time = time;
    }

    public RobotAction parseRobotAction(String s){
        RobotAction r = new RobotAction();

        StringTokenizer st = new StringTokenizer(s, " ");

        r.actionCode = st.nextToken();
        r.hatch = Boolean.parseBoolean(st.nextToken());
        r.time = Integer.parseInt(st.nextToken());

        return r;
    }

    public String toString(){
        return actionCode + " " + hatch + " " + time;

    }

    public boolean getHatch() {
        return this.hatch;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
