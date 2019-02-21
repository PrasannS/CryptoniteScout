package cryptonite624.android.apps.com.cryptonitescout;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cryptonite624.android.apps.com.cryptonitescout.Models.Schedule;

public class ScheduleActivity extends AppCompatActivity implements ServerLoader.ServerLoadListener {

    public ServerLoader serverLoader ;
    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        serverLoader = new ServerLoader();
        serverLoader.loadFromTBA();

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
        setContentView(R.layout.activity_schedule);
    }
    public View view2;
    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = super.onCreateView(name, context, attrs);
        view2 = view;
        return view2;
    }

    public void addAllRows(List<Schedule> data) {
        cryptonite624.android.apps.com.cryptonitescout.Fragments.ScheduleFragment temp;
        for(Schedule s:data){
            temp = new cryptonite624.android.apps.com.cryptonitescout.Fragments.ScheduleFragment();
            temp.setArgument(s);
            if (view2.findViewById(R.id.infoframe) != null) {
                cryptonite624.android.apps.com.cryptonitescout.PregameFragment pregameFragment = new cryptonite624.android.apps.com.cryptonitescout.PregameFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.infoframe, pregameFragment, null);
                fragmentTransaction.commit();
            }
        }
    }

    @Override
    public void onServerLoad() {
        addAllRows(Schedule.listAll(Schedule.class));
    }
}