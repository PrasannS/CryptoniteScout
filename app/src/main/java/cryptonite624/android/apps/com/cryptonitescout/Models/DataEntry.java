package cryptonite624.android.apps.com.cryptonitescout.Models;

public class DataEntry {

    public String teamnum;
    public boolean baseline;
    public int [] ownswitchcubes = new int[4];
    public int [] opponentswitchcubes = new int[4];
    public int [] scalecubes = new int[4];
    //0 is no climb, 1 is solo, 2 is +1 and 3 is +2
    public int climb;

    public DataEntry(){

    }
    public DataEntry(String tn){
        teamnum = tn;
    }

    public void setTeamnum(String tn){
        teamnum = tn;
    }

}
