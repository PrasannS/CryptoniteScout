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

    public static EndgameEntry endgameParse(String str){
        String[] split = str.split(",,,");
        EndgameEntry e = new EndgameEntry();
        e.climb = Integer.parseInt(split[0]);
        e.climbSupported = Integer.parseInt(split[1]);
        e.techFouls = Boolean.parseBoolean(split[2]);
        e.endgameComments = split[3];
        return e;
    }
}
