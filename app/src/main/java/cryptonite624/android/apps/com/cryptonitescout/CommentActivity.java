package cryptonite624.android.apps.com.cryptonitescout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CommentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener{
    private Button submit;
    private EditText commentget;
    private String comment;
    private EditText teamnameget;
    private String teamname;

    private Spinner Rating;
    private String RatingLevel;
    private String [] Levels = {"1","2","3","4","5","6"};

    private Spinner Driver;
    private String DriverRatings;
    private String [] DriverRatingLevels = {"1","2","3","4","5","6"};

    private Spinner Defense;
    private String DefenseRatings;
    private String [] DefenseRatingLevels = {"1","2","3","4","5","6"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);


        submit= (Button)findViewById(R.id.Submitcomments);
        commentget= (EditText)findViewById(R.id.commentsave);
        comment = commentget.getText().toString();
        teamnameget = (EditText) findViewById(R.id.TeamName);
        teamname = teamnameget.getText().toString();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToDashboard();
            }
        });

        Rating = findViewById(R.id.Rating);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,Levels);
        Rating.setAdapter(adapter);

        Driver = findViewById(R.id.DriverRating);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,DriverRatingLevels);
        Driver.setAdapter(adapter1);

        Defense = findViewById(R.id.DefenseRating);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,DefenseRatingLevels);
        Defense.setAdapter(adapter2);


    }
    public void goToDashboard()
    {
        Intent intent1 = new Intent(this, MapView.class);
        startActivity(intent1);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //setting the dropdown elements and displaying the texts for the programming languages
        RatingLevel = Levels[position];
        Toast.makeText(parent.getContext(), RatingLevel, Toast.LENGTH_SHORT).show();

        //setting the dropdown elements and displaying the texts for the hatch intakes
        DriverRatings = DriverRatingLevels[position];
        Toast.makeText(parent.getContext(), DriverRatings, Toast.LENGTH_SHORT).show();

        //setting the dropdown elements and displaying the texts for the cargo intakes
        DefenseRatings = DefenseRatingLevels[position];
        Toast.makeText(parent.getContext(), DefenseRatings, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
