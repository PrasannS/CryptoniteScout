package cryptonite624.android.apps.com.cryptonitescout.Models;

public class PregameEntry extends DataEntry {
    public PregameEntry(){
        super();
    }
    public int matchNumber;
    public boolean noShow = false;
    public boolean LostCommns = false;

    @Override
    public void setTeamnum(int tn) {
        super.setTeamnum(tn);
    }

    public PregameEntry (int teamNum){
       teamNum = teamnum;
    }

    public String toString(){
        return ""+matchNumber+",,,"+noShow+",,,"+LostCommns;
    }

    public static PregameEntry PregameParse(String str) {
        String [] split = str.split(",,,");
        PregameEntry p = new PregameEntry();
        p.matchNumber = Integer.parseInt(split[0]);
        p.noShow = Boolean.parseBoolean(split[1]);
        p.LostCommns = Boolean.parseBoolean(split[2]);
        return p;
    }

}
