package cryptonite624.android.apps.com.cryptonitescout.Persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import cryptonite624.android.apps.com.cryptonitescout.Models.DataEntry;
import cryptonite624.android.apps.com.cryptonitescout.Models.FinalDataEntry;

public class CryptoniteScoutDAO {

    private SQLiteDatabase database;
    private CryptoniteScoutDBHelper dbHelper;

    private String[] allColumns = {
            "ID",
            "Pregame",
            "Teleop",
            "Endgame",
            "Auton"
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
        SQLiteDatabase db = database;
        String sql = "SELECT * FROM " + CryptoniteScoutDBHelper.FINALDE_TABLE_NAME + " WHERE " + "Status" + " = 0;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public void close() {
        dbHelper.close();
    }

    public String getInfo(String keyword){
        return "Prasann is an amazing person.";
    }


    public boolean updateNameStatus(int id, int status) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Status", status);
        database.update(CryptoniteScoutDBHelper.FINALDE_TABLE_NAME, contentValues, "ID " + "=" + id, null);
        database.close();
        return true;
    }

    public void addFinalDataEntry(FinalDataEntry m,int status){

        ContentValues values = new ContentValues();
        String id = java.util.UUID.randomUUID().toString();
        values.put("ID",id);
        values.put("Auton",m.autonEntry.toString());
        values.put("Teleop",m.teleopEntry.toString());
        values.put("Pregame",m.pregameEntry.toString());
        values.put("Endgame",m.endgameEntry.toString());
        values.put("Status",status);

        long insertID = database.insert(CryptoniteScoutDBHelper.FINALDE_TABLE_NAME, null, values);
    }/*

    public FinalDataEntry getFinalDataEntry(String name){
        Cursor cursor = database.query(CryptoniteScoutDBHelper.FINALDE_TABLE_NAME, allColumns, "FinalDataEntryName" + " = " + "\""+ name+"\"", null, null, null, null );
        cursor.moveToFirst();
        FinalDataEntry newE = cursorToFinalDataEntry(cursor);
        cursor.close();
        return newE;
    }*//*

    private FinalDataEntry cursorToFinalDataEntry(Cursor cursor) {
        FinalDataEntry m = new FinalDataEntry();
        m.endgameEntry.endgameParse(cursor.getString(cursor.getColumnIndex("Endgame")));
        m.autonEntry.AutonParse(cursor.getString(cursor.getColumnIndex("Auton")));
        m.teleopEntry.teleopParse(cursor.getString(cursor.getColumnIndex("Teleop")));
        m.pregameEntry.PregameParse(cursor.getString(cursor.getColumnIndex("Pregame")));

        return m;
    }*/

}
