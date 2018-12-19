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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;
import cryptonite624.android.apps.com.cryptonitescout.Models.RobotAction;

public class MapView extends AppCompatActivity implements View.OnTouchListener {

    public int x, y;
    TextView xDisplay, yDisplay, CodeDisplay;
    public static int[] REDSWITCH1MIN = {530, 530};
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
    public static int[] BLUESWITCH2MAX = {1240, 825};
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);

        //xDisplay = (TextView)findViewById(R.id.XDisplay);
        //yDisplay = (TextView)findViewById(R.id.YDisplay);
        //CodeDisplay = (TextView)findViewById(R.id.CodeDisplay);
        statusDisplay = (TextView)findViewById(R.id.StatusDisplay);
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
        });

        //drawing = (Drawing) findViewById(R.id.)

        //setContentView(drawing);
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
        if(x > REDSWITCH1MIN[0] && x < REDSWITCH1MAX[0] && y > REDSWITCH1MIN[1] && y < REDSWITCH1MAX[1]){
            return 1;
        }
        else if(x > BLUESWITCH1MIN[0] && x < BLUESWITCH1MAX[0] && y > BLUESWITCH1MIN[1] && y < BLUESWITCH1MAX[1]){
            return 2;
        }
        else if(x > BLUESCALEMIN[0] && x < BLUESCALEMAX[0] && y > BLUESCALEMIN[1] && y < BLUESCALEMAX[1]){
            return 3;
        }
        else if(x > REDSCALEMIN[0] && x < REDSCALEMAX[0] && y > REDSCALEMIN[1] && y < REDSCALEMAX[1]){
            return 4;
        }
        else if(x > REDSWITCH2MIN[0] && x < REDSWITCH2MAX[0] && y > REDSWITCH2MIN[1] && y < REDSWITCH2MAX[1]){
            return 5;
        }
        else if(x > BLUESWITCH2MIN[0] && x < BLUESWITCH2MAX[0] && y > BLUESWITCH2MIN[1] && y < BLUESWITCH2MAX[1]){
            return 6;
        }
        else if(y > 330)
            return 0;
        else
            return 7;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    public class Drawing extends View{

        Bitmap circle;

        public Drawing(Context context) {
            super(context);
            circle = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        }

        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setColor(Color.GREEN);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(0, canvas.getWidth()/2, 10, paint);
        }
    }

    public void updateDisplay(){
        statusDisplay.setText(statusStrings[matchStatus]);
        totalDisplay.setText("" + actionMap.numCubes(ALLCODES, ALLSTATUS));
    }
}


