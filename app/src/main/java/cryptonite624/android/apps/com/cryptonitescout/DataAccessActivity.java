package cryptonite624.android.apps.com.cryptonitescout;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class DataAccessActivity extends AppCompatActivity implements MatchAccessFragment.OnFragmentInteractionListener,LeftMapFragment.OnLeftMapReadListener,DashboardFragment.OnDashboardReadListener, MatchFragment.OnMatchReadListener, TeamFragment.OnTeamReadListener{

    public static FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.TaskbarTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_access);

        fragmentManager = getSupportFragmentManager();
        if(findViewById(R.id.fragmentcontainer)!=null){
            if(savedInstanceState!=null){
                return;
            }
            DashboardFragment DashboardFragment= new DashboardFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragmentcontainer,DashboardFragment,null);
            fragmentTransaction.commit();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.pitnote_menu) {
            Toast.makeText(DataAccessActivity.this, "pitnote", Toast.LENGTH_LONG).show();
            startActivity(new Intent(DataAccessActivity.this, pitNote.class));
            return true;
        }

        if (id == R.id.matches_menu) {
            Toast.makeText(DataAccessActivity.this, "matches", Toast.LENGTH_LONG).show();
            if(findViewById(R.id.fragmentcontainer)!=null){
                MatchFragment matchFragment= new MatchFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontainer,matchFragment,null);
                fragmentTransaction.commit();
            }
            return true;
        }
        if (id == R.id.newEntry_menu) {
            Toast.makeText(DataAccessActivity.this, "new entry", Toast.LENGTH_LONG).show();
            startActivity(new Intent(DataAccessActivity.this, MapView.class));
            return true;
        }
        if (id == R.id.rankings_menu) {
            Toast.makeText(DataAccessActivity.this, "rankings", Toast.LENGTH_LONG).show();
            return true;
        }
        if (id == R.id.settings) {
            Toast.makeText(DataAccessActivity.this, "settings", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnDashboardRead(String message) {
        switch(message){
            /*case "toMatches":
                if(findViewById(R.id.fragmentcontainer)!=null){
                    MatchFragment matchFragment = new MatchFragment();
                    PregameFragment pregameFragment= new PregameFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentcontainer,MatchFragment,null);
                    fragmentTransaction.commit();
                }*/
            /*case "toPitnotes":

                }*/
            case "toMatch":
                if(findViewById(R.id.fragmentcontainer)!=null){
                    MatchFragment matchFragment= new MatchFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentcontainer,matchFragment,null);
                    fragmentTransaction.commit();
                }
                break;
            case "toMapview":
                startActivity(new Intent(DataAccessActivity.this, MapView.class));
                break;
            case "toPitnote":
                startActivity(new Intent(DataAccessActivity.this, pitNote.class));
            default:
        }
    }

    @Override
    public void OnMatchRead(String message) {
        switch(message){
            case "toDashboard":
                if(findViewById(R.id.fragmentcontainer)!=null){
                    DashboardFragment dashboardFragment= new DashboardFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentcontainer,dashboardFragment,null);
                    fragmentTransaction.commit();
                }
                break;
            case "toTeam":
                if(findViewById(R.id.fragmentcontainer)!=null){
                    TeamFragment teamFragment= new TeamFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentcontainer,teamFragment,null);
                    fragmentTransaction.commit();
                }

            /*case "toMatches":
                if(findViewById(R.id.fragmentcontainer)!=null){
                    TeleopFragment teleopFragment= new TeleopFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentcontainer,teleopFragment,null);
                    fragmentTransaction.commit();
                }
                break;*/

            default:
        }
    }

    @Override
    public void OnTeamRead(String message) {
        switch(message){
            case "toMatch":
                if(findViewById(R.id.fragmentcontainer)!=null){
                    MatchFragment matchFragment= new MatchFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentcontainer,matchFragment,null);
                    fragmentTransaction.commit();
                }
                break;

            case "toDashboard":
                if(findViewById(R.id.fragmentcontainer)!=null){
                    DashboardFragment dashboardFragment= new DashboardFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentcontainer,dashboardFragment,null);
                    fragmentTransaction.commit();
                }
                break;

            default:
        }
    }

    @Override
    public void OnLeftMapRead(int x, int y) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
