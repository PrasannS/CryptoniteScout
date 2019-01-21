package cryptonite624.android.apps.com.cryptonitescout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class Pitnote_page2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    public Switch LevTwoStart;
    public Button Previous;
    //pgLanguage = Programming Language
    public Spinner pgLanguage;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pitnote_page2);


        pgLanguage = findViewById(R.id.Programming_Language);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ProgrammingLanguages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pgLanguage.setAdapter(adapter);
        pgLanguage.setOnItemClickListener(this);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String language = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), language, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //Nothing goes here
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Nothing goes here
    }
}
