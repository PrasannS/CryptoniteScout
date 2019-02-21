package cryptonite624.android.apps.com.cryptonitescout.Persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;
import cryptonite624.android.apps.com.cryptonitescout.Models.RankingData;
import cryptonite624.android.apps.com.cryptonitescout.Utils.ActionMapUtils;

public class CryptoniteScoutDAO {

    private SQLiteDatabase database;

    private String[] allRankingColumns = {
            "Teamnum"            ,
            "Status"             ,
            "MatchesPlayed"      ,
            "CargoAvg"           ,
            "HatchAvg"           ,
            "SandstormHatchOne"  ,
            "SandstormHatchTwo"  ,
            "SandstormHatchThree",
            "SandstormCargoOne"  ,
            "SandstormCargoTwo"  ,
            "SandstormCargoThree",
            "TeleopCargoOne"     ,
            "TeleopCargoTwo"     ,
            "TeleopCargoThree"   ,
            "TeleopHatchOne"     ,
            "TeleopHatchTwo"     ,
            "TeleopHatchThree"   ,
            "ClimbOne"           ,
            "ClimbTwo"           ,
            "ClimbThree"         ,
            "CxHatchCargoShip"   ,
            "CxCargoCargoShip"   ,
            "CxComboCargoShip"   ,
            "CxCargoRocket"      ,
            "CxHatchRocket"      ,
            "CxComboRocket"

    };

    public CryptoniteScoutDAO(Context context){
    }


    public void /*Cursor*/ getUnsyncedNames() {
        //TODO
        return ;
    }


