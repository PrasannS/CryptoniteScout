package cryptonite624.android.apps.com.cryptonitescout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.jar.Attributes;

import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;
import cryptonite624.android.apps.com.cryptonitescout.Models.RobotAction;
import cryptonite624.android.apps.com.cryptonitescout.R;

public class CustomImageView extends View {

    Canvas canvas;
    Paint paint;
    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tempfield);
    ActionMap actionMap = null;
    int x, y;

    public CustomImageView(Context context){
        super(context);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //canvas = new Canvas();
    }

    public CustomImageView(Context context, AttributeSet attrs, ActionMap map) {
        super(context, attrs);
        actionMap = map;
        //canvas = new Canvas();
    }

    /*public CustomImageView(Context context, int xCoor, int yCoor){
        super(context);
        x = xCoor;
        y = yCoor;
    }*/

    public void setActionMap(ActionMap map){
        actionMap = map;
        this.invalidate();
    }

    public void setClickLocation(int xCoor, int yCoor){
        x = xCoor;
        y = yCoor;
        this.invalidate();
    }

    public void drawMap(){

    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        if(actionMap == null) {
            canvas.drawCircle(x, y, 10, paint);
        }
        else
            for (RobotAction r : actionMap.actions) {
                canvas.drawCircle(r.x, r.y, 10, paint);
            }
        }

}



