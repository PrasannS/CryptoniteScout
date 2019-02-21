package cryptonite624.android.apps.com.cryptonitescout.Models;

import com.orm.SugarRecord;

public class Comment extends SugarRecord {

     String comment;
     int teamnum;
     int matchnum;
     int pos;
     int hatchefficiency;
     int cargoefficiency;
    //ic int climbefficiency;
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

     boolean excessivefouls;
     boolean broken;
     String whybroken;

    public Comment(){
    }
}
