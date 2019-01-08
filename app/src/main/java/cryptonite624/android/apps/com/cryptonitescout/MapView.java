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
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;
import cryptonite624.android.apps.com.cryptonitescout.Models.RobotAction;

public class MapView extends AppCompatActivity implements View.OnTouchListener {

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

    public static int[] ROCKET1MIN = {1050, 350};
    public static int[] ROCKET1MAX = {1260, 420};
    public static int[] ROCKET2MIN = {1050, 815};
    public static int[] ROCKET2MAX = {1270, 960};
    public static int[] CARGO1MIN = {1030, 570};
    public static int[] CARGO1MAX = {1100, 620};
    public static int[] CARGO2MIN = {1130, 520};
    public static int[] CARGO2MAX = {1260, 590};
    public static int[] CARGO3MIN = {1030, 570};
    public static int[] CARGO3MAX = {1100, 620};
    public static int[] CARGO4MIN = {1500, 540};
    public static int[] CARGO4MAX = {1630, 600};
    public static int[] CARGO5MIN = {1030, 670};
    public static int[] CARGO5MAX = {1100, 720};
    public static int[] CARGO6MIN = {1145, 685};
    public static int[] CARGO6MAX = {1250, 770};
    public static int[] CARGO7MIN = {1300, 570};
    public static int[] CARGO7MAX = {1430, 760};
    public static int[] CARGO8MIN = {1500, 700};
    public static int[] CARGO8MAX = {1620, 760};








    Drawing drawing;
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

        //mCustomDrawableView = new CustomDrawableView(this);

        //setContentView(mCustomDrawableView);

        xDisplay = (TextView)findViewById(R.id.XDisplay);
        yDisplay = (TextView)findViewById(R.id.YDisplay);
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

        updateDisplay();



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


    public void updateDisplay(){
        //statusDisplay.setText(statusStrings[matchStatus]);
        //totalDisplay.setText("" + actionMap.numCubes(ALLCODES, ALLSTATUS));
        xDisplay.setText("" + x);
        yDisplay.setText("" + y);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
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


