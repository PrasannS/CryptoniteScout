package cryptonite624.android.apps.com.cryptonitescout.Models;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "comment")
public class Comment  {
    @Property(nameInDb = "comment")
     String comment;
    @Property(nameInDb = "teamnum")
     int teamnum;
    @Property(nameInDb = "matchnum")
     int matchnum;
    @Property(nameInDb = "pos")
     int pos;
    @Property(nameInDb = "hatchefficiency")
     int hatchefficiency;
    @Property(nameInDb = "cargoefficiency")
     int cargoefficiency;
    @Property(nameInDb = "defense")
     int defense;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getTeamnum() {
        return teamnum;
    }

    public void setTeamnum(int teamnum) {
        this.teamnum = teamnum;
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

    public int getHatchefficiency() {
        return hatchefficiency;
    }

    public void setHatchefficiency(int hatchefficiency) {
        this.hatchefficiency = hatchefficiency;
    }

    public int getCargoefficiency() {
        return cargoefficiency;
    }

    public void setCargoefficiency(int cargoefficiency) {
        this.cargoefficiency = cargoefficiency;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public boolean isExcessivefouls() {
        return excessivefouls;
    }

    public void setExcessivefouls(boolean excessivefouls) {
        this.excessivefouls = excessivefouls;
    }

    public boolean isBroken() {
        return broken;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    public String getWhybroken() {
        return whybroken;
    }

    public void setWhybroken(String whybroken) {
        this.whybroken = whybroken;
    }

    public boolean getExcessivefouls() {
        return this.excessivefouls;
    }

    public boolean getBroken() {
        return this.broken;
    }

     boolean excessivefouls;
     boolean broken;
     String whybroken;

    public Comment(){
    }

    @Generated(hash = 1127973256)
    public Comment(String comment, int teamnum, int matchnum, int pos,
            int hatchefficiency, int cargoefficiency, int defense,
            boolean excessivefouls, boolean broken, String whybroken) {
        this.comment = comment;
        this.teamnum = teamnum;
        this.matchnum = matchnum;
        this.pos = pos;
        this.hatchefficiency = hatchefficiency;
        this.cargoefficiency = cargoefficiency;
        this.defense = defense;
        this.excessivefouls = excessivefouls;
        this.broken = broken;
        this.whybroken = whybroken;
    }
}
