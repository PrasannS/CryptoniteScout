package cryptonite624.android.apps.com.cryptonitescout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.service.autofill.FieldClassification;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.messages.MessagesClient;
import com.google.android.gms.nearby.messages.MessagesOptions;
import com.google.android.gms.nearby.messages.NearbyPermissions;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


import cryptonite624.android.apps.com.cryptonitescout.Utils.ActionMapUtils;

import cryptonite624.android.apps.com.cryptonitescout.Fragments.AutonFragment;
import cryptonite624.android.apps.com.cryptonitescout.Fragments.EndgameFragment;
import cryptonite624.android.apps.com.cryptonitescout.Fragments.InputFragment;
import cryptonite624.android.apps.com.cryptonitescout.Fragments.TeleopFragment;
import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;
import cryptonite624.android.apps.com.cryptonitescout.Models.RobotAction;
import cryptonite624.android.apps.com.cryptonitescout.Utils.ActionMapUtils;
import java.util.Date;

public class MapView extends AppCompatActivity implements EmptyFragment.OnFragmentInteractionListener,View.OnTouchListener, InputFragment.OnInputReadListener, EndgameFragment.OnEndgameReadListener, cryptonite624.android.apps.com.cryptonitescout.PregameFragment.OnPregameReadListener,AutonFragment.OnAutonReadListener,TeleopFragment.OnTeleopReadListener,RocketFragment.OnrocketReadListener, LeftMapFragment.OnLeftMapReadListener, RightMapFragment.OnRightMapReadListener, SubmissionReviewFragment.OnSubmissionListener, HabTimerFragment.OnHabTimerReadListener, BluetoothHandler.BluetoothListener {


    public int x, y;
    public int topx;

    public int recordeddevices = 0;

    public int commnum = 0;
    public int mapnum = 0;

    public CountUpTimer timer;

    public int climbTime;


    //bluetooth

    public boolean red = true;
    public boolean left = true;

    public static int[] ROCKET1MIN ={740, 100};
    public static int[] ROCKET1MAX ={942, 195};
    public static int[] ROCKET2MIN ={752, 472};
    public static int[] ROCKET2MAX ={933, 545};
    public static int[] CARGO1MIN = {614, 265};
    public static int[] CARGO1MAX = {710, 321};
    public static int[] CARGO2MIN = {743, 255};
    public static int[] CARGO2MAX = {822, 300};
    public static int[] CARGO3MIN = {840, 260};
    public static int[] CARGO3MAX = {923, 300};
    public static int[] CARGO4MIN = {936, 260};
    public static int[] CARGO4MAX = {1018,299};
    public static int[] CARGO5MIN = {614, 329};
    public static int[] CARGO5MAX = {710, 377};
    public static int[] CARGO6MIN = {743, 342};
    public static int[] CARGO6MAX = {822, 396};
    public static int[] CARGO7MIN = {840, 342};
    public static int[] CARGO7MAX = {923, 396};
    public static int[] CARGO8MIN = {936, 342};
    public static int[] CARGO8MAX = {1018, 396};
    public static int[] HAB1MIN =   {348, 237};
    public static int[] HAB1MAX =   {445, 293};
    public static int[] HAB2MIN =   {348, 293};
    public static int[] HAB2MAX =   {445, 362};
    public static int[] HAB3MIN =   {348, 362};
    public static int[] HAB3MAX =   {445, 413};


    public static FragmentManager fragmentManager;
    public ActionMap actionMap = new ActionMap();
    //matchstatus, 0 = pregame, 1 = auton, 2 = teleop, 3 = endgame
    public int matchStatus = 0;;
    public int habLevel;
    public long starttime;
    public long endtime;
    public double cycletime = -1;
    public boolean stopwatch= false;
    public int totalcycles = 0;
    private RelativeLayout mapview;
    private TextView habDisplay;



    private Button cancel;

    public LeftMapFragment leftMapFragment;
    public RightMapFragment rightMapFragment;




    private Button imageswitch;

    public boolean sandstorm;
    public boolean rocket = false;

