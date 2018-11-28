package cryptonite624.android.apps.com.cryptonitescout.Models;

import android.widget.Button;

public class AutonEntry extends DataEntry {

    public boolean baslineCrossed = false;
    public boolean AutoSwitchAttempt = false;
    public boolean AutonSscaleAttemp = false;
    public String AutonNotes = "";
    public int AutonSwitchCubes;
    public int AutonScaleCubes;
    public int StartingPosition;
    // 0 = not selected, 1 = Left, 2 = Center, 3 = Right;

    public AutonEntry(){
        super();
    }
     public void setTeamNum(String tn){
        super.setTeamnum(tn);
     }

     AutonEntry autonEntry = new AutonEntry();


}

