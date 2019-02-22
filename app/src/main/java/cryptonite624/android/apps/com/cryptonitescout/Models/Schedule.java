package cryptonite624.android.apps.com.cryptonitescout.Models;

import com.cpjd.models.matches.Match;
import com.cpjd.models.matches.MatchAlliance;
import com.orm.SugarDb;
import com.orm.SugarRecord;

import java.util.List;

public class Schedule extends SugarRecord {

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

    String r1,r2,r3,b1,b2,b3;
    int matchnum;

    public String getR1() {
        return r1;
    }

    public void setR1(String r1) {
        this.r1 = r1;
    }

    public void setR2(String r2) {
        this.r2 = r2;
    }

    public String getR3() {
        return r3;
    }

    public void setR3(String r3) {
        this.r3 = r3;
    }

    public String getB1() {
        return b1;
    }

    public void setB1(String b1) {
        this.b1 = b1;
    }

    public String getB2() {
        return b2;
    }

    public void setB2(String b2) {
        this.b2 = b2;
    }

    public String getB3() {
        return b3;
    }

    public void setB3(String b3) {
        this.b3 = b3;
    }

    public int getMatchnum() {
        return matchnum;
    }

    public void setMatchnum(int matchnum) {
        this.matchnum = matchnum;
    }

    public String getR2() {

        return r2;
    }
}
