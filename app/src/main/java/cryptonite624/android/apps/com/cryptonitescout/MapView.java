package cryptonite624.android.apps.com.cryptonitescout;

import android.annotation.SuppressLint;
import android.content.Context;
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
import java.util.Timer;
import java.util.TimerTask;

import org.w3c.dom.Text;

import java.util.Arrays;

import cryptonite624.android.apps.com.cryptonitescout.Fragments.AutonFragment;
import cryptonite624.android.apps.com.cryptonitescout.Fragments.EndgameFragment;
import cryptonite624.android.apps.com.cryptonitescout.Fragments.InputFragment;
import cryptonite624.android.apps.com.cryptonitescout.Fragments.TeleopFragment;
import cryptonite624.android.apps.com.cryptonitescout.RocketFragment;
import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;
import cryptonite624.android.apps.com.cryptonitescout.Models.RobotAction;
import cryptonite624.android.apps.com.cryptonitescout.Utils.ActionMapUtils;

import java.util.Date;

public class MapView extends AppCompatActivity implements EmptyFragment.OnFragmentInteractionListener,View.OnTouchListener, InputFragment.OnInputReadListener, EndgameFragment.OnEndgameReadListener, cryptonite624.android.apps.com.cryptonitescout.PregameFragment.OnPregameReadListener,AutonFragment.OnAutonReadListener,TeleopFragment.OnTeleopReadListener,RocketFragment.OnrocketReadListener, LeftMapFragment.OnLeftMapReadListener, RightMapFragment.OnRightMapReadListener, SubmissionReviewFragment.OnSubmissionListener {


    /**
     *
     * TODO
     * popup on click for hatch, cargo, successful\
     * rocket, 12 buttons?
     * total hatches, total cargo
     * forward cycle?
     *
     * PICTURE!!!
     * already done display
     * - make button slightly different
     * - show preinstalled as different
     *
     * two color versions, make map look nice
     * calibration
     *
     * dylan - superscout
     *record preloads
     * easy touch targets
     * 
     *
     */
    public int x, y;
    TextView xDisplay, yDisplay, CodeDisplay;
    /*public static int[] REDSWITCH1MIN = {530, 530};
    public static int[] REDSWITCH1MAX = {630, 610};
    public static int[] BLUESWITCH1MIN = {530, 750};
    public static int[] BLUESWITCH1MAX = {630, 810};
    public static int[] BLUESCALEMIN = {840, 475};
    public static int[] BLUESCALEMAX = {945, 565};
    public static int[] REDSCALEMIN = {840, 770};
    public static int[] REDSCALEMAX = {945, 850};
    public static int[] REDSWITCH2MIN = {1130, 530};
    public static int[] REDSWITCH2MAX = {1240, 615};
    public static int[] BLUESWITCH2MIN = {1130, 750};
    public static int[] BLUESWITCH2MAX = {1240, 825};*/
    
    public boolean red = true;
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


    public static int[] imageratio = {1,1};

    public static int[] screenratio = new int[2];
    public static double conversionfactor;

    public static FragmentManager fragmentManager;
    public ActionMap actionMap = new ActionMap();
    //matchstatus, 0 = pregame, 1 = auton, 2 = teleop, 3 = endgame
    public int matchStatus = 0;
    public Button statusForward, statusBack;
    public TextView statusDisplay;
    public static String[] statusStrings = {"pregame", "auton", "teleop", "endgame"};
    public boolean actionReady;
    public int tempX, tempY;
    public TextView totalDisplay;
    public static int[] ALLCODES = {1, 2, 3, 4, 5, 6};
    public static int[] ALLSTATUS = {1, 2, 3};
    private static final int OFFSET = 120;
    public static int topx, topy, bttmx, bttmy;
    public int habLevel;

    Button cargobutton1;
    Button cargobutton2;
    Button cargobutton3;
    Button cargobutton4;
    Button cargobutton5;
    Button cargobutton6;
    Button cargobutton7;
    Button cargobutton8;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //RelativeLayout layout = (RelativeLayout)findViewById(R.id.mapview);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        //customView = (CustomImageView) findViewById(R.id.drawview);