    TextView hatchDisplay;
    TextView cargoDisplay;

    public RobotAction currentAction = new RobotAction();
    Button statusButton;

    private CountDownTimer countDownTimer;
    private long timeLeftInMilli = 150000;
    private boolean timerRunning;
    int minutes;
    int seconds;

    private TextView timerDisplay;
    private Button timerButton;

    public BluetoothHandler bluetoothHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);

        bluetoothHandler = new BluetoothHandler(this);


        setContentView(R.layout.activity_map_view);

        fragmentManager = getSupportFragmentManager();
        if (findViewById(R.id.infoframe) != null) {
            if (savedInstanceState != null) {
                return;
            }
            cryptonite624.android.apps.com.cryptonitescout.PregameFragment pregameFragment = new cryptonite624.android.apps.com.cryptonitescout.PregameFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.infoframe, pregameFragment, null);
            fragmentTransaction.commit();
        }
        if (findViewById(R.id.inputcontainer) != null) {
            EmptyFragment emptyFragment = new EmptyFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.inputcontainer, emptyFragment, null);
            fragmentTransaction.commit();
        }

        if (findViewById(R.id.mapcontainer) != null) {
            rightMapFragment = new RightMapFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.mapcontainer, rightMapFragment, null);
            fragmentTransaction.commit();
        }

        switchbounds();

        //endmatch = getCurrentMatch(true);

        mapview = (RelativeLayout)findViewById(R.id.mapview);
        imageswitch = (Button) findViewById(R.id.mapswitch);
        cancel = (Button) findViewById(R.id.cancel);

        stopwatch=false;

        imageswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchbounds();
                if(left){
                    if (findViewById(R.id.mapcontainer) != null) {
                        rightMapFragment = new RightMapFragment();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.mapcontainer, rightMapFragment, null);
                        fragmentTransaction.commit();
                    }
                }
                else{
                    if (findViewById(R.id.mapcontainer) != null) {
                        leftMapFragment = new LeftMapFragment();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.mapcontainer, leftMapFragment, null);
                        fragmentTransaction.commit();

                    }
                }

            }
        });


        cargoDisplay = (TextView) findViewById(R.id.cargodisplay);
        hatchDisplay = (TextView) findViewById(R.id.hatchdisplay);
        habDisplay = (TextView) findViewById(R.id.hableveldisplay);

        statusButton = (Button) findViewById(R.id.statuschanger);
        statusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                matchStatus++;
                if (matchStatus > 3) {
                    matchStatus = 0;
                    //sandstorm = false;
                }
                if (matchStatus == 0) {
                    statusButton.setText("Pregame");
                    changeFragment(matchStatus);
                    sandstorm = false;

                }
                if (matchStatus == 1) {
                    statusButton.setText("Sandstorm");
                    sandstorm = true;
                    changeFragment(matchStatus);

                }
                if (matchStatus == 2) {
                    statusButton.setText("Teleop");
                    sandstorm = false;
                    changeFragment(matchStatus);

                }
                if (matchStatus == 3) {
                    statusButton.setText("Endgame");
                    sandstorm = false;
                    changeFragment(matchStatus);


                }

            }
        });

        timerDisplay = (TextView) findViewById(R.id.timerdisplay);

        timerButton = (Button) findViewById(R.id.timerbutton);

        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();
            }

        });
    }




    public void startStop(){
        if(timerRunning){
            stopTimer();
        }
        else{
            startTimer();
        }
    }

    public void startTimer(){
        countDownTimer = new CountDownTimer(timeLeftInMilli, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilli = l;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();

        timerButton.setText("PAUSE");
        timerRunning = true;
    }

    public void stopTimer(){
        countDownTimer.cancel();
        timerRunning = false;

        timerButton.setText("START");
    }

    public void updateTimer(){
        minutes = (int) timeLeftInMilli / 60000;
        seconds = (int) timeLeftInMilli % 60000 / 1000;

        String timeLeftText;

        timeLeftText = "" + minutes + ":";
        if(seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        timerDisplay.setText(timeLeftText);
    }

    @SuppressLint("WrongCall")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Date date = new Date();

        x = (int)event.getX();
        y = (int)event.getY();
        cancel.setVisibility(View.VISIBLE);

        cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(findViewById(R.id.inputcontainer)!=null){
                            EmptyFragment emptyFragment= new EmptyFragment();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.inputcontainer,emptyFragment,null);
                            fragmentTransaction.commit();
                        }
                        if(sandstorm){
                            if(findViewById(R.id.infoframe)!=null){
                                AutonFragment rocketFragment= new AutonFragment();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.infoframe,rocketFragment,null);
                                fragmentTransaction.commit();
                            }
                        }
                        else{
                            if(findViewById(R.id.infoframe)!=null){
                                TeleopFragment rocketFragment= new TeleopFragment();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.infoframe,rocketFragment,null);
                                fragmentTransaction.commit();
                            }
                        }
                        currentAction = new RobotAction();
                    }
                }
        );

        if(!getCode(x, y).equals("Z")){

            if(!stopwatch||((endtime-starttime)/1000)<1){
                starttime = date.getTime();
                stopwatch=true;
            }
            else{
                endtime = date.getTime();
                if(cycletime==-1)
                cycletime =(double) ((endtime-starttime)/1000);
                else{
                    cycletime =(cycletime*totalcycles)+(double) ((endtime-starttime)/1000)/(totalcycles+1);
                }
                totalcycles++;
                stopwatch=false;
            }
            //actionReady = true;
            if (findViewById(R.id.inputcontainer) != null) {
                EmptyFragment emptyFragment = new EmptyFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.inputcontainer, emptyFragment, null);
                fragmentTransaction.commit();
            }
            if (sandstorm) {
                if (findViewById(R.id.infoframe) != null) {
                    AutonFragment rocketFragment = new AutonFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.infoframe, rocketFragment, null);
                    fragmentTransaction.commit();
                }
            }

            currentAction.actionCode = getCode(x, y);
            if (getCode(x, y).equals("H1") || getCode(x, y).equals("H3")) {
                System.out.println("hab level 2");
                habLevel = 2;
                if (findViewById(R.id.infoframe) != null) {
                    RocketFragment rocketFragment = new RocketFragment();
                    rocketFragment.setArguments(actionMap);
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.infoframe, rocketFragment, null);
                    fragmentTransaction.commit();
                }

            } else if (getCode(x, y).equals("H2")) {
                System.out.println("hab level 3");
                habLevel = 3;
                updateScreen();
            }

        if (getCode(x, y).equals("A") || getCode(x, y).equals("B")) {
            openRocket();
        } else if (findViewById(R.id.inputcontainer) != null && !("" + getCode(x, y).charAt(0)).equals("H")) {
            InputFragment inputFragment = new InputFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.inputcontainer, inputFragment, null);
            fragmentTransaction.commit();
        }

        currentAction.time = (int)(timeLeftInMilli/1000);
        currentAction.actionCode = getCode(x, y);

    }
        return false;
    }

    //0 = not on switch, 1 = red switch 1, 2 = blue switch1, 3 = blue scale, 4 = red scale, 5 = red switch2, 6 = blue switch, 7 = invalid click
    public String getCode(int x, int y){
        if(x > ROCKET1MIN[0] && x < ROCKET1MAX[0] && y > ROCKET1MIN[1] && y < ROCKET1MAX[1]){
            return "A";
        }
        if(x > ROCKET2MIN[0] && x < ROCKET2MAX[0] && y > ROCKET2MIN[1] && y < ROCKET2MAX[1]){
            return "B";
        }
        else if(x > CARGO1MIN[0] && x < CARGO1MAX[0] && y > CARGO1MIN[1] && y < CARGO1MAX[1]){
            return "C1";
        }
        else if(x > CARGO2MIN[0] && x < CARGO2MAX[0] && y > CARGO2MIN[1] && y < CARGO2MAX[1]){
            return "C2";
        }
        else if(x > CARGO3MIN[0] && x < CARGO3MAX[0] && y > CARGO3MIN[1] && y < CARGO3MAX[1]){
            return "C3";
        }
        else if(x > CARGO4MIN[0] && x < CARGO4MAX[0] && y > CARGO4MIN[1] && y < CARGO4MAX[1]){
            return "C4";
        }
        else if(x > CARGO5MIN[0] && x < CARGO5MAX[0] && y > CARGO5MIN[1] && y < CARGO5MAX[1]){
            return "C5";
        }
        else if(x > CARGO6MIN[0] && x < CARGO6MAX[0] && y > CARGO6MIN[1] && y < CARGO6MAX[1]){
            return "C6";
        }
        else if(x > CARGO7MIN[0] && x < CARGO7MAX[0] && y > CARGO7MIN[1] && y < CARGO7MAX[1]){
            return "C7";
        }
        else if(x > CARGO8MIN[0] && x < CARGO8MAX[0] && y > CARGO8MIN[1] && y < CARGO8MAX[1]){
            return "C8";
        }else if (x > HAB1MIN[0] && x < HAB1MAX[0] && y > HAB2MIN[1] && y < HAB2MAX[1]) {
            return "H1";
        }else if (x > HAB2MIN[0] && x < HAB2MAX[0] && y > HAB2MIN[1] && y < HAB2MAX[1]) {
            return "H2";
        }else if (x > HAB3MIN[0] && x < HAB3MAX[0] && y > HAB3MIN[1] && y < HAB3MAX[1]) {
            return "H3";
        } else if (x > topx) {
            return "Z";
        }
        else
            return "Z";

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void OnAutonRead(String message) {
        switch (message) {
            default:
        }
    }

    @Override
    public void OnPregameRead(String message) {
        switch (message) {

            default:

        }
    }

    @Override
    public void OnTeleopRead(String message) {
        switch (message) {

            default:

        }
    }

    @Override
    public void OnEndgameRead(String message) {
        switch (message) {

            case "toReview":
                if(findViewById(R.id.mapcontainer)!=null){
                    SubmissionReviewFragment submissionReviewFragment= new SubmissionReviewFragment();
                    submissionReviewFragment.setArguments(actionMap);
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.mapcontainer,submissionReviewFragment,null);
                    fragmentTransaction.commit();
                }
                bluetoothHandler.startlooking();
                bluetoothHandler.sendMessage('m',actionMap.toString());
                actionMap.save();
                break;

            default:
                break;
        }
    }




    public void openRocket() {
        if (findViewById(R.id.infoframe) != null) {
            RocketFragment rocketFragment = new RocketFragment();
            rocketFragment.setArguments(actionMap);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.infoframe, rocketFragment, null);
            fragmentTransaction.commit();
        }


    }

    @Override
    public void OnrocketRead(String message) {
        switch (message) {
            case "return":
                if (sandstorm) {
                    if (findViewById(R.id.infoframe) != null) {
                        AutonFragment rocketFragment = new AutonFragment();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.infoframe, rocketFragment, null);
                        fragmentTransaction.commit();
                    }
                } else {
                    if (findViewById(R.id.infoframe) != null) {
                        TeleopFragment rocketFragment = new TeleopFragment();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.infoframe, rocketFragment, null);
                        fragmentTransaction.commit();
                    }
                }
                break;
            case "1":
                currentAction.actionCode = currentAction.actionCode + 1;
                rocket = true;
                break;
            case "2":
                currentAction.actionCode = currentAction.actionCode + 2;
                rocket = true;
                break;
            case "3":
                currentAction.actionCode = currentAction.actionCode + 3;
                rocket = true;
                break;
            case "4":
                currentAction.actionCode = currentAction.actionCode + 4;
                rocket = true;
                break;
            case "5":
                currentAction.actionCode = currentAction.actionCode + 5;
                rocket = true;
                break;
            case "6":
                currentAction.actionCode = currentAction.actionCode + 6;
                rocket = true;
                break;

        }

        if(findViewById(R.id.inputcontainer)!=null){
            InputFragment inputFragment= new InputFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.inputcontainer,inputFragment,null);
            fragmentTransaction.commit();
        }
        }

    @Override
    public void OnHabTimerRead(String message) {
        climbTime = Integer.parseInt(message);
        actionMap.climbTime = this.climbTime;
    }


    public void switchbounds(){
        if(left) {
            ROCKET1MIN[0] = 170;
            ROCKET1MAX[0] = 260;
            ROCKET2MIN[0] = 170;
            ROCKET2MAX[0] = 260;
            CARGO1MIN[0] = 280;
            CARGO1MAX[0] = 340;
            CARGO2MIN[0] = 380;
            CARGO2MAX[0] = 440;
            CARGO3MIN[0] = 450;
            CARGO3MAX[0] = 520;
            CARGO4MIN[0] = 520;
            CARGO4MAX[0] = 590;
            CARGO5MIN[0] = 280;
            CARGO5MAX[0] = 340;
            CARGO6MIN[0] = 380;
            CARGO6MAX[0] = 440;
            CARGO7MIN[0] = 450;
            CARGO7MAX[0] = 520;
            CARGO8MIN[0] = 520;
            CARGO8MAX[0] = 590;
            HAB1MIN[0] = 0;
            HAB1MAX[0] = 70;
            HAB2MIN[0] = 0;
            HAB2MAX[0] = 70;
            HAB3MIN[0] = 0;
            HAB3MAX[0] = 70;
            ROCKET1MIN[1] = 0;
            ROCKET1MAX[1] = 125;
            ROCKET2MIN[1] = 275;
            ROCKET2MAX[1] = 400;
            CARGO1MIN[1] = 120;
            CARGO1MAX[1] = 195;
            CARGO2MIN[1] = 115;
            CARGO2MAX[1] = 150;
            CARGO3MIN[1] = 115;
            CARGO3MAX[1] = 150;
            CARGO4MIN[1] = 115;
            CARGO4MAX[1] = 150;
            CARGO5MIN[1] = 240;
            CARGO5MAX[1] = 280;
            CARGO6MIN[1] = 240;
            CARGO6MAX[1] = 280;
            CARGO7MIN[1] = 240;
            CARGO7MAX[1] = 280;
            CARGO8MIN[1] = 240;
            CARGO8MAX[1] = 280;
            HAB1MIN[1] = 110;
            HAB1MAX[1] = 165;
            HAB2MIN[1] = 165;
            HAB2MAX[1] = 235;
            HAB3MIN[1] = 235;
            HAB3MAX[1] = 290;
        }
        else{
            ROCKET1MIN[0] =   330;
            ROCKET1MAX[0] =   425;
            ROCKET2MIN[0] =   330;
            ROCKET2MAX[0] =   425;
            CARGO1MIN[0] =    280;
            CARGO1MAX[0] =    315;
            CARGO2MIN[0] =    160;
            CARGO2MAX[0] =    220;
            CARGO3MIN[0] =    85;
            CARGO3MAX[0] =    150;
            CARGO4MIN[0] =    10;
            CARGO4MAX[0] =    70;
            CARGO5MIN[0] =    280;
            CARGO5MAX[0] =    315;
            CARGO6MIN[0] =    160;
            CARGO6MAX[0] =    220;
            CARGO7MIN[0] =    85;
            CARGO7MAX[0] =    145;
            CARGO8MIN[0] =    10;
            CARGO8MAX[0] =    75;
            HAB1MIN[0] =      530;
            HAB1MAX[0] =      600;
            HAB2MIN[0] =      530;
            HAB2MAX[0] =      600;
            HAB3MIN[0] =      530;
            HAB3MAX[0] =      600;
            ROCKET1MIN[1] =   0;
            ROCKET1MAX[1] =   125;
            ROCKET2MIN[1] =   280;
            ROCKET2MAX[1] =   400;
            CARGO1MIN[1] =    200;
            CARGO1MAX[1] =    265;
            CARGO2MIN[1] =    240;
            CARGO2MAX[1] =    280;
            CARGO3MIN[1] =    240;
            CARGO3MAX[1] =    280;
            CARGO4MIN[1] =    240;
            CARGO4MAX[1] =    280;
            CARGO5MIN[1] =    125;
            CARGO5MAX[1] =    190;
            CARGO6MIN[1] =    115;
            CARGO6MAX[1] =    150;
            CARGO7MIN[1] =    115;
            CARGO7MAX[1] =    150;
            CARGO8MIN[1] =    115;
            CARGO8MAX[1] =    150;
            HAB1MIN[1] =      110;
            HAB1MAX[1] =      165;
            HAB2MIN[1] =      165;
            HAB2MAX[1] =      235;
            HAB3MIN[1] =      235;
            HAB3MAX[1] =      290;
        }
        left = !left;

    }

    @Override
    public void hatch(Boolean b) {
        currentAction.hatch = b;

        if (findViewById(R.id.inputcontainer) != null) {
            EmptyFragment emptyFragment = new EmptyFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.inputcontainer, emptyFragment, null);
            fragmentTransaction.commit();
        }
        if (sandstorm) {
            if (findViewById(R.id.infoframe) != null) {
                AutonFragment rocketFragment = new AutonFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.infoframe, rocketFragment, null);
                fragmentTransaction.commit();
            }
        } else {
            if (findViewById(R.id.infoframe) != null) {
                TeleopFragment rocketFragment = new TeleopFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.infoframe, rocketFragment, null);
                fragmentTransaction.commit();
            }
        }
        cancel.setVisibility(View.GONE);
        currentAction.matchStatus= matchStatus;
        actionMap.actions.add(currentAction);
        currentAction = new RobotAction();
        if(left){

        }
        else{
            //rightMapFragment.updateButtons();
        }
        updateScreen();
    }



    public void updateScreen() {
        cargoDisplay.setText("" + ActionMapUtils.totalhatches(false, actionMap.actions));
        hatchDisplay.setText("" + ActionMapUtils.totalhatches(true, actionMap.actions) );
        habDisplay.setText("" + habLevel);
        System.out.println(actionMap.actions);
        //updateFilled();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void changeFragment(int fragmentNum) {
        if (fragmentNum == 0) {
            if (findViewById(R.id.infoframe) != null) {
                cryptonite624.android.apps.com.cryptonitescout.PregameFragment pregameFragment = new cryptonite624.android.apps.com.cryptonitescout.PregameFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.infoframe, pregameFragment, null);
                fragmentTransaction.commit();
            }
        }
        if (fragmentNum == 1) {
            if (findViewById(R.id.infoframe) != null) {
                AutonFragment autonFragment = new AutonFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.infoframe, autonFragment, null);
                fragmentTransaction.commit();
            }
        }
        if (fragmentNum == 2) {
            if (findViewById(R.id.infoframe) != null) {
                TeleopFragment teleopFragment = new TeleopFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.infoframe, teleopFragment, null);
                fragmentTransaction.commit();
                sandstorm = false;
            }
        }
        if (fragmentNum == 3) {
            if (findViewById(R.id.infoframe) != null) {
                EndgameFragment endgameFragment = new EndgameFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.infoframe, endgameFragment, null);
                fragmentTransaction.commit();
            }
        }
    }

    @Override
    public void OnLeftMapRead(int x, int y) {

    }

    @Override
    public void OnRightMapRead(int x, int y) {

    }

    @Override
    public void OnSubmissionRead(String message) {

    }

    @Override
    public void OnBluetoothRead(String message) {

    }

    @Override
    public void start(Intent intent) {

    }


    public abstract class CountUpTimer extends CountDownTimer {
        private static final long INTERVAL_MS = 1000;
        private final long duration;

        protected CountUpTimer(long durationMs) {
            super(durationMs, INTERVAL_MS);
            this.duration = durationMs;
        }

        public abstract void onTick(int second);

        @Override
        public void onTick(long msUntilFinished) {
            int second = (int) ((duration - msUntilFinished) / 1000);
            onTick(second);
        }

        @Override
        public void onFinish() {
            onTick(duration / 1000);
        }
    }



    //TODO do this method done, updates to current match based on matches db
    public void getCurrentMatch(boolean useless){

    }


}