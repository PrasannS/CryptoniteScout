package cryptonite624.android.apps.com.cryptonitescout.Persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;
import cryptonite624.android.apps.com.cryptonitescout.Models.RankingData;

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
        r.get(0).MatchesPlayed++;
        temparr[0]="A1";
        temparr[1]="B1";
        temparr[2]="A2";
        temparr[3]="B2";
        int[] status = {1};
        r.get(0).SandstormCargoOne+=a.numScored(temparr,status,false);
        temparr[0]="A3";
        temparr[1]="B3";
        temparr[2]="A4";
        temparr[3]="B4";
        cargototal+=r.get(0).SandstormCargoOne;
        r.get(0).SandstormCargoTwo+=a.numScored(temparr,status,false);
        temparr[0]="A5";
        temparr[1]="B5";
        temparr[2]="A6";
        temparr[3]="B6";
        cargototal+=r.get(0).SandstormCargoTwo;
        r.get(0).SandstormCargoThree+=a.numScored(temparr,status,false);
        temparr[0]="A1";
        temparr[1]="B1";
        temparr[2]="A2";
        temparr[3]="B2";
        cargototal+=r.get(0).SandstormCargoThree;
        r.get(0).SandstormHatchOne+=a.numScored(temparr,status,false);
        temparr[0]="A3";
        temparr[1]="B3";
        temparr[2]="A4";
        temparr[3]="B4";
        hatchtotal+=r.get(0).SandstormHatchOne;
        r.get(0).SandstormHatchTwo+=a.numScored(temparr,status,false);
        temparr[0]="A5";
        temparr[1]="B5";
        temparr[2]="A6";
        temparr[3]="B6";
        hatchtotal+=r.get(0).SandstormHatchTwo;
        r.get(0).SandstormHatchThree+=a.numScored(temparr,status,false);
        temparr[0]="A1";
        temparr[1]="B1";
        temparr[2]="A2";
        temparr[3]="B2";
        status[0]=2;
        hatchtotal+=r.get(0).SandstormHatchThree;
        r.get(0).TeleopCargoOne+=a.numScored(temparr,status,false);
        temparr[0]="A3";
        temparr[1]="B3";
        temparr[2]="A4";
        temparr[3]="B4";
        cargototal+=r.get(0).TeleopCargoOne;
        r.get(0).TeleopCargoTwo+=a.numScored(temparr,status,false);
        temparr[0]="A5";
        temparr[1]="B5";
        temparr[2]="A6";
        temparr[3]="B6";
        cargototal+=r.get(0).TeleopCargoTwo;
        r.get(0).TeleopCargoThree+=a.numScored(temparr,status,false);
        temparr[0]="A1";
        temparr[1]="B1";
        temparr[2]="A2";
        temparr[3]="B2";
        cargototal+=r.get(0).TeleopCargoThree;
        r.get(0).TeleopHatchOne+=a.numScored(temparr,status,false);
        temparr[0]="A3";
        temparr[1]="B3";
        temparr[2]="A4";
        temparr[3]="B4";
        hatchtotal+=r.get(0).TeleopHatchOne;
        r.get(0).TeleopHatchTwo+=a.numScored(temparr,status,false);
        temparr[0]="A5";
        temparr[1]="B5";
        temparr[2]="A6";
        temparr[3]="B6";
        hatchtotal+=r.get(0).TeleopHatchTwo;
        r.get(0).TeleopHatchThree+=a.numScored(temparr,status,false);
        hatchtotal+=r.get(0).TeleopHatchThree;
        String[] cargoship = {"C1","C2","C3","C4","C5","C6"};
        int[]all = {0,1,2,3};

        r.get(0).CargoAvg = cargototal/(double)r.get(0).MatchesPlayed;
        r.get(0).HatchAvg = hatchtotal/(double)r.get(0).MatchesPlayed;
        r.get(0).CxCargoCargoShip=(r.get(0).CxCargoCargoShip*(r.get(0).MatchesPlayed-1)+a.numScored(cargoship,all,false))/(double)r.get(0).MatchesPlayed;
        r.get(0).CxHatchCargoShip=(r.get(0).CxHatchCargoShip*(r.get(0).MatchesPlayed-1)+a.numScored(cargoship,all,true))/(double)r.get(0).MatchesPlayed;
        r.get(0).CxCargoRocket = ((r.get(0).SandstormCargoOne+r.get(0).TeleopCargoOne)+(r.get(0).SandstormCargoTwo+r.get(0).TeleopCargoTwo)*1.5+(r.get(0).SandstormCargoThree+r.get(0).TeleopCargoThree)*2)/r.get(0).MatchesPlayed;
        r.get(0).CxHatchRocket = ((r.get(0).SandstormHatchOne+r.get(0).TeleopHatchOne)+(r.get(0).SandstormHatchTwo+r.get(0).TeleopHatchTwo)*1.5+(r.get(0).SandstormHatchThree+r.get(0).TeleopHatchThree)*2)/r.get(0).MatchesPlayed;
        r.get(0).CxComboRocket = r.get(0).CxCargoRocket+r.get(0).CxHatchRocket;
        r.get(0).CxComboCargoShip = r.get(0).CxCargoCargoShip+r.get(0).CxHatchCargoShip;

        if(a.endclimb!=0){
            if (a.endclimb == 1) {
                r.get(0).ClimbOne = (r.get(0).ClimbOne*(r.get(0).MatchesPlayed-1)+1)/r.get(0).MatchesPlayed;
            }
            if (a.endclimb == 2) {
                r.get(0).ClimbTwo = (r.get(0).ClimbTwo*(r.get(0).MatchesPlayed-1)+1)/r.get(0).MatchesPlayed;
            }
            if (a.endclimb == 3) {
                r.get(0).ClimbThree = (r.get(0).ClimbThree*(r.get(0).MatchesPlayed-1)+1)/r.get(0).MatchesPlayed;
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