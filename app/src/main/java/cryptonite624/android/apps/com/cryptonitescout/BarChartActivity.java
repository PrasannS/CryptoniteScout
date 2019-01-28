package cryptonite624.android.apps.com.cryptonitescout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import android.support.v7.app.AppCompatActivity;

public class BarChartActivity extends AppCompatActivity {

    BarChart barChart;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_match);

        barChart = (BarChart) findViewById(R.id.idBarChart);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(25.3f,0));
        barEntries.add(new BarEntry(10.6f,0));
        barEntries.add(new BarEntry(66.76f,0));
        barEntries.add(new BarEntry(44.32f,0));
        barEntries.add(new BarEntry(46.01f,0));
        barEntries.add(new BarEntry(16.89f,0));

        BarDataSet barDataSet = new BarDataSet(barEntries,"Average Hatches");

        ArrayList<String> theTeams = new ArrayList<>();
        theTeams.add("TeamX");
        theTeams.add("TeamA");
        theTeams.add("TeamB");
        theTeams.add("TeamC");
        theTeams.add("TeamD");
        theTeams.add("TeamE");

        BarData barData = new BarData(theTeams,barDataSet);
        barChart.setData(barData);

        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);











    }
}
