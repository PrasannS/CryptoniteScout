package cryptonite624.android.apps.com.cryptonitescout;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v7.app.ActionBarDrawerToggle;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;
import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMapDao;
import cryptonite624.android.apps.com.cryptonitescout.Models.DaoSession;
import cryptonite624.android.apps.com.cryptonitescout.Models.PitnoteData;
import cryptonite624.android.apps.com.cryptonitescout.Models.RankingData;
import cryptonite624.android.apps.com.cryptonitescout.Models.Schedule;

public class DataAccessActivity extends AppCompatActivity implements MatchAccessFragment.OnFragmentInteractionListener,LeftMapFragment.OnLeftMapReadListener,DashboardFragment.OnDashboardReadListener, MatchFragment.OnMatchReadListener, TeamFragment.OnTeamReadListener,ServerLoader.ServerLoadListener,ScheduleFragment.OnScheduleRead,BluetoothHandler.BluetoothListener, RankingFragment.OnRankingRead{

    public static FragmentManager fragmentManager;

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    private Toolbar toolbar;
    public ServerLoader serverLoader;
    public DaoSession daoSession;

    public BluetoothHandler bluetoothHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_access);

        dl = (DrawerLayout)findViewById(R.id.drawer_layout);
        t = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        daoSession = ((CRyptoniteApplication)getApplication()).getDaoSession();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bluetoothHandler = new BluetoothHandler(getApplication(),this);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if (id == R.id.nav_home) {
                    Toast.makeText(DataAccessActivity.this, "home", Toast.LENGTH_LONG).show();
                    if(findViewById(R.id.fragmentcontainer)!=null){
                        TeamFragment teamFragment = new TeamFragment();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentcontainer, teamFragment,null);
                        fragmentTransaction.commit();
                    }
                    return true;
                }
                /*if (id == R.id.nav_home) {
                    Toast.makeText(DataAccessActivity.this, "home", Toast.LENGTH_LONG).show();
                    if(findViewById(R.id.fragmentcontainer)!=null){
                        DashboardFragment dashboardFragment = new DashboardFragment();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentcontainer, dashboardFragment,null);
                        fragmentTransaction.commit();
                    }
                    return true;
                }*/
                if (id == R.id.nav_matches) {
                    bluetoothHandler.endstuff();
                    Toast.makeText(DataAccessActivity.this, "schedules", Toast.LENGTH_LONG).show();
                    if(findViewById(R.id.fragmentcontainer)!=null){
                        ScheduleFragment rankingFragment = new ScheduleFragment();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentcontainer, rankingFragment,null);
                        fragmentTransaction.commit();
                    }
                    return true;
                }
                if (id == R.id.nav_newentry) {
                    Toast.makeText(DataAccessActivity.this, "new entry", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(DataAccessActivity.this, MapView.class));
                    return true;
                }
                if (id == R.id.nav_rankings) {
                    Toast.makeText(DataAccessActivity.this, "rankings", Toast.LENGTH_LONG).show();
                    if(findViewById(R.id.fragmentcontainer)!=null){
                        RankingFragment rankingFragment = new RankingFragment();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentcontainer, rankingFragment,null);
                        fragmentTransaction.commit();
                    }
                    return true;
                }
                if (id == R.id.nav_sketchmap) {
                    Toast.makeText(DataAccessActivity.this, "map draw", Toast.LENGTH_LONG).show();
                    if(findViewById(R.id.fragmentcontainer)!=null){
                        MapDrawFragment mapDrawFragment = new MapDrawFragment();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentcontainer, mapDrawFragment,null);
                        fragmentTransaction.commit();
                    }
                    return true;
                }
                if (id == R.id.nav_replayview) {
                    Toast.makeText(DataAccessActivity.this, "replayview", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(DataAccessActivity.this, ReplayViewPage.class));

                    return true;
                }
                if (id == R.id.nav_commentscout) {
                    Toast.makeText(DataAccessActivity.this, "comm scout", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(DataAccessActivity.this, CommentActivity.class));

                    return true;
                }
                if (id == R.id.nav_settings) {
                    Toast.makeText(DataAccessActivity.this, "settings", Toast.LENGTH_LONG).show();
                    return true;
                }
                if (id == R.id.nav_pitnote) {
                    startActivity(new Intent(DataAccessActivity.this, pitNote.class));
                    Toast.makeText(DataAccessActivity.this, "pitnotes", Toast.LENGTH_LONG).show();
                }
                if (id == R.id.nav_groupchat) {
                    Intent intent1 = new Intent(DataAccessActivity.this, MainActivity.class);
                    startActivity(intent1);
                }
                if (id == R.id.picklist) {
                    startActivity(new Intent(DataAccessActivity.this, PickListActivity.class));
                    Toast.makeText(DataAccessActivity.this, "picklist", Toast.LENGTH_LONG).show();
                }
                if (id == R.id.admin) {
                    Intent intent1 = new Intent(DataAccessActivity.this, AdminActivity.class);
                    startActivity(intent1);
                }

                return true;
            }
        });
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
        //getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        t.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(t.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnDashboardRead(String message) {
        switch(message){
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
            default:
        }
    }

    //this is for matches
    @Override
    public void OpenTeam(String key) {
        openTeamFromKey(key);
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

    @Override
    public void onServerLoad() {
        Toast.makeText(DataAccessActivity.this, "load unsuccessful", Toast.LENGTH_LONG).show();
    }

    @Override
    public void addSchedule(Schedule s) {
        daoSession.getScheduleDao().save(s);
    }

    @Override
    public void OnScheduleRead(String message) {

    }

    @Override
    public void openMatch(int matchnum) {
        openMatchFromNumber(matchnum);
    }

    @Override
    public void OnBluetoothRead(String message) {

    }

    @Override
    public void start(Intent intent) {
        startActivity(intent);
    }



    @Override
    public void OnRankingRead(String message) {

    }

    public void openTeamFromKey(String key){
        int tn = Integer.parseInt(key.substring(4));
        QueryBuilder<ActionMap> qb = daoSession.getActionMapDao().queryBuilder();
        qb.where(ActionMapDao.Properties.Teamnum.eq(tn));
        List<ActionMap> actionMaps = qb.list();
        if(findViewById(R.id.fragmentcontainer)!=null){
            TeamFragment teamFragment= new TeamFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentcontainer,teamFragment,null);
            fragmentTransaction.commit();
            teamFragment.setArguments(actionMaps);
        }
    }

    public void openMatchFromNumber(int num){
        QueryBuilder<ActionMap> qb = daoSession.getActionMapDao().queryBuilder();
        qb.where(ActionMapDao.Properties.Matchnum.eq(num));
        List<ActionMap> actionMaps = qb.list();
        if(findViewById(R.id.fragmentcontainer)!=null){
            MatchFragment teamFragment= new MatchFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentcontainer,teamFragment,null);
            fragmentTransaction.commit();
            //teamFragment.setArguments(actionMaps);
        }
    }


    //this is for rankings
    @Override
    public void openTeam(String key) {
        openTeamFromKey(key);
    }
}
