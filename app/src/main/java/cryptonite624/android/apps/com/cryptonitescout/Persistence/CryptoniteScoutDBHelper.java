package cryptonite624.android.apps.com.cryptonitescout.Persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.IOException;

public class CryptoniteScoutDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DB_CRYPTONITESCOUT";
    private static final int DATABASE_VERSION = 1;
    public static final String MATCHES_TABLE_NAME = "TBL_MATCHES";
    public static final String RANKINGS_TABLE_NAME = "TBL_RANKINGS";
    public static final String USERS_TABLE_NAME = "TBL_USERS";
    public static final String CONFIG_TABLE_NAME = "TBL_CONFIG";



    //CREATE TABLE FINALDE_TABLE_NAME(ID TEXT primary key,

    private static final String MATCHES_TABLE_CREATE =
            "CREATE TABLE " + MATCHES_TABLE_NAME + " (" +
                    "Matchnum" + " INT primary key, " +
                    "Teamnums" + " TEXT, "+
                    "Matchdata" + " TEXT, " +
                    "Comments" + " TEXT);";

    private static final String USERS_TABLE_CREATE =
            "CREATE TABLE " + USERS_TABLE_NAME + " (" +
                    "Username" + " TEXT primary key, " +
                    "Status" + " INT, " +
                    "Type" + " TEXT, " +
                    "Email" + " TEXT);";

    private static final String CONFIG_TABLE_CREATE =
            "CREATE TABLE " + CONFIG_TABLE_NAME + " (" +
                    "User" + " TEXT primary key, " +
                    "Admin" + " BOOLEAN, " +
                    "Comment" + " BOOLEAN, " +
                    "MacAddress" + " TEXT);";

    private static final String RANKINGS_TABLE_CREATE =
            "CREATE TABLE " + RANKINGS_TABLE_NAME + " (" +
                    "Teamnum"                + " INT primary key, " +
                    "Status"                + " INT, " +
                    "MatchesPlayed"                + " INT, " +
                    "CargoAvg"               + " DOUBLE, "+
                    "HatchAvg"              + " DOUBLE, " +
                    "SandstormHatchOne"     + " DOUBLE, " +
                    "SandstormHatchTwo"     + " DOUBLE, " +
                    "SandstormHatchThree"    + " DOUBLE, " +
                    "SandstormCargoOne"     + " DOUBLE, " +
                    "SandstormCargoTwo"     + " DOUBLE, " +
                    "SandstormCargoThree"    + " DOUBLE, " +
                    "TeleopCargoOne"        + " DOUBLE, " +
                    "TeleopCargoTwo"        + " DOUBLE, " +
                    "TeleopCargoThree"      + " DOUBLE, " +
                    "TeleopHatchOne"         + " DOUBLE, " +
                    "TeleopHatchTwo"        + " DOUBLE, " +
                    "TeleopHatchThree"      + " DOUBLE, " +
                    "ClimbOne"              + " DOUBLE, " +
                    "ClimbTwo"              + " DOUBLE, " +
                    "ClimbThree"            + " DOUBLE, " +
                    "CxHatchCargoShip"      + " DOUBLE, " +
                    "CxCargoCargoShip"      + " DOUBLE, " +
                    "CxComboCargoShip"       + " DOUBLE, " +
                    "CxCargoRocket"         +    " DOUBLE, " +
                    "CxHatchRocket"         + " DOUBLE, " +
                    "CxComboRocket"         + " DOUBLE);";



    CryptoniteScoutDBHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //create tables below
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(MATCHES_TABLE_CREATE);
        db.execSQL(RANKINGS_TABLE_CREATE);
        db.execSQL(USERS_TABLE_CREATE);
        db.execSQL(CONFIG_TABLE_CREATE);
        try{
            initialLoad(db);
        }
        catch (IOException e){
            Log.d("!@#$%^","filenotfound or already loaded",e);
        }

    }

    public Cursor getAllData() {
        String selectQuery = "Select * from "+MATCHES_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXISTS " + MATCHES_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RANKINGS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CONFIG_TABLE_NAME);
        onCreate(db);
    }


    public void initialLoad(SQLiteDatabase database)throws IOException{

    }

}
