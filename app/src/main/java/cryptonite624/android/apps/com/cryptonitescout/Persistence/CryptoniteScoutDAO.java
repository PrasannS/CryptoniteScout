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
        r.get(0).Teamnum=teamnum;
        r.get(0).Status =0;
        r.get(0).getMatchesPlayed()++;
        temparr[0]="A1";
        temparr[1]="B1";
        temparr[2]="A2";
        temparr[3]="B2";
        int[] status = {1};
        r.get(0).getSandstormCargoOne+=ActionMapUtils.numScored(temparr, status, false, a.getActions); 
        temparr[0]="A3";
        temparr[1]="B3";
        temparr[2]="A4";
        temparr[3]="B4";
        cargototal+=r.get(0).getSandstormCargoOne;
        r.get(0).getSandstormCargoTwo+=ActionMapUtils.numScored(temparr, status, false, a.getActions);
        temparr[0]="A5";
        temparr[1]="B5";
        temparr[2]="A6";
        temparr[3]="B6";
        cargototal+=r.get(0).getSandstormCargoTwo;
        r.get(0).getSandstormCargoThree+=ActionMapUtils.numScored(temparr, status, false, a.getActions);
        temparr[0]="A1";
        temparr[1]="B1";
        temparr[2]="A2";
        temparr[3]="B2";
        cargototal+=r.get(0).getSandstormCargoThree;
        r.get(0).getSandstormHatchOne+=ActionMapUtils.numScored(temparr, status, false, a.getActions);
        temparr[0]="A3";
        temparr[1]="B3";
        temparr[2]="A4";
        temparr[3]="B4";
        hatchtotal+=r.get(0).getSandstormHatchOne;
        r.get(0).getSandstormHatchTwo+=ActionMapUtils.numScored(temparr, status, false, a.getActions);
        temparr[0]="A5";
        temparr[1]="B5";
        temparr[2]="A6";
        temparr[3]="B6";
        hatchtotal+=r.get(0).getSandstormHatchTwo;
        r.get(0).getSandstormHatchThree+=ActionMapUtils.numScored(temparr, status, false, a.getActions);
        temparr[0]="A1";
        temparr[1]="B1";
        temparr[2]="A2";
        temparr[3]="B2";
        status[0]=2;
        hatchtotal+=r.get(0).getSandstormHatchThree;
        r.get(0).getTeleopCargoOne+=ActionMapUtils.numScored(temparr, status, false, a.getActions);
        temparr[0]="A3";
        temparr[1]="B3";
        temparr[2]="A4";
        temparr[3]="B4";
        cargototal+=r.get(0).getTeleopCargoOne;
        r.get(0).getTeleopCargoTwo+=ActionMapUtils.numScored(temparr, status, false, a.getActions);
        temparr[0]="A5";
        temparr[1]="B5";
        temparr[2]="A6";
        temparr[3]="B6";
        cargototal+=r.get(0).getTeleopCargoTwo;
        r.get(0).getTeleopCargoThree+=ActionMapUtils.numScored(temparr, status, false, a.getActions);
        temparr[0]="A1";
        temparr[1]="B1";
        temparr[2]="A2";
        temparr[3]="B2";
        cargototal+=r.get(0).getTeleopCargoThree;
        r.get(0).getTeleopHatchOne+=ActionMapUtils.numScored(temparr, status, false, a.getActions);
        temparr[0]="A3";
        temparr[1]="B3";
        temparr[2]="A4";
        temparr[3]="B4";
        hatchtotal+=r.get(0).getTeleopHatchOne;
        r.get(0).getTeleopHatchTwo+=ActionMapUtils.numScored(temparr, status, false, a.getActions);
        temparr[0]="A5";
        temparr[1]="B5";
        temparr[2]="A6";
        temparr[3]="B6";
        hatchtotal+=r.get(0).getTeleopHatchTwo;
        r.get(0).getTeleopHatchThree+=ActionMapUtils.numScored(temparr, status, false, a.getActions);
        hatchtotal+=r.get(0).getTeleopHatchThree;
        String[] cargoship = {"C1","C2","C3","C4","C5","C6"};
        int[]all = {0,1,2,3};

        r.get(0).setCargoAvg = cargototal/(double)r.get(0).getMatchesPlayed();
        r.get(0).setHatchAvg = hatchtotal/(double)r.get(0).getMatchesPlayed();
        r.get(0).setCxCargoCargoShip=(r.get(0).CxCargoCargoShip*(r.get(0).getMatchesPlayed()-1)+ActionMapUtils.numScored(cargoship, all, false, a.actions)/(double)r.get(0).getMatchesPlayed());
        r.get(0).setCxHatchCargoShip=(r.get(0).CxHatchCargoShip*(r.get(0).getMatchesPlayed()-1)+ActionMapUtils.numScored(cargoship, all, false, a.actions)/(double)r.get(0).getMatchesPlayed());
        r.get(0).setCxCargoRocket = ((r.get(0).SandstormCargoOne+r.get(0).TeleopCargoOne)+(r.get(0).SandstormCargoTwo+r.get(0).TeleopCargoTwo)*1.5+(r.get(0).SandstormCargoThree+r.get(0).TeleopCargoThree)*2)/r.get(0).getMatchesPlayed();
        r.get(0).setCxHatchRocket = ((r.get(0).SandstormHatchOne+r.get(0).TeleopHatchOne)+(r.get(0).SandstormHatchTwo+r.get(0).TeleopHatchTwo)*1.5+(r.get(0).SandstormHatchThree+r.get(0).TeleopHatchThree)*2)/r.get(0).getMatchesPlayed();
        r.get(0).setCxComboRocket = r.get(0).CxCargoRocket+r.get(0).CxHatchRocket;
        r.get(0).setCxComboCargoShip = r.get(0).CxCargoCargoShip+r.get(0).CxHatchCargoShip;

        if(a.getEndclimb()!=0){
            if (a.getEndclimb() == 1) {
                r.get(0).getClimbOne() = (r.get(0).getClimbOne()*(r.get(0).getMatchesPlayed()-1)+1)/r.get(0).getMatchesPlayed();
            }
            if (a.getEndclimb() == 2) {
                r.get(0).ClimbTwo = (r.get(0).ClimbTwo*(r.get(0).getMatchesPlayed()-1)+1)/r.get(0).getMatchesPlayed();
            }
            if (a.getEndclimb() == 3) {
                r.get(0).ClimbThree = (r.get(0).ClimbThree*(r.get(0).getMatchesPlayed()-1)+1)/r.get(0).getMatchesPlayed();
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