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

import cryptonite624.android.apps.com.cryptonitescout.Models.Comment;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;
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




    public Map<String,String> lastmessages  = new HashMap<>();
    public static String regex = "0624";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        bluetoothHandler = new BluetoothHandler(this);

        comm = new Comment();

        submit= (Button)findViewById(R.id.Submitcomments);
        commentget= (EditText)findViewById(R.id.commentsave);
        comment = commentget.getText().toString();
        brokenswitch = findViewById(R.id.brokenswitch);
        //teamnameget = (EditText) findViewById(R.id.TeamName);
        //teamname = teamnameget.getText().toString();
        cargoefficiencySeekbar.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                comm.cargoefficiency = (int)rightValue;
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
                comm.hatchefficiency = (int)rightValue;
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
                comm.defense = (int)rightValue;
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

                comm.comment = commentget.getText().toString();
                comm.whybroken = WhyBroken.getText().toString();
                comm.broken = brokenswitch.isChecked();

                comm.save();
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

        hatchefficiencySeekbar = findViewById(R.id.hatchefficiency_seekbar);
        hatchefficiencySeekbar.setRange(1f, 10f);
        hatchefficiencySeekbar.setTickMarkMode(RangeSeekBar.TRICK_MARK_MODE_NUMBER);

        cargoefficiencySeekbar = findViewById(R.id.cargoefficiency_seekbar);
        hatchefficiencySeekbar.setRange(1f, 10f);
        hatchefficiencySeekbar.setTickMarkMode(RangeSeekBar.TRICK_MARK_MODE_NUMBER);

        defenseratingSeekbar = findViewById(R.id.defenseeffeciency_seekbar);
        hatchefficiencySeekbar.setRange(1f, 10f);
        hatchefficiencySeekbar.setTickMarkMode(RangeSeekBar.TRICK_MARK_MODE_NUMBER);
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

    //TODO do this method done, updates to current match based on matches db
    public void getCurrentMatch(boolean useless){

    }

    @Override
    public void OnBluetoothRead(String message) {

    }

    @Override
    public void start(Intent intent) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Don't forget to unregister the ACTION_FOUND receiver.
        bluetoothHandler.endstuff();
    }
}
