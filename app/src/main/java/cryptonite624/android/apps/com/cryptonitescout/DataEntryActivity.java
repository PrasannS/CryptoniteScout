package cryptonite624.android.apps.com.cryptonitescout;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import cryptonite624.android.apps.com.cryptonitescout.Fragments.AutonFragment;
import cryptonite624.android.apps.com.cryptonitescout.Fragments.EndgameFragment;
import cryptonite624.android.apps.com.cryptonitescout.Fragments.TeleopFragment;
import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;

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

public class DataEntryActivity extends AppCompatActivity implements EndgameFragment.OnEndgameReadListener, cryptonite624.android.apps.com.cryptonitescout.PregameFragment.OnPregameReadListener,AutonFragment.OnAutonReadListener,TeleopFragment.OnTeleopReadListener{
    public static FragmentManager fragmentManager;
    public ActionMap actionMap = new ActionMap();

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
            cryptonite624.android.apps.com.cryptonitescout.PregameFragment pregameFragment= new cryptonite624.android.apps.com.cryptonitescout.PregameFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragmentcontainer,pregameFragment,null);
            fragmentTransaction.commit();

        }
    }

    public int getteam(){
        //this is just placeholder code I will replace it with actual data loading stuff when I learn how
        return 624;
    }

    //All the override methods are the implementations of the fragment interfaces which allow for the
    //communication between the fragment and the activity

    @Override
    public void OnAutonRead(String message) {
        switch(message){
            case "toPrematch":
                if(findViewById(R.id.fragmentcontainer)!=null){
                    cryptonite624.android.apps.com.cryptonitescout.PregameFragment pregameFragment= new cryptonite624.android.apps.com.cryptonitescout.PregameFragment();
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


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.settings_menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DataEntryActivity.this, SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public int getNumAutonHatches(){
        return 0;
    }
}
