package cryptonite624.android.apps.com.cryptonitescout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.greenrobot.greendao.query.QueryBuilder;
import org.w3c.dom.Text;

import java.util.Arrays;

import cryptonite624.android.apps.com.cryptonitescout.Fragments.AutonFragment;
import cryptonite624.android.apps.com.cryptonitescout.Fragments.EndgameFragment;
import cryptonite624.android.apps.com.cryptonitescout.Fragments.InputFragment;
import cryptonite624.android.apps.com.cryptonitescout.Fragments.TeleopFragment;
import cryptonite624.android.apps.com.cryptonitescout.Models.Config;
import cryptonite624.android.apps.com.cryptonitescout.Models.DaoSession;
import cryptonite624.android.apps.com.cryptonitescout.Models.RankingData;
import cryptonite624.android.apps.com.cryptonitescout.Models.Schedule;
import cryptonite624.android.apps.com.cryptonitescout.Models.User;
import cryptonite624.android.apps.com.cryptonitescout.Models.UserDao;
import cryptonite624.android.apps.com.cryptonitescout.RocketFragment;
import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;
import cryptonite624.android.apps.com.cryptonitescout.Models.RobotAction;
import cryptonite624.android.apps.com.cryptonitescout.Utils.ActionMapUtils;

import java.util.Date;

public class MapView extends AppCompatActivity implements EmptyFragment.OnFragmentInteractionListener,View.OnTouchListener, InputFragment.OnInputReadListener, EndgameFragment.OnEndgameReadListener, cryptonite624.android.apps.com.cryptonitescout.PregameFragment.OnPregameReadListener,AutonFragment.OnAutonReadListener,TeleopFragment.OnTeleopReadListener,RocketFragment.OnrocketReadListener, LeftMapFragment.OnLeftMapReadListener, RightMapFragment.OnRightMapReadListener, SubmissionReviewFragment.OnSubmissionListener ,BluetoothHandler.BluetoothListener{

    public int x, y;

    boolean red;
    public boolean left = false;

    public static int[] ROCKET1MIN = {380, 90};
    public static int[] ROCKET1MAX = {530, 150};
    public static int[] ROCKET2MIN = {370, 500};
    public static int[] ROCKET2MAX = {540, 560};
    public static int[] CARGO1MIN = {260, 260};
    public static int[] CARGO1MAX = {340, 300};
    public static int[] CARGO2MIN = {375, 255};
    public static int[] CARGO2MAX = {450, 300};
    public static int[] CARGO3MIN = {460, 260};
    public static int[] CARGO3MAX = {540, 300};
    public static int[] CARGO4MIN = {580, 260};
    public static int[] CARGO4MAX = {670, 330};
    public static int[] CARGO5MIN = {260, 355};
    public static int[] CARGO5MAX = {345, 390};
    public static int[] CARGO6MIN = {370, 340};
    public static int[] CARGO6MAX = {450, 400};
    public static int[] CARGO7MIN = {460, 350};
    public static int[] CARGO7MAX = {540, 390};
    public static int[] CARGO8MIN = {580, 335};
    public static int[] CARGO8MAX = {670, 380 };
    public static int[] HAB1MIN = {850, 200};
    public static int[] HAB1MAX = {940, 300};
    public static int[] HAB2MIN = {850, 300};
    public static int[] HAB2MAX = {940, 360};
    public static int[] HAB3MIN = {850, 360};
    public static int[] HAB3MAX = {940, 460};
    public static int[] HAB4MIN = {750, 255};
    public static int[] HAB4MAX = {845, 430};


    public static FragmentManager fragmentManager;
    public ActionMap actionMap;
    //matchstatus, 0 = pregame, 1 = auton, 2 = teleop, 3 = endgame
    public int matchStatus = 0;
    public int habLevel;


    public long starttime;
    public long endtime;
    public double cycletime = -1;
    public boolean stopwatch= false;
    public int totalcycles = 0;
    private RelativeLayout mapview;
    private TextView habDisplay;

    private Button cancel;
    private Button undoButton;

    public LeftMapFragment leftMapFragment;
    public RightMapFragment rightMapFragment;


    private Button imageswitch;

    public boolean sandstorm;
    public boolean rocket = false;

    TextView hatchDisplay;
    TextView cargoDisplay;
    private Button b;

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

    public BluetoothHandler bluetoothHandler;
    public DaoSession daoSession;

    public User curuser;



