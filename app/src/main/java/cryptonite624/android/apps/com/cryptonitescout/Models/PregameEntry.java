package cryptonite624.android.apps.com.cryptonitescout.Models;

public class PregameEntry extends DataEntry {
    public PregameEntry(){
        super();
    }
    public int matchNumber;
    public boolean noShow = false;
    public boolean LostCommns = false;

    @Override
    public void setTeamnum(String tn) {
        super.setTeamnum(tn);
    }

    public PregameEntry (int teamNum){
        teamNum = teamNum;
    }

    public String toString(){
        return ""+matchNumber+",,,"+noShow+",,,"+LostCommns;
    }

    public void PregameParse(String str) {
        String [] split = str.split(",,,");
        matchNumber = Integer.parseInt(split[0]);
        noShow = Boolean.parseBoolean(split[1]);
        LostCommns = Boolean.parseBoolean(split[2]);
    }

}
