package cryptonite624.android.apps.com.cryptonitescout;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;
import cryptonite624.android.apps.com.cryptonitescout.Utils.ActionMapUtils;

public class ReplayViewPage extends AppCompatActivity implements MatchAccessFragment.OnFragmentInteractionListener, LeftMapFragment.OnLeftMapReadListener{

    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);

        ActionMap actionMap= ActionMapUtils.parseActionMap(getIntent().getStringExtra("teamnum"));

        fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.replayview_map) != null) {
            MatchAccessFragment matchAccessFragment = new MatchAccessFragment();
            matchAccessFragment.setArguments(actionMap);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.replayview_map, matchAccessFragment, null);
            fragmentTransaction.commit();
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void OnLeftMapRead(int x, int y) {

    }
}