    public int getCurrentMatch(){
        return daoSession.getConfigDao().loadAll().get(0).getCurrentmatch();
    }

    public Config config;

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

    public Schedule curschedule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map_view);
        daoSession = ((CRyptoniteApplication)getApplication()).getDaoSession();

        bluetoothHandler = new BluetoothHandler(getApplication(),this);



        setvars();

        fragmentManager = getSupportFragmentManager();
        if (findViewById(R.id.infoframe) != null) {
            if (savedInstanceState != null) {
                return;
            }
            cryptonite624.android.apps.com.cryptonitescout.PregameFragment pregameFragment = new cryptonite624.android.apps.com.cryptonitescout.PregameFragment();
            pregameFragment.setArguments(actionMap.getMatchnum(),actionMap.getTeamnum());
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
        //setBounds();




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

        actionMap = new ActionMap();


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
        undoButton = (Button) findViewById(R.id.undo);
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(actionMap.getActionsList().size() > 0){
                    actionMap.removeLast();
                    updateScreen();
                    System.out.println("You undid an action");
                }
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

    public void setvars(){
        curschedule = daoSession.getScheduleDao().loadAll().get(getCurrentMatch());
        config = daoSession.getConfigDao().loadAll().get(0);
        String user = config.getCurrentuser();
        QueryBuilder<User> qb = daoSession.getUserDao().queryBuilder();
        qb.where(UserDao.Properties.Email.eq(config.getCurrentuser()));
        List<User> users = qb.list();
        String team = getTeam(users.get(0), curschedule);
        curuser = users.get(0);
        if(curuser.getType().charAt(2)=='C')
            startActivity(new Intent(MapView.this, CommentActivity.class));
        int teamnum = Integer.parseInt(team.substring(3));
        actionMap.setTeamnum(teamnum);
        actionMap.setMatchnum(getCurrentMatch());
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

        //cargoDisplay.setText("" + y);
        //hatchDisplay.setText("" + x);

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
                        cancel.setVisibility(View.INVISIBLE);
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
            currentAction = new RobotAction();
            currentAction.setActionCode(getCode(x, y));
            if (getCode(x, y).equals("H2")) {
                System.out.println("hab level 2");
                habLevel = 2;
                actionMap.setEndclimb(habLevel);
                updateScreen();
            } else if (getCode(x, y).equals("H3")) {
                System.out.println("hab level 3");
                habLevel = 3;
                actionMap.setEndclimb(habLevel);
                updateScreen();
            } else if(getCode(x, y).equals("H1")){
                System.out.println("hab level 1");
                habLevel = 1;
                actionMap.setEndclimb(habLevel);
                updateScreen();
            }

        if (getCode(x, y).equals("A")) {
            openRocket(true);

        }else if(getCode(x, y).equals("B")){
            openRocket(false);
        }
        else if (findViewById(R.id.inputcontainer) != null && !("" + getCode(x, y).charAt(0)).equals("H")) {
            InputFragment inputFragment = new InputFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.inputcontainer, inputFragment, null);
            fragmentTransaction.commit();
        }

        currentAction.setTime((int)(timeLeftInMilli/1000));
        currentAction.setActionCode(getCode(x, y));

    }


        //xDisplay.setText("" + x);
        //yDisplay.setText("" + y);
        //CodeDisplay.setText("" + getCode(x, y));
        return false;
    }

    //0 = not on switch, 1 = jankredleft switch 1, 2 = jankblueleft switch1, 3 = jankblueleft scale, 4 = jankredleft scale, 5 = jankredleft switch2, 6 = jankblueleft switch, 7 = invalid click
    public String getCode(int x, int y){
        if(x > ROCKET1MIN[0] && x < ROCKET1MAX[0] && y > ROCKET1MIN[1] && y < ROCKET1MAX[1]){
            return "A";
        }
        if(x > ROCKET2MIN[0] && x < ROCKET2MAX[0] && y > ROCKET2MIN[1] && y < ROCKET2MAX[1]){
            return "B";
        } else if(x > CARGO1MIN[0] && x < CARGO1MAX[0] && y > CARGO1MIN[1] && y < CARGO1MAX[1]){
            return "C1";
        } else if(x > CARGO2MIN[0] && x < CARGO2MAX[0] && y > CARGO2MIN[1] && y < CARGO2MAX[1]){
            return "C2";
        } else if(x > CARGO3MIN[0] && x < CARGO3MAX[0] && y > CARGO3MIN[1] && y < CARGO3MAX[1]){
            return "C3";
        } else if(x > CARGO4MIN[0] && x < CARGO4MAX[0] && y > CARGO4MIN[1] && y < CARGO4MAX[1]){
            return "C4";
        } else if(x > CARGO5MIN[0] && x < CARGO5MAX[0] && y > CARGO5MIN[1] && y < CARGO5MAX[1]){
            return "C5";
        } else if(x > CARGO6MIN[0] && x < CARGO6MAX[0] && y > CARGO6MIN[1] && y < CARGO6MAX[1]){
            return "C6";
        } else if(x > CARGO7MIN[0] && x < CARGO7MAX[0] && y > CARGO7MIN[1] && y < CARGO7MAX[1]){
            return "C7";
        } else if(x > CARGO8MIN[0] && x < CARGO8MAX[0] && y > CARGO8MIN[1] && y < CARGO8MAX[1]){
            return "C8";
        }else if (x > HAB1MIN[0] && x < HAB1MAX[0] && y > HAB1MIN[1] && y < HAB1MAX[1]) {
            return "H2";
        }else if (x > HAB2MIN[0] && x < HAB2MAX[0] && y > HAB2MIN[1] && y < HAB2MAX[1]) {
            return "H3";
        }else if (x > HAB3MIN[0] && x < HAB3MAX[0] && y > HAB3MIN[1] && y < HAB3MAX[1]) {
            return "H2";
        }else if (x > HAB4MIN[0] && x < HAB4MAX[0] && y > HAB4MIN[1] && y < HAB4MAX[1]) {
            return "H1";
        }
        return "Z";

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void OnAutonRead(String message) {
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
                break;

            default:
        }
    }



    public void openRocket(boolean b) {
        if (findViewById(R.id.infoframe) != null) {
            RocketFragment rocketFragment = new RocketFragment();
            rocketFragment.setArguments(actionMap, b);
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
                currentAction.setActionCode( currentAction.getActionCode() + 1);
                rocket = true;
                break;
            case "2":
                currentAction.setActionCode( currentAction.getActionCode() + 2);
                rocket = true;
                break;
            case "3":
                currentAction.setActionCode( currentAction.getActionCode() + 3);
                rocket = true;
                break;
            case "4":
                currentAction.setActionCode( currentAction.getActionCode() + 4);
                rocket = true;
                break;
            case "5":
                currentAction.setActionCode( currentAction.getActionCode() + 5);
                rocket = true;
                break;
            case "6":
                currentAction.setActionCode( currentAction.getActionCode() + 6);
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
            ROCKET1MIN[0] = 690;
            ROCKET1MAX[0] = 850;
            ROCKET2MIN[0] = 690;
            ROCKET2MAX[0] = 850;
            CARGO1MIN[0] = 540;
            CARGO1MAX[0] = 640;
            CARGO2MIN[0] = 670;
            CARGO2MAX[0] = 750;
            CARGO3MIN[0] = 770;
            CARGO3MAX[0] = 850;
            CARGO4MIN[0] = 865;
            CARGO4MAX[0] = 940;
            CARGO5MIN[0] = 540;
            CARGO5MAX[0] = 635;
            CARGO6MIN[0] = 660;
            CARGO6MAX[0] = 740;
            CARGO7MIN[0] = 770;
            CARGO7MAX[0] = 850;
            CARGO8MIN[0] = 860;
            CARGO8MAX[0] = 950;
            HAB1MIN[0] = 260;
            HAB1MAX[0] = 360;
            HAB2MIN[0] = 260;
            HAB2MAX[0] = 360;
            HAB3MIN[0] = 260;
            HAB3MAX[0] = 360;


            ROCKET1MIN[1] = 100;
            ROCKET1MAX[1] = 160;
            ROCKET2MIN[1] = 490;
            ROCKET2MAX[1] = 550;
            CARGO1MIN[1] = 270;
            CARGO1MAX[1] = 330;
            CARGO2MIN[1] = 260;
            CARGO2MAX[1] = 300;
            CARGO3MIN[1] = 260;
            CARGO3MAX[1] = 300;
            CARGO4MIN[1] = 260;
            CARGO4MAX[1] = 330;
            CARGO5MIN[1] = 330;
            CARGO5MAX[1] = 395;
            CARGO6MIN[1] = 350;
            CARGO6MAX[1] = 400;
            CARGO7MIN[1] = 350;
            CARGO7MAX[1] = 405;
            CARGO8MIN[1] = 335;
            CARGO8MAX[1] = 380;

            HAB4MIN[0] =370;
            HAB4MAX[0] = 455;

        }
        else{
            ROCKET1MIN[0] = 380;
            ROCKET1MAX[0] = 530;
            ROCKET2MIN[0] = 370;
            ROCKET2MAX[0] = 540;
            CARGO1MIN[0] = 260;
            CARGO1MAX[0] = 340;
            CARGO2MIN[0] = 375;
            CARGO2MAX[0] = 450;
            CARGO3MIN[0] = 460;
            CARGO3MAX[0] = 540;
            CARGO4MIN[0] = 580;
            CARGO4MAX[0] = 670;
            CARGO5MIN[0] = 260;
            CARGO5MAX[0] = 355;
            CARGO6MIN[0] = 370;
            CARGO6MAX[0] = 450;
            CARGO7MIN[0] = 460;
            CARGO7MAX[0] = 540;
            CARGO8MIN[0] = 580;
            CARGO8MAX[0] = 670;
            HAB1MIN[0] = 850;
            HAB1MAX[0] = 940;
            HAB2MIN[0] = 850;
            HAB2MAX[0] = 940;
            HAB3MIN[0] = 850;
            HAB3MAX[0] = 940;

            ROCKET1MIN[1] = 90;
            ROCKET1MAX[1] = 150;
            ROCKET2MIN[1] = 500;
            ROCKET2MAX[1] = 560;
            CARGO1MIN[1] = 260;
            CARGO1MAX[1] = 300;
            CARGO2MIN[1] = 255;
            CARGO2MAX[1] = 300;
            CARGO3MIN[1] = 260;
            CARGO3MAX[1] = 300;
            CARGO4MIN[1] = 260;
            CARGO4MAX[1] = 330;
            CARGO5MIN[1] = 355;
            CARGO5MAX[1] = 390;
            CARGO6MIN[1] = 340;
            CARGO6MAX[1] = 400;
            CARGO7MIN[1] = 350;
            CARGO7MAX[1] = 390;
            CARGO8MIN[1] = 335;
            CARGO8MAX[1] = 380;

            HAB4MIN[0] = 750;
            HAB4MAX[0] = 845;
        }
        left = !left;

    }

    @Override
    public void hatch(Boolean b) {

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
        currentAction.setMatchStatus(matchStatus);
        currentAction.setHatch(b);
        //actionMap.getActionsList().add(currentAction);
        List<RobotAction> temp = actionMap.getActionsList();
        if(temp!= null)
            temp.add(currentAction);
        else{
            temp = new ArrayList<RobotAction>();
            temp.add(currentAction);
        }
        actionMap.setActionsList((ArrayList<RobotAction>)temp);
        currentAction = new RobotAction();

        if(left){
            rightMapFragment.lightUpButtons((ArrayList<RobotAction>) temp);

        }
        else{
            leftMapFragment.lightUpButtons((ArrayList<RobotAction>) temp);
        }
        updateScreen();


    }

    public void updateScreen() {
        cargoDisplay.setText("" + ActionMapUtils.totalhatches(false, actionMap.getActionsList()));
        hatchDisplay.setText("" + ActionMapUtils.totalhatches(true, actionMap.getActionsList()));
        habDisplay.setText("" + habLevel);
        System.out.println(actionMap.getActionsList());
        //updateFilled();
    }

    public void updateFilled(){
        System.out.println("IT WORKS!!");
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public ActionMap getActionMap(){
        return actionMap;
    }




    public void changeFragment(int fragmentNum) {
        /*if (fragmentNum == 3) {
            matchStatus = -1;
        }*/
        //matchStatus++;
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
        switch(message){
            case "submit":
                daoSession.getActionMapDao().save(actionMap);
                try {
                    bluetoothHandler.sendMessage('m',ActionMapUtils.toString(actionMap));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                config.setCurrentmatch((config.getCurrentmatch())+1);
                updateRD(actionMap);
                break;
            case "replay":
                Intent intent = new Intent(getBaseContext(), ReplayViewPage.class);
                intent.putExtra("match", ActionMapUtils.toString(actionMap));
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    public void updateRD(ActionMap m){
        RankingData r = new RankingData();
        //TODO add something for climb and other variables
    }

    @Override
    public void OnBluetoothRead(String message) {
        Toast.makeText(MapView.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void start(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void makediscoverable() {
    }
}