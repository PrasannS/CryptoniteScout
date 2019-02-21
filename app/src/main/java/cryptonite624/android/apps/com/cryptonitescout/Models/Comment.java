package cryptonite624.android.apps.com.cryptonitescout.Models;

import com.orm.SugarRecord;

public class Comment extends SugarRecord {

    public String comment;
    public int teamnum;
    public int matchnum;
    public int pos;
    public int hatchefficiency;
    public int cargoefficiency;
    //public int climbefficiency;
    public int defense;
    public boolean excessivefouls;
    public boolean broken;
    public String whybroken;

    public Comment(){
    }
}
