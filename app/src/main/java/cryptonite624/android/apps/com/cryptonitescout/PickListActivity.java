package cryptonite624.android.apps.com.cryptonitescout;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cpjd.models.teams.Team;

import java.util.ArrayList;

import cryptonite624.android.apps.com.cryptonitescout.Models.RankingData;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class PickListActivity extends AppCompatActivity {

    TableView<RankingData> pickListTableView;
    private static final String[] TABLE_HEADERS = { "This", "is", "a", "test" };

    private ArrayList<RankingData> rankings;

    public static String usercode = "cryptonite";
    public static String admincode = "impulse624";

    public int checkpassword (String s){
        if(s.equals(""))
            return 0 ;
        if(s.equals(usercode))
            return 1;
        if(s.equals(admincode))
            return 2;
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pick_list);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Verification Code");
        alert.setMessage("Enter in code");
// Create TextView
        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                switch (checkpassword(input.getText().toString())){
                    case 0:

                }

                // Do something with value!
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });
        alert.show();

        rankings = new ArrayList<RankingData>();

        pickListTableView = (TableView<RankingData>) findViewById(R.id.picklisttable);

        pickListTableView = (TableView<RankingData>) findViewById(R.id.tableView);
        RankingDataAdapter adapter = new RankingDataAdapter(this, rankings);
        pickListTableView.setDataAdapter(adapter);
        pickListTableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, TABLE_HEADERS));





    }
}
