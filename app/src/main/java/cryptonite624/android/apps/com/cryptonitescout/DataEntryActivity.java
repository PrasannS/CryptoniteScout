package cryptonite624.android.apps.com.cryptonitescout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cryptonite624.android.apps.com.cryptonitescout.Models.DataEntry;

public class DataEntryActivity extends AppCompatActivity {

    DataEntry dataEntry = new DataEntry(getteam());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);
    }

    public String getteam(){
        //this is just placeholder code I will replace it with actual data loading stuff when I learn how
        return "624";
    }
}
