package cryptonite624.android.apps.com.cryptonitescout;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;

import org.greenrobot.greendao.query.QueryBuilder;

import cryptonite624.android.apps.com.cryptonitescout.Models.Comment;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;
import cryptonite624.android.apps.com.cryptonitescout.Models.Config;
import cryptonite624.android.apps.com.cryptonitescout.Models.DaoSession;
import cryptonite624.android.apps.com.cryptonitescout.Models.Schedule;
import cryptonite624.android.apps.com.cryptonitescout.Models.User;
import cryptonite624.android.apps.com.cryptonitescout.Models.UserDao;
import cryptonite624.android.apps.com.cryptonitescout.Utils.ActionMapUtils;
import cryptonite624.android.apps.com.cryptonitescout.Utils.CommentUtils;

public class CommentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener,BluetoothHandler.BluetoothListener{
    private Button submit;
    private EditText commentget;
    private String comment;
    private EditText teamnameget;
    private String teamname;

    private Spinner Rating;
    private String RatingLevel;
    private String [] Levels = {"1","2","3","4","5","6"};

    private Spinner Driver;
    private String DriverRatings;
    private String [] DriverRatingLevels = {"1","2","3","4","5","6"};

    //private Spinner Defense;
    //private String DefenseRatings;
    private String [] DefenseRatingLevels = {"1","2","3","4","5","6"};

    private int HatchEfficiency;
    private int CargoEfficiency;
    private boolean ExcessiveFoul;
    private String BrokenComment;
    private EditText WhyBroken;
    private Switch brokenswitch;

    private RangeSeekBar hatchefficiencySeekbar;
    private RangeSeekBar cargoefficiencySeekbar;
    private RangeSeekBar defenseratingSeekbar;

    public Comment comm;

    public BluetoothHandler bluetoothHandler;

    public DaoSession daoSession;

    public Schedule curschedule;
    public Config config;

    private EditText cteamnum;
    private EditText cmatchnum;


    public String getTeam(User u, Schedule s){
        if(u.getType().charAt(0)=='B')
            switch (u.getType().charAt(1)){
                case '1':
                    return s.getB1();
                case '2':
                    return s.getB2();
                case '3':
                    return s.getB3();

            }
        switch (u.getType().charAt(1)){
            case '1':
                return s.getR1();
            case '2':
                return s.getR2();
            case '3':
                return s.getR3();

        }
        return "0";
    }

    public User curuser;

    public void setvars(){
        curschedule = daoSession.getScheduleDao().loadAll().get(getCurrentMatch());
        config = daoSession.getConfigDao().loadAll().get(0);
        QueryBuilder<User> qb = daoSession.getUserDao().queryBuilder();
        qb.where(UserDao.Properties.Email.eq(config.getCurrentuser()));
        List<User> users = qb.list();
        String team = getTeam(users.get(0), curschedule);
        curuser = users.get(0);
        int teamnum = Integer.parseInt(team.substring(3));
        comm.setTeamnum(teamnum);
        comm.setMatchnum(getCurrentMatch());
        cteamnum.setText(teamnum+"");
        cmatchnum.setText(getCurrentMatch()+"");
    }


    public int getCurrentMatch(){
        return daoSession.getConfigDao().loadAll().get(0).getCurrentmatch();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        bluetoothHandler = new BluetoothHandler(getApplication(),this,"Comments");
        daoSession = daoSession = ((CRyptoniteApplication)getApplication()).getDaoSession();

        cteamnum = findViewById(R.id.comment_teamnum);
        cmatchnum = findViewById(R.id.comment_matchnum);

        comm = new Comment();
        setvars();

        submit= (Button)findViewById(R.id.Submitcomments);
        commentget= (EditText)findViewById(R.id.commentsave);
        comment = commentget.getText().toString();
        brokenswitch = findViewById(R.id.brokenswitch);
        hatchefficiencySeekbar = findViewById(R.id.hatchefficiency_seekbar);
        cargoefficiencySeekbar = findViewById(R.id.cargoefficiency_seekbar);
        defenseratingSeekbar = findViewById(R.id.defenseeffeciency_seekbar);
        WhyBroken = findViewById(R.id.whybroken_edittext);


        //teamnameget = (EditText) findViewById(R.id.TeamName);
        //teamname = teamnameget.getText().toString();
        cargoefficiencySeekbar.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                if(isFromUser)
                comm.setCargoefficiency ((int)leftValue);
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view,  boolean isLeft) {
                //start tracking touch
            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view,  boolean isLeft) {
                //stop tracking touch
            }
        });
        hatchefficiencySeekbar.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                if(isFromUser)
                comm.setHatchefficiency( (int)leftValue);
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view,  boolean isLeft) {
                //start tracking touch
            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view,  boolean isLeft) {
                //stop tracking touch
            }
        });
        defenseratingSeekbar.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                if(isFromUser)
                comm.setDefense((int)leftValue);
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view,  boolean isLeft) {
                //start tracking touch
            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view,  boolean isLeft) {
                //stop tracking touch
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bluetoothHandler.startlooking();

                comm.setComment( commentget.getText().toString());
                comm.setWhybroken( WhyBroken.getText().toString());
                comm.setBroken(brokenswitch.isChecked());

                Config config = daoSession.getConfigDao().loadAll().get(0);
                config.setCurrentmatch(config.getCurrentmatch()+1);
                daoSession.update(config);

                daoSession.getCommentDao().save(comm);
                try {
                    bluetoothHandler.sendMessage('f',CommentUtils.toString(comm));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(getBaseContext(), DataAccessActivity.class);
                startActivity(intent);
            }
        });
        /*Rating = findViewById(R.id.Rating);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,Levels);
        Rating.setAdapter(adapter);

        Driver = findViewById(R.id.DriverRating);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,DriverRatingLevels);
        Driver.setAdapter(adapter1);
*/
        //Defense = findViewById(R.id.DefenseRating);
        //ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,DefenseRatingLevels);
        //Defense.setAdapter(adapter2);


        hatchefficiencySeekbar.setRange(1f, 10f);
        hatchefficiencySeekbar.setTickMarkMode(RangeSeekBar.TRICK_MARK_MODE_NUMBER);


        cargoefficiencySeekbar.setRange(1f, 10f);
        cargoefficiencySeekbar.setTickMarkMode(RangeSeekBar.TRICK_MARK_MODE_NUMBER);


        defenseratingSeekbar.setRange(1f, 10f);
        defenseratingSeekbar.setTickMarkMode(RangeSeekBar.TRICK_MARK_MODE_NUMBER);
    }
    /*public void goToDashboard()
    {
        Intent intent1 = new Intent(this, MapView.class);
        startActivity(intent1);
    }*/

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //setting the dropdown elements and displaying the texts for the programming languages
        RatingLevel = Levels[position];
        Toast.makeText(parent.getContext(), RatingLevel, Toast.LENGTH_SHORT).show();

        //setting the dropdown elements and displaying the texts for the hatch intakes
        DriverRatings = DriverRatingLevels[position];
        Toast.makeText(parent.getContext(), DriverRatings, Toast.LENGTH_SHORT).show();

        //setting the dropdown elements and displaying the texts for the cargo intakes
        //DefenseRatings = DefenseRatingLevels[position];
        //Toast.makeText(parent.getContext(), DefenseRatings, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void OnBluetoothRead(String message) {
        Toast.makeText(CommentActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void start(Intent intent) {
        startActivity(intent);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        // Don't forget to unregister the ACTION_FOUND receiver.
        bluetoothHandler.endstuff();
    }
}
