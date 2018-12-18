package cryptonite624.android.apps.com.cryptonitescout.Persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import cryptonite624.android.apps.com.cryptonitescout.Models.FinalDataEntry;

public class CryptoniteScoutDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DB_CRYPTONITESCOUT";
    private static final int DATABASE_VERSION = 1;
    public static final String FINALDE_TABLE_NAME = "TBL_FINAL_DE";

    //CREATE TABLE FINALDE_TABLE_NAME(ID TEXT primary key,

    private static final String FINALDE_TABLE_CREATE =
            "CREATE TABLE " + FINALDE_TABLE_NAME + " (" +
                    "ID" + " TEXT primary key, " +
                    "Auton" + " TEXT, " +
                    "Teleop" + " TEXT, " +
                    "Endgame" + " TEXT, " +
                    "Status" + " TINYINT, " +
                    "Pregame" + " TEXT);";

    CryptoniteScoutDBHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //create tables below
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(FINALDE_TABLE_CREATE);
        try{
            initialLoad(db);
        }
        catch (IOException e){
            Log.d("!@#$%^","filenotfound or already loaded",e);
        }

    }

    public Cursor getAllData() {
        String selectQuery = "Select * from "+FINALDE_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXISTS " + FINALDE_TABLE_NAME);
        onCreate(db);
    }

    public void addFinalDataEntry(FinalDataEntry m,SQLiteDatabase database){
        ContentValues values = new ContentValues();
        String id = java.util.UUID.randomUUID().toString();
        values.put("Auton",m.pregameEntry.toString());
        values.put("Teleop",m.autonEntry.toString());
        values.put("Endgame",m.pregameEntry.toString());
        values.put("Pregame",m.teleopEntry.toString());
        long insertID = database.insert(FINALDE_TABLE_NAME, null, values);
    }

    public void initialLoad(SQLiteDatabase database)throws IOException{

    }

}
