package cryptonite624.android.apps.com.cryptonitescout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cryptonite624.android.apps.com.cryptonitescout.Models.Config;

import java.util.List;

import cryptonite624.android.apps.com.cryptonitescout.Models.Config;
import cryptonite624.android.apps.com.cryptonitescout.Models.DaoSession;
import cryptonite624.android.apps.com.cryptonitescout.Models.User;

public class SettingsActivity extends AppCompatActivity {

    public DaoSession daoSession;
    private EditText matchNum;
    private EditText eventKey;
    private Button logout;
    private Button save;
    public Config cur;

    public SharedPreferences preferences;

    public void updateLayouts(Config c){
        matchNum.setText(c.getCurrentmatch());
        eventKey.setText(c.getEventkey());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        daoSession = ((CRyptoniteApplication)getApplication()).getDaoSession();
        List<Config> c = daoSession.getConfigDao().loadAll();
        cur = c.get(0);
        matchNum = findViewById(R.id.matchnum_settings);
        eventKey = findViewById(R.id.evenKey_settings);
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        /*if(preferences.getBoolean("night", false)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            restartApp();
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            restartApp();
        }*/
    }

    public void save(){
        cur.setCurrentmatch(Integer.parseInt(matchNum.getText().toString()));
        cur.setEventkey(eventKey.getText().toString());
        daoSession.getConfigDao().update(cur);
        Intent i = new Intent(SettingsActivity.this, DataAccessActivity.class);
        startActivity(i);
    }

    public void logout(){
        cur.setCurrentuser("default@default.com");
        daoSession.getConfigDao().update(cur);
        Intent i = new Intent(SettingsActivity.this, LoginActivity.class);
        startActivity(i);
    }
}
