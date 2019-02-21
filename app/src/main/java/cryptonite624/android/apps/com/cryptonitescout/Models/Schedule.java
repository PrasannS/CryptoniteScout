package cryptonite624.android.apps.com.cryptonitescout.Models;

import com.cpjd.models.matches.Match;
import com.cpjd.models.matches.MatchAlliance;
import com.orm.SugarRecord;

import java.util.List;

public class Schedule extends SugarRecord<Schedule> {

    public Schedule(){

    }

    public static void addScheduleFromAPI(Match m,int num){
        Schedule schedule = new Schedule();
        schedule.matchnum = num;
        String[] red = m.getRed().getTeamKeys();
        String[] blue = m.getBlue().getTeamKeys();
        schedule.r1 = red[0];
        schedule.r2 = red[1];
        schedule.r3 = red[2];
        schedule.b1 = blue[0];
        schedule.b2 = blue[1];
        schedule.b3 = blue[2];
        schedule.save();
    }

    public String r1,r2,r3,b1,b2,b3;
    public int matchnum;
}
