package cryptonite624.android.apps.com.cryptonitescout.Models;

public class FinalDataEntry {
    public AutonEntry autonEntry;
    public PregameEntry pregameEntry ;
    public TeleopEntry teleopEntry;
    public EndgameEntry endgameEntry;

    public FinalDataEntry(){

    }

    public FinalDataEntry(AutonEntry a,PregameEntry p, TeleopEntry t, EndgameEntry e){
        autonEntry = a;
        pregameEntry = p;
        teleopEntry = t;
        endgameEntry = e;
    }
}
