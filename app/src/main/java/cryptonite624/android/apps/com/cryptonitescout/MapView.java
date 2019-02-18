package cryptonite624.android.apps.com.cryptonitescout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
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

import cryptonite624.android.apps.com.cryptonitescout.Fragments.AutonFragment;
import cryptonite624.android.apps.com.cryptonitescout.Fragments.EndgameFragment;
import cryptonite624.android.apps.com.cryptonitescout.Fragments.InputFragment;
import cryptonite624.android.apps.com.cryptonitescout.Fragments.TeleopFragment;
import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;
import cryptonite624.android.apps.com.cryptonitescout.Models.RobotAction;
import java.util.Date;

public class MapView extends AppCompatActivity implements EmptyFragment.OnFragmentInteractionListener,View.OnTouchListener, InputFragment.OnInputReadListener, EndgameFragment.OnEndgameReadListener, cryptonite624.android.apps.com.cryptonitescout.PregameFragment.OnPregameReadListener,AutonFragment.OnAutonReadListener,TeleopFragment.OnTeleopReadListener,RocketFragment.OnrocketReadListener, LeftMapFragment.OnLeftMapReadListener, RightMapFragment.OnRightMapReadListener {


    public int x, y;

    public Map<String,String> lastmessages  = new HashMap<>();
    public static String regex = "0624";

    public Match endmatch = new Match();
    private BluetoothAdapter bluetoothAdapter = null;
    public int topx;

    public Match getCurrentMatch(){
        return new Match();
    }

    public int recordeddevices = 0;

    public int commnum = 0;
    public int mapnum = 0;



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


    public static int[] imageratio = {1,1};

    public static int[] screenratio = new int[2];
    public static double conversionfactor;

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

    int status = 0;

    private CountDownTimer countDownTimer;
    private long timeLeftInMilli = 150000;
    private boolean timerRunning;
    int minutes;
    int seconds;

    private TextView timerDisplay;
    private Button timerButton;

