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

import org.w3c.dom.Text;

import java.util.Arrays;

import cryptonite624.android.apps.com.cryptonitescout.Fragments.AutonFragment;
import cryptonite624.android.apps.com.cryptonitescout.Fragments.EndgameFragment;
import cryptonite624.android.apps.com.cryptonitescout.Fragments.InputFragment;
import cryptonite624.android.apps.com.cryptonitescout.Fragments.TeleopFragment;
import cryptonite624.android.apps.com.cryptonitescout.RocketFragment;
import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;
import cryptonite624.android.apps.com.cryptonitescout.Models.AutonEntry;
import cryptonite624.android.apps.com.cryptonitescout.Models.EndgameEntry;
import cryptonite624.android.apps.com.cryptonitescout.Models.PregameEntry;
import cryptonite624.android.apps.com.cryptonitescout.Models.RobotAction;
import cryptonite624.android.apps.com.cryptonitescout.Models.TeleopEntry;

public class MapView extends AppCompatActivity implements View.OnTouchListener, InputFragment.OnInputReadListener, EndgameFragment.OnEndgameReadListener, cryptonite624.android.apps.com.cryptonitescout.PregameFragment.OnPregameReadListener,AutonFragment.OnAutonReadListener,TeleopFragment.OnTeleopReadListener,RocketFragment.OnrocketReadListener{


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

    public static int[] ROCKET1MIN = {238, 8};
    public static int[] ROCKET1MAX = {345, 75};
    public static int[] ROCKET2MIN = {237, 271};
    public static int[] ROCKET2MAX = {345, 348};
    public static int[] CARGO1MIN = {225, 145};
    public static int[] CARGO1MAX = {260, 180};
    public static int[] CARGO2MIN = {280, 126};
    public static int[] CARGO2MAX = {340, 160};
    public static int[] CARGO3MIN = {360, 127};
    public static int[] CARGO3MAX = {430, 160};
    public static int[] CARGO4MIN = {467, 128};
    public static int[] CARGO4MAX = {533, 163};
    public static int[] CARGO5MIN = {225, 200};
    public static int[] CARGO5MAX = {260, 225};
    public static int[] CARGO6MIN = {280, 210};
    public static int[] CARGO6MAX = {337, 244};
    public static int[] CARGO7MIN = {365, 210};
    public static int[] CARGO7MAX = {428, 244};
    public static int[] CARGO8MIN = {471, 210};
    public static int[] CARGO8MAX = {530, 245};
    public static int[] HAB1MIN = {8, 58};
    public static int[] HAB1MAX = {110, 118};
    public static int[] HAB2MIN = {8, 120};
    public static int[] HAB2MAX = {110, 238};
    public static int[] HAB3MIN = {8, 242};
    public static int[] HAB3MAX = {110, 300};


    public static int[] imageratio = {620,357};

    public static int[] screenratio = new int[2];
    public static double conversionfactor;

    public static FragmentManager fragmentManager;
    public ActionMap actionMap = new ActionMap();
    Drawing drawing;
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

    CustomDrawableView mCustomDrawableView;

    private Canvas mCanvas;
    private Paint mPaint = new Paint();
    private Paint mPaintText = new Paint(Paint.UNDERLINE_TEXT_FLAG);
    private Bitmap mBitmap;
    private ImageView mImageView;
    private int mColorBackground;
    private int mColorRectangle;
    private int mColorAccent;

    private int mOffset = OFFSET;

    public boolean sandstorm = true;

    CustomImageView customView;

    private Rect mRect = new Rect();
    private Rect mBounds = new Rect();


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

        //mCustomDrawableView = new CustomDrawableView(this);

        //setContentView(mCustomDrawableView);

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

