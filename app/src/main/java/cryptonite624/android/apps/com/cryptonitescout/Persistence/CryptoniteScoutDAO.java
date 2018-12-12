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
            "FinalDataEntryName",
            "FinalDataEntryString",
            "FinalDataEntryInfo"
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

    public void close() {
        dbHelper.close();
    }

    public String getInfo(String keyword){
        return "Prasann is an amazing person.";
    }

    public void addFinalDataEntry(FinalDataEntry m, String name){

        ContentValues values = new ContentValues();
        String id = java.util.UUID.randomUUID().toString();
        values.put("ID",id);
        values.put("FinalDataEntryName",name);
        values.put("FinalDataEntryString",m.toString());
        values.put("FinalDataEntryInfo",getInfo(name));
        long insertID = database.insert(CryptoniteScoutDBHelper.FINALDE_TABLE_NAME, null, values);
    }

    public FinalDataEntry getFinalDataEntry(String name){
        Cursor cursor = database.query(CryptoniteScoutDBHelper.FINALDE_TABLE_NAME, allColumns, "FinalDataEntryName" + " = " + "\""+ name+"\"", null, null, null, null );
        cursor.moveToFirst();
        FinalDataEntry newE = cursorToFinalDataEntry(cursor);
        cursor.close();
        return newE;
    }

    private FinalDataEntry cursorToFinalDataEntry(Cursor cursor) {
        FinalDataEntry m = new FinalDataEntry();
        //m.info = cursor.getString(cursor.getColumnIndex("FinalDataEntryInfo"));
        //m.name= cursor.getString(cursor.getColumnIndex("FinalDataEntryName"));
        //m.equationString = cursor.getString(cursor.getColumnIndex("FinalDataEntryString"));

        return m;
    }

    // check if a record exists so it won't insert the next time you run this code
    public boolean checkIfExists(String objectName){

        boolean recordExists = false;

        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT " + allColumns[0] + " FROM " + dbHelper.FINALDE_TABLE_NAME + " WHERE " + allColumns[1] + " = '" + objectName + "'", null);

        if(cursor!=null) {

            if(cursor.getCount()>0) {
                recordExists = true;
            }
        }

        cursor.close();
        database.close();

        return recordExists;
    }
}
