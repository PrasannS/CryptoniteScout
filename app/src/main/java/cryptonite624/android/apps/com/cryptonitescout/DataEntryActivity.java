package cryptonite624.android.apps.com.cryptonitescout;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cryptonite624.android.apps.com.cryptonitescout.Fragments.AutonFragment;
import cryptonite624.android.apps.com.cryptonitescout.Fragments.EndgameFragment;
import cryptonite624.android.apps.com.cryptonitescout.Fragments.PregameFragment;
import cryptonite624.android.apps.com.cryptonitescout.Fragments.TeleopFragment;
import cryptonite624.android.apps.com.cryptonitescout.Models.AutonEntry;
import cryptonite624.android.apps.com.cryptonitescout.Models.DataEntry;
import cryptonite624.android.apps.com.cryptonitescout.Models.EndgameEntry;
import cryptonite624.android.apps.com.cryptonitescout.Models.FinalDataEntry;
import cryptonite624.android.apps.com.cryptonitescout.Models.PregameEntry;
import cryptonite624.android.apps.com.cryptonitescout.Models.TeleopEntry;
import cryptonite624.android.apps.com.cryptonitescout.Persistence.CryptoniteScoutDAO;

/** TODO
 * field map analysis touch
 * picture input
 * data analysis graphs
 * based off of time
 * elo
 * ranking system(elo), clash royale
 * excel - spreadsheet, csv output
 * team comparison - 2 or more
 * 1678 frc whitepaper
 * separate input superscout ranking commentbox
 * 
 *
 */

public class DataEntryActivity extends AppCompatActivity implements EndgameFragment.OnEndgameReadListener, PregameFragment.OnPregameReadListener,AutonFragment.OnAutonReadListener,TeleopFragment.OnTeleopReadListener {

    DataEntry dataEntry = new DataEntry(getteam());
    public static FragmentManager fragmentManager;
    private CryptoniteScoutDAO datasource=null;
    private List<FinalDataEntry>finals;
    public FinalDataEntry cur = new FinalDataEntry();

    public static final String URL_SAVE_NAME = "http://192.168.64.2/appload/apploader.php";

    public static final int NAME_SYNCED_WITH_SERVER = 1;
    public static final int NAME_NOT_SYNCED_WITH_SERVER = 0;

    //a broadcast to know weather the data is synced or not
    public static final String DATA_SAVED_BROADCAST = "net.simplifiedcoding.datasaved";

    //Broadcast receiver to know the sync status
    private BroadcastReceiver broadcastReceiver;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);
        //this is the initial code that generates the pregame fragment and adds it to the layout
        datasource = new CryptoniteScoutDAO(this.getApplicationContext());
        datasource.open();
        finals = new ArrayList<>();
        fragmentManager = getSupportFragmentManager();
        if(findViewById(R.id.fragmentcontainer)!=null){
            if(savedInstanceState!=null){
                return;
            }
            PregameFragment pregameFragment= new PregameFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragmentcontainer,pregameFragment,null);
            fragmentTransaction.commit();
        }

        //calling the method to load all the stored names
        //loadNames();

        //the broadcast receiver to update sync status
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                //loading the names again
                //loadNames();
            }
        };
        registerReceiver(new NetworkStateChecker(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        //registering the broadcast receiver to update sync status
        registerReceiver(broadcastReceiver, new IntentFilter(DATA_SAVED_BROADCAST));
    }

    public int getteam(){
        //this is just placeholder code I will replace it with actual data loading stuff when I learn how
        return 624;
    }

    //All the override methods are the implementations of the fragment interfaces which allow for the communication between the fragment and the activity


    private void saveFinalDataToServer(final FinalDataEntry name) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading to server...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SAVE_NAME,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                //if there is a success
                                //storing the name to sqlite with status synced
                                saveFinalDataEntryToLocalStorage(name, NAME_SYNCED_WITH_SERVER);
                            } else {
                                //if there is some error
                                //saving the name to sqlite with status unsynced
                                saveFinalDataEntryToLocalStorage(name, NAME_NOT_SYNCED_WITH_SERVER);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        //on error storing the name to sqlite with status unsynced
                        saveFinalDataEntryToLocalStorage(name, NAME_NOT_SYNCED_WITH_SERVER);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("FinalDE", name.toString());
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    @Override
    public void OnAutonRead(String message) {
        switch(message){
            case "toPrematch":
                if(findViewById(R.id.fragmentcontainer)!=null){
                    PregameFragment pregameFragment= new PregameFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentcontainer,pregameFragment,null);
                    fragmentTransaction.commit();
                }
                break;

            case "toTeleop":
                if(findViewById(R.id.fragmentcontainer)!=null){
                    TeleopFragment teleopFragment= new TeleopFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentcontainer,teleopFragment,null);
                    fragmentTransaction.commit();
                }
                break;

            default:
        }
    }

    @Override
    public void LoadAutonData(AutonEntry a) {
        cur.autonEntry = a;
    }

    @Override
    public void OnPregameRead(String message) {
        switch (message){
            case "toAuton":
                if(findViewById(R.id.fragmentcontainer)!=null){
                    AutonFragment autonFragment= new AutonFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentcontainer,autonFragment,null);
                    fragmentTransaction.commit();
                }
                break;

            default:

        }
    }

    @Override
    public void LoadPregameData(PregameEntry p) {
        cur = new FinalDataEntry();
        cur.pregameEntry = p;
    }

    @Override
    public void OnTeleopRead(String message) {
        switch (message){
            case "toAuton":
                if(findViewById(R.id.fragmentcontainer)!=null){
                    AutonFragment autonFragment = new AutonFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentcontainer,autonFragment,null);
                    fragmentTransaction.commit();
                }
                break;

            case "toEndgame":
                if(findViewById(R.id.fragmentcontainer)!=null){
                    EndgameFragment endgameFragment= new EndgameFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentcontainer,endgameFragment,null);
                    fragmentTransaction.commit();
                }
                break;

            default:

        }
    }

    @Override
    public void LoadTeleopData(TeleopEntry t) {
        cur.teleopEntry = t;
    }

    @Override
    public void OnEndgameRead(String message) {
        switch(message){
            case "toTeleop":
                if(findViewById(R.id.fragmentcontainer)!=null){
                    TeleopFragment teleopFragment= new TeleopFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentcontainer,teleopFragment,null);
                    fragmentTransaction.commit();
                }
                break;

            default:
        }

    }

    @Override
    public void LoadEndgameData(EndgameEntry e) {
        cur.endgameEntry = e;
        saveFinalDataToServer(cur);
        if(findViewById(R.id.fragmentcontainer)!=null){
            PregameFragment pregameFragment= new PregameFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentcontainer,pregameFragment,null);
            fragmentTransaction.commit();
        }
    }

    //saving the name to local storage
    private void saveFinalDataEntryToLocalStorage(FinalDataEntry fde, int status) {
        datasource.addFinalDataEntry(fde, status);
        finals.add(fde);
    }
}
