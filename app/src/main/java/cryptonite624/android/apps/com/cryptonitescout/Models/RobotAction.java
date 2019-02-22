package cryptonite624.android.apps.com.cryptonitescout.Models;
import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

import java.util.StringTokenizer;

public class RobotAction extends SugarRecord{
    //matchstatus, 0 = pregame, 1 = auton, 2 = teleop, 3 = endgame
    //actionCode 0 = not on switch, 1 = jankredleft switch 1, 2 = jankblueleft switch1, 3 = jankblueleft scale, 4 = jankredleft scale, 5 = jankredleft switch2, 6 = jankblueleft switch, 7 = invalid click
    int matchStatus;
    String actionCode;
    boolean hatch;
    int habLevel;
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

}