        setContentView(R.layout.activity_map_view);
        //setContentView(view);

        /*cargobutton1 = (Button)findViewById(R.id.cargobutton1);
        cargobutton2 = (Button)findViewById(R.id.cargobutton2);
        cargobutton3 = (Button)findViewById(R.id.cargobutton3);
        cargobutton4 = (Button)findViewById(R.id.cargobutton4);
        cargobutton5 = (Button)findViewById(R.id.cargobutton5);
        cargobutton6 = (Button)findViewById(R.id.cargobutton6);
        cargobutton7 = (Button)findViewById(R.id.cargobutton7);
        cargobutton8 = (Button)findViewById(R.id.cargobutton8);
        */

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

        //mCustomDrawableView = new CustomDrawableView(this);

        //setContentView(mCustomDrawableView);


        cargoDisplay = (TextView) findViewById(R.id.cargodisplay);
        hatchDisplay = (TextView) findViewById(R.id.hatchdisplay);
        habDisplay = (TextView) findViewById(R.id.hableveldisplay);


        //xDisplay = (TextView)findViewById(R.id.XDisplay);
        //yDisplay = (TextView)findViewById(R.id.YDisplay);
        //CodeDisplay = (TextView)findViewById(R.id.CodeDisplay);
        /*statusDisplay = (TextView)findViewById(R.id.StatusDisplay);
        totalDisplay = (TextView)findViewById(R.id.TotalDisplay);

        statusForward = (Button)findViewById(R.id.StatusButtonForward);
        statusForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchStatus < 3) {
                    matchStatus++;
                    updateDisplay();
                }
            }
        });

        statusBack = (Button)findViewById(R.id.StatusButtonBack);
        statusBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchStatus > 0) {
                    matchStatus--;
                    updateDisplay();
                }
            }
        });*/

        //drawing = (Drawing) findViewById(R.id.)

        //drawing = new Drawing(this);
        //setContentView(drawing);


        //mImageView = (ImageView) findViewById(R.id.mapview);

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

        cargoDisplay.setText("" + y);
        hatchDisplay.setText("" + x);

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
        //hatchDisplay.setText(""+x);
        //cargoDisplay.setText(""+y);

        //drawing = new Drawing(this);

        /*if (actionReady) {
            actionReady = false;
            actionMap.actions.add(new RobotAction(getCode(x, y), matchStatus));
        }*/

