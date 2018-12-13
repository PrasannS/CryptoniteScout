package cryptonite624.android.apps.com.cryptonitescout;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cryptonite624.android.apps.com.cryptonitescout.Models.AutonEntry;
import cryptonite624.android.apps.com.cryptonitescout.Models.DataEntry;
import cryptonite624.android.apps.com.cryptonitescout.Models.EndgameEntry;
import cryptonite624.android.apps.com.cryptonitescout.Models.PregameEntry;
import cryptonite624.android.apps.com.cryptonitescout.Models.TeleopEntry;

public class DataEntryActivity extends AppCompatActivity implements EndgameFragment.OnEndgameReadListener, PregameFragment.OnPregameReadListener,AutonFragment.OnAutonReadListener,TeleopFragment.OnTeleopReadListener {

    DataEntry dataEntry = new DataEntry(getteam());
    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);
        //this is the initial code that generates the pregame fragment and adds it to the layout
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
    }

    public int getteam(){
        //this is just placeholder code I will replace it with actual data loading stuff when I learn how
        return 624;
    }

    //All the override methods are the implementations of the fragment interfaces which allow for the communication between the fragment and the activity

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
        p.setTeamnum("");
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

    }
}
