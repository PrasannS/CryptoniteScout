package cryptonite624.android.apps.com.cryptonitescout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CommentActivity extends AppCompatActivity {
    private Button submit;
    private EditText commentget;
    private String comment;
    private EditText teamnameget;
    private String teamname;
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
    }
    public void goToDashboard()
    {
        Intent intent1 = new Intent(this, MapView.class);
        startActivity(intent1);
    }
}
