package cryptonite624.android.apps.com.cryptonitescout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {

    Button pitnotes;
    Button matches;
    Button ranking;
    Button newentry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Button newentry = (Button) findViewById(R.id.newentrybutton);
        newentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, MapView.class));
            }
        });

        Button ranking = (Button) findViewById(R.id.rankingbutton);
        ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, rankings.class));
            }
        });
    }
}
