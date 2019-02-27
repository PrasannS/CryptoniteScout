package cryptonite624.android.apps.com.cryptonitescout.Models;

import com.cpjd.models.matches.Match;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "schedule")

public class Schedule  {

    public Schedule(){

    }

    @Generated(hash = 940805172)
    public Schedule(String r1, String r2, String r3, String b1, String b2,
            String b3, int matchnum) {
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.matchnum = matchnum;
    }



    @Property(nameInDb = "r1")
    String r1;
    @Property(nameInDb = "r2")
    String r2;
    @Property(nameInDb = "r3")
    String r3;
    @Property(nameInDb = "b1")
    String b1;
    @Property(nameInDb = "b2")
    String b2;
    @Property(nameInDb = "b3")
    String b3;
    @Property(nameInDb = "matchnum")
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
