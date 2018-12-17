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
import android.widget.TextView;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);

        xDisplay = (TextView)findViewById(R.id.XDisplay);
        yDisplay = (TextView)findViewById(R.id.YDisplay);
        CodeDisplay = (TextView)findViewById(R.id.CodeDisplay);
    }

    @SuppressLint("WrongCall")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = (int)event.getX();
        y = (int)event.getY();


        /*Canvas canvas = new Canvas();
        Drawing drawing = new Drawing(this);
        //drawing.onDraw(canvas);*/

        xDisplay.setText("" + x);
        yDisplay.setText("" + y);
        CodeDisplay.setText("" + getCode(x, y));
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
            canvas.drawColor(Color.WHITE);
            //canvas.drawBitmap(circle, x, y, null);*/
            canvas.drawText("If you see this then it's working", x, y, null);
            //canvas.drawPath(path, brush);
        }
    }
}


