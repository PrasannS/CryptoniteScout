package cryptonite624.android.apps.com.cryptonitescout.Models;

public class EndgameEntry extends DataEntry {
    //0 = did not attempt, 1 = attempted, 2 = successful
    public int climb;
    //
    public int climbSupported;
    public boolean techFouls;
    public String endgameComments;

    public EndgameEntry(){super();}
    public EndgameEntry(int tn){super.setTeamnum(tn);}

    public String toString(){
        return "" + climb + ",,," + climbSupported + ",,," + techFouls + ",,," + endgameComments;
    }

    public void endgameParse(String str){
        String[] split = str.split(",,,");
        climb = Integer.parseInt(split[0]);
        climbSupported = Integer.parseInt(split[1]);
        techFouls = Boolean.parseBoolean(split[2]);
        endgameComments = split[3];
    }
}