        /*if(actionReady == false){
            customView.setClickLocation(x, y);
        }*/

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
            if (getCode(x, y).equals("H2")) {
                System.out.println("hab level 2");
                habLevel = 2;
                updateScreen();
            } else if (getCode(x, y).equals("H3")) {
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
        }else if (x > HAB1MIN[0] && x < HAB1MAX[0] && y > HAB1MIN[1] && y < HAB1MAX[1]) {
            return "H2";
        }else if (x > HAB2MIN[0] && x < HAB2MAX[0] && y > HAB2MIN[1] && y < HAB2MAX[1]) {
            return "H3";
        }else if (x > HAB3MIN[0] && x < HAB3MAX[0] && y > HAB3MIN[1] && y < HAB3MAX[1]) {
            return "H2";
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
            /*
            case "toPrematch":
                if(findViewById(R.id.infoframe)!=null){
                    cryptonite624.android.apps.com.cryptonitescout.PregameFragment pregameFragment= new cryptonite624.android.apps.com.cryptonitescout.PregameFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.infoframe,pregameFragment,null);
                    fragmentTransaction.commit();
                }
                break;

            case "toTeleop":
                if(findViewById(R.id.infoframe)!=null){
                    TeleopFragment teleopFragment= new TeleopFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.infoframe,teleopFragment,null);
                    fragmentTransaction.commit();
                    sandstorm=false;
                }
                break;*/

            default:
        }
    }

    @Override
    public void OnPregameRead(String message) {
        switch (message) {
            /*
            case "toAuton":
                if(findViewById(R.id.infoframe)!=null){
                    AutonFragment autonFragment= new AutonFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.infoframe,autonFragment,null);
                    fragmentTransaction.commit();
                }
                break;*/

            default:

        }
    }

    @Override
    public void OnTeleopRead(String message) {
        switch (message) {
            /*
            case "toAuton":
                if(findViewById(R.id.infoframe)!=null){
                    AutonFragment autonFragment = new AutonFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.infoframe,autonFragment,null);
                    fragmentTransaction.commit();
                    sandstorm=true;
                }
                break;

            case "toEndgame":
                if(findViewById(R.id.infoframe)!=null){
                    EndgameFragment endgameFragment= new EndgameFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.infoframe,endgameFragment,null);
                    fragmentTransaction.commit();
                }
                break;*/

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


    public int getpixelheight() {
        return (int) ((int) ((double) imageratio[1] * ((double) 2 / 3) * screenratio[0]) / (double) imageratio[0]);
    }

    public void setScreenratio(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenratio[0]= size.x;
        screenratio[1] = size.y;
        conversionfactor = screenratio[0]/imageratio[0];
        Log.e("ScreenRatio*&*%F&^%", "" + Arrays.toString(screenratio));
    }


    public void switchbounds(){
        if(left) {
            /*
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
            HAB1MIN[1] = 200;
            HAB1MAX[1] = 300;
            HAB2MIN[1] = 300;
            HAB2MAX[1] = 360;
            HAB3MIN[1] = 360;
            HAB3MAX[1] = 460;
            /*
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
            HAB3MAX[1] = 283;*/
            //setBounds();
            ROCKET1MIN[0] = 690;
            ROCKET1MAX[0] = 850;
            ROCKET2MIN[0] = 490;
            ROCKET2MAX[0] = 550;
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
            HAB1MIN[1] = 200;
            HAB1MAX[1] = 300;
            HAB2MIN[1] = 300;
            HAB2MAX[1] = 360;
            HAB3MIN[1] = 360;
            HAB3MAX[1] = 460;

        }
        else{
            /*
            ROCKET1MIN[0] = 690;
            ROCKET1MAX[0] = 850;
            ROCKET2MIN[0] = 490;
            ROCKET2MAX[0] = 550;
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
            HAB1MIN[1] = 200;
            HAB1MAX[1] = 300;
            HAB2MIN[1] = 300;
            HAB2MAX[1] = 360;
            HAB3MIN[1] = 360;
            HAB3MAX[1] = 460;*/
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
            HAB1MIN[1] = 200;
            HAB1MAX[1] = 300;
            HAB2MIN[1] = 300;
            HAB2MAX[1] = 360;
            HAB3MIN[1] = 360;
            HAB3MAX[1] = 460;
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
        hatchDisplay.setText("" + ActionMapUtils.totalhatches(true, actionMap.actions));
        habDisplay.setText("" + habLevel);
        System.out.println(actionMap.actions);
        //updateFilled();
    }

    public void updateFilled(){
        System.out.println("IT WORKS!!");
    }

/*
    public void updateFilled(){
        //cargobutton5.setBackgroundColor(Color.BLUE);
        for(int i = 0; i < actionMap.actions.size(); i++){
            System.out.println(actionMap.actions.get(i));
            if(actionMap.actions.get(i).actionCode.equals("C1")){
                cargobutton1.setBackgroundColor(Color.YELLOW);
            }
            else if(actionMap.actions.get(i).actionCode.equals("C2")){
                cargobutton2.setBackgroundColor(Color.YELLOW);
            }
            else if(actionMap.actions.get(i).actionCode.equals("C3")){
                cargobutton3.setBackgroundColor(Color.YELLOW);
            }
            else if(actionMap.actions.get(i).actionCode.equals("C4")){
                cargobutton4.setBackgroundColor(Color.YELLOW);
            }
            else if(actionMap.actions.get(i).actionCode.equals("C5")){
                cargobutton5.setBackgroundColor(Color.YELLOW);
            }
            else if(actionMap.actions.get(i).actionCode.equals("C6")){
                cargobutton6.setBackgroundColor(Color.YELLOW);
            }
            else if(actionMap.actions.get(i).actionCode.equals("C7")){
                cargobutton7.setBackgroundColor(Color.YELLOW);
            }
            else if(actionMap.actions.get(i).actionCode.equals("C8")){
                cargobutton8.setBackgroundColor(Color.YELLOW);
            }
        }
    }
*/
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public ActionMap getActionMap(){
        return actionMap;
    }



    public void setBounds(){
        int topx,topy,bttmx,bttmy;
        topx = screenratio[0]/3;
        topy = (screenratio[1]-getpixelheight())/2;
        bttmx = screenratio[0];
        bttmy = getpixelheight()+topy;
        ROCKET1MIN [0] =topx+(int)(238*conversionfactor); ROCKET1MIN [1] =topy+(int)( 8*conversionfactor);
        ROCKET1MAX [0] =topx+(int)( 345*conversionfactor); ROCKET1MAX [1] =topy+(int)( 75*conversionfactor);
        ROCKET2MIN [0] =topx+(int)( 237*conversionfactor); ROCKET2MIN [1] =topy+(int)( 271*conversionfactor);
        ROCKET2MAX [0] =topx+(int)( 345*conversionfactor); ROCKET2MAX [1] =topy+(int)( 348*conversionfactor);
        CARGO1MIN [0] =topx+(int)( 225*conversionfactor);CARGO1MIN [1] =topy+(int)( 145*conversionfactor);
        CARGO1MAX [0] =topx+(int)( 260*conversionfactor);CARGO1MAX [1] =topy+(int)( 180*conversionfactor);
        CARGO2MIN [0] =topx+(int)( 280*conversionfactor);CARGO2MIN [1] =topy+(int)( 126*conversionfactor);
        CARGO2MAX [0] =topx+(int)( 340*conversionfactor);CARGO2MAX [1] =topy+(int)( 160*conversionfactor);
        CARGO3MIN [0] =topx+(int)( 360*conversionfactor);CARGO3MIN [1] =topy+(int)( 127*conversionfactor);
        CARGO3MAX [0] =topx+(int)( 430*conversionfactor);CARGO3MAX [1] =topy+(int)( 160*conversionfactor);
        CARGO4MIN [0] =topx+(int)( 467*conversionfactor);CARGO4MIN [1] =topy+(int)( 128*conversionfactor);
        CARGO4MAX [0] =topx+(int)( 533*conversionfactor);CARGO4MAX [1] =topy+(int)( 163*conversionfactor);
        CARGO5MIN [0] =topx+(int)( 225*conversionfactor);CARGO5MIN [1] =topy+(int)( 200*conversionfactor);
        CARGO5MAX [0] =topx+(int)( 260*conversionfactor);CARGO5MAX [1] =topy+(int)( 225*conversionfactor);
        CARGO6MIN [0] =topx+(int)( 280*conversionfactor);CARGO6MIN [1] =topy+(int)( 210*conversionfactor);
        CARGO6MAX [0] =topx+(int)( 337*conversionfactor);CARGO6MAX [1] =topy+(int)( 244*conversionfactor);
        CARGO7MIN [0] =topx+(int)( 365*conversionfactor);CARGO7MIN [1] =topy+(int)( 210*conversionfactor);
        CARGO7MAX [0] =topx+(int)( 428*conversionfactor);CARGO7MAX [1] =topy+(int)( 244*conversionfactor);
        CARGO8MIN [0] =topx+(int)( 471*conversionfactor);CARGO8MIN [1] =topy+(int)( 210*conversionfactor);
        CARGO8MAX [0] =topx+ (int)( 530*conversionfactor);CARGO8MAX [1] =(int)( 245*conversionfactor);
        HAB1MIN [0] =topx+(int)( 8*conversionfactor);   HAB1MIN [1] =topy+(int)(   58*conversionfactor);
        HAB1MAX [0] =topx+(int)( 110*conversionfactor); HAB1MAX [1] =topy+(int)(    118*conversionfactor);
        HAB2MAX [0] =topx+(int)( 110*conversionfactor); HAB2MAX [1] =topy+(int)(    238*conversionfactor);
        HAB3MIN [0] =topx+(int)( 8*conversionfactor);   HAB3MIN [1] =topy+(int)(   242*conversionfactor);
        HAB3MAX [0] =topx+(int)( 110*conversionfactor); HAB3MAX [1] =topy+(int)(    300*conversionfactor);

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

    }
}