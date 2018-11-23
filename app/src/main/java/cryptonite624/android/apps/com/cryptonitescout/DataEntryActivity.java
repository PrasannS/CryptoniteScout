package cryptonite624.android.apps.com.cryptonitescout;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cryptonite624.android.apps.com.cryptonitescout.Models.DataEntry;

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

    public String getteam(){
        //this is just placeholder code I will replace it with actual data loading stuff when I learn how
        return "624";
    }

    //All the override methods are the implementations of the fragment interfaces which allow for the communication between the fragment and the activity

    @Override
    public void OnAutonRead(String message) {

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
    public void OnTeleopRead(String message) {

    }

    @Override
    public void OnEndgameRead(String message) {

    }
}
