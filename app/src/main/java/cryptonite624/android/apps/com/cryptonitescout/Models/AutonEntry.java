package cryptonite624.android.apps.com.cryptonitescout.Models;

import android.widget.Button;

public class AutonEntry extends DataEntry {

    public boolean baslineCrossed = false;
    public boolean AutoSwitchAttempt = false;
    public String AutonNotes = "";
    public int AutonSwitchCubes;
    public int AutonScaleCubes;
    public int StartingPosition;
    // 0 = not selected, 1 = Left, 2 = Center, 3 = Right;

    public AutonEntry(){
        super();

    }

    public AutonEntry(int tn){
        super.setTeamnum(tn);
}
     public void setTeamNum(int tn){
        super.setTeamnum(tn);
     }

     public AutonEntry autonEntry;

    public String toString() {
        return ""+baslineCrossed+",,,"+AutoSwitchAttempt+",,,"+AutonNotes+",,,"+AutonSwitchCubes+",,,"
                +AutonScaleCubes+",,,"+StartingPosition;
    }

    public void AutonParse (String str) {
        String [] split = str.split(",,,");
        baslineCrossed = Boolean.parseBoolean(split[0]);
        AutoSwitchAttempt = Boolean.parseBoolean(split[1]);
        AutonNotes = split[2];
        AutonScaleCubes = Integer.parseInt(split[3]);
        AutonScaleCubes = Integer.parseInt(split[4]);
        StartingPosition = Integer.parseInt(split[5]);
    }


}

