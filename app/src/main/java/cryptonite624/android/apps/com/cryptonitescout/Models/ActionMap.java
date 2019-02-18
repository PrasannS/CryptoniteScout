package cryptonite624.android.apps.com.cryptonitescout.Models;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

public class ActionMap extends SugarRecord<ActionMap>{

    ArrayList<RobotAction> actions;
    int endclimb;
    int matchnum;
    int pos;


    public ActionMap(){
        actions = new ArrayList<>();
    }


}
