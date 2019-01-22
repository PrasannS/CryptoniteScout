package cryptonite624.android.apps.com.cryptonitescout;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;

import cryptonite624.android.apps.com.cryptonitescout.Fragments.AutonFragment;
import cryptonite624.android.apps.com.cryptonitescout.Fragments.EndgameFragment;
import cryptonite624.android.apps.com.cryptonitescout.Fragments.InputFragment;
import cryptonite624.android.apps.com.cryptonitescout.Fragments.TeleopFragment;
import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;
import cryptonite624.android.apps.com.cryptonitescout.Models.RobotAction;
import java.util.Date;

public class MapView extends AppCompatActivity implements EmptyFragment.OnFragmentInteractionListener,View.OnTouchListener, InputFragment.OnInputReadListener, EndgameFragment.OnEndgameReadListener, cryptonite624.android.apps.com.cryptonitescout.PregameFragment.OnPregameReadListener,AutonFragment.OnAutonReadListener,TeleopFragment.OnTeleopReadListener,RocketFragment.OnrocketReadListener{


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
    public static int topx,topy,bttmx,bttmy;

    CustomDrawableView mCustomDrawableView;

    private Canvas mCanvas;
    private Paint mPaint = new Paint();
    private Paint mPaintText = new Paint(Paint.UNDERLINE_TEXT_FLAG);
    private Bitmap mBitmap;
    private ImageView mImageView;
    private int mColorBackground;
    private int mColorRectangle;
    private int mColorAccent;

    public long starttime;
    public long endtime;
    public double cycletime = -1;
    public boolean stopwatch= false;
    public int totalcycles = 0;

    private Button cancel;


    private Button imageswitch;
    private int mOffset = OFFSET;

    public boolean sandstorm = true;
    private RelativeLayout mapview;

    CustomImageView customView;

    private Rect mRect = new Rect();
    private Rect mBounds = new Rect();

    TextView hatchDisplay;
    TextView cargoDisplay;
    private Button b;

    public RobotAction currentAction = new RobotAction();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //RelativeLayout layout = (RelativeLayout)findViewById(R.id.mapview);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        //customView = (CustomImageView) findViewById(R.id.drawview);

        setContentView(R.layout.activity_map_view);
        //setContentView(view);

        fragmentManager = getSupportFragmentManager();
        if(findViewById(R.id.infoframe)!=null){
            if(savedInstanceState!=null){
                return;
            }
            cryptonite624.android.apps.com.cryptonitescout.PregameFragment pregameFragment= new cryptonite624.android.apps.com.cryptonitescout.PregameFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.infoframe,pregameFragment,null);
            fragmentTransaction.commit();
        }
        if(findViewById(R.id.inputcontainer)!=null){
            EmptyFragment emptyFragment= new EmptyFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.inputcontainer,emptyFragment,null);
            fragmentTransaction.commit();
        }

        switchbounds();

        mapview = (RelativeLayout)findViewById(R.id.mapview);
        imageswitch = (Button) findViewById(R.id.mapswitch);
        cancel = (Button) findViewById(R.id.cancel);

        stopwatch=false;


        //mCustomDrawableView = new CustomDrawableView(this);

        //setContentView(mCustomDrawableView);

        imageswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(red){
                    if(left){
                        mapview.setBackgroundResource(R.drawable.redright);
                    }else{
                        mapview.setBackgroundResource(R.drawable.red);
                    }
                }
                else{
                    if(left){
                        mapview.setBackgroundResource(R.drawable.blueright);
                    }
                    else {
                        mapview.setBackgroundResource(R.drawable.blue);
                    }
                }
                switchbounds();

            }
        });


        cargoDisplay = (TextView)findViewById(R.id.cargodisplay);
        hatchDisplay = (TextView)findViewById(R.id.hatchdisplay);

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

        mColorBackground = ResourcesCompat.getColor(getResources(),
                R.color.colorBackground, null);
        mColorRectangle = ResourcesCompat.getColor(getResources(),
                R.color.colorRectangle, null);
        mColorAccent = ResourcesCompat.getColor(getResources(),
                R.color.colorAccent, null);
        mPaint.setColor(mColorBackground);


        //mImageView = (ImageView) findViewById(R.id.mapview);
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
        hatchDisplay.setText(""+x);
        cargoDisplay.setText(""+y);

        //drawing = new Drawing(this);

        if(actionReady){
            actionReady = false;
            actionMap.actions.add(new RobotAction(getCode(x, y), matchStatus));
        }
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

            currentAction.actionCode = getCode(x, y);
            if(getCode(x,y).equals("A")||getCode(x,y).equals("B")){
                openRocket();
            }
            else if(findViewById(R.id.inputcontainer)!=null){
                InputFragment inputFragment= new InputFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.inputcontainer,inputFragment,null);
                fragmentTransaction.commit();
            }
        }






        //xDisplay.setText("" + x);
        //yDisplay.setText("" + y);
        //CodeDisplay.setText("" + getCode(x, y));
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
        }
        else if(x > topx)
            return "Z";
        else
            return "0";

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void OnAutonRead(String message) {
        switch(message){
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
                break;

            default:
        }
    }



    @Override
    public void OnPregameRead(String message) {
        switch (message){
            case "toAuton":
                if(findViewById(R.id.infoframe)!=null){
                    AutonFragment autonFragment= new AutonFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.infoframe,autonFragment,null);
                    fragmentTransaction.commit();
                }
                break;

            default:

        }
    }



    @Override
    public void OnTeleopRead(String message) {
        switch (message){
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
                break;

            default:

        }
    }


    @Override
    public void OnEndgameRead(String message) {
        switch(message){
            case "toTeleop":
                if(findViewById(R.id.infoframe)!=null){
                    TeleopFragment teleopFragment= new TeleopFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.infoframe,teleopFragment,null);
                    fragmentTransaction.commit();
                }
                break;

            default:
        }

    }

    public void openRocket(){
        if(findViewById(R.id.infoframe)!=null){
            RocketFragment rocketFragment= new RocketFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.infoframe,rocketFragment,null);
            fragmentTransaction.commit();
        }


    }

    @Override
    public void OnrocketRead(String message) {
        switch (message){
            case "return":
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
                break;
            case "1":
                currentAction.actionCode = currentAction.actionCode+1;
                break;
            case "2":
                currentAction.actionCode = currentAction.actionCode+2;break;

            case "3":
                currentAction.actionCode = currentAction.actionCode+3;break;
            case "4":
                currentAction.actionCode = currentAction.actionCode+4;break;
            case "5":
                currentAction.actionCode = currentAction.actionCode+5;break;
            case "6":
                currentAction.actionCode = currentAction.actionCode+6;break;




        }

        if(findViewById(R.id.inputcontainer)!=null){
            InputFragment inputFragment= new InputFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.inputcontainer,inputFragment,null);
            fragmentTransaction.commit();
        }


        }


    public int getpixelheight(){
        return (int)((int)((double)imageratio[1]*((double)2/3)*screenratio[0])/(double)imageratio[0]);
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
        actionMap.actions.add(currentAction);
        currentAction = new RobotAction();
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
        cancel.setVisibility(View.GONE);
        updateScreen();
    }

    public void updateScreen(){
        cargoDisplay.setText(""+actionMap.totalhatches(false));
        hatchDisplay.setText(""+actionMap.totalhatches(true));
        cargoDisplay.setText(cycletime+"");
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}


