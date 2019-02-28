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

public class SettingsActivity extends AppCompatActivity {

    private EditText matchNum;
    private EditText eventKey;
    private Button logout;

    SwitchCompat nightmode, switch1, switch2;

    boolean night, stateSwitch1, stateSwitch2;

    public SharedPreferences preferences;

    public void updateLayouts(Config c){
        matchNum.setText(c.getCurrentmatch());
        eventKey.setText(c.getEventkey());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        matchNum = findViewById(R.id.matchnum_settings);
        eventKey = findViewById(R.id.evenKey_settings);
        logout = findViewById(R.id.logout);

        nightmode.setChecked(night);
        switch1.setChecked(stateSwitch1);
        switch2.setChecked(stateSwitch2);

        nightmode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                night = !night;
                nightmode.setChecked(night);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("nightmode", night);
                editor.apply();

                if(nightmode.isChecked()){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    restartApp();
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    restartApp();
                }
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

    public void restartApp() {
        Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(i);
        finish();
    }
}
