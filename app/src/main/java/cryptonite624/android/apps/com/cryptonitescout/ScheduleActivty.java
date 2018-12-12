package cryptonite624.android.apps.com.cryptonitescout;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ScheduleActivty extends AppCompatActivity {

    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        fragmentManager = getSupportFragmentManager();
        if(findViewById(R.id.fragmentcontainer)!=null){
            if(savedInstanceState!=null){
                return;
            }
            ScheduleFragment scheduleFragment= new ScheduleFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragmentcontainer,scheduleFragment,null);
            fragmentTransaction.commit();

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_activty);
    }
}
