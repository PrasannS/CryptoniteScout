package cryptonite624.android.apps.com.cryptonitescout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SwitchCompat;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {

    SwitchCompat nightmode, switch1, switch2;

    boolean night, stateSwitch1, stateSwitch2;

    public SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.darktheme);
        }
        else setTheme(R.style.lighttheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        preferences = getSharedPreferences("PREFS", 0);
        night = preferences.getBoolean("nightmode", false);
        stateSwitch1 = preferences.getBoolean("switch1", false);
        stateSwitch2 = preferences.getBoolean("switch2", false);

        nightmode = (SwitchCompat) findViewById(R.id.nightmode);
        switch1 = (SwitchCompat) findViewById(R.id.switch1);
        switch2 = (SwitchCompat) findViewById(R.id.switch2);

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
