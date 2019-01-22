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

    public String getInfo(String keyword){
        return "Prasann is an amazing person.";
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

    public void addTeamData(ActionMap a, int teamnum){
        RankingData r = getTeamStats(teamnum);
        r.Status =0;
        r.MatchesPlayed++;
        r.CargoAvg = r.CargoAvg*r.MatchesPlayed+1;
    }

    public void updateTables(Match match){
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Matchnum",match.matchNum);
        values.put("Teamnums",match.getTeamsString());
        values.put("Matchdata",match.toString());
        values.put("Comments",match.apppendComments());
        long insertID = database.insert(CryptoniteScoutDBHelper.USERS_TABLE_NAME, null, values);

        for(ActionMap actionMap:match.maps){

        }
    }
}
