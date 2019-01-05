package cryptonite624.android.apps.com.cryptonitescout;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;

public class MapDisplayActivity extends AppCompatActivity {


    Canvas canvas;
    Paint paint = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_display);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.powerupfield);

        Canvas canvas = new Canvas(bitmap.copy(Bitmap.Config.ARGB_8888, true));
    }

    public void drawCoords(ActionMap map){
        for(int i = 0; i < map.actions.size(); i++){
            canvas.drawCircle(map.actions.get(i).x, map.actions.get(i).y, 10, paint);
        }
    }
}
