package cryptonite624.android.apps.com.cryptonitescout.Persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.service.notification.NotificationListenerService;
import android.util.Log;

import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;
import cryptonite624.android.apps.com.cryptonitescout.Models.Match;
import cryptonite624.android.apps.com.cryptonitescout.Models.RankingData;
import cryptonite624.android.apps.com.cryptonitescout.Models.User;

public class CryptoniteScoutDAO {

    private SQLiteDatabase database;
    private CryptoniteScoutDBHelper dbHelper;

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
        try{
            dbHelper = new CryptoniteScoutDBHelper(context);}
        catch (Exception e){
            Log.d("DBLoadError","DBHelper Constructor not working",e);
        }
    }

    public void open() throws SQLException {
        database=dbHelper.getWritableDatabase();
    }

    public Cursor getUnsyncedNames() {
        String sql = "SELECT * FROM " + CryptoniteScoutDBHelper.RANKINGS_TABLE_NAME + " WHERE " + "Status" + " = 0;";
        Cursor c = database.rawQuery(sql, null);
        return c;
    }

    public void close() {
        dbHelper.close();
    }

    public void addUser(User user){
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Username",user.username);
        values.put("Status",user.status);
        values.put("Type",user.type);
        values.put("Email",user.email);
        long insertID = database.insert(CryptoniteScoutDBHelper.USERS_TABLE_NAME, null, values);
        database.close();
    }

    public void updateRankingData(RankingData r){
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Teamnum"            ,r.Teamnum);
        values.put("Status"             ,r.Status);
        values.put("MatchesPlayed"      ,r.MatchesPlayed);
        values.put("CargoAvg"           ,r.CargoAvg);
        values.put("HatchAvg"           ,r.HatchAvg);
        values.put("SandstormHatchOne"  ,r.SandstormHatchOne);
        values.put("SandstormHatchTwo"  ,r.SandstormHatchTwo);
        values.put("SandstormHatchThree",r.SandstormHatchThree);
        values.put("SandstormCargoOne"  ,r.SandstormCargoOne);
        values.put("SandstormCargoTwo"  ,r.SandstormCargoTwo);
        values.put("SandstormCargoThree",r.SandstormCargoThree);
        values.put("TeleopCargoOne"     ,r.TeleopCargoOne);
        values.put("TeleopCargoTwo"     ,r.TeleopCargoTwo);
        values.put("TeleopCargoThree"   ,r.TeleopCargoThree);
        values.put("TeleopHatchOne"     ,r.TeleopHatchOne);
        values.put("TeleopHatchTwo"     ,r.TeleopHatchTwo);
        values.put("TeleopHatchThree"   ,r.TeleopHatchThree);
        values.put("ClimbOne"           ,r.ClimbOne);
        values.put("ClimbTwo"           ,r.ClimbTwo);
        values.put("ClimbThree"         ,r.ClimbThree);
        values.put("CxHatchCargoShip"   ,r.CxHatchCargoShip);
        values.put("CxCargoCargoShip"   ,r.CxCargoCargoShip);
        values.put("CxComboCargoShip"   ,r.CxComboCargoShip);
        values.put("CxCargoRocket"      ,r.CxCargoRocket);
        values.put("CxHatchRocket"      ,r.CxHatchRocket);
        values.put("CxComboRocket"      ,r.CxComboRocket);

        /**
         * TODO
         * Make sure to verify this*/
        long insertID = database.update(CryptoniteScoutDBHelper.RANKINGS_TABLE_NAME, values,"WHERE Teamnum = "+r.Teamnum,null);
        database.close();
    }

    public RankingData getTeamStats(int Teamnum){
        String sql = "";
        sql += "SELECT * FROM " + dbHelper.RANKINGS_TABLE_NAME+" WHERE Teamnum = "+Teamnum;
        // execute the query
        Cursor cursor = database.rawQuery(sql, null) ;
        RankingData r = new RankingData();

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            r.Teamnum            = Integer.parseInt(cursor.getString(cursor.getColumnIndex(allRankingColumns[1])) );
            r.Status             = Integer.parseInt(cursor.getString(cursor.getColumnIndex(allRankingColumns[2])) );
            r.MatchesPlayed      = Integer.parseInt(cursor.getString(cursor.getColumnIndex(allRankingColumns[3])) );
            r.CargoAvg           = Double.parseDouble(cursor.getString(cursor.getColumnIndex(allRankingColumns[4])) );
            r.HatchAvg           = Double.parseDouble(cursor.getString(cursor.getColumnIndex(allRankingColumns[5])) );
            r.SandstormHatchOne  = Integer.parseInt(cursor.getString(cursor.getColumnIndex(allRankingColumns[6])) );
            r.SandstormHatchTwo  = Integer.parseInt(cursor.getString(cursor.getColumnIndex(allRankingColumns[7])) );
            r.SandstormHatchThree= Integer.parseInt(cursor.getString(cursor.getColumnIndex(allRankingColumns[8])) );
            r.SandstormCargoOne  = Integer.parseInt(cursor.getString(cursor.getColumnIndex(allRankingColumns[9])) );
            r.SandstormCargoTwo  = Integer.parseInt(cursor.getString(cursor.getColumnIndex(allRankingColumns[10])));
            r.SandstormCargoThree= Integer.parseInt(cursor.getString(cursor.getColumnIndex(allRankingColumns[11])));
            r.TeleopCargoOne     = Integer.parseInt(cursor.getString(cursor.getColumnIndex(allRankingColumns[12])));
            r.TeleopCargoTwo     = Integer.parseInt(cursor.getString(cursor.getColumnIndex(allRankingColumns[13])));
            r.TeleopCargoThree   = Integer.parseInt(cursor.getString(cursor.getColumnIndex(allRankingColumns[14])));
            r.TeleopHatchOne     = Integer.parseInt(cursor.getString(cursor.getColumnIndex(allRankingColumns[15])));
            r.TeleopHatchTwo     = Integer.parseInt(cursor.getString(cursor.getColumnIndex(allRankingColumns[16])));
            r.TeleopHatchThree   = Integer.parseInt(cursor.getString(cursor.getColumnIndex(allRankingColumns[17])));
            r.ClimbOne           = Double.parseDouble(cursor.getString(cursor.getColumnIndex(allRankingColumns[18])));
            r.ClimbTwo           = Double.parseDouble(cursor.getString(cursor.getColumnIndex(allRankingColumns[19])));
            r.ClimbThree         = Double.parseDouble(cursor.getString(cursor.getColumnIndex(allRankingColumns[20])));
            r.CxHatchCargoShip   = Double.parseDouble(cursor.getString(cursor.getColumnIndex(allRankingColumns[21])));
            r.CxCargoCargoShip   = Double.parseDouble(cursor.getString(cursor.getColumnIndex(allRankingColumns[22])));
            r.CxComboCargoShip   = Double.parseDouble(cursor.getString(cursor.getColumnIndex(allRankingColumns[23])));
            r.CxCargoRocket      = Double.parseDouble(cursor.getString(cursor.getColumnIndex(allRankingColumns[24])));
            r.CxHatchRocket      = Double.parseDouble(cursor.getString(cursor.getColumnIndex(allRankingColumns[25])));
            r.CxComboRocket      = Double.parseDouble(cursor.getString(cursor.getColumnIndex(allRankingColumns[26])));

        }

        cursor.close();
        // return the list of records

        return r;
    }

    public void getRankingStat(int teamnum,int colindex){

    }

    public void addTeamData(ActionMap a, String teamnum){
        String[] temparr = new String[4];
        RankingData r = getTeamStats(Integer.parseInt(teamnum));
        int cargototal=0;
        int hatchtotal=0;
        r.Teamnum=Integer.parseInt(teamnum);
        r.Status =0;
        r.MatchesPlayed++;
        temparr[0]="A1";
        temparr[1]="B1";
        temparr[2]="A2";
        temparr[3]="B2";
        int[] status = {1};
        r.SandstormCargoOne+=a.numScored(temparr,status,false);
        temparr[0]="A3";
        temparr[1]="B3";
        temparr[2]="A4";
        temparr[3]="B4";
        cargototal+=r.SandstormCargoOne;
        r.SandstormCargoTwo+=a.numScored(temparr,status,false);
        temparr[0]="A5";
        temparr[1]="B5";
        temparr[2]="A6";
        temparr[3]="B6";
        cargototal+=r.SandstormCargoTwo;
        r.SandstormCargoThree+=a.numScored(temparr,status,false);
        temparr[0]="A1";
        temparr[1]="B1";
        temparr[2]="A2";
        temparr[3]="B2";
        cargototal+=r.SandstormCargoThree;
        r.SandstormHatchOne+=a.numScored(temparr,status,false);
        temparr[0]="A3";
        temparr[1]="B3";
        temparr[2]="A4";
        temparr[3]="B4";
        hatchtotal+=r.SandstormHatchOne;
        r.SandstormHatchTwo+=a.numScored(temparr,status,false);
        temparr[0]="A5";
        temparr[1]="B5";
        temparr[2]="A6";
        temparr[3]="B6";
        hatchtotal+=r.SandstormHatchTwo;
        r.SandstormHatchThree+=a.numScored(temparr,status,false);
        temparr[0]="A1";
        temparr[1]="B1";
        temparr[2]="A2";
        temparr[3]="B2";
        status[0]=2;
        hatchtotal+=r.SandstormHatchThree;
        r.TeleopCargoOne+=a.numScored(temparr,status,false);
        temparr[0]="A3";
        temparr[1]="B3";
        temparr[2]="A4";
        temparr[3]="B4";
        cargototal+=r.TeleopCargoOne;
        r.TeleopCargoTwo+=a.numScored(temparr,status,false);
        temparr[0]="A5";
        temparr[1]="B5";
        temparr[2]="A6";
        temparr[3]="B6";
        cargototal+=r.TeleopCargoTwo;
        r.TeleopCargoThree+=a.numScored(temparr,status,false);
        temparr[0]="A1";
        temparr[1]="B1";
        temparr[2]="A2";
        temparr[3]="B2";
        cargototal+=r.TeleopCargoThree;
        r.TeleopHatchOne+=a.numScored(temparr,status,false);
        temparr[0]="A3";
        temparr[1]="B3";
        temparr[2]="A4";
        temparr[3]="B4";
        hatchtotal+=r.TeleopHatchOne;
        r.TeleopHatchTwo+=a.numScored(temparr,status,false);
        temparr[0]="A5";
        temparr[1]="B5";
        temparr[2]="A6";
        temparr[3]="B6";
        hatchtotal+=r.TeleopHatchTwo;
        r.TeleopHatchThree+=a.numScored(temparr,status,false);
        hatchtotal+=r.TeleopHatchThree;
        String[] cargoship = {"C1","C2","C3","C4","C5","C6"};
        int[]all = {0,1,2,3};

        r.CargoAvg = cargototal/(double)r.MatchesPlayed;
        r.HatchAvg = hatchtotal/(double)r.MatchesPlayed;
        r.CxCargoCargoShip=(r.CxCargoCargoShip*(r.MatchesPlayed-1)+a.numScored(cargoship,all,false))/(double)r.MatchesPlayed;
        r.CxHatchCargoShip=(r.CxHatchCargoShip*(r.MatchesPlayed-1)+a.numScored(cargoship,all,true))/(double)r.MatchesPlayed;
        r.CxCargoRocket = ((r.SandstormCargoOne+r.TeleopCargoOne)+(r.SandstormCargoTwo+r.TeleopCargoTwo)*1.5+(r.SandstormCargoThree+r.TeleopCargoThree)*2)/r.MatchesPlayed;
        r.CxHatchRocket = ((r.SandstormHatchOne+r.TeleopHatchOne)+(r.SandstormHatchTwo+r.TeleopHatchTwo)*1.5+(r.SandstormHatchThree+r.TeleopHatchThree)*2)/r.MatchesPlayed;
        r.CxComboRocket = r.CxCargoRocket+r.CxHatchRocket;
        r.CxComboCargoShip = r.CxCargoCargoShip+r.CxHatchCargoShip;

        if(a.endclimb!=0){
            if (a.endclimb == 1) {
                r.ClimbOne = (r.ClimbOne*(r.MatchesPlayed-1)+1)/r.MatchesPlayed;
            }
            if (a.endclimb == 2) {
                r.ClimbTwo = (r.ClimbTwo*(r.MatchesPlayed-1)+1)/r.MatchesPlayed;
            }
            if (a.endclimb == 3) {
                r.ClimbThree = (r.ClimbThree*(r.MatchesPlayed-1)+1)/r.MatchesPlayed;
            }
        }
        updateRankingData(r);

    }

    /**
     * TODO
     * Recheck all this stuff, not yet tested
     * @param match
     */

    public void updateTables(Match match){
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Matchnum",match.matchNum);
        values.put("Teamnums",match.getTeamsString());
        values.put("Matchdata",match.toString());
        values.put("Comments",match.apppendComments());
        long insertID = database.insert(CryptoniteScoutDBHelper.USERS_TABLE_NAME, null, values);

        for(int i = 0; i<6;i++){
            addTeamData(match.maps[i],match.teamNums[i]);
        }
    }
}
