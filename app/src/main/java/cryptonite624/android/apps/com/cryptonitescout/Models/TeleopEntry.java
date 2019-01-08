package cryptonite624.android.apps.com.cryptonitescout.Models;

import android.widget.TextView;

public class TeleopEntry extends DataEntry {
    public int exchangeCubes;
    public int ownSwitchCubes;
    public int scaleCubes;
    public int opponentCubes;
    public int totalCubes;

    public TeleopEntry(){}

    public TeleopEntry(int tn){super.setTeamnum(tn);}

    public String toString(){
        return "" + exchangeCubes + ",,," + ownSwitchCubes + ",,," + scaleCubes + ",,," + opponentCubes + ",,," + totalCubes;
    }

    public static TeleopEntry teleopParse(String str){
        String[] split = str.split(",,,");
        TeleopEntry t = new TeleopEntry();
        t.exchangeCubes = Integer.parseInt(split[0]);
        t.ownSwitchCubes = Integer.parseInt(split[1]);
        t.scaleCubes = Integer.parseInt(split[2]);
        t.opponentCubes = Integer.parseInt(split[3]);
        t.totalCubes = Integer.parseInt(split[4]);
        return t;
    }

}
