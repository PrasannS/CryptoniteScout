package cryptonite624.android.apps.com.cryptonitescout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;

public class PaintView extends View {
    public static final float TOUCH_TOLERANCE = 10;

    private Bitmap bitmap;
    private Bitmap mutableBitmap;
    private Canvas bitmapCanvas;
    private Paint paintScreen;
    private Paint paintLine;
    private HashMap<Integer, Path> pathMap;
    private HashMap<Integer, Point> previousPointMap;



    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    void init(){
        paintScreen = new Paint();

        paintLine = new Paint();
        paintLine.setAntiAlias(true);
        paintLine.setColor(Color.GREEN);
        paintLine.setStrokeWidth(7);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeCap(Paint.Cap.ROUND);

        pathMap = new HashMap<>();
        previousPointMap = new HashMap<>();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.redright);
        mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        //mutableBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(mutableBitmap);
        //mutableBitmap.eraseColor(Color.WHITE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getActionMasked();
        int actionIndex = event.getActionIndex();

        if(action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_UP){
            touchStarted(event.getX(actionIndex), event.getY(actionIndex), event.getPointerId(actionIndex));
        }
        else if(action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP){
            touchEnded(event.getPointerId(actionIndex));
        }
        else{
            touchMoved(event);
        }

        invalidate();

        return true;
    }

    public void clear(){
        pathMap.clear();
        previousPointMap.clear();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.redright);
        mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        bitmapCanvas = new Canvas(mutableBitmap);
        invalidate();
    }

    private void touchMoved(MotionEvent event) {

        for(int i = 0; i < event.getPointerCount(); i++){
            int pointerId = event.getPointerId(i);
            int pointerIndex = event.findPointerIndex(pointerId);

            if(pathMap.containsKey(pointerId)){
                float newX = event.getX(pointerIndex);
                float newY = event.getY(pointerIndex);

                Path path = pathMap.get(pointerId);
                Point point = previousPointMap.get(pointerId);

                float deltaX = Math.abs(newX - point.x);
                float deltaY = Math.abs(newY - point.y);

                if(deltaX >= TOUCH_TOLERANCE || deltaY >= TOUCH_TOLERANCE){
                    path.quadTo(point.x, point.y, (newX + point.x) /2, (newY + point.y) /2);

                    point.x = (int) newX;
                    point.y = (int) newY;
                }
            }
        }
    }

    private void touchEnded(int pointerId) {
    }

    private void touchStarted(float x, float y, int pointerId) {
        Path path;
        Point point;

        if(pathMap.containsKey(pointerId)){
            path = pathMap.get(pointerId);
            point = previousPointMap.get(pointerId);
        }
        else{
            path = new Path();
            pathMap.put(pointerId, path);
            point = new Point();
            previousPointMap.put(pointerId, point);
        }

        path.moveTo(x, y);
        point.x = (int) x;
        point.y = (int) y;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mutableBitmap, 0, 0, paintScreen);

        for(Integer key : pathMap.keySet()){
            canvas.drawPath(pathMap.get(key), paintLine);
        }
    }
}
