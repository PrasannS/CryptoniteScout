package cryptonite624.android.apps.com.cryptonitescout.Models;
import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

import java.util.StringTokenizer;

public class RobotAction extends SugarRecord<RobotAction>{
    //matchstatus, 0 = pregame, 1 = auton, 2 = teleop, 3 = endgame
    //actionCode 0 = not on switch, 1 = red switch 1, 2 = blue switch1, 3 = blue scale, 4 = red scale, 5 = red switch2, 6 = blue switch, 7 = invalid click
    public int matchStatus;
    public String actionCode;
    public boolean hatch;
    public int habLevel;
    public int time;

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