        setScreenratio();
        setBounds();
        //mImageView = (ImageView) findViewById(R.id.mapview);
    }

    @SuppressLint("WrongCall")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = (int)event.getX();
        y = (int)event.getY();

        //drawing = new Drawing(this);

        if(actionReady){
            actionReady = false;
            actionMap.actions.add(new RobotAction(tempX, tempY, getCode(x, y), matchStatus));
        }

        /*if(actionReady == false){
            customView.setClickLocation(x, y);
        }*/

        if(getCode(x, y) == 0){
            actionReady = true;
            tempX = x;
            tempY = y;
        }
        else if(getCode(x,y)==1||getCode(x,y)==2){
            openRocket();
        }



        //xDisplay.setText("" + x);
        //yDisplay.setText("" + y);
        //CodeDisplay.setText("" + getCode(x, y));
        return false;
    }

    //0 = not on switch, 1 = red switch 1, 2 = blue switch1, 3 = blue scale, 4 = red scale, 5 = red switch2, 6 = blue switch, 7 = invalid click
    public int getCode(int x, int y){
        if(x > ROCKET1MIN[0] && x < ROCKET1MAX[0] && y > ROCKET1MIN[1] && y < ROCKET1MAX[1]){
            return 1;
        }
        if(x > ROCKET2MIN[0] && x < ROCKET2MAX[0] && y > ROCKET2MIN[1] && y < ROCKET2MAX[1]){
            return 2;
        }
        else if(x > CARGO1MIN[0] && x < CARGO1MAX[0] && y > CARGO1MIN[1] && y < CARGO1MAX[1]){
            return 3;
        }
        else if(x > CARGO2MIN[0] && x < CARGO2MAX[0] && y > CARGO2MIN[1] && y < CARGO2MAX[1]){
            return 4;
        }
        else if(x > CARGO3MIN[0] && x < CARGO3MAX[0] && y > CARGO3MIN[1] && y < CARGO3MAX[1]){
            return 5;
        }
        else if(x > CARGO4MIN[0] && x < CARGO4MAX[0] && y > CARGO4MIN[1] && y < CARGO4MAX[1]){
            return 6;
        }
        else if(x > CARGO5MIN[0] && x < CARGO5MAX[0] && y > CARGO5MIN[1] && y < CARGO5MAX[1]){
            return 7;
        }
        else if(x > CARGO6MIN[0] && x < CARGO6MAX[0] && y > CARGO6MIN[1] && y < CARGO6MAX[1]){
            return 8;
        }
        else if(x > CARGO7MIN[0] && x < CARGO7MAX[0] && y > CARGO7MIN[1] && y < CARGO7MAX[1]){
            return 9;
        }
        else if(x > CARGO8MIN[0] && x < CARGO8MAX[0] && y > CARGO8MIN[1] && y < CARGO8MAX[1]){
            return 10;
        }
        else if(y > 330)
            return 0;
        else
            return 11;
    }

    public void drawSomething(View view){
        int vWidth = view.getWidth();
        int vHeight = view.getHeight();
        int halfWidth = vWidth / 2;
        int halfHeight = vHeight / 2;

        mImageView.invalidate();

       // BitmapDrawable drawable = (BitmapDrawable) mImageView.getDrawable();

       // mImageView.invalidateDrawable(drawable);

        //mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo);

        mBitmap = Bitmap.createBitmap(
                vWidth, vHeight, Bitmap.Config.ARGB_8888);

        //mBitmap = drawable.getBitmap();

        Bitmap field = BitmapFactory.decodeResource(getResources(), R.drawable.powerupfield);

        //mImageView.setImageBitmap(field);

        mCanvas = new Canvas(mBitmap);

        mCanvas.drawColor(mColorBackground);
        mPaint.setColor(mColorRectangle);

        mRect.set(
                mOffset, mOffset, vWidth - mOffset, vHeight - mOffset);

        mCanvas.drawRect(mRect, mPaint);

        mCanvas.drawBitmap(field, 100, 100, mPaint);

        mCanvas.drawText("If you see this then it's probably working so far", 100, 100, mPaintText);
        mCanvas.drawRect(mRect, mPaint);

        //Canvas.drawCircle(
         //       halfWidth, halfHeight, halfWidth / 3, mPaint);

        //Drawable d = getResources().getDrawable(R.drawable.powerupfield, null);

        //Canvas canvas = new Canvas();
        //d.setBounds(100, 100, 100, 100);
        //d.draw(canvas);
        //canvas.drawCircle(100, 100, 50, mPaint);

        view.invalidate();
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
    public void LoadAutonData(AutonEntry a) {

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
    public void LoadPregameData(PregameEntry p) {
        p.setTeamnum(0);
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
    public void LoadTeleopData(TeleopEntry t) {

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

    @Override
    public void LoadEndgameData(EndgameEntry e) {

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

    @Override
    public void hatch(Boolean b) {

    }


    public class Drawing extends View{

        //Bitmap circle;

        public Drawing(Context context) {
            super(context);
            //circle = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        }

        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            /*Paint paint = new Paint();
            paint.setColor(Color.GREEN);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(500, 500, 50, paint);*/
        }

    }




}


