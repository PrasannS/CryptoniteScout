package cryptonite624.android.apps.com.cryptonitescout.Models;

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

    public void teleopParse(String str){
        String[] split = str.split(",,,");
        exchangeCubes = Integer.parseInt(split[0]);
        ownSwitchCubes = Integer.parseInt(split[1]);
        scaleCubes = Integer.parseInt(split[2]);
        opponentCubes = Integer.parseInt(split[3]);
        totalCubes = Integer.parseInt(split[4]);
    }

}