    public void getRankingFromMap(ActionMap a, int teamnum){
        String[] temparr = new String[4];
        List<RankingData> r = RankingData.findWithQuery(RankingData.class, "Select * from RankingData where Teamnum = ?", teamnum+"");;
        int cargototal=0;
        int hatchtotal=0;
        r.get(0).setTeamnum(teamnum);
        r.get(0).setStatus(0);
        r.get(0).getMatchesPlayed();
        temparr[0]="A1";
        temparr[1]="B1";
        temparr[2]="A2";
        temparr[3]="B2";
        int[] status = {1};
        r.get(0).setHatchAvg(ActionMapUtils.numScored(temparr, status, false, a.getActions()));
        temparr[0]="A3";
        temparr[1]="B3";
        temparr[2]="A4";
        temparr[3]="B4";
        cargototal+=r.get(0).getSandstormCargoOne();
        r.get(0).setSandstormCargoTwo(ActionMapUtils.numScored(temparr, status, false, a.getActions()));
        temparr[0]="A5";
        temparr[1]="B5";
        temparr[2]="A6";
        temparr[3]="B6";
        cargototal+=r.get(0).getSandstormCargoTwo();
        r.get(0).setSandstormCargoThree(ActionMapUtils.numScored(temparr, status, false, a.getActions()));
        temparr[0]="A1";
        temparr[1]="B1";
        temparr[2]="A2";
        temparr[3]="B2";
        cargototal+=r.get(0).getSandstormCargoThree();
        r.get(0).setSandstormHatchOne(ActionMapUtils.numScored(temparr, status, false, a.getActions()));
        temparr[0]="A3";
        temparr[1]="B3";
        temparr[2]="A4";
        temparr[3]="B4";
        hatchtotal+=r.get(0).getSandstormHatchOne();
        r.get(0).setSandstormHatchTwo(ActionMapUtils.numScored(temparr, status, false, a.getActions()));
        temparr[0]="A5";
        temparr[1]="B5";
        temparr[2]="A6";
        temparr[3]="B6";
        hatchtotal+=r.get(0).getSandstormHatchTwo();
        r.get(0).setSandstormHatchThree(ActionMapUtils.numScored(temparr, status, false, a.getActions()));
        temparr[0]="A1";
        temparr[1]="B1";
        temparr[2]="A2";
        temparr[3]="B2";
        status[0]=2;
        hatchtotal+=r.get(0).getSandstormHatchThree();
        r.get(0).setTeleopCargoOne(ActionMapUtils.numScored(temparr, status, false, a.getActions()));
        temparr[0]="A3";
        temparr[1]="B3";
        temparr[2]="A4";
        temparr[3]="B4";
        cargototal+=r.get(0).getTeleopCargoOne();
        r.get(0).setTeleopCargoTwo(ActionMapUtils.numScored(temparr, status, false, a.getActions()));
        temparr[0]="A5";
        temparr[1]="B5";
        temparr[2]="A6";
        temparr[3]="B6";
        cargototal+=r.get(0).getTeleopCargoTwo();
        r.get(0).setTeleopCargoThree(ActionMapUtils.numScored(temparr, status, false, a.getActions()));
        temparr[0]="A1";
        temparr[1]="B1";
        temparr[2]="A2";
        temparr[3]="B2";
        cargototal+=r.get(0).getTeleopCargoThree();
        r.get(0).setTeleopHatchOne(ActionMapUtils.numScored(temparr, status, false, a.getActions()));
        temparr[0]="A3";
        temparr[1]="B3";
        temparr[2]="A4";
        temparr[3]="B4";
        hatchtotal+=r.get(0).getTeleopHatchOne();
        r.get(0).setTeleopHatchTwo(ActionMapUtils.numScored(temparr, status, false, a.getActions()));
        temparr[0]="A5";
        temparr[1]="B5";
        temparr[2]="A6";
        temparr[3]="B6";
        hatchtotal+=r.get(0).getTeleopHatchTwo();
        r.get(0).setTeleopHatchThree(ActionMapUtils.numScored(temparr, status, false, a.getActions()));
        hatchtotal+=r.get(0).getTeleopHatchThree();
        String[] cargoship = {"C1","C2","C3","C4","C5","C6"};
        int[]all = {0,1,2,3};

        r.get(0).setCargoAvg(cargototal/(double)r.get(0).getMatchesPlayed());
        r.get(0).setHatchAvg(hatchtotal/(double)r.get(0).getMatchesPlayed());
        r.get(0).setCxCargoCargoShip((r.get(0).getCxCargoCargoShip()*(r.get(0).getMatchesPlayed()-1)+ActionMapUtils.numScored(cargoship, all, false, a.getActions()))/(double)r.get(0).getMatchesPlayed());
        r.get(0).setCxHatchCargoShip(r.get(0).getCxHatchRocket()*(r.get(0).getMatchesPlayed()-1)+ActionMapUtils.numScored(cargoship, all, false, a.getActions())/(double)r.get(0).getMatchesPlayed());
        r.get(0).setCxCargoRocket(((r.get(0).getSandstormCargoOne()+r.get(0).getTeleopCargoOne())+(r.get(0).getSandstormCargoTwo()+r.get(0).getTeleopCargoTwo())*1.5+(r.get(0).getSandstormCargoThree()+r.get(0).getTeleopCargoThree())*2)/r.get(0).getMatchesPlayed());
        r.get(0).setCxHatchRocket(((r.get(0).getSandstormHatchOne()+r.get(0).getTeleopHatchOne())+(r.get(0).getSandstormHatchTwo()+r.get(0).getTeleopHatchTwo())*1.5+(r.get(0).getSandstormHatchThree()+r.get(0).getTeleopHatchThree())*2)/r.get(0).getMatchesPlayed());
        r.get(0).setCxComboRocket( r.get(0).getCxCargoRocket()+r.get(0).getCxHatchRocket());
        r.get(0).setCxComboCargoShip(r.get(0).getCxCargoCargoShip()+r.get(0).getCxHatchCargoShip());

        if(a.getEndclimb()!=0){
            if (a.getEndclimb() == 1) {
                r.get(0).setClimbOne((r.get(0).getClimbOne()*(r.get(0).getMatchesPlayed()-1)+1)/r.get(0).getMatchesPlayed()) ;
            }
            if (a.getEndclimb() == 2) {
                r.get(0).setClimbTwo((r.get(0).getClimbTwo()*(r.get(0).getMatchesPlayed()-1)+1)/r.get(0).getMatchesPlayed());
            }
            if (a.getEndclimb() == 3) {
                r.get(0).setClimbThree((r.get(0).getClimbThree()*(r.get(0).getMatchesPlayed()-1)+1)/r.get(0).getMatchesPlayed());
            }
        }
        r.get(0).save();

    }

    /**
     * TODO
     * Recheck all this stuff, not yet tested
     * @param match
     */


}