    public MessagesClient mMessagesClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_map_view);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMessagesClient = Nearby.getMessagesClient(this, new MessagesOptions.Builder()
                    .setPermissions(NearbyPermissions.BLE)
                    .build());
        }

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

        endmatch = getCurrentMatch();

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
                        rightMapFragment = rightMapFragment;
                    }
                }
                else{
                    if (findViewById(R.id.mapcontainer) != null) {
                        leftMapFragment = new LeftMapFragment();
                        leftMapFragment = leftMapFragment;
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

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
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
                updateScreen();
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
            case "submit":
                startDiscovery();
                sendMessage();
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        // Your database code here
                        startDiscovery();
                        ensureDiscoverable();
                    }
                }, 3*1000, 3*1000);
                break;
            default:
                break;
        }
    }

    private void ensureDiscoverable() {
        if (bluetoothAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(
                    BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 3600);
            startActivity(discoverableIntent);
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


    public void switchbounds(){
        if(left) {
            ROCKET1MIN[0] = 430;
            ROCKET1MAX[0] = 624;
            ROCKET2MIN[0] = 439;
            ROCKET2MAX[0] = 624;
            CARGO1MIN[0] = 659;
            CARGO1MAX[0] = 749;
            CARGO2MIN[0] = 542;
            CARGO2MAX[0] = 621;
            CARGO3MIN[0] = 446;
            CARGO3MAX[0] = 529;
            CARGO4MIN[0] = 348;
            CARGO4MAX[0] = 427;
            CARGO5MIN[0] = 654;
            CARGO5MAX[0] = 751;
            CARGO6MIN[0] = 547;
            CARGO6MAX[0] = 623;
            CARGO7MIN[0] = 442;
            CARGO7MAX[0] = 522;
            CARGO8MIN[0] = 347;
            CARGO8MAX[0] = 422;
            HAB1MIN[0] = 924;
            HAB1MAX[0] = 1019;
            HAB2MIN[0] = 929;
            HAB2MAX[0] = 1019;
            HAB3MIN[0] = 926;
            HAB3MAX[0] = 1015;
            ROCKET1MIN[1] = 456;
            ROCKET1MAX[1] = 545;
            ROCKET2MIN[1] = 100;
            ROCKET2MAX[1] = 175;
            CARGO1MIN[1] = 332;
            CARGO1MAX[1] = 386;
            CARGO2MIN[1] = 347;
            CARGO2MAX[1] = 385;
            CARGO3MIN[1] = 346;
            CARGO3MAX[1] = 392;
            CARGO4MIN[1] = 349;
            CARGO4MAX[1] = 392;
            CARGO5MIN[1] = 264;
            CARGO5MAX[1] = 314;
            CARGO6MIN[1] = 254;
            CARGO6MAX[1] = 307;
            CARGO7MIN[1] = 255;
            CARGO7MAX[1] = 307;
            CARGO8MIN[1] = 258;
            CARGO8MAX[1] = 300;
            HAB1MIN[1] = 359;
            HAB1MAX[1] = 413;
            HAB2MIN[1] = 293;
            HAB2MAX[1] = 357;
            HAB3MIN[1] = 237;
            HAB3MAX[1] = 283;
        }
        else{
            ROCKET1MIN[0] =   740;
            ROCKET1MAX[0] =   942;
            ROCKET2MIN[0] =   752;
            ROCKET2MAX[0] =   933;
            CARGO1MIN[0] =    614;
            CARGO1MAX[0] =    710;
            CARGO2MIN[0] =    743;
            CARGO2MAX[0] =    822;
            CARGO3MIN[0] =    840;
            CARGO3MAX[0] =    923;
            CARGO4MIN[0] =    936;
            CARGO4MAX[0] =    1018;
            CARGO5MIN[0] =    614;
            CARGO5MAX[0] =    710;
            CARGO6MIN[0] =    743;
            CARGO6MAX[0] =    822;
            CARGO7MIN[0] =    840;
            CARGO7MAX[0] =    923;
            CARGO8MIN[0] =    936;
            CARGO8MAX[0] =    1018;
            HAB1MIN[0] =      348;
            HAB1MAX[0] =      445;
            HAB2MIN[0] =      348;
            HAB2MAX[0] =      445;
            HAB3MIN[0] =      348;
            HAB3MAX[0] =      445;
            ROCKET1MIN[1] =   100;
            ROCKET1MAX[1] =   195;
            ROCKET2MIN[1] =   472;
            ROCKET2MAX[1] =   545;
            CARGO1MIN[1] =    265;
            CARGO1MAX[1] =    321;
            CARGO2MIN[1] =    255;
            CARGO2MAX[1] =    300;
            CARGO3MIN[1] =    260;
            CARGO3MAX[1] =    300;
            CARGO4MIN[1] =    260;
            CARGO4MAX[1] =    299;
            CARGO5MIN[1] =    329;
            CARGO5MAX[1] =    377;
            CARGO6MIN[1] =    342;
            CARGO6MAX[1] =    396;
            CARGO7MIN[1] =    342;
            CARGO7MAX[1] =    396;
            CARGO8MIN[1] =    342;
            CARGO8MAX[1] =     396;
            HAB1MIN[1] =      237;
            HAB1MAX[1] =      293;
            HAB2MIN[1] =      293;
            HAB2MAX[1] =      362;
            HAB3MIN[1] =      362;
            HAB3MAX[1] =      413;
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
        cargoDisplay.setText("" + actionMap.totalhatches(false));
        hatchDisplay.setText("" + actionMap.totalhatches(true));
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

    /******************************************************************************************************************/

    private void sendMessage() {
        final String sNewName = "0624"+'m'+actionMap.toString();
        final BluetoothAdapter myBTAdapter = BluetoothAdapter.getDefaultAdapter();
        final long lTimeToGiveUp_ms = System.currentTimeMillis() + 10000;
        if (myBTAdapter != null)
        {
            String sOldName = myBTAdapter.getName();
            if (sOldName.equalsIgnoreCase(sNewName) == false)
            {
                final Handler myTimerHandler = new Handler();
                myBTAdapter.enable();
                myTimerHandler.postDelayed(
                        new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                if (myBTAdapter.isEnabled())
                                {
                                    myBTAdapter.setName(sNewName);
                                    if (sNewName.equalsIgnoreCase(myBTAdapter.getName()))
                                    {
                                        Log.i("hi", "Updated BT Name to " + myBTAdapter.getName());
                                    }
                                }
                                if ((sNewName.equalsIgnoreCase(myBTAdapter.getName()) == false) && (System.currentTimeMillis() < lTimeToGiveUp_ms))
                                {
                                    myTimerHandler.postDelayed(this, 500);
                                    if (myBTAdapter.isEnabled())
                                        Log.i("hi", "Update BT Name: waiting on BT Enable");
                                    else
                                        Log.i("hi", "Update BT Name: waiting for Name (" + sNewName + ") to set in");
                                }
                            }
                        } , 500);
            }
        }
    }

    private void startDiscovery() {
        if (bluetoothAdapter.isDiscovering()) {
            bluetoothAdapter.cancelDiscovery();
        }

        bluetoothAdapter.startDiscovery();
    }

    private final BroadcastReceiver mBroadcastReceiver2 = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            BluetoothDevice bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            startFetch(bluetoothDevice);
            ActionMap tempmap = new ActionMap();
            String temp;
            String name = intent.getStringExtra(BluetoothDevice.EXTRA_NAME);
            if(name!=null)
                if(name.contains(regex)){
                    temp = lastmessages.put(bluetoothDevice.getAddress(),name);
                    if(temp==null){
                        if (name.charAt(regex.length()) == 'm') {
                            tempmap.parseString(name.substring(regex.length()));
                            recordeddevices++;
                        }
                    }
                    else if(!temp.equals(name)){
                        if (name.charAt(regex.length()) == 'm') {
                            tempmap.parseString(name.substring(regex.length()));
                            recordeddevices++;
                        }
                    }
                }
        }
    };

    public List<BluetoothDevice> mNewDevicesArrayAdapter = new ArrayList<BluetoothDevice>();

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            // When discovery finds a device
            String temp;
            ActionMap tempmap = new ActionMap();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                startFetch(device);
                String name = intent.getStringExtra(BluetoothDevice.EXTRA_NAME);
                if(name!=null)
                    if(name.contains(regex)){
                        temp = lastmessages.put(device.getAddress(),name);
                        if(temp==null){
                            if (name.charAt(regex.length()) == 'm') {
                                tempmap.parseString(name.substring(regex.length()));
                                recordeddevices++;
                            }
                        }
                        else if(!temp.equals(name)) {
                            if (name.charAt(regex.length()) == 'm') {
                                tempmap.parseString(name.substring(regex.length()));
                                recordeddevices++;
                            }
                        }
                    }
                Log.d("mReceiver","ACTION_FOUND:"+device.getAddress()+" :"+device.getName());
                // If it's already paired, skip it, because it's been listed already

                if(device.getName()==null)
                { //when name is null, skip
                    //But if you want to make lists asap, comment out this block.
                }
                else
                {
                    if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                        //mNewDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                        boolean listed = false;
                        for (BluetoothDevice bd:mNewDevicesArrayAdapter) {
                            String address = bd.getAddress();
                            if (device.getAddress().equals(address)) {
                                Log.d("mReceiver", "ACTION_FOUND: replace the item of lists");
                                mNewDevicesArrayAdapter.remove(bd);
                                mNewDevicesArrayAdapter.add(device);
                                listed = true;
                                break;
                            }
                        }
                        if(listed==false)
                        {//if it is new device( not in lists), add it.
                            mNewDevicesArrayAdapter.add(device);
                        }
                    }
                }

            }else if(BluetoothDevice.ACTION_NAME_CHANGED.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                startFetch(device);
                String name = intent.getStringExtra(BluetoothDevice.EXTRA_NAME);
                if(name!=null)
                    if(name.contains(regex)){
                        temp = lastmessages.put(device.getAddress(),name);
                        /**TODO check this out after finishing bluetooth output system*/
                        if(temp==null){
                            if (name.charAt(regex.length()) == 'm') {
                                tempmap.parseString(name.substring(regex.length()));
                                recordeddevices++;
                            }

                        }
                        else if(!temp.equals(name)){
                            if (name.charAt(regex.length()) == 'm') {
                                tempmap.parseString(name.substring(regex.length()));
                                recordeddevices++;
                            }
                        }
                    }
                Log.d("mReceiver", "NAME_CHANGED:" + device.getAddress() + " :" + device.getName());
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    boolean listed = false;
                    for (BluetoothDevice bd:mNewDevicesArrayAdapter) {
                        String address = bd.getAddress();
                        if (device.getAddress().equals(address)) {
                            Log.d("mReceiver", "NAME_CHANGED: replace the item of lists");
                            mNewDevicesArrayAdapter.remove(bd);
                            mNewDevicesArrayAdapter.add(device);
                            listed = true;
                            break;
                        }
                    }
                    if(listed==false)
                    {//if it is new device( not in lists), add it.
                        mNewDevicesArrayAdapter.add(device);
                    }
                }
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                setProgressBarIndeterminateVisibility(false);
                setTitle(R.string.select_device);
                if (mNewDevicesArrayAdapter.size() == 0) {
                    mNewDevicesArrayAdapter.add(null);
                }
            }
        }
    };

    public void updatePending(){
        //TODO Place Pending Logic over here
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Don't forget to unregister the ACTION_FOUND receiver.
        unregisterReceiver(mBroadcastReceiver2);
        bluetoothAdapter.cancelDiscovery();
    }

    public static void startFetch( BluetoothDevice device ) {
        // Need to use reflection prior to API 15
        Class cl = null;
        try {
            cl = Class.forName("android.bluetooth.BluetoothDevice");
        } catch( ClassNotFoundException exc ) {
            Log.e("hiya", "android.bluetooth.BluetoothDevice not found." );
        }
        if (null != cl) {
            Class[] param = {};
            Method method = null;
            try {
                method = cl.getMethod("fetchUuidsWithSdp", param);
            } catch( NoSuchMethodException exc ) {
                Log.e("hiya", "fetchUuidsWithSdp not found." );
            }
            if (null != method) {
                Object[] args = {};
                try {
                    method.invoke(device, args);
                } catch (Exception exc) {
                    Log.e("hiya", "Failed to invoke fetchUuidsWithSdp method." );
                }
            }
        }
    }

    /******************************************************************************************************************/


    //TODO do this method done, updates to current match based on matches db
    public void getCurrentMatch(boolean useless){

    }


